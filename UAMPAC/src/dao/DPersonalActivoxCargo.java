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
import java.util.ArrayList;

/**
 *
 * @author cfco5
 */

public class DPersonalActivoxCargo {

    private Connection conn = null;
    private Connection conn2 = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null; 
    
    private PreparedStatement ps2 = null;
    private ResultSet rs2 = null; 

    public void obtRegistros() {
        try {
            conn = Conexion.obtConexion();
            String tSQL = "Select * from [RRHH].[PersonalActivoXCargo]";
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
    
    public boolean guardarPersonalActivoxCargo(String id_personalactivo,String Cod_Facultad) {
        boolean guardado = false;
        this.obtRegistros();
        try {
            rs.moveToInsertRow();
            rs.updateString("id_personalactivo", id_personalactivo);
            rs.updateString("codigo_cargo", Cod_Facultad);
            rs.insertRow();
            rs.moveToCurrentRow();
            guardado = true;
        } catch (SQLException ex) {
            System.out.println("Error al guardar en tabla PersonalActivoXCargo: " + ex.getMessage());
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
    public boolean editarPersonalActivoxCargo(String id_personalactivo,String Cod_Facultad) {
        boolean resp = false;
        this.obtRegistros();

        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("id_personalactivo").equals(id_personalactivo) && rs.getString("codigo_cargo").equals(Cod_Facultad)) {
                    rs.updateString("id_personalactivo", id_personalactivo);
                    rs.updateString("codigo_cargo", Cod_Facultad);
                    rs.updateRow();
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al editar en tabla PersonalActivoXCargo: " + ex.getMessage());
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
    
    public ArrayList<String> listarCargo(String cif){
        ArrayList<String> lista = new ArrayList<>();
        try {
            this.obtRegistros2("Select codigo_cargo from [RRHH].[PersonalActivoXCargo]"
                    + " WHERE id_personalactivo LIKE '"+cif+"'");
            while (rs2.next()) {
                lista.add(rs2.getString("codigo_cargo"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar el codigo del cargo: " + ex.getMessage());
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
        return lista;
    }
    
    public boolean eliminarTodoPerxCar(String id_personalactivo) {
        boolean resp = false;
        this.obtRegistros2("Select * from [RRHH].[PersonalActivoXCargo]" + " WHERE id_personalactivo LIKE '" + id_personalactivo + "'");
        try {
            rs2.beforeFirst();
            while (rs2.next()) {
                if (rs2.getString("id_personalactivo").equals(id_personalactivo) /*&& rs.getString("codigo_autor").equals(cod) */ ) {
                    rs2.deleteRow();
                    //resp = true;
                    //break;
                }
            }
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Error al eliminar Docente: " + ex.getMessage());
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
