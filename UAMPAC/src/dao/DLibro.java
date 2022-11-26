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
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    

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

    public ArrayList<Libro> listarLibro() {
        ArrayList<Libro> lista = new ArrayList<>();
        try {
            this.obtRegistros("Select * from [CATALOGO].[VW_Libro]");
            while (rs.next()) {
              ArrayList<Autor> autores = listarAutor(rs.getString("ISBN"));
                lista.add(new Libro(rs.getString("ISBN"),
                        rs.getString("titulo"),
                        rs.getString("MFN"),
                        new Clasificacion(rs.getString("codigo_clasificacion"),rs.getString("nombre_clasificacion")),
                        new Editorial (rs.getString("codigo_editorial"),rs.getString("nombre_editorial")),
                        autores
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Libro " + ex.getMessage());
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
        return lista;
    }
    
    public ArrayList<Autor> listarAutor(String isbn){
        ArrayList<Autor> lista = new ArrayList<>();
        try {
            this.obtRegistros("Select * from [CATALOGO].[VW_Libro]"
                    + " WHERE ISBN LIKE '"+isbn+"'");
            while (rs.next()) {
                lista.add(new Autor(
                        rs.getString("codigo_autor"),
                        rs.getString("nombre")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Autores " + ex.getMessage());
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

    public boolean editarLibro(Libro a) {
        boolean resp = false;
        this.obtRegistros("SELECT * FROM[CATALOGO].[Libro]");
        DAutorxLibro daxl= new DAutorxLibro();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("ISBN").equals(a.getIsbn())) {
                    rs.updateString("titulo",a.getTitulo_libro());
                    rs.updateString("MFN",a.getMfn());
                    rs.updateString("codigo_editorial", a.getEditorial().getCod_editorial());
                    rs.updateString("codigo_clasificacion", a.getClasificacion().getCod_clasificacion());
                    rs.updateRow();
                    for(Autor aut:a.getAutores()){
                        daxl.editarAutorxLibro(a.getIsbn(), aut.getCodigo_autor());
                    }
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
