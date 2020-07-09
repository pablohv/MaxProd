package dao;

import JDBC.Conexion;
import dto.Tecnico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Reincidencia {

    public static final String OBTENER_REINCIDENCIA = "SELECT reincidencia from asignacion where Equipo_idEquipo = ?";
    public static final String REINICIAR_TIEMPOS = "update asignacion set inicio_diagnostico = ?, fin_diagnostico = NULL, inicio_reparacion = NULL, fin_reparacion = NULL, inicio_pruebas = NULL, fin_pruebas = NULL where Equipo_idEquipo = ?";
    public static final String VERIFICAR = "SELECT Equipo_idEquipo from asignacion where Equipo_idEquipo = ?";
    public static final String ACUALIZAR_REINCIDENCIA = "update asignacion set reincidencia = ? where Equipo_idEquipo = ?";

    public int verificar(int folio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int resultado = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(VERIFICAR);
            stmt.setInt(1, folio);
            rs = stmt.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt("Equipo_idEquipo");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return resultado;
    }

    public void reincidencia(int folio) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String fecha = "";

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(REINICIAR_TIEMPOS);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            java.util.Date date = new java.util.Date();
            fecha = dateFormat.format(date);

            stmt.setString(1, fecha);
            stmt.setInt(2, folio);
            stmt.execute();

            stmt = conn.prepareStatement(OBTENER_REINCIDENCIA);
            stmt.setInt(1, folio);
            rs = stmt.executeQuery();

            int reincidencia = 0;

            while (rs.next()) {
                reincidencia = rs.getInt("reincidencia");
            }

            reincidencia++;

            stmt = conn.prepareStatement(ACUALIZAR_REINCIDENCIA);
            stmt.setInt(1, reincidencia);
            stmt.setInt(2, folio);
            stmt.execute();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

    }

}
