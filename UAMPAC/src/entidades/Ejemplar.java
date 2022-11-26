/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;

/**
 *
 * @author cfco5
 */
public class Ejemplar extends Libro {
    private String cod_inventario;
    private boolean estado;
    private int num_copia;
    private Ubicacion ubicacion;

    public String getCod_inventario() {
        return cod_inventario;
    }

    public void setCod_inventario(String cod_inventario) {
        this.cod_inventario = cod_inventario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getNum_copia() {
        return num_copia;
    }

    public void setNum_copia(int num_copia) {
        this.num_copia = num_copia;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Ejemplar() {
    }

    public Ejemplar(String cod_inventario, boolean estado, int num_copia, Ubicacion ubicacion, String isbn, String titulo_libro, String mfn, Clasificacion clasificacion, Editorial editorial, ArrayList<Autor> autores) {
        super(isbn, titulo_libro, mfn, clasificacion, editorial, autores);
        this.cod_inventario = cod_inventario;
        this.estado = estado;
        this.num_copia = num_copia;
        this.ubicacion = ubicacion;
    }

   

    


    
    
}
