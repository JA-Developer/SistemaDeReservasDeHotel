/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Date;

/**
 *
 * @author louis
 */
public class PagoDTO {
    private long id;
    private long reserva_id;
    private String rtn;
    private String no_factura;
    private Date fecha;
    private float monto;

    public PagoDTO(long id, long reserva_id, String rtn, String no_factura, Date fecha, float monto) {
        this.id = id;
        this.reserva_id = reserva_id;
        this.rtn = rtn;
        this.no_factura = no_factura;
        this.fecha = fecha;
        this.monto = monto;
    }    

    public long getReservaId() {
        return reserva_id;
    }

    public void setReservaId(long reserva_id) {
        this.reserva_id = reserva_id;
    }

    public String getRtn() {
        return rtn;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
    }

    public String getNoFactura() {
        return no_factura;
    }

    public void setNoFactura(String no_factura) {
        this.no_factura = no_factura;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public long getId() {
        return id;
    }
}
