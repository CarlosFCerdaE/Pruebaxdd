/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author cfco5
 */
public class Persona {
    private String id_pers;
    private String nombre_pers;
    private String apellidos_pers;
    private String telefono_pers;

    public String getId_pers() {
        return id_pers;
    }

    public void setId_pers(String id_pers) {
        this.id_pers = id_pers;
    }

    public String getNombre_pers() {
        return nombre_pers;
    }

    public void setNombre_pers(String nombre_pers) {
        this.nombre_pers = nombre_pers;
    }

    public String getApellidos_pers() {
        return apellidos_pers;
    }

    public void setApellidos_pers(String apellidos_pers) {
        this.apellidos_pers = apellidos_pers;
    }

    public String getTelefono_pers() {
        return telefono_pers;
    }

    public void setTelefono_pers(String telefono_pers) {
        this.telefono_pers = telefono_pers;
    }

    public Persona() {
    }

    public Persona(String id_pers, String nombre_pers, String apellidos_pers, String telefono_pers) {
        this.id_pers = id_pers;
        this.nombre_pers = nombre_pers;
        this.apellidos_pers = apellidos_pers;
        this.telefono_pers = telefono_pers;
    }
    
    
}
