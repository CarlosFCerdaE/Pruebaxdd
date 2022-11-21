/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author cfco5
 */
public class Editorial {
    private String cod_editorial;
    private String nombre_editorial;

    public String getCod_editorial() {
        return cod_editorial;
    }

    public void setCod_editorial(String cod_editorial) {
        this.cod_editorial = cod_editorial;
    }

    public String getNombre_editorial() {
        return nombre_editorial;
    }

    public void setNombre_editorial(String nombre_editorial) {
        this.nombre_editorial = nombre_editorial;
    }

    public Editorial() {
    }

    public Editorial(String cod_editorial, String nombre_editorial) {
        this.cod_editorial = cod_editorial;
        this.nombre_editorial = nombre_editorial;
    }
    
    
}
