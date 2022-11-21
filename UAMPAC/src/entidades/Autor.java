/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author cfco5
 */
public class Autor {
    private String cod_autor;
    private String nombre_autor;

    public String getCodigo_autor() {
        return cod_autor;
    }

    public void setCodigo_autor(String codigo_autor) {
        this.cod_autor = codigo_autor;
    }

    public String getNombre_autor() {
        return nombre_autor;
    }

    public void setNombre_autor(String nombre_autor) {
        this.nombre_autor = nombre_autor;
    }

    public Autor() {
    }

    public Autor(String codigo_autor, String nombre_autor) {
        this.cod_autor = codigo_autor;
        this.nombre_autor = nombre_autor;
    }
    
    
    
}
