package com.curso.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.curso.conf.Properties;
import com.curso.enums.Cat;
import com.curso.model.Pedido;
import com.curso.model.Producto;

public class PedidoService {
	static Properties p;
	static {
		p = new Properties();
	}

	public static List<Pedido> getListPedidos() {
		List<Pedido> lista = new ArrayList<>();
		try (Connection con = p.getDs().getConnection(); Statement st = con.createStatement()) {
			String query = "SELECT * FROM pedidos as p join productos as pro on p.producto = pro.id";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Producto p = new Producto(rs.getLong(5), rs.getString(6), Cat.valueOf(rs.getString(7)), rs.getDouble(8),
						rs.getInt(9));

				// Fuente -> StackOverFlow
				// Paso 1: Obtener la fecha desde el ResultSet
				java.sql.Date sqlDate = rs.getDate(4);

				// Paso 2: Convertir a LocalDateTime
				LocalDateTime fecha = sqlDate.toLocalDate().atStartOfDay();
				lista.add(new Pedido(rs.getString(1), p, rs.getInt(3), fecha));

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return lista;
	}

	public Pedido obtenerPedidoByRef(String referencia) {
		// TODO implementar pedidos por referencia
		return null;
	}
/**
 * Método que crea un nuevo pedido en el sistema. Transacción : Actualiza stock del producto y inserta en pedido
 * @param Pedido pes
 */
	public static void nuevoPedido(Pedido pe) {
		Connection con = null;
		try {
			con = p.getDs().getConnection();
			if(con != null) {
				String query = "UPDATE productos set stock= (stock - ?) WHERE id=?";
				String query2 = "INSERT INTO pedidos values(?,?,?,?)";

				try(PreparedStatement st = con.prepareStatement(query)){
					// Iniciamos transacción
					con.setAutoCommit(false);
					st.setInt(1, pe.getProducto().getStock());
					st.setLong(2, pe.getProducto().getId());
					if(st.executeUpdate() > 0) {
						PreparedStatement st2 = con.prepareStatement(query2);
						st.setString(1, pe.getRef());
						st.setLong(2, pe.getProducto().getId());
						st.setInt(3,pe.getUnidades() );
					//	st.setDate(4, java.sql.Date new Date().getTime());
						//Por implementar
					}else {
						con.rollback();
					}
				}catch(SQLException ex) {
					con.rollback();
				}
			}
		} catch (SQLException e) {
			
		}
	}

}
