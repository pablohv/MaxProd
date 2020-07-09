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
public class Reparacion {
    
    private int folio;
    private String tipoEquipo;
    private String marca;
    private String nombre;
    private String inicioReparacion;
    private String diagnostico;
    private String finReparacion;

    public Reparacion(int folio, String tipoEquipo, String marca, String nombre, String inicioReparacion, String diagnostico, String finReparacion) {
        this.folio = folio;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.nombre = nombre;
        this.inicioReparacion = inicioReparacion;
        this.diagnostico = diagnostico;
        this.finReparacion = finReparacion;
    }

    public Reparacion(int folio, String tipoEquipo, String marca, String nombre, String inicioReparacion, String diagnostico) {
        this.folio = folio;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.nombre = nombre;
        this.inicioReparacion = inicioReparacion;
        this.diagnostico = diagnostico;
    }
    
    

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInicioReparacion() {
        return inicioReparacion;
    }

    public void setInicioReparacion(String inicioReparacion) {
        this.inicioReparacion = inicioReparacion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getFinReparacion() {
        return finReparacion;
    }

    public void setFinReparacion(String finReparacion) {
        this.finReparacion = finReparacion;
    }
    
    
    
}
