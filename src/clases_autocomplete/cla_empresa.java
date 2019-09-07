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
public class cla_empresa {
    
    private int id_empresa;
    private String ruc;
    private String razon_social;

    public cla_empresa(int id_empresa, String ruc, String razon_social) {
        this.id_empresa = id_empresa;
        this.ruc = ruc;
        this.razon_social = razon_social;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public String getRuc() {
        return ruc;
    }

    public String getRazon_social() {
        return razon_social;
    }

    @Override
    public String toString() {
        return ruc + " | " + razon_social;
    }
}
