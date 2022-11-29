/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Estudiante;
import complementos.Conexion;
import entidades.Carrera;
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
    
    public ArrayList<Estudiante> listarEstudiante() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        try {
            this.obtRegistros2("Select * from [CATALOGO].[VW_ESTUDIANTES]");
            while (rs2.next()) {
              ArrayList<Carrera> carrera = listarCarrera(rs2.getString("CIF_Estudiante"));
                
                lista.add(new Estudiante(rs2.getString("CIF_Estudiante"),
                        carrera,
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
    
    public ArrayList<Carrera> listarCarrera(String cif_estudiante){
        ArrayList<Carrera> lista = new ArrayList<>();
        try {
            this.obtRegistros("Select * from [RRHH].[VWLISTADOCARRERAS]"
                    + " WHERE cif LIKE '"+cif_estudiante+"'");
            while (rs.next()) {
                lista.add(new Carrera(
                        rs.getString("codigo_carrera"),
                        rs.getString("nombre")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Carreras " + ex.getMessage());
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
        this.obtRegistros("Select * from [RRHH].[Estudiante]");
        DPersona dpersona = new DPersona();
        DEstudiantexCarrera dexc = new DEstudiantexCarrera();
        if(dpersona.guardarPersona(new Persona(e.getId_pers(),e.getNombre_pers(),e.getApellidos_pers(),e.getTelefono_pers()))){
            try {
                rs.moveToInsertRow();
                rs.updateString("cif", e.getCif());
                rs.updateString("id_persona", e.getId_pers());
                rs.insertRow();
                rs.moveToCurrentRow();
                for(Carrera carr: e.getCarreras()){
                    dexc.guardarEstudiantexCarrera(e.getCif(),carr.getCod_carrera());
                }
                
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
        this.obtRegistros("Select * from [RRHH].[Estudiante]");
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
        this.obtRegistros("Select * from [RRHH].[Estudiante]");
       DPersona dpersona = new DPersona();
       DEstudiantexCarrera dexc = new DEstudiantexCarrera();
        if(dpersona.existePersona(e.getId_pers())){
            try {
                dpersona.editarPersona(new Persona(e.getId_pers(),e.getNombre_pers(),e.getApellidos_pers(),e.getTelefono_pers()));
                rs.beforeFirst();
                while (rs.next()) {
                    if (rs.getString("cif").equals(e.getCif())) {
                        rs.updateString("cif",e.getCif());
                        rs.updateRow();
                        for(Carrera carr: e.getCarreras()){
                            dexc.editarEstudiantexCarrera(e.getCif(),carr.getCod_carrera());
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
             System.out.println("No existe el estudiante");
             
        }
         return resp;
    }

    public boolean eliminarEstudiante(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [RRHH].[Estudiante]");
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
