/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author cfco5
 */
/*http://www.java2s.com/Code/Java/Database-SQL-JDBC/StandardSQLDataTypeswithTheirJavaEquivalents.htm*/
public class Prestamo {

    private String cod_prestamo;
    private java.sql.Date f_emision;
    private java.sql.Date f_devolucion;
    private java.math.BigDecimal mora;
    private boolean estado;
    private Persona persona;
    private ArrayList<Ejemplar> ejemplares;

    public String getCod_prestamo() {
        return cod_prestamo;
    }

    public void setCod_prestamo(String cod_prestamo) {
        this.cod_prestamo = cod_prestamo;
    }

    public java.sql.Date getF_emision() {
        return f_emision;
    }

    public void setF_emision(java.sql.Date f_emision) {
        this.f_emision = f_emision;
    }

    public java.sql.Date getF_devolucion() {
        return f_devolucion;
    }

    public void setF_devolucion(java.sql.Date f_devolucion) {
        this.f_devolucion = f_devolucion;
    }

    public java.math.BigDecimal getMora() {
        return mora;
    }

    public void setMora(java.math.BigDecimal mora) {
        this.mora = mora;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ArrayList<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(ArrayList<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Prestamo() {
    }

    public Prestamo(String cod_prestamo, Date f_emision, Date f_devolucion, BigDecimal mora, boolean estado, Persona persona, ArrayList<Ejemplar> ejemplares) {
        this.cod_prestamo = cod_prestamo;
        this.f_emision = f_emision;
        this.f_devolucion = f_devolucion;
        this.mora = mora;
        this.estado = estado;
        this.persona = persona;
        this.ejemplares = ejemplares;
    }

   
   

    
     
   
}
