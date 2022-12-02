/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import complementos.Conexion;
import entidades.ListadoPersonas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */

public class DListadoPersonas {
    
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
    

    public ArrayList<ListadoPersonas> listarListadoPersonas() {
        ArrayList<ListadoPersonas> lista = new ArrayList<>();
        try {
            this.obtRegistros2("Select * from [RRHH].[VWLISTADOPERSONAS]");
            while (rs2.next()) {
              //ArrayList<Autor> autores = listarAutor(rs2.getString("ISBN"));
                //System.out.println(autores.get(0).getNombre_autor());
                //System.out.println(autores.get(1).getNombre_autor());
                //System.out.println(autores.get(2).getNombre_autor());
                
                //AQUI ES EL PROBLEMA (YA NO :D)
                
                
                
                lista.add(new ListadoPersonas(rs2.getString("id_persona"),
                        rs2.getString("nombres"),
                        rs2.getString("apellidos"),
                        rs2.getString("telefono"),
                        rs2.getString("tipo")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Libro: " + ex.getMessage());
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
    
}
