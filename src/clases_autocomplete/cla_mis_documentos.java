/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases_autocomplete;

/**
 *
 * @author luis
 */
public class cla_mis_documentos {
    
    private int id_tido;
    private String nombre;

    public cla_mis_documentos(int id_tido, String nombre) {
        this.id_tido = id_tido;
        this.nombre = nombre;
    }

    
    public int getId_tido() {
        return id_tido;
    }

    public void setId_tido(int id_tido) {
        this.id_tido = id_tido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
