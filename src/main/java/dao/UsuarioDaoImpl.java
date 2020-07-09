package dao;

import JDBC.Conexion;
import dto.Usuario;
import java.sql.*;

public class UsuarioDaoImpl {

    private final String SQL_VALIDAR = "SELECT * FROM usuario WHERE Nombre=? AND contraseña=? and cargo = 'A'";
    private final String SQL_VALIDART = "SELECT * FROM usuario WHERE Nombre=? AND contraseña=? and cargo = 'T'";

    public boolean ingresar(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_VALIDAR);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getContra());
            rs = stmt.executeQuery();

            if (rs.absolute(1)) {
                existe = true;
            } else {
                existe = false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return existe;
    }

    public boolean ingresarTecnico(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean existe = false;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_VALIDART);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getContra());
            rs = stmt.executeQuery();

            if (rs.absolute(1)) {
                existe = true;
            } else {
                existe = false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return existe;
    }

}
