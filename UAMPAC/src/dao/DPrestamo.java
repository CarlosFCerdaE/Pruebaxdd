/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Prestamo;
import complementos.Conexion;
import entidades.Ejemplar;
import entidades.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cfco5
 */
public class DPrestamo {

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

    public DefaultTableModel mostrarPrestamos(){
        DefaultTableModel dtm = new DefaultTableModel();
        String titulos[] = {
             "Codigo Prestamo","Nombres","Apellidos","Titulo",
            "Numero de Copia","Fecha de emision","Fecha de devolucion","Mora"
        };
        
        dtm.setColumnIdentifiers(titulos);
        
        try{
            ResultSet rs_vista = null;
            conn = Conexion.obtConexion();
            String tSQL = "SELECT * FROM [PRESTAMOS].[VW_LISTADOPRESTAMOS]";
            ps = conn.prepareStatement(tSQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            rs_vista = ps.executeQuery();
            
            while(rs_vista.next()){
                Object filas[]= new Object[8];
                filas[0] = rs_vista.getString("codigo_prestamo");
                filas[1] = rs_vista.getString("nombres");
                filas[2] = rs_vista.getString("apellidos");
                filas[3] = rs_vista.getString("titulo");
                filas[4] = rs_vista.getInt("numero_copia");
                filas[5] = rs_vista.getTimestamp("f_emision");
                filas[6] = rs_vista.getTimestamp("f_devolucion");
                filas[7] = rs_vista.getInt("Mora");
            }
        }catch (SQLException ex) {
            System.out.println("Error al obtener registros: " + ex.getMessage());
        }
        return dtm;
    }
    
    
    
    
    
    public ArrayList<Prestamo> listarPrestamo() {
        ArrayList<Prestamo> lista = new ArrayList<>();
        DEjemplar dejemplar = new DEjemplar();
        try {
            this.obtRegistros("Select * from [PRESTAMOS].[Prestamo]");
            while (rs.next()) {
                ArrayList<Ejemplar> ejemplar = dejemplar.listarEjemplar("Select * from [CATALOGO].[VW_Libro]"
                    + " WHERE codigo_prestamo LIKE '"+rs.getString("codigo_prestamo")+"'");
                lista.add(new Prestamo(rs.getString("codigo_prestamo"),
                        rs.getTimestamp("f_emision"),
                        rs.getTimestamp("f_devolucion"),
                        rs.getBigDecimal("mora"),
                        rs.getBoolean("estado"),
                        new Persona(rs.getString("id_persona"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getString("telefono")
                        ),
                        ejemplar
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar el Prestamo " + ex.getMessage());
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

    public boolean guardarPrestamo(Prestamo a) {
        boolean guardado = false;
        this.obtRegistros("Select * from [PRESTAMOS].[Prestamo]");
        DPrestamoxEjemplar dpxe = new DPrestamoxEjemplar();
        try {
            rs.moveToInsertRow();
            rs.updateString("codigo_prestamo",a.getCod_prestamo());
            rs.updateTimestamp("f_emision",a.getf_emision());
            rs.updateTimestamp("f_devolucion",a.getf_devolucion());
            rs.updateBigDecimal("mora",a.getMora());
            rs.updateBoolean("estado",a.isEstado());
            rs.updateString("id_persona", a.getPersona().getId_pers());
            rs.insertRow();
            rs.moveToCurrentRow();
            for(Ejemplar ej: a.getEjemplares()){
                dpxe.guardarPrestamoxEjemplar(a.getCod_prestamo(), ej.getCod_inventario());
            }
            
            guardado = true;
        } catch (SQLException ex) {
            System.out.println("Error al guardar Prestamo:" + ex.getMessage());
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

    public boolean existePrestamo(String id) {
        boolean resp = false;
        this.obtRegistros("Select * from [PRESTAMOS].[Prestamo]");
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_prestamo").equals(id)) {
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar Prestamo: " + ex.getMessage());
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

    public boolean editarPrestamo(Prestamo a) {
        boolean resp = false;
        this.obtRegistros("Select * from [PRESTAMOS].[Prestamo]");
        DPrestamoxEjemplar dpxe = new DPrestamoxEjemplar();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_prestamo").equals(a.getCod_prestamo())) {
                    rs.updateTimestamp("f_emision",a.getf_emision());
                    rs.updateTimestamp("f_devolucion",a.getf_devolucion());
                    rs.updateBigDecimal("mora",a.getMora());
                    rs.updateBoolean("estado",a.isEstado());
                    rs.updateString("id_persona", a.getPersona().getId_pers());
                    rs.updateRow();
                    for(Ejemplar ej: a.getEjemplares()){
                        dpxe.editarPrestamoxEjemplar(a.getCod_prestamo(), ej.getCod_inventario());
                    }
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
}
