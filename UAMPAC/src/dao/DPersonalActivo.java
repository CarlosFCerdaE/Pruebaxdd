/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.PersonalActivo;
import complementos.Conexion;
import entidades.Cargo;
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
public class DPersonalActivo {

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
    
    public ArrayList<PersonalActivo> listarPersonalActivo() {
        ArrayList<PersonalActivo> lista = new ArrayList<>();
        try {
            this.obtRegistros2("Select * from [CATALOGO].[VW_PERSONALACTIVO]");
            while (rs2.next()) {
              ArrayList<Cargo> cargo = listarCargo(rs2.getString("CIF_Personal"));
                
                lista.add(new PersonalActivo(rs2.getString("CIF_Personal"),
                        cargo,
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
    
    public ArrayList<Cargo> listarCargo(String cif_personalactivo){
        ArrayList<Cargo> lista = new ArrayList<>();
        try {
            this.obtRegistros("SELECT * FROM [RRHH].[VWLISTADOCARGOS]"
                    + " WHERE id_personalactivo LIKE '"+cif_personalactivo+"'");
            while (rs.next()) {
                lista.add(new Cargo(
                        rs.getString("codigo_cargo"),
                        rs.getString("nombre")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Cargos " + ex.getMessage());
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

    public boolean guardarPersonalActivo(PersonalActivo pa) {
        boolean guardado = false;
        this.obtRegistros("Select * from [RRHH].[PersonalActivo]");
        DPersona dpersona = new DPersona();
        DPersonalActivoxCargo dpaxc= new DPersonalActivoxCargo();
        if(dpersona.guardarPersona(new Persona(pa.getId_pers(),pa.getNombre_pers(),pa.getApellidos_pers(),pa.getTelefono_pers()))){
            try {
                rs.moveToInsertRow();
                rs.updateString("id_personalactivo", pa.getId_PersonalActivo());
                rs.updateString("id_persona", pa.getId_pers());
                rs.insertRow();
                rs.moveToCurrentRow();
                for(Cargo car: pa.getCargos()){
                    dpaxc.guardarPersonalActivoxCargo(pa.getId_PersonalActivo(),car.getCod_cargo());
                }
                guardado = true;
                dpersona = null;
            } catch (SQLException ex) {
                System.out.println("Error al guardar Personal Activo:" + ex.getMessage());
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
            System.out.println("No existe el personal activo");
            return !guardado;
        }
    }

    public boolean existePersonalActivo(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[PersonalActivo]");
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("id_personalactivo").equals(id)) {
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar Personal Activo: " + ex.getMessage());
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

    public boolean editarPersonalActivo(PersonalActivo pa) {
        boolean resp = false;
        this.obtRegistros("Select * from [RRHH].[PersonalActivo]");
       DPersona dpersona = new DPersona();
       DPersonalActivoxCargo dpaxc = new DPersonalActivoxCargo();
        if(dpersona.existePersona(pa.getId_pers())){

            try {
                dpersona.editarPersona(new Persona(pa.getId_pers(),pa.getNombre_pers(),pa.getApellidos_pers(),pa.getTelefono_pers()));
                rs.beforeFirst();
                while (rs.next()) {
                    if (rs.getString("id_personalactivo").equals(pa.getId_PersonalActivo())) {
                        
                        dpaxc.eliminarTodoPerxCar(pa.getId_PersonalActivo());
                        
                        for(Cargo car: pa.getCargos()){
                            dpaxc.guardarPersonalActivoxCargo(pa.getId_PersonalActivo(),car.getCod_cargo());
                        }
                        resp = true;
                        //dpersona=null;
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
             System.out.println("No existe el personal activo");
             
        }
         return resp;
    }

    public boolean eliminarPersonalActivo(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [CATALOGO].[PersonalActivo]");
        DPersona dpersona = new DPersona();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("id_personalactivo").equals(id)) {
                    dpersona.eliminarPersona(rs.getString("id_persona"));
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al eliminar personal activo" + ex.getMessage());
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
    
    public ArrayList<String> listarCif(String id_persona){
        ArrayList<String> lista = new ArrayList<>();
        try {
            this.obtRegistros("Select id_personalactivo from [RRHH].[PersonalActivo]"
                    + " WHERE id_persona LIKE '"+id_persona+"'");
            while (rs.next()) {
                lista.add(rs.getString("id_personalactivo"));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar el cif " + ex.getMessage());
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
