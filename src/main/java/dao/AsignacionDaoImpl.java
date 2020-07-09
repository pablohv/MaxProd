package dao;

import JDBC.Conexion;
import dto.Asignados;
import dto.Equipo;
import dto.Tecnico;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AsignacionDaoImpl {

    private final String BUSCAR_IDTECNICO = "select idUsuario from usuario where Nombre =? and cargo = 'T'";
    private final String SQL_ASIGNAR = "INSERT INTO asignacion (Usuario_idUsuario, Equipo_idEquipo, inicio_diagnostico) VALUES (?, ?, ?)";
    private final String SQL_LISTARTECNICO = "SELECT idUsuario, Nombre FROM usuario WHERE cargo = 'T'";
    private final String SQL_LISTAREQUIPO = "select asignacion.Equipo_idEquipo, Tipo_equipo, Marca, Modelo, No_serie, Accesorios, Falla_cliente, asignacion.Usuario_idUsuario, Nombre from asignacion inner join equipo on equipo.idEquipo = asignacion.Equipo_idEquipo inner join usuario on usuario.idUsuario = asignacion.Usuario_idUsuario order by inicio_diagnostico desc";
    //aqui se a;adio el order by
    public List<Tecnico> listarTecnicos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Tecnico> tecnicos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_LISTARTECNICO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idUsuario");
                String nombre = rs.getString("Nombre");

                Tecnico tecnico = new Tecnico(id, nombre);
                tecnicos.add(tecnico);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tecnicos;
    }

    public List<Asignados> listarEquipos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Asignados> asignados = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_LISTAREQUIPO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int folio = rs.getInt("Equipo_idEquipo");
                String tipoEquipo = rs.getString("Tipo_equipo");
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
                int noSerie = rs.getInt("No_serie");
                String accesorios = rs.getString("Accesorios");
                String falla = rs.getString("Falla_cliente");
                String nombre = rs.getString("Nombre");

                Asignados asignado = new Asignados(folio, tipoEquipo, marca, modelo, noSerie, accesorios, falla, nombre);
                asignados.add(asignado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return asignados;
    }

    public int buscarTecnico(String nombre) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int mivalor = 0;

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(BUSCAR_IDTECNICO);
            // ejecutamos el comando 
            stmt.setString(1, nombre);
            //recuperamos el valor
            rs = stmt.executeQuery();

            while (rs.next()) {
                mivalor = rs.getInt("idUsuario");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return mivalor;
    }

    public void asignarTecnico(int idTecnico, int folio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String fecha = "";

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ASIGNAR);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            java.util.Date date = new java.util.Date();
            fecha = dateFormat.format(date);

            stmt.setInt(1, idTecnico);
            stmt.setInt(2, folio);
            stmt.setString(3, fecha);

            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

}
