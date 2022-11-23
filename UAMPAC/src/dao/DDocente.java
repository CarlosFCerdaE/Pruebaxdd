/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Docente;
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
public class DDocente {

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
    

    public ArrayList<Docente> listarDocente() {
        ArrayList<Docente> lista = new ArrayList<>();
        try {
            this.obtRegistros("Select * from [CATALOGO].[VW_DOCENTES]");
            while (rs.next()) {
                lista.add(new Docente(rs.getString("CIF_Docente"),
                        rs.getString("Cedula"),rs.getString("Nombres"),rs.getString("Apellidos"),rs.getString("telefono")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Docente " + ex.getMessage());
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

    public boolean guardarDocente(Docente d) {
        boolean guardado = false;
        this.obtRegistros("Select * from [CATALOGO].[Docente]");
        DPersona dpersona = new DPersona();
        if(dpersona.guardarPersona(new Persona(d.getId_pers(),d.getNombre_pers(),d.getApellidos_pers(),d.getTelefono_pers()))){
            try {
                rs.moveToInsertRow();
                rs.updateString("id_docente", d.getId_docente());
                rs.updateString("id_persona", d.getId_pers());
                rs.insertRow();
                rs.moveToCurrentRow();
                guardado = true;
                dpersona = null;
            } catch (SQLException ex) {
                System.out.println("Error al guardar Docente:" + ex.getMessage());
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
            System.out.println("No existe el docente");
            return !guardado;
        }
    }

    public boolean existeDocente(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[Docente]");
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("id_docente").equals(id)) {
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar Docente: " + ex.getMessage());
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

    public boolean editarDocente(Docente d) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[Docente]");
       DPersona dpersona = new DPersona();
        if(dpersona.existePersona(d.getId_pers())){

            try {
                dpersona.editarPersona(new Persona(d.getId_pers(),d.getNombre_pers(),d.getApellidos_pers(),d.getTelefono_pers()));
                rs.beforeFirst();
                while (rs.next()) {
                    if (rs.getString("id_docente").equals(d.getId_docente())) {
                        rs.updateString("id_docente",d.getId_docente());
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
             System.out.println("No existe el docente");
             
        }
         return resp;
    }

    public boolean eliminarDocente(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[Docente]");
        DPersona dpersona = new DPersona();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("id_docente").equals(id)) {
                    dpersona.eliminarPersona(rs.getString("id_persona"));
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar docente" + ex.getMessage());
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
