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
public class Diagnostico {
    
    private int folio;
    private String tipoEquipo;
    private String marca;
    private String falla;
    private String nombre;
    private String inicioDiagnostico;
    private String diagnostico;
    private String finDiagnostico;

    public Diagnostico(int folio, String tipoEquipo, String marca, String falla, String nombre, String inicioDiagnostico) {
        this.folio = folio;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.falla = falla;
        this.nombre = nombre;
        this.inicioDiagnostico = inicioDiagnostico;
    }

    public Diagnostico(int folio, String tipoEquipo, String marca, String falla, String nombre, String inicioDiagnostico, String diagnostico, String finDiagnostico) {
        this.folio = folio;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.falla = falla;
        this.nombre = nombre;
        this.inicioDiagnostico = inicioDiagnostico;
        this.diagnostico = diagnostico;
        this.finDiagnostico = finDiagnostico;
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

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInicioDiagnostico() {
        return inicioDiagnostico;
    }

    public void setInicioDiagnostico(String inicioDiagnostico) {
        this.inicioDiagnostico = inicioDiagnostico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getFinDiagnostico() {
        return finDiagnostico;
    }

    public void setFinDiagnostico(String finDiagnostico) {
        this.finDiagnostico = finDiagnostico;
    }
    
    
    
}
