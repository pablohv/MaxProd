package dto;

public class Asignados {

    private int folio;
    private String tipoEquipo;
    private String marca;
    private String modelo;
    private int noSerie;
    private String accesorios;
    private String falla;
    private String nombre;

    public Asignados(int folio, String tipoEquipo, String marca, String modelo, int noSerie, String accesorios, String falla, String nombre) {
        this.folio = folio;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.modelo = modelo;
        this.noSerie = noSerie;
        this.accesorios = accesorios;
        this.falla = falla;
        this.nombre = nombre;
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

    public int getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(int noSerie) {
        this.noSerie = noSerie;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public String getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(String accesorios) {
        this.accesorios = accesorios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Asignados{" + "folio=" + folio + ", tipoEquipo=" + tipoEquipo + ", marca=" + marca + ", modelo=" + modelo + ", noSerie=" + noSerie + ", falla=" + falla + ", accesorios=" + accesorios + ", nombre=" + nombre + '}';
    }

}
