/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import JDBC.Conexion;
import dto.Autorizacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author acer
 */
public class AutorizacionDao {

    private final String SQL_DIAGNOSTICOACABADO = "select asignacion.Equipo_idEquipo, Nombre_cliente, Apellido_cliente, telefono, Tipo_equipo, Marca, Modelo, Falla_cliente, inicio_diagnostico, fin_diagnostico, diagnostico FROM asignacion INNER JOIN equipo ON equipo.idEquipo = asignacion.Equipo_idEquipo where inicio_diagnostico is not null and fin_diagnostico is not null and reparacion is null";
    private final String SQL_NOREPARAR = "update asignacion set reparacion = ? where Equipo_idEquipo = ?";
    private final String SQL_REPARAR = "update asignacion set reparacion = ?, inicio_reparacion = ? where Equipo_idEquipo = ?";

    //// cargar diagnostico
    public List<Autorizacion> diagnosticoTerminado() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Autorizacion> estatusLis = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DIAGNOSTICOACABADO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int folio = rs.getInt("Equipo_idEquipo");
                String nombre = rs.getString("Nombre_cliente");
                String apellido = rs.getString("Apellido_cliente");
                String telefono = rs.getString("telefono");
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
                String falla = rs.getString("Falla_cliente");
                String diagnostico = rs.getString("diagnostico");

                Autorizacion estatu = new Autorizacion(folio, nombre, apellido, telefono, marca, modelo, falla, diagnostico);
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

    //acepta o no el cliente
    public void aceptadoONo(int folio, String reparacion) {
        Connection conn = null;
        PreparedStatement stmt = null;

        if (reparacion.equals("SI")) {

            String fecha = "";

            try {
                conn = Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_REPARAR);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
                java.util.Date date = new java.util.Date();
                fecha = dateFormat.format(date);

                stmt.setString(1, reparacion);
                stmt.setString(2, fecha);
                stmt.setInt(3, folio);

                stmt.execute();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            } finally {
                Conexion.close(stmt);
                Conexion.close(conn);
            }

        } else {

            try {
                conn = Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_NOREPARAR);

                stmt.setString(1, reparacion);
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

}
