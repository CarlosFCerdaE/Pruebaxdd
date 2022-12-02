/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import complementos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cfco5
 */

public class DAutorxLibro {

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

    

    public boolean guardarAutorxLibro(String ISBN, String Cod_autor) {
        boolean guardado = false;
        this.obtRegistros("Select * from [CATALOGO].[AutorXLibro]");
        try {
            rs.moveToInsertRow();
            rs.updateString("ISBN", ISBN);
            rs.updateString("codigo_autor", Cod_autor);
            rs.insertRow();
            rs.moveToCurrentRow();
            guardado = true;
        } catch (SQLException ex) {
            System.out.println("Error al guardar en tabla AutorXLibro: " + ex.getMessage());
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
        return guardado;
    }
    
    public boolean editarAutorxLibro(String ISBN, String Cod_autor, String cod_autorNuevo) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[AutorXLibro]");

        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("ISBN").equals(ISBN) && rs.getString("codigo_autor").equals(Cod_autor)) {
                    //System.out.println(ISBN + " " + Cod_autor);
                    //rs.updateString("ISBN", ISBN);
                    rs.updateString("codigo_autor", cod_autorNuevo);
                    rs.updateRow();
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al editar en tabla autorxlibro: " + ex.getMessage());
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
        return resp;
    }

  
    public boolean eliminarTodoAutorxLibro(String isbn) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[AutorXLibro]" + " WHERE ISBN LIKE '" + isbn + "'");
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("ISBN").equals(isbn) /*&& rs.getString("codigo_autor").equals(cod) */ ) {
                    rs.deleteRow();
                    //resp = true;
                    //break;
                }
            }
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Error al eliminar Libro: " + ex.getMessage());
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
        return resp;
    } 
    
}
