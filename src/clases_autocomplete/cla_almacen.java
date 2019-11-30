/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases_autocomplete;

/**
 *
 * @author User
 */
public class cla_almacen {

    private int id_almacen;
    private String nombre;

    public cla_almacen(int id_almacen, String nombre) {
        this.id_almacen = id_almacen;
        this.nombre = nombre;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
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
