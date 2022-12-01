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
public class DDocentexFacultad {

    private Connection conn = null;
    private Connection conn2 = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null; 
    
    private PreparedStatement ps2 = null;
    private ResultSet rs2 = null; 
    
    public void obtRegistros() {
        try {
            conn = Conexion.obtConexion();
            String tSQL = "Select * from [RRHH].[DocenteXFacultad]";
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

    

    public boolean guardarDocentexFacultad(String id_docente,String Cod_Facultad) {
        boolean guardado = false;
        this.obtRegistros();
        try {
            rs.moveToInsertRow();
            rs.updateString("id_docente", id_docente);
            rs.updateString("codigo_facultad", Cod_Facultad);
            rs.insertRow();
            rs.moveToCurrentRow();
            guardado = true;
        } catch (SQLException ex) {
            System.out.println("Error al guardar en tabla DocenteXFacultad: " + ex.getMessage());
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
    public boolean editarDocentexFacultad(String id_docente,String Cod_Facultad) {
        boolean resp = false;
        this.obtRegistros();

        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("id_docente").equals(id_docente) && rs.getString("codigo_facultad").equals(Cod_Facultad)) {
                    rs.updateString("id_docente", id_docente);
                    rs.updateString("codigo_facultad", Cod_Facultad);
                    rs.updateRow();
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al editar en tabla DocenteXFacultad: " + ex.getMessage());
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
    
    public ArrayList<String> listarFacultad(String cif){
        ArrayList<String> lista = new ArrayList<>();
        try {
            this.obtRegistros2("Select codigo_facultad from [RRHH].[DocenteXFacultad]"
                    + " WHERE id_docente LIKE '"+cif+"'");
            while (rs2.next()) {
                lista.add(rs2.getString("codigo_facultad"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar el codigo de la facultad " + ex.getMessage());
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

    
}
