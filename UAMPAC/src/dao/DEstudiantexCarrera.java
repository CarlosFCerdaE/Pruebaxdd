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
public class DEstudiantexCarrera {

    private Connection conn = null;
    private Connection conn2 = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null; 
    
    private PreparedStatement ps2 = null;
    private ResultSet rs2 = null; 

    public void obtRegistros() {
        try {
            conn = Conexion.obtConexion();
            String tSQL = "Select * from [RRHH].[EstudianteXCarrera]";
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

    

    public boolean guardarEstudiantexCarrera(String cif,String Cod_Carrera) {
        boolean guardado = false;
        this.obtRegistros();
        try {
            rs.moveToInsertRow();
            rs.updateString("cif", cif);
            rs.updateString("codigo_carrera", Cod_Carrera);
            rs.insertRow();
            rs.moveToCurrentRow();
            guardado = true;
        } catch (SQLException ex) {
            System.out.println("Error al guardar en tabla EstudianteXCarrera: " + ex.getMessage());
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
    public boolean editarEstudiantexCarrera(String cif,String Cod_Carrera) {
        boolean resp = false;
        this.obtRegistros();

        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("cif").equals(cif) && rs.getString("codigo_carrera").equals(Cod_Carrera)) {
                    rs.updateString("cif", cif);
                    rs.updateString("codigo_carrera", Cod_Carrera);
                    rs.updateRow();
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al editar en tabla EstudianteXCarrera: " + ex.getMessage());
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
    
    public ArrayList<String> listarCarrera(String cif){
        ArrayList<String> lista = new ArrayList<>();
        try {
            this.obtRegistros2("Select codigo_carrera from [RRHH].[EstudianteXCarrera]"
                    + " WHERE cif LIKE '"+cif+"'");
            while (rs2.next()) {
                lista.add(rs2.getString("codigo_carrera"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar el codigo de la carrera " + ex.getMessage());
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
    
    public boolean eliminarTodoEstxCar(String cif_estudiante) {
        boolean resp = false;
        this.obtRegistros2("Select * from [RRHH].[EstudianteXCarrera]" + " WHERE cif LIKE '" + cif_estudiante + "'");
        try {
            rs2.beforeFirst();
            while (rs2.next()) {
                if (rs2.getString("cif").equals(cif_estudiante) /*&& rs.getString("codigo_autor").equals(cod) */ ) {
                    rs2.deleteRow();
                    //resp = true;
                    //break;
                }
            }
            resp = true;

        } catch (SQLException ex) {
            System.out.println("Error al eliminar Estudiante" + ex.getMessage());
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
