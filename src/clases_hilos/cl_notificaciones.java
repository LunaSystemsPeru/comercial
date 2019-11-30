/*
 * Copyright (c) 2019, luis
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package clases_hilos;

import clases.cl_traslados;
import nicon.notify.core.Notification;
import comercial.frm_principal;

/**
 *
 * @author luis
 */
public class cl_notificaciones {

    cl_traslados c_traslado = new cl_traslados();
    int id_almacen;

    public cl_notificaciones() {
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }
    
    
    private void mostrar_traslados() {
        c_traslado.setId_tienda_recibe(id_almacen);
        int cantidad = c_traslado.obtener_traslados_pendientes();
        frm_principal.lbl_traslados_encontrados.setText(cantidad + "");
        if (cantidad > 0) {
        Notification.show("Traslados", "Tienes " + cantidad + " Traslados Pendientes!!", Notification.CONFIRM_MESSAGE,Notification.NICON_LIGHT_THEME);
        }
    }

    public void mostrar() {
        mostrar_traslados();
    }
}
