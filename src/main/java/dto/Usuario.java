package dto;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String cargo;
    private String contra;

    public Usuario() {
    }

    public Usuario(String nombre, String contra) {
        this.nombre = nombre;
        this.contra = contra;
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

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

}
