/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Timestamp;

/**
 *
 * @author louis
 */
public class ReservaDTO {
    private long id;
    private Timestamp fechaDeInicio;
    private int horas;
    private long habitacionId;
    private float habitacionPrecioHora;
    private long huespedId;
    private String huespedNombre;

    public ReservaDTO(long id, Timestamp fechaDeInicio, int horas, long habitacionId, float habitacionPrecioHora, long huespedId, String huespedNombre) {
        this.id = id;
        this.fechaDeInicio = fechaDeInicio;
        this.horas = horas;
        this.habitacionId = habitacionId;
        this.habitacionPrecioHora = habitacionPrecioHora;
        this.huespedId = huespedId;
        this.huespedNombre = huespedNombre;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public Timestamp getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(Timestamp fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public long getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(long habitacionId) {
        this.habitacionId = habitacionId;
    }

    public float getHabitacionPrecioHora() {
        return habitacionPrecioHora;
    }

    public void setHabitacionPrecioHora(float habitacionPrecioHora) {
        this.habitacionPrecioHora = habitacionPrecioHora;
    }
    
    public long getHuespedId() {
        return huespedId;
    }

    public void setHuespedId(long huespedId) {
        this.huespedId = huespedId;
    }

    public String getHuespedNombre() {
        return huespedNombre;
    }

    public void setHuespedNombre(String huespedNombre) {
        this.huespedNombre = huespedNombre;
    }
}
