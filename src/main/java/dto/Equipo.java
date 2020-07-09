package dto;

public class Equipo {

    private int folio;
    private String nombreCliente;
    private String apellidoCliente;
    private String telefono;
    private String direccion;
    private String tipoEquipo;
    private String marca;
    private String modelo;
    private int noSerie;
    private String falla;
    private String accesorios;

    public Equipo() {
    }

    //alta del equipo y cliente
    public Equipo(int folio, String nombreCliente, String apellidoCliente, String telefono, String direccion, String tipoEquipo, String marca, String modelo, int noSerie, String falla, String accesorios) {
        this.folio = folio;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.modelo = modelo;
        this.noSerie = noSerie;
        this.falla = falla;
        this.accesorios = accesorios;
    }

    //obtener los valores
    public Equipo(int folio, String tipoEquipo, String marca, String modelo, int noSerie, String falla, String accesorios) {
        this.folio = folio;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.modelo = modelo;
        this.noSerie = noSerie;
        this.falla = falla;
        this.accesorios = accesorios;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    @Override
    public String toString() {
        return "Equipo{" + "folio=" + folio + ", nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente + ", telefono=" + telefono + ", direccion=" + direccion + ", tipoEquipo=" + tipoEquipo + ", marca=" + marca + ", modelo=" + modelo + ", noSerie=" + noSerie + ", falla=" + falla + ", accesorios=" + accesorios + '}';
    }

}
