/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author louis
 */
public class Habitacion {
    private long id;
    private int cantidad_de_camas;
    private float precio_por_hora;
    

    public Habitacion() {
    }

    public Habitacion(int cantidad_de_camas, float precio_por_hora) {
        this.cantidad_de_camas = cantidad_de_camas;
        this.precio_por_hora = precio_por_hora;
    }

    public Habitacion(long id, int cantidad_de_camas, float precio_por_hora) {
        this.id = id;
        this.cantidad_de_camas = cantidad_de_camas;
        this.precio_por_hora = precio_por_hora;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
