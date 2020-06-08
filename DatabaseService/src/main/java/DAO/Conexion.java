package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Configuration.ConfigDatabase;

public class Conexion {

	public Connection conectar() {
		Connection conexion = null;
		try {
			Class.forName(ConfigDatabase.getInstance().getControlador());
			conexion = DriverManager.getConnection(ConfigDatabase.getInstance().getURL()
					, ConfigDatabase.getInstance().getUsuario(),
					ConfigDatabase.getInstance().getClave());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error al cargar el controlador");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en la conexion");
		}
		return conexion;
	}

}
