/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author acer
 */
public class Tecnico {

    private int idUsuario;
    private String nombre;
    private String cargo;

    public Tecnico() {
    }

    public Tecnico(int idUsuario, String nombre, String cargo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.cargo = cargo;
    }

    public Tecnico(int idUsuario, String nombre) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Tecnico{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", cargo=" + cargo + '}';
    }

}
