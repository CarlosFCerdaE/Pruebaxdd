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
    

    public ArrayList<PersonalActivo> listarPersonalActivo() {
        ArrayList<PersonalActivo> lista = new ArrayList<>();
        try {
            this.obtRegistros("Select * from [CATALOGO].[VW_PERSONALACTIVO]");
            while (rs.next()) {
                ArrayList<Cargo> cargos = listarCargo(rs.getString("CIF_Cargo"));
                lista.add(new PersonalActivo(rs.getString("CIF_Personal"),
                        cargos,
                        rs.getString("Cedula"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("telefono")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Personal Activo " + ex.getMessage());
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

    public boolean guardarPersonalActivo(PersonalActivo pa) {
        boolean guardado = false;
        this.obtRegistros("Select * from [CATALOGO].[PersonalActivo]");
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
                        rs.updateString("id_personalactivo",pa.getId_PersonalActivo());
                        rs.updateRow();
                        for(Cargo car: pa.getCargos()){
                            dpaxc.editarPersonalActivoxCargo(pa.getId_PersonalActivo(),car.getCod_cargo());
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
}
