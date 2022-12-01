/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Docente;
import complementos.Conexion;
import entidades.Facultad;
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
    private Connection conn2 = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null; 
    
    private PreparedStatement ps2 = null;
    private ResultSet rs2 = null; 


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
    
    public ArrayList<Docente> listarDocente() {
        ArrayList<Docente> lista = new ArrayList<>();
        try {
            this.obtRegistros2("Select * from [CATALOGO].[VW_DOCENTES]");
            while (rs2.next()) {
              ArrayList<Facultad> facultad = listarFacultad(rs2.getString("CIF_Docente"));
                
                lista.add(new Docente(rs2.getString("CIF_Docente"),
                        facultad,
                        rs2.getString("Cedula"),
                        rs2.getString("Nombres"),
                        rs2.getString("Apellidos"),
                        rs2.getString("telefono")
                        
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Libro " + ex.getMessage());
        } finally {
            try {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }

                if (conn2 != null) {
                    Conexion.cerrarConexion(conn2);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return lista;
    }
    
    public ArrayList<Facultad> listarFacultad(String cif_docente){
        ArrayList<Facultad> lista = new ArrayList<>();
        try {
            this.obtRegistros("SELECT * FROM [RRHH].[VWLISTADOFACULTADES]"
                    + " WHERE id_docente LIKE '"+cif_docente+"'");
            while (rs.next()) {
                lista.add(new Facultad(
                        rs.getString("codigo_facultad"),
                        rs.getString("nombre")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Facultades " + ex.getMessage());
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

    public boolean guardarDocente(Docente e) {
        boolean guardado = false;
        this.obtRegistros("Select * from [RRHH].[Docente]");
        DPersona dpersona = new DPersona();
        DDocentexFacultad ddxf = new DDocentexFacultad();
        if(dpersona.guardarPersona(new Persona(e.getId_pers(),e.getNombre_pers(),e.getApellidos_pers(),e.getTelefono_pers()))){
            try {
                rs.moveToInsertRow();
                rs.updateString("id_docente", e.getId_docente());
                rs.updateString("id_persona", e.getId_pers());
                rs.insertRow();
                rs.moveToCurrentRow();
                for(Facultad fac: e.getFacultad()){
                    ddxf.guardarDocentexFacultad(e.getId_docente(),fac.getCod_facultad());
                }
                
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
            System.out.println("No existe el libro");
            return !guardado;
        }
    }

    public boolean existeDocente(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [RRHH].[Docente]");
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("cif").equals(id)) {
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

    public boolean editarDocente(Docente e) {
        boolean resp = false;
        this.obtRegistros("Select * from [RRHH].[Docente]");
       DPersona dpersona = new DPersona();
       DDocentexFacultad dexc = new DDocentexFacultad();
        if(dpersona.existePersona(e.getId_pers())){
            try {
                dpersona.editarPersona(new Persona(e.getId_pers(),e.getNombre_pers(),e.getApellidos_pers(),e.getTelefono_pers()));
                rs.beforeFirst();
                while (rs.next()) {
                    if (rs.getString("id_docente").equals(e.getId_docente())) {
                        rs.updateString("id_docente",e.getId_docente());
                        rs.updateRow();
                        for(Facultad fac: e.getFacultad()){
                            dexc.editarDocentexFacultad(e.getId_docente(),fac.getCod_facultad());
                        }
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
        this.obtRegistros("Select * from [RRHH].[Docente]");
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
