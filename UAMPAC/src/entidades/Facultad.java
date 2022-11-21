/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author cfco5
 */
public class Facultad {
    private String cod_facultad;
    private String nombre_facultad;

    public String getCod_facultad() {
        return cod_facultad;
    }

    public void setCod_facultad(String cod_facultad) {
        this.cod_facultad = cod_facultad;
    }

    public String getNombre_facultad() {
        return nombre_facultad;
    }

    public void setNombre_facultad(String nombre_facultad) {
        this.nombre_facultad = nombre_facultad;
    }

    public Facultad() {
    }

    public Facultad(String cod_facultad, String nombre_facultad) {
        this.cod_facultad = cod_facultad;
        this.nombre_facultad = nombre_facultad;
    }
    
    
}
