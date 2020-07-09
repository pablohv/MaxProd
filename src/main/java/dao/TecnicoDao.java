/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import JDBC.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author acer
 */
public class TecnicoDao {

    private final String SQL_FINALIZAR_DIAGNOSTICO = "update asignacion set fin_diagnostico = ?, diagnostico = ?, pruebas = ? where Equipo_idEquipo = ?";
    private final String SQL_FINALIZAR_PRUEBA = "UPDATE asignacion set fin_pruebas = ? where Equipo_idEquipo = ?";
    private final String SQL_FINALIZAR_REPARACION = "UPDATE asignacion set fin_reparacion = ?, inicio_pruebas = ? where Equipo_idEquipo = ?";

    public void finalizarDiagnostico(int folio, String diagnostico, String pruebas) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String fecha = "";

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_FINALIZAR_DIAGNOSTICO);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            java.util.Date date = new java.util.Date();
            fecha = dateFormat.format(date);

            stmt.setString(1, fecha);
            stmt.setString(2, diagnostico);
            stmt.setString(3, pruebas);
            stmt.setInt(4, folio);

            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public void terminarReparacion(int folio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String fecha = "";

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_FINALIZAR_REPARACION);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            java.util.Date date = new java.util.Date();
            fecha = dateFormat.format(date);

            stmt.setString(1, fecha);
            stmt.setString(2, fecha);
            stmt.setInt(3, folio);

            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public void terminarPruebas(int folio) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String fecha = "";

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_FINALIZAR_PRUEBA);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            java.util.Date date = new java.util.Date();
            fecha = dateFormat.format(date);

            stmt.setString(1, fecha);
            stmt.setInt(2, folio);

            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

}
