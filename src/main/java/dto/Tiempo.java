package dto;

import Vistas.Administrador;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tiempo {

    private int folio;
    private String tipoEquipo;
    private String marca;
    private String modelo;
    private String falla;
    private String tecnico;
    private String reparacion;
    private int reincidencia;
    private String inicioDiag;
    private String finDiag;
    private String inicioRep;
    private String finRep;
    private String inicioPru;
    private String finPru;

    public Tiempo(int folio, String tipoEquipo, String marca, String falla, String tecnico, String reparacion, int reincidencia, String inicioDiag, String finDiag, String inicioRep, String finRep, String inicioPru, String finPru) {
        this.folio = folio;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.falla = falla;
        this.tecnico = tecnico;
        this.reparacion = reparacion;
        this.reincidencia = reincidencia;
        this.inicioDiag = inicioDiag;
        this.finDiag = finDiag;
        this.inicioRep = inicioRep;
        this.finRep = finRep;
        this.inicioPru = inicioPru;
        this.finPru = finPru;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getInicioDiag() {
        return inicioDiag;
    }

    public void setInicioDiag(String inicioDiag) {
        this.inicioDiag = inicioDiag;
    }

    public String getFinDiag() {
        return finDiag;
    }

    public void setFinDiag(String finDiag) {
        this.finDiag = finDiag;
    }

    public String getInicioRep() {
        return inicioRep;
    }

    public void setInicioRep(String inicioRep) {
        this.inicioRep = inicioRep;
    }

    public String getFinRep() {
        return finRep;
    }

    public void setFinRep(String finRep) {
        this.finRep = finRep;
    }

    public String getInicioPru() {
        return inicioPru;
    }

    public void setInicioPru(String inicioPru) {
        this.inicioPru = inicioPru;
    }

    public String getFinPru() {
        return finPru;
    }

    public void setFinPru(String finPru) {
        this.finPru = finPru;
    }

    public String getReparacion() {
        return reparacion;
    }

    public void setReparacion(String reparacion) {
        this.reparacion = reparacion;
    }

    public int getReincidencia() {
        return reincidencia;
    }

    public void setReincidencia(int reincidencia) {
        this.reincidencia = reincidencia;
    }

    @Override
    public String toString() {
        return "Tiempo{" + "folio=" + folio + ", tipoEquipo=" + tipoEquipo + ", marca=" + marca + ", modelo=" + modelo + ", falla=" + falla + ", tecnico=" + tecnico + ", reparacion=" + reparacion + ", reincidencia=" + reincidencia + ", inicioDiag=" + inicioDiag + ", finDiag=" + finDiag + ", inicioRep=" + inicioRep + ", finRep=" + finRep + ", inicioPru=" + inicioPru + ", finPru=" + finPru + '}';
    }

}
