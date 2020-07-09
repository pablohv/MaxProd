package dao;

import JDBC.Conexion;
import dto.Equipo;
import java.sql.*;

public class EquipoDaoImpl{

    private final String SQL_INSERTAR = "INSERT INTO equipo (idEquipo, Nombre_cliente, Apellido_cliente, Telefono, Direccion, Tipo_equipo, Marca, Modelo, No_serie, Falla_cliente, Accesorios) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void altaEquipo(Equipo equipo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERTAR);

            stmt.setInt(1, equipo.getFolio());
            stmt.setString(2, equipo.getNombreCliente());
            stmt.setString(3, equipo.getApellidoCliente());
            stmt.setString(4, equipo.getTelefono());
            stmt.setString(5, equipo.getDireccion());
            stmt.setString(6, equipo.getTipoEquipo());
            stmt.setString(7, equipo.getMarca());
            stmt.setString(8, equipo.getModelo());
            stmt.setInt(9, equipo.getNoSerie());
            stmt.setString(10, equipo.getFalla());
            stmt.setString(11, equipo.getAccesorios());

            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

}
