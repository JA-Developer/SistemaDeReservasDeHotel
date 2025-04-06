/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author louis
 */
public class HabitacionDTO {
    private long id;
    private int cantidad_de_camas;
    private float precio_por_hora;

    public HabitacionDTO(long id, int cantidad_de_camas, float precio_por_hora) {
        this.id = id;
        this.cantidad_de_camas = cantidad_de_camas;
        this.precio_por_hora = precio_por_hora;
    }    

    public int getCantidadDeCamas() {
        return cantidad_de_camas;
    }

    public void setCantidadDeCamas(int cantidad_de_camas) {
        this.cantidad_de_camas = cantidad_de_camas;
    }

    public float getPrecioPorHora() {
        return precio_por_hora;
    }

    public void setPrecioPorHora(float precio_por_hora) {
        this.precio_por_hora = precio_por_hora;
    }
    
    public long getId() {
        return id;
    }
}
