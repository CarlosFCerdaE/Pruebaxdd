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
public class Estudiante extends Persona {
    private String cif;
    private ArrayList<Carrera> carreras;

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(ArrayList<Carrera> carreras) {
        this.carreras = carreras;
    }
       public Estudiante() {
    }

    public Estudiante(String cif, ArrayList<Carrera> carreras, String id_pers, String nombre_pers, String apellidos_pers, String telefono_pers) {
        super(id_pers, nombre_pers, apellidos_pers, telefono_pers);
        this.cif = cif;
        this.carreras = carreras;
    }

   
    
} 