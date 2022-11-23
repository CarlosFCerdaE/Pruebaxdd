/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Autor;
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
public class DAutorxLibro {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public void obtRegistros() {
        try {
            conn = Conexion.obtConexion();
            String tSQL = "Select * from [CATALOGO].[AutorXLibro]";
            ps = conn.prepareStatement(tSQL, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al obtener registros: " + ex.getMessage());
        }
    }

    

    public boolean guardarAutorxLibro(String ISBN,String Cod_autor) {
        boolean guardado = false;
        this.obtRegistros();
        try {
            rs.moveToInsertRow();
            rs.updateString("ISBN", ISBN);
            rs.updateString("codigo_autor", Cod_autor);
            rs.insertRow();
            rs.moveToCurrentRow();
            guardado = true;
        } catch (SQLException ex) {
            System.out.println("Error al guardar en tabla AutorXLibro: " + ex.getMessage());
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

    
}
