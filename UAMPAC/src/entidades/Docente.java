/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author cfco5
 */
public class Docente extends Persona {
    private String id_docente;

    public String getId_docente() {
        return id_docente;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }

    public Docente() {
    }

    public Docente(String id_docente, String id_pers, String nombre_pers, String apellidos_pers, String telefono_pers) {
        super(id_pers, nombre_pers, apellidos_pers, telefono_pers);
        this.id_docente = id_docente;
    }
    
}
