/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author louis
 */
import java.sql.Timestamp;

public class Reserva {
    private long id;
    private Timestamp fechaDeInicio;
    private int horas;
    private long habitacionId;
    private long huespedId;
    
    public Reserva() {
    }
    
    public Reserva(Timestamp fechaDeInicio, int horas, long habitacionId, long huespedId) {
        this.fechaDeInicio = fechaDeInicio;
        this.horas = horas;
        this.habitacionId = habitacionId;
        this.huespedId = huespedId;
    }
    
    public Reserva(long id, Timestamp fechaDeInicio, int horas, long habitacionId, long huespedId) {
        this.id = id;
        this.fechaDeInicio = fechaDeInicio;
        this.horas = horas;
        this.habitacionId = habitacionId;
        this.huespedId = huespedId;
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

    public long getHuespedId() {
        return huespedId;
    }

    public void setHuespedId(long huespedId) {
        this.huespedId = huespedId;
    }
}
