package com.curso.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.curso.conf.Properties;
import com.curso.enums.Cat;
import com.curso.model.Producto;

/**
 * Clase que actúa de servicio con la clase Producto
 * 
 * @author DavidH
 * @version 1.0
 */
public class ProductoService {
	private static Properties ds;

	static {
		ds = new Properties();
	}

	/**
	 * Método que obtiene los productos de la bd
	 * 
	 * @return List<Producto> lista
	 */
	public static List<Producto> getProductos() {
		List<Producto> lista = new ArrayList<>();
		try {
			Connection con = ds.getDs().getConnection();
			if (con != null) {
				try (Statement st = con.createStatement()) {
					String query = "SELECT * FROM productos";
					ResultSet rs = st.executeQuery(query);
					while (rs.next()) {
						lista.add(new Producto(rs.getLong(1), rs.getString(2), Cat.valueOf(rs.getString(3)),
								rs.getDouble(4), rs.getInt(5)));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public static Producto obtenerProductoById(Long id) {
		Producto p = null;
		try {
			Connection con = ds.getDs().getConnection();
			if (con != null) {
				String query = "SELECT * FROM productos WHERE id=?";
				try (PreparedStatement ps = con.prepareStatement(query)) {
					ps.setLong(1, id);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						p = new Producto(rs.getLong(1), rs.getString(2), Cat.valueOf(rs.getString(3)), rs.getDouble(4),
								rs.getInt(5));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public static void modificarProducto(Long idP, String nombre, String cat, String precio, String stock) {

		try {
			Connection con = ds.getDs().getConnection();
			if (con != null) {
				String query = "UPDATE productos SET nombre = ?, cat = ?, precio = ?, stock = ? WHERE id = ?";
				try (PreparedStatement ps = con.prepareStatement(null)) {
					ps.setString(1, nombre);
					ps.setString(2, cat);
					ps.setDouble(3, Double.parseDouble(precio));
					ps.setInt(4, Integer.parseInt(stock));
					ps.setLong(5, idP);
					ps.executeUpdate();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
