package dao;

import JDBC.Conexion;
import dto.Diagnostico;
import dto.Prueba;
import dto.Reparacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstatusDao {

    private final String SQL_ENDIAGNOSTIGO = "select asignacion.Equipo_idEquipo, Tipo_equipo, Marca, Modelo, No_serie, Falla_cliente, asignacion.Usuario_idUsuario, Nombre, inicio_diagnostico FROM asignacion INNER JOIN equipo ON equipo.idEquipo = asignacion.Equipo_idEquipo INNER JOIN usuario ON usuario.idUsuario = asignacion.Usuario_idUsuario where inicio_diagnostico is not null and fin_diagnostico is null";
    private final String SQL_ENREPARACION = "select asignacion.Equipo_idEquipo, Tipo_equipo, Marca, Modelo, No_serie, Falla_cliente, asignacion.Usuario_idUsuario, Nombre, inicio_reparacion,  diagnostico FROM asignacion INNER JOIN equipo ON equipo.idEquipo = asignacion.Equipo_idEquipo INNER JOIN usuario ON usuario.idUsuario = asignacion.Usuario_idUsuario where inicio_reparacion is not null and fin_reparacion is null";
    private final String SQL_ENPRUEBA = "select asignacion.Equipo_idEquipo, Tipo_equipo, Marca, Modelo, No_serie, Falla_cliente, asignacion.Usuario_idUsuario, Nombre, inicio_pruebas,pruebas FROM asignacion INNER JOIN equipo ON equipo.idEquipo = asignacion.Equipo_idEquipo INNER JOIN usuario ON usuario.idUsuario = asignacion.Usuario_idUsuario where inicio_pruebas is not null and fin_pruebas is null";

    
    
    public List<Diagnostico> equipoEnDiagnostico() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Diagnostico> estatusLis = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENDIAGNOSTIGO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int folio = rs.getInt("Equipo_idEquipo");
                String tipoEquipo = rs.getString("Tipo_equipo");
                String marca = rs.getString("Marca");
                String falla = rs.getString("Falla_cliente");
                String nombre = rs.getString("Nombre");
                String iniDiagnostico = rs.getString("inicio_diagnostico");

                Diagnostico estatu = new Diagnostico(folio, tipoEquipo, marca, falla, nombre, iniDiagnostico);
                estatusLis.add(estatu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return estatusLis;
    }

    public List<Reparacion> equipoEnReparacion() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Reparacion> estatusLis = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENREPARACION);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int folio = rs.getInt("Equipo_idEquipo");
                String tipoEquipo = rs.getString("Tipo_equipo");
                String marca = rs.getString("Marca");
                String nombre = rs.getString("Nombre");
                String inicioRep = rs.getString("inicio_reparacion");
                String diag = rs.getString("diagnostico");

                Reparacion estatu = new Reparacion(folio, tipoEquipo, marca, nombre, inicioRep, diag);
                estatusLis.add(estatu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return estatusLis;
    }

    public List<Prueba> equipoEnPrueba() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Prueba> estatusLis = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_ENPRUEBA);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int folio = rs.getInt("Equipo_idEquipo");
                String tipoEquipo = rs.getString("Tipo_equipo");
                String marca = rs.getString("Marca");
                String nombre = rs.getString("Nombre");
                String inicioPruebas = rs.getString("inicio_pruebas");
                String pruebas = rs.getString("pruebas");

                Prueba estatu = new Prueba(folio, tipoEquipo, marca, nombre, inicioPruebas, pruebas);
                estatusLis.add(estatu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return estatusLis;
    }
    
    
}
