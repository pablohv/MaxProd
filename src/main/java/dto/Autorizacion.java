package dto;

public class Autorizacion {

    private int folio;
    private String nombre;
    private String apellido;
    private String telefono;
    private String marca;
    private String modelo;
    private String falla;
    private String diagnostico;

    public Autorizacion(int folio, String nombre, String apellido, String telefono, String marca, String modelo, String falla, String diagnostico) {
        this.folio = folio;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.marca = marca;
        this.modelo = modelo;
        this.falla = falla;
        this.diagnostico = diagnostico;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

}
