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
package clases;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author luis
 */
public class cl_reportes_graficas {
    
    cl_conectar c_conectar = new cl_conectar();
    
    private int id_almacen;

    public cl_reportes_graficas() {
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }
    
    public ResultSet ver_ventas_ayer() {
        Statement st = c_conectar.conexion();
        String query = "select  a.nombre, (select max(vc.fecha) from ventas_cobros as vc where vc.fecha != CURRENT_DATE()) as fecha, ifnull(sum(vc.monto), 0) as monto "
                + "from almacen as a "
                + "LEFT join ventas_cobros as vc on vc.id_almacen = a.id_almacen and vc.fecha = (select max(vc.fecha) from ventas_cobros as vc where vc.fecha != CURRENT_DATE()) "
                + "where a.id_almacen != 1 "
                + "group by a.id_almacen ";
        System.out.println(query);
        ResultSet rs = c_conectar.consulta(st, query);
        return rs;
    }
    
    public ResultSet ver_ventas_hoy() {
        Statement st = c_conectar.conexion();
        String query = "select  a.nombre, CURRENT_DATE() as fecha, ifnull(sum(vc.monto), 0) as monto "
                + "from almacen as a "
                + "LEFT join ventas_cobros as vc on vc.id_almacen = a.id_almacen and vc.fecha = CURRENT_DATE() "
                + "where a.id_almacen != 1 "
                + "group by a.id_almacen";
        System.out.println(query);
        ResultSet rs = c_conectar.consulta(st, query);
       // c_conectar.cerrar(rs);
      //  c_conectar.cerrar(st);
        return rs;
    }

    public ResultSet ver_ventas_cobros() {
        Statement st = c_conectar.conexion();
        String query = "select date_format(v.fecha, '%m-%d') as dia, sum(v.total) as total_venta, sum(vc.monto) as total_ingreso "
                + "from ventas as v "
                + "inner join ventas_cobros as vc on vc.id_ventas = v.id_ventas and vc.id_almacen = v.id_almacen and vc.fecha = v.fecha "
                + "where v.id_almacen = '" + this.id_almacen + "' and v.fecha <= CURRENT_DATE() "
                + "group by v.fecha "
                + "order by v.fecha desc "
                + "limit 7";
        ResultSet rs = c_conectar.consulta(st, query);
       // c_conectar.cerrar(rs);
       // c_conectar.cerrar(st);
        return rs;
    }

    public ResultSet ver_cobros_mensuales() {
        Statement st = c_conectar.conexion();
        String query = "select m.nombre, ifnull(sum(vc.monto), 0) as monto "
                + "from mes as m left join ventas_cobros as vc on month(vc.fecha) = m.id and "
                + "year(vc.fecha) = year(CURRENT_DATE()) and vc.id_almacen = '" + this.id_almacen + "'  "
                + "group by m.id "
                + "order by m.id asc";
        ResultSet rs = c_conectar.consulta(st, query);
      //  c_conectar.cerrar(rs);
       // c_conectar.cerrar(st);
        return rs;
    }

    public ResultSet ver_cobros_anuales() {
        Statement st = c_conectar.conexion();
        String query = "select year(vc.fecha) as anio, sum(vc.monto) as monto "
                + "from ventas_cobros as vc "
                + "where vc.id_almacen = '" + this.id_almacen + "' "
                + "group by year(vc.fecha) "
                + "order by year(vc.fecha) asc";
        ResultSet rs = c_conectar.consulta(st, query);
      //  c_conectar.cerrar(rs);
       // c_conectar.cerrar(st);
        return rs;
    }
    
    public ResultSet ver_ingresos_tienda_mes() {
        Statement st = c_conectar.conexion();
        String query = "select a.nombre as tienda, sum(vc.monto) as monto "
                + "from ventas_cobros as vc "
                + "inner join almacen as a on a.id_almacen = vc.id_almacen "
                + "where month(vc.fecha) = month(CURRENT_DATE()) and year(vc.fecha) = year(CURRENT_DATE()) "
                + "group by a.id_almacen "
                + "order by a.nombre asc";
        ResultSet rs = c_conectar.consulta(st, query);
      //  c_conectar.cerrar(rs);
       // c_conectar.cerrar(st);
        return rs;
    }
    
    public ResultSet ver_ingresos_tienda_anio() {
        Statement st = c_conectar.conexion();
        String query = "select a.nombre as tienda, sum(vc.monto) as monto "
                + "from ventas_cobros as vc "
                + "inner join almacen as a on a.id_almacen = vc.id_almacen "
                + "where year(vc.fecha) = year(CURRENT_DATE()) "
                + "group by a.id_almacen "
                + "order by a.nombre asc";
        ResultSet rs = c_conectar.consulta(st, query);
      //  c_conectar.cerrar(rs);
       // c_conectar.cerrar(st);
        return rs;
    }
}
