/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Estudiante;
import complementos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Persona;
import javax.swing.JOptionPane;

/**
 *
 * @author cfco5
 */
public class DEstudiante {

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
    

    public ArrayList<Estudiante> listarEstudiante() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        try {
            this.obtRegistros("Select * from [CATALOGO].[VW_ESTUDIANTES]");
            while (rs.next()) {
                lista.add(new Estudiante(rs.getString("CIF_Estudiante"),
                        rs.getString("Cedula"),rs.getString("Nombres"),rs.getString("Apellidos"),rs.getString("telefono")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar la Estudiante " + ex.getMessage());
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

    public boolean guardarEstudiante(Estudiante e) {
        boolean guardado = false;
        this.obtRegistros("Select * from [CATALOGO].[Estudiante]");
        DPersona dpersona = new DPersona();
        if(dpersona.guardarPersona(new Persona(e.getId_pers(),e.getNombre_pers(),e.getApellidos_pers(),e.getTelefono_pers()))){
            try {
                rs.moveToInsertRow();
                rs.updateString("cif", e.getCif());
                rs.updateString("id_persona", e.getId_pers());
                rs.insertRow();
                rs.moveToCurrentRow();
                guardado = true;
                dpersona = null;
            } catch (SQLException ex) {
                System.out.println("Error al guardar Estudiante:" + ex.getMessage());
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
        else{
            System.out.println("No existe el libro");
            return !guardado;
        }
    }

    public boolean existeEstudiante(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[Estudiante]");
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("cif").equals(id)) {
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar Estudiante: " + ex.getMessage());
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

    public boolean editarEstudiante(Estudiante e) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[Estudiante]");
       DPersona dpersona = new DPersona();
        if(dpersona.existePersona(e.getId_pers())){
            try {
                dpersona.editarPersona(new Persona(e.getId_pers(),e.getNombre_pers(),e.getApellidos_pers(),e.getTelefono_pers()));
                rs.beforeFirst();
                while (rs.next()) {
                    if (rs.getString("cif").equals(e.getCif())) {
                        rs.updateString("cif",e.getCif());
                        rs.updateRow();
                        resp = true;
                        dpersona=null;
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
             System.out.println("No existe el estudiante");
             
        }
         return resp;
    }

    public boolean eliminarEstudiante(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[Estudiante]");
        DPersona dpersona = new DPersona();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("cif").equals(id)) {
                    dpersona.eliminarPersona(rs.getString("id_persona"));
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar estudiante" + ex.getMessage());
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
