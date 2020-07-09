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
public class Prueba {

    private int folio;
    private String tipoEquipo;
    private String marca;
    private String nombre;
    private String inicioPruebas;
    private String puebas;
    private String finPruebas;

    public Prueba(int folio, String tipoEquipo, String marca, String nombre, String inicioPruebas, String puebas, String finPruebas) {
        this.folio = folio;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.nombre = nombre;
        this.inicioPruebas = inicioPruebas;
        this.puebas = puebas;
        this.finPruebas = finPruebas;
    }

    public Prueba(int folio, String tipoEquipo, String marca, String nombre, String inicioPruebas, String puebas) {
        this.folio = folio;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.nombre = nombre;
        this.inicioPruebas = inicioPruebas;
        this.puebas = puebas;
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

    public String getInicioPruebas() {
        return inicioPruebas;
    }

    public void setInicioPruebas(String inicioPruebas) {
        this.inicioPruebas = inicioPruebas;
    }

    public String getPuebas() {
        return puebas;
    }

    public void setPuebas(String puebas) {
        this.puebas = puebas;
    }

    public String getFinPruebas() {
        return finPruebas;
    }

    public void setFinPruebas(String finPruebas) {
        this.finPruebas = finPruebas;
    }

}
