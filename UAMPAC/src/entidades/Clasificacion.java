/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author cfco5
 */
public class Clasificacion {
    private String cod_clasificacion;
    private String nombre_clasificacion;

    public String getCod_clasificacion() {
        return cod_clasificacion;
    }

    public void setCod_clasificacion(String cod_clasificacion) {
        this.cod_clasificacion = cod_clasificacion;
    }

    public String getNombre_clasificacion() {
        return nombre_clasificacion;
    }

    public void setNombre_clasificacion(String nombre_clasificacion) {
        this.nombre_clasificacion = nombre_clasificacion;
    }

    public Clasificacion() {
    }

    public Clasificacion(String cod_clasificacion, String nombre_clasificacion) {
        this.cod_clasificacion = cod_clasificacion;
        this.nombre_clasificacion = nombre_clasificacion;
    }
    
    
}
