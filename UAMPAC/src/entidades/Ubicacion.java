/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author cfco5
 */
public class Ubicacion {
    private String cod_ubicacion;
    private String nombre_ubi;

    public String getCod_ubicacion() {
        return cod_ubicacion;
    }

    public void setCod_ubicacion(String cod_ubicacion) {
        this.cod_ubicacion = cod_ubicacion;
    }

    public String getNombre_ubi() {
        return nombre_ubi;
    }

    public void setNombre_ubi(String nombre_ubi) {
        this.nombre_ubi = nombre_ubi;
    }

    public Ubicacion() {
    }

    public Ubicacion(String cod_ubicacion, String nombre_ubi) {
        this.cod_ubicacion = cod_ubicacion;
        this.nombre_ubi = nombre_ubi;
    }
    
    
}
