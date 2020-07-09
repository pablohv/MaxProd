package dao;

import JDBC.Conexion;
import dto.Tiempo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TiempoDao {

    //select Equipo_idEquipo, Tipo_equipo, Marca, Modelo, Falla_cliente, asignacion.Usuario_idUsuario, Nombre, inicio_diagnostico, fin_diagnostico, inicio_reparacion, fin_reparacion, inicio_pruebas, fin_pruebas FROM asignacion INNER JOIN equipo ON equipo.idEquipo = asignacion.Equipo_idEquipo INNER JOIN usuario ON usuario.idUsuario = asignacion.Usuario_idUsuario where year(inicio_diagnostico) = ? and month(inicio_diagnostico) = ? and fin_diagnostico is not null order by inicio_diagnostico desc"
    private final String SQL_TIEMPO = "select Equipo_idEquipo, Tipo_equipo, Marca, Falla_cliente, asignacion.Usuario_idUsuario, Nombre, reparacion, reincidencia, inicio_diagnostico, fin_diagnostico, inicio_reparacion, fin_reparacion, inicio_pruebas, fin_pruebas FROM asignacion INNER JOIN equipo ON equipo.idEquipo = asignacion.Equipo_idEquipo INNER JOIN usuario ON usuario.idUsuario = asignacion.Usuario_idUsuario where year(inicio_diagnostico) = ? and month(inicio_diagnostico) = ? order by inicio_diagnostico desc";
    private final String SQL_INTERVALO = "select Equipo_idEquipo, Tipo_equipo, Marca, Falla_cliente, asignacion.Usuario_idUsuario, Nombre, reparacion, reincidencia, inicio_diagnostico, fin_diagnostico, inicio_reparacion, fin_reparacion, inicio_pruebas, fin_pruebas FROM asignacion INNER JOIN equipo ON equipo.idEquipo = asignacion.Equipo_idEquipo INNER JOIN usuario ON usuario.idUsuario = asignacion.Usuario_idUsuario where inicio_diagnostico between ? and ? order by inicio_diagnostico desc";

    public List<Tiempo> porMes(int anio, int mes) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Tiempo> tiempos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_TIEMPO);

            stmt.setInt(1, anio);
            stmt.setInt(2, mes);

            rs = stmt.executeQuery();

            while (rs.next()) {

                int folio = rs.getInt("Equipo_idEquipo");
                String tipoEquipo = rs.getString("Tipo_equipo");
                String marca = rs.getString("Marca");
                String falla = rs.getString("Falla_cliente");
                String tecnico = rs.getString("Nombre");
                String inicioDiag = rs.getString("inicio_diagnostico");
                String finDiag = rs.getString("fin_diagnostico");
                String inicioRep = rs.getString("inicio_reparacion");
                String finRep = rs.getString("fin_reparacion");
                String inicioPru = rs.getString("inicio_pruebas");
                String finPru = rs.getString("fin_pruebas");
                String reparacion = rs.getString("reparacion");
                int reincidencia = rs.getInt("reincidencia");

                Tiempo tiempo = new Tiempo(folio, tipoEquipo, marca, falla, tecnico, reparacion, reincidencia, inicioDiag, finDiag, inicioRep, finRep, inicioPru, finPru);
                tiempos.add(tiempo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tiempos;
    }

    public List<Tiempo> porRango(String inicio, String fin) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Tiempo> tiempos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INTERVALO);

            stmt.setString(1, inicio + " 00:00:00");
            stmt.setString(2, fin + " 23:59:59");

            rs = stmt.executeQuery();

            while (rs.next()) {

                int folio = rs.getInt("Equipo_idEquipo");
                String tipoEquipo = rs.getString("Tipo_equipo");
                String marca = rs.getString("Marca");
                String falla = rs.getString("Falla_cliente");
                String tecnico = rs.getString("Nombre");
                String inicioDiag = rs.getString("inicio_diagnostico");
                String finDiag = rs.getString("fin_diagnostico");
                String inicioRep = rs.getString("inicio_reparacion");
                String finRep = rs.getString("fin_reparacion");
                String inicioPru = rs.getString("inicio_pruebas");
                String finPru = rs.getString("fin_pruebas");
                String reparacion = rs.getString("reparacion");
                int reincidencia = rs.getInt("reincidencia");

                Tiempo tiempo = new Tiempo(folio, tipoEquipo, marca, falla, tecnico, reparacion, reincidencia, inicioDiag, finDiag, inicioRep, finRep, inicioPru, finPru);
                tiempos.add(tiempo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tiempos;
    }

}
