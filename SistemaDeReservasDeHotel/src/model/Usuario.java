/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author louis
 */
public class Usuario {
    private long id;
    private String username;
    private String password_hash;
    private String rol;
    

    public Usuario() {
    }

    public Usuario(String username, String password_hash, String rol) {
        this.username = username;
        this.password_hash = password_hash;
        this.rol = rol;
    }

    public Usuario(long id, String username, String password_hash, String rol) {
        this.id = id;
        this.username = username;
        this.password_hash = password_hash;
        this.rol = rol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String rtn) {
        this.username = rtn;
    }

    public String getPasswordHash() {
        return password_hash;
    }

    public void setPasswordHash(String no_factura) {
        this.password_hash = no_factura;
    }
    
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
