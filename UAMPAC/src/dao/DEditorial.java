/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Editorial;
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

public class DEditorial {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public void obtRegistros() {
        try {
            conn = Conexion.obtConexion();
            String tSQL = "Select * from [CATALOGO].[Editorial]";
            ps = conn.prepareStatement(tSQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al obtener registros: " + ex.getMessage());
        }
    }

    public ArrayList<Editorial> listarEditorial() {
        ArrayList<Editorial> lista = new ArrayList<>();
        try {
            this.obtRegistros();
            while (rs.next()) {
                lista.add(new Editorial(rs.getString("codigo_editorial"),
                        rs.getString("nombre_editorial")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar la Editorial: " + ex.getMessage());
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

    public boolean guardarEditorial(Editorial a) {
        boolean guardado = false;
        this.obtRegistros();
        try {
            rs.moveToInsertRow();
            rs.updateString("codigo_editorial", a.getCod_editorial());
            rs.updateString("nombre_editorial", a.getNombre_editorial());
            rs.insertRow();
            rs.moveToCurrentRow();
            guardado = true;
        } catch (SQLException ex) {
            System.out.println("Error al guardar Editorial: " + ex.getMessage());
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

    public boolean existeEditorial(String id) {
        boolean resp = false;
        this.obtRegistros();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_editorial").equals(id)) {
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar la Editorial: " + ex.getMessage());
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

    public boolean editarEditorial(Editorial a) {
        boolean resp = false;
        this.obtRegistros();

        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_editorial").equals(a.getCod_editorial())) {
                    rs.updateString("nombre_editorial", a.getNombre_editorial());
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

    public boolean eliminarEditorial(String id) {
        boolean resp = false;
        this.obtRegistros();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_editorial").equals(id)) {
                    rs.deleteRow();
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar editorial: " + ex.getMessage());
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
