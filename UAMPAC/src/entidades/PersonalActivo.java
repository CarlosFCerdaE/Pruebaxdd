/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author cfco5
 */
public class PersonalActivo extends Persona {
    private String id_PersonalActivo;

    public String getId_PersonalActivo() {
        return id_PersonalActivo;
    }

    public void setId_PersonalActivo(String id_PersonalActivo) {
        this.id_PersonalActivo = id_PersonalActivo;
    }

    public PersonalActivo() {
    }

    public PersonalActivo(String id_PersonalActivo, String id_pers, String nombre_pers, String apellidos_pers, String telefono_pers) {
        super(id_pers, nombre_pers, apellidos_pers, telefono_pers);
        this.id_PersonalActivo = id_PersonalActivo;
    }
    
}
