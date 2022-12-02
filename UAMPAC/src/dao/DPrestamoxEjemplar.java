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

public class DPrestamoxEjemplar {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public void obtRegistros() {
        try {
            conn = Conexion.obtConexion();
            String tSQL = "Select * from [PRESTAMOS].[PrestamoXEjemplar]";
            ps = conn.prepareStatement(tSQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al obtener registros: " + ex.getMessage());
        }
    }

    

    public boolean guardarPrestamoxEjemplar(String codigo_prestamo,String Cod_inventario) {
        boolean guardado = false;
        this.obtRegistros();
        try {
            rs.moveToInsertRow();
            rs.updateString("codigo_prestamo", codigo_prestamo);
            rs.updateString("codigo_inventario", Cod_inventario);
            rs.insertRow();
            rs.moveToCurrentRow();
            guardado = true;
        } catch (SQLException ex) {
            System.out.println("Error al guardar en tabla PrestamoXEjemplar: " + ex.getMessage());
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
    public boolean editarPrestamoxEjemplar(String codigo_prestamo,String Cod_inventario) {
        boolean resp = false;
        this.obtRegistros();

        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_prestamo").equals(codigo_prestamo) && rs.getString("codigo_inventario").equals(Cod_inventario)) {
                    rs.updateString("codigo_prestamo", codigo_prestamo);
                    rs.updateString("codigo_inventario", Cod_inventario);
                    rs.updateRow();
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al editar en tabla PrestamoXEjemplar: " + ex.getMessage());
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

    
}
