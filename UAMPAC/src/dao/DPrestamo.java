/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Prestamo;
import complementos.Conexion;
import entidades.Autor;
import entidades.Clasificacion;
import entidades.Editorial;
import entidades.Ejemplar;
import entidades.Persona;
import entidades.Ubicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cfco5
 */
public class DPrestamo {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection conn2 = null;
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
            System.out.println("Error al obtener registros2: " + ex.getMessage());
        }
    }

    public DefaultTableModel mostrarPrestamos(){
        DefaultTableModel dtm = new DefaultTableModel();
        String titulos[] = {
             "Codigo Prestamo","Nombres","Rol","Titulo",
            "Numero de Copia","Fecha de emision","Fecha de devolucion","Mora"
        };
        
        dtm.setColumnIdentifiers(titulos);
        
        try{
            this.obtRegistros("SELECT * FROM [PRESTAMOS].[VW_LISTADOPRESTAMOS]");
            
            while(rs.next()){
                this.obtRegistros2("SELECT L.titulo,E.numero_copia\n" +
                "FROM [PRESTAMOS].[PrestamoXEjemplar] pxe INNER JOIN [CATALOGO].[Ejemplar] E\n" +
                "ON pxe.codigo_inventario = E.codigo_inventario\n" +
                "INNER JOIN [CATALOGO].[Libro] L\n" +
                "ON L.ISBN = E.ISBN\n" +
                "WHERE PXE.codigo_prestamo LIKE '"+rs.getString("codigo_prestamo")+"'");
                String libros ="";
                String num_copia ="";
                while(rs2.next()){
                    if(!rs2.isLast()){
                        libros+=rs2.getString("titulo")+", ";
                        num_copia+=rs2.getString("numero_copia")+", ";
                    }
                    else{
                        libros+=rs2.getString("titulo");
                        num_copia+=rs2.getString("numero_copia");
                    }
                    
                }
                
                
                Object filas[]= new Object[8];
                filas[0] = rs.getString("codigo_prestamo");
                filas[1] = rs.getString("nombre");
                filas[2] = rs.getString("tipo");
                filas[3] = libros;
                filas[4] = num_copia;
                filas[5] = rs.getDate("f_emision");
                filas[6] = rs.getDate("f_devolucion");
                filas[7] = rs.getInt("Mora");
                dtm.addRow(filas);
                
            }
        }catch (SQLException ex) {
            System.out.println("Error al obtener registros: " + ex.getMessage());
        }
        return dtm;
    }
    
    
    
    
    
    public ArrayList<Prestamo> listarPrestamo() {
        ArrayList<Prestamo> lista = new ArrayList<>();
        DEjemplar dejemplar = new DEjemplar();
        ArrayList<Ejemplar> ejemplares = new ArrayList<>();
        try {
            this.obtRegistros2("Select * from [PRESTAMOS].[VW_PRESTAMOS]");
            
            while (rs2.next()) {
                 ejemplares=listarEjemplarforPrincipal("SELECT PXE.codigo_prestamo,VE.codigo_inventario,VE.estado,VE.numero_copia,VE.codigo_ubicacion,VE.nombre_ubicacion,VE.ISBN,VE.titulo,VE.MFN,VE.codigo_clasificacion,VE.nombre_clasificacion,VE.codigo_editorial,VE.nombre_editorial\n" +
"FROM [CATALOGO].[VW_EJEMPLAR] VE INNER JOIN [PRESTAMOS].[PrestamoXEjemplar] PXE\n" +
"ON VE.codigo_inventario = PXE.codigo_inventario\n" +
"WHERE PXE.codigo_prestamo LIKE '"+rs2.getString("codigo_prestamo")+"'");
                lista.add(new Prestamo(rs2.getString("codigo_prestamo"),
                        rs2.getDate("f_emision"),
                        rs2.getDate("f_devolucion"),
                        rs2.getBigDecimal("mora"),
                        rs2.getBoolean("estado"),
                        new Persona(rs2.getString("id_persona"),
                            rs2.getString("nombres"),
                            rs2.getString("apellidos"),
                            rs2.getString("telefono")
                        ),
                        ejemplares
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listarr el Prestamo " + ex.getMessage());
        } finally {
            try {
                if (rs2 != null) {
                    rs2.close();
                    
                }
                if (ps2 != null) {
                    ps2.close();
                  
                }

                if (conn2 != null) {
                    Conexion.cerrarConexion(conn2);
              
                }
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
    
    public ArrayList<Ejemplar> listarEjemplarforPrincipal(String x) {
        
        ArrayList<Ejemplar> lista = new ArrayList<>();
        DLibro dlibro = new DLibro();
        try {
            this.obtRegistros(x);
            while (rs.next()) {
                ArrayList<Autor> autores = dlibro.listarAutor(rs.getString("ISBN"));
                lista.add(new Ejemplar(rs.getString("codigo_inventario"),
                        rs.getBoolean("estado"),
                        rs.getInt("numero_copia"),
                        new Ubicacion(rs.getString("codigo_ubicacion"),rs.getString("nombre_ubicacion")),
                        rs.getString("ISBN"),
                        rs.getString("titulo"),
                        rs.getString("MFN"),
                        new Clasificacion(rs.getString("codigo_clasificacion"),rs.getString("nombre_clasificacion")),
                        new Editorial (rs.getString("codigo_editorial"),rs.getString("nombre_editorial")),
                        autores
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar el Ejemplar en for principal " + ex.getMessage());
        }
        return lista;
    }

    public boolean guardarPrestamo(Prestamo a) {
        boolean guardado = false;
        this.obtRegistros("Select * from [PRESTAMOS].[Prestamo]");
        DPrestamoxEjemplar dpxe = new DPrestamoxEjemplar();
        DEjemplar dejemplar = new DEjemplar();
        try {
            rs.moveToInsertRow();
            rs.updateString("codigo_prestamo",a.getCod_prestamo());
            rs.updateDate("f_emision",a.getF_emision());
            rs.updateDate("f_devolucion",a.getF_devolucion());
            rs.updateBigDecimal("mora",a.getMora());
            rs.updateBoolean("estado",a.isEstado());
            rs.updateString("id_persona", a.getPersona().getId_pers());
            rs.insertRow();
            rs.moveToCurrentRow();
            for(Ejemplar ej: a.getEjemplares()){
                dpxe.guardarPrestamoxEjemplar(a.getCod_prestamo(), ej.getCod_inventario());
                dejemplar.editarEjemplar(ej);
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
        DEjemplar dejemplar = new DEjemplar();
        try {
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getString("codigo_prestamo").equals(a.getCod_prestamo())) {
                    rs.updateBigDecimal("mora",a.getMora());
                    rs.updateBoolean("estado",true);
                    rs.updateString("id_persona", a.getPersona().getId_pers());
                    rs.updateRow();
                    for(Ejemplar ej: a.getEjemplares()){
                        dejemplar.editarEjemplar(ej);
                    }
                    resp = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al editar Prestamo: " + ex.getMessage());
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
