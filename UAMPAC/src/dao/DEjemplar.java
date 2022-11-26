/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Ejemplar;
import entidades.Ubicacion;
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
public class DEjemplar {

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
    

    public ArrayList<Ejemplar> listarEjemplar() {
        ArrayList<Ejemplar> lista = new ArrayList<>();
        DLibro dlibro = new DLibro();
        try {
            this.obtRegistros("Select * from [CATALOGO].[VW_EJEMPLAR]");
            while (rs.next()) {
                ArrayList<Autor> autores = dlibro.listarAutor(rs.getString("ISBN"));
                lista.add(new Ejemplar(rs.getString("codigo_inventario"),
                        rs.getBoolean("estado"),
                        rs.getInt("numero_copia"),
                        new Ubicacion(rs.getString("codigo_ubicacion"),rs.getString("nombre")),
                        rs.getString("ISBN"),
                        rs.getString("titulo"),
                        rs.getString("MFN"),
                        new Clasificacion(rs.getString("codigo_clasificacion"),rs.getString("nombre_clasificacion")),
                        new Editorial (rs.getString("codigo_editorial"),rs.getString("nombre_editorial")),
                        autores
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar la Ejemplar " + ex.getMessage());
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
    
    public ArrayList<Ejemplar> listarEjemplar(String x) {
        ArrayList<Ejemplar> lista = new ArrayList<>();
        DLibro dlibro = new DLibro();
        try {
            this.obtRegistros(x);
            while (rs.next()) {
                ArrayList<Autor> autores = dlibro.listarAutor(rs.getString("ISBN"));
                lista.add(new Ejemplar(rs.getString("codigo_inventario"),
                        rs.getBoolean("estado"),
                        rs.getInt("numero_copia"),
                        new Ubicacion(rs.getString("codigo_ubicacion"),rs.getString("nombre")),
                        rs.getString("ISBN"),
                        rs.getString("titulo"),
                        rs.getString("MFN"),
                        new Clasificacion(rs.getString("codigo_clasificacion"),rs.getString("nombre_clasificacion")),
                        new Editorial (rs.getString("codigo_editorial"),rs.getString("nombre_editorial")),
                        autores
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar la Ejemplar " + ex.getMessage());
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

    public boolean guardarEjemplar(Ejemplar a) {
        boolean guardado = false;
        this.obtRegistros("Select * from [CATALOGO].[Ejemplar]");
        DLibro dlibro = new DLibro();
        boolean existeLibro = dlibro.existeLibro(a.getIsbn());
        if(!existeLibro){
            dlibro.guardarLibro(new Libro(a.getIsbn(),
                    a.getTitulo_libro(),
                    a.getMfn(),
                    a.getClasificacion(),
                    a.getEditorial(),
                    a.getAutores()
            ));
            existeLibro = true;
        }
        else if(existeLibro){
            try {
                rs.moveToInsertRow();
                rs.updateString("codigo_inventario", a.getCod_inventario());
                rs.updateBoolean("estado", a.isEstado());
                rs.updateInt("numero_copia",a.getNum_copia());
                rs.updateString("ISBN", a.getIsbn());
                rs.updateString("codigo_ubicacion", a.getUbicacion().getCod_ubicacion());
                rs.insertRow();
                rs.moveToCurrentRow();
                guardado = true;
                dlibro = null;
            } catch (SQLException ex) {
                System.out.println("Error al guardar Ejemplar:" + ex.getMessage());
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
        }
        else{
            System.out.println("No existe el libro");
            return !guardado;
        }
        return guardado;
    }

    public boolean existeEjemplar(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[Ejemplar]");
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_inventario").equals(id)) {
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar Ejemplar: " + ex.getMessage());
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

    public boolean editarEjemplar(Ejemplar a) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[Ejemplar]");
       DLibro dlibro = new DLibro();
        if(dlibro.existeLibro(a.getIsbn())){
            try {
                rs.beforeFirst();
                while (rs.next()) {
                    if (rs.getString("codigo_inventario").equals(a.getCod_inventario())) {
                        rs.updateBoolean("estado", a.isEstado());
                        rs.updateInt("numero_copia", a.getNum_copia());
                        rs.updateString("ISBN", a.getIsbn());
                        rs.updateString("codigo_ubicacion", a.getUbicacion().getCod_ubicacion());
                        rs.updateRow();
                        resp = true;
                        dlibro=null;
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
        }
        else{
             System.out.println("No existe el libro");
             
        }
         return resp;
    }

    public boolean eliminarEjemplar(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[Ejemplar]");
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_inventario").equals(id)) {
                    rs.deleteRow();
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar ejemplar" + ex.getMessage());
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
