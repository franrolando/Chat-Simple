package strategy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.Conexion;
import modelo.mensajes.Mensaje;
import modelo.mensajes.MensajeAlerta;
import modelo.mensajes.MensajeAvisoRecep;

public class DBStrategy implements IPersistenciaStrategy {

	@Override
	public void persistirMensaje(Mensaje mensaje) {
		Conexion c = new Conexion();
		PreparedStatement ps = null;
		boolean rpta = false;
		try {
			if (mensaje != null) {
				String sql = "INSERT INTO mensajes (nombreEmisor,nombreReceptor,ipDestino,asunto,cuerpo,tipo,hora) VALUES (?,?,?,?,?,?,?);";
				ps = c.conectar().prepareStatement(sql);
				ps.setString(1, mensaje.getEmisor());
				ps.setString(2, mensaje.getReceptor());
				ps.setString(3, mensaje.getIpDestino());
				ps.setString(4, mensaje.getAsunto());
				ps.setString(5, mensaje.getCuerpo());
				ps.setString(6, mensaje.getTipo().toString());
				ps.setString(7, mensaje.getHora());
				rpta = ps.execute();
				if (rpta) {
					System.out.println("Se persistio mensaje.");
				}
			}
		} catch (SQLException e) {
		} finally {
			try {
				ps.close();
				c.conectar().close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public List<Mensaje> getMensajes(String nombreReceptor) {
		Conexion c = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Mensaje> listaMensajes = new ArrayList<Mensaje>();
		boolean rpta = false;
		try {
			if (nombreReceptor != null) {
				String sql = "SELECT nombreEmisor,ipDestino,asunto,cuerpo,tipo,hora FROM mensajes WHERE nombreReceptor=?;";
				ps = c.conectar().prepareStatement(sql);
				ps.setString(1, nombreReceptor);
				rpta = ps.execute();
				if (rpta) {
					System.out.println("Se recibieron los mensajes.");
					rs = ps.executeQuery();
					while (rs.next()) {
						String tipo = rs.getString("tipo");
						Mensaje mensaje = null;
						switch (tipo) {
						case "SIMPLE":
							mensaje = new Mensaje();
							break;
						case "CONAVISORECEPCION":
							mensaje = new MensajeAvisoRecep();
							break;
						case "CONALERTASONIDO":
							mensaje = new MensajeAlerta();
							break;
						}
						mensaje.setAsunto(rs.getString("asunto"));
						mensaje.setCuerpo(rs.getString("cuerpo"));
						mensaje.setEmisor(rs.getString("nombreEmisor"));
						mensaje.setIpDestino(rs.getString("ipDestino"));
						mensaje.setHora(rs.getString("hora"));
						listaMensajes.add(mensaje);
					}
					eliminaMensajes(nombreReceptor);
				}
			}
		} catch (SQLException e) {
			
		} finally {
			try {
				ps.close();
				c.conectar().close();
			} catch (Exception e) {
				
			}
		}
		return listaMensajes;
	}

	private void eliminaMensajes(String nombreReceptor) {
		Conexion c = new Conexion();
		PreparedStatement ps = null;
		boolean rpta = false;
		try {
			if (nombreReceptor != null) {
				String sql = "DELETE FROM mensajes WHERE nombreReceptor=?;";
				ps = c.conectar().prepareStatement(sql);
				ps.setString(1, nombreReceptor);
				rpta = ps.execute();
				if (rpta) {
					System.out.println("Se eliminaron los mensajes.");
				}
			}
		} catch (SQLException e) {
			
		} finally {
			try {
				ps.close();
				c.conectar().close();
			} catch (Exception e) {
				
			}
		}
	}

}
