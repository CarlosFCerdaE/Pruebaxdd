/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Clasificacion;
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

public class DClasificacion {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public void obtRegistros() {
        try {
            conn = Conexion.obtConexion();
            String tSQL = "Select * from [CATALOGO].[Clasificacion]";
            ps = conn.prepareStatement(tSQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al obtener registros: " + ex.getMessage());
        }
    }

    public ArrayList<Clasificacion> listarClasificacion() {
        ArrayList<Clasificacion> lista = new ArrayList<>();
        try {
            this.obtRegistros();
            while (rs.next()) {
                lista.add(new Clasificacion(rs.getString("codigo_clasificacion"),
                        rs.getString("nombre_clasificacion")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar la clasificacion: " + ex.getMessage());
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

    public boolean guardarClasificacion(Clasificacion a) {
        boolean guardado = false;
        this.obtRegistros();
        try {
            rs.moveToInsertRow();
            rs.updateString("codigo_clasificacion", a.getCod_clasificacion());
            rs.updateString("nombre_clasificacion", a.getNombre_clasificacion());
            rs.insertRow();
            rs.moveToCurrentRow();
            guardado = true;
        } catch (SQLException ex) {
            System.out.println("Error al guardar clasificacion: " + ex.getMessage());
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

    public boolean existeClasificacion(String id) {
        boolean resp = false;
        this.obtRegistros();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_clasificacion").equals(id)) {
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar la clasificacion: " + ex.getMessage());
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

    public boolean editarClasificacion(Clasificacion a) {
        boolean resp = false;
        this.obtRegistros();

        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_clasificacion").equals(a.getCod_clasificacion())) {
                    rs.updateString("nombre_clasificacion", a.getNombre_clasificacion());
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

    public boolean eliminarClasificacion(String id) {
        boolean resp = false;
        this.obtRegistros();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_clasificacion").equals(id)) {
                    rs.deleteRow();
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar clasificacion: " + ex.getMessage());
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
