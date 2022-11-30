/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Libro;
import complementos.Conexion;
import entidades.Autor;
import entidades.Clasificacion;
import entidades.Editorial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cfco5
 */
public class DLibro {

    private Connection conn = null;
    private Connection conn2 = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null; 
    
    private PreparedStatement ps2 = null;
    private ResultSet rs2 = null; 

    public void obtRegistros(String x) {
        try {
            conn = Conexion.obtConexion();
            String tSQL = x;
            ps = conn.prepareStatement(tSQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al obtener registros: " + ex.getMessage());
        }
    }
    
    public void obtRegistros2(String x) {
        try {
            conn2 = Conexion.obtConexion();
            String tSQL = x;
            ps2 = conn2.prepareStatement(tSQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            rs2 = ps2.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al obtener registros: " + ex.getMessage());
        }
    }
    

    public ArrayList<Libro> listarLibro() {
        ArrayList<Libro> lista = new ArrayList<>();
        try {
            this.obtRegistros2("Select * from [CATALOGO].[VW_LibroCopias]");
            while (rs2.next()) {
              ArrayList<Autor> autores = listarAutor(rs2.getString("ISBN"));
                //System.out.println(autores.get(0).getNombre_autor());
                //System.out.println(autores.get(1).getNombre_autor());
                //System.out.println(autores.get(2).getNombre_autor());
                
                //AQUI ES EL PROBLEMA (YA NO :D)
                
                autores.add(new Autor("0", (rs2.getString("copias"))));
                
                lista.add(new Libro(rs2.getString("ISBN"),
                        rs2.getString("titulo"),
                        rs2.getString("MFN"),
                        new Clasificacion(rs2.getString("codigo_clasificacion"),rs2.getString("nombre_clasificacion")),
                        new Editorial (rs2.getString("codigo_editorial"),rs2.getString("nombre_editorial")),
                        autores
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Libro " + ex.getMessage());
        } finally {
            try {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }

                if (conn2 != null) {
                    Conexion.cerrarConexion(conn2);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return lista;
    }
    
    public ArrayList<Autor> listarAutor(String isbn){
        ArrayList<Autor> lista = new ArrayList<>();
        try {
            this.obtRegistros("Select codigo_autor, nombre_autor from [CATALOGO].[VW_Libro]"
                    + " WHERE ISBN LIKE '"+isbn+"'");
            while (rs.next()) {
                lista.add(new Autor(
                        rs.getString("codigo_autor"),
                        rs.getString("nombre_autor")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Autores " + ex.getMessage());
        } /*finally {
            try {
                
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                } 

                if (conn != null) {
                    Conexion.cerrarConexion(conn);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } 
        } */
        return lista;
    }

    public boolean guardarLibro(Libro a) {
        boolean guardado = false;
        this.obtRegistros("SELECT * FROM[CATALOGO].[Libro]");
        DAutorxLibro daxl= new DAutorxLibro();
        try {
            rs.moveToInsertRow();
            rs.updateString("ISBN", a.getIsbn());
            rs.updateString("titulo", a.getTitulo_libro());
            rs.updateString("MFN", a.getMfn());
            rs.updateString("codigo_editorial", a.getEditorial().getCod_editorial());
            rs.updateString("codigo_clasificacion", a.getClasificacion().getCod_clasificacion());
            rs.insertRow();
            rs.moveToCurrentRow();
            
            for(Autor aut:a.getAutores()){
                daxl.guardarAutorxLibro(a.getIsbn(), aut.getCodigo_autor());
            }
            
            guardado = true;
        } catch (SQLException ex) {
            System.out.println("Error al guardar Libro:" + ex.getMessage());
        } /*finally {
            try {
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.ps != null) {
                    this.ps.close();
                }
                if (this.conn != null) {
                    Conexion.cerrarConexion(this.conn);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }*/
        return guardado;
    }

    public boolean existeLibro(String id) {
        boolean resp = false;
        this.obtRegistros("SELECT * FROM[CATALOGO].[Libro]");
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("ISBN").equals(id)) {
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar Libro: " + ex.getMessage());
        } /*finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                if (conn != null) {
                    Conexion.cerrarConexion(conn);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }*/

        return resp;

    }

    public boolean editarLibro(Libro a) {
        boolean resp = false;
        this.obtRegistros("SELECT * FROM[CATALOGO].[Libro]");
        DAutorxLibro daxl = new DAutorxLibro();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("ISBN").equals(a.getIsbn())) {
                    rs.updateString("titulo",a.getTitulo_libro());
                    rs.updateString("MFN",a.getMfn());
                    rs.updateString("codigo_editorial", a.getEditorial().getCod_editorial());
                    rs.updateString("codigo_clasificacion", a.getClasificacion().getCod_clasificacion());
                    rs.updateRow();
                    
                    daxl.eliminarTodoAutorxLibro(a.getIsbn());
                    
                    
                    for(Autor aut: a.getAutores()) {
                        daxl.guardarAutorxLibro(a.getIsbn(), aut.getCodigo_autor());       
                    }
                    
                    //hay un problema aqui, solo edita 1 autor (quita los otros si tiene mas)
                    //nuevo
                    /*
                    for(Autor aut: a.getAutores()){
                        //original
                        for(Autor aut2: original) {
                            if (aut.getCodigo_autor().equals(aut2.getCodigo_autor())) {
                                System.out.println("hola");
                                daxl.editarAutorxLibro(a.getIsbn(), aut2.getCodigo_autor(), aut.getCodigo_autor());
                            }
                        }
                        //daxl.editarAutorxLibro(a.getIsbn(), aut.getCodigo_autor(), cod_autorNuevo);
                    } */
                    
                    /*
                    for (int i = 0; i < a.getAutores().size(); i++) {
                        //System.out.println(a.getAutores().get(i).getCodigo_autor() + " " + original.get(i).getCodigo_autor());
                        
                        if (a.getAutores().size() < original.size()) {
                            System.out.println("hola else if 2");
                            daxl.eliminarAutorxLibro(a.getIsbn(), original.get(i).getCodigo_autor());
                            
                        }
                        
                        if (i < original.size()) {
                            if ((!a.getAutores().get(i).getCodigo_autor().equals(original.get(i).getCodigo_autor()))) {
                            
                                System.out.println("iteracion " + i);

                                daxl.editarAutorxLibro(a.getIsbn(), original.get(i).getCodigo_autor()
                                , a.getAutores().get(i).getCodigo_autor());
                            }   
                        } else if (i >= original.size()) {
                            System.out.println("hola");
                            daxl.guardarAutorxLibro(a.getIsbn(), a.getAutores().get(i).getCodigo_autor());
                            
                        }  else  {
                            System.out.println("estoy en el else");
                        }
                        
                        
                    } */
                    
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al editar: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                if (conn != null) {
                    Conexion.cerrarConexion(conn);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resp;
    }

    public boolean eliminarLibro(String id) {
        boolean resp = false;
        this.obtRegistros("SELECT * FROM[CATALOGO].[Libro]");
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("ISBN").equals(id)) {
                    rs.deleteRow();
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar Libro" + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }

                if (conn != null) {
                    Conexion.cerrarConexion(conn);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resp;
    }
}
