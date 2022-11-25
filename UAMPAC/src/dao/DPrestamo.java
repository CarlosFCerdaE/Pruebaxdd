/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Prestamo;
import complementos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cfco5
 */
public class DPrestamo {

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
/*
    public ArrayList<Prestamo> listarPrestamo() {
        ArrayList<Prestamo> lista = new ArrayList<>();
        Object VPrestamo[] = new Object[8];
        try {
            this.obtRegistros("Select * from [PRESTAMOS].[VW_LISTADOPRESTAMOS]");
            while (rs.next()) {
                lista.add(new VPrestamo(rs.getString("codigo_prestamo"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("titulo"),
                        rs.getInt("numero_copia"),
                        rs.getTimestamp("f_emision"),
                        rs.getTimestamp("f_devolucion"),
                        rs.getBigDecimal("mora")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar el Prestamo " + ex.getMessage());
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
*/
    public boolean guardarPrestamo(Prestamo a, String x) {
        boolean guardado = false;
        this.obtRegistros("Select * from [PRESTAMOS].[Prestamo]");
        try {
            rs.moveToInsertRow();
            rs.updateString("codigo_prestamo",a.getCod_prestamo());
            rs.updateTimestamp("f_emision",a.getf_emision());
            rs.updateTimestamp("f_devolucion",a.getf_devolucion());
            rs.updateBigDecimal("mora",a.getMora());
            rs.updateBoolean("estado",a.isEstado());
            rs.updateString("id_persona", x);
            rs.insertRow();
            rs.moveToCurrentRow();
            guardado = true;
        } catch (SQLException ex) {
            System.out.println("Error al guardar Prestamo:" + ex.getMessage());
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

    public boolean existePrestamo(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [PRESTAMOS].[Prestamo]");
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_prestamo").equals(id)) {
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar Prestamo: " + ex.getMessage());
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

    public boolean editarPrestamo(Prestamo a,String x) {
        boolean resp = false;
        this.obtRegistros("Select * from [PRESTAMOS].[Prestamo]");

        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_prestamo").equals(a.getCod_prestamo())) {
                    rs.updateTimestamp("f_emision",a.getf_emision());
                    rs.updateTimestamp("f_devolucion",a.getf_devolucion());
                    rs.updateBigDecimal("mora",a.getMora());
                    rs.updateBoolean("estado",a.isEstado());
                    rs.updateString("id_persona", x);
                    rs.updateRow();
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
}
