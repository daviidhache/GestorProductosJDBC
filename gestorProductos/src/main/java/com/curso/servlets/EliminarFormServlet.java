package com.curso.servlets;

import java.io.IOException;

import com.curso.model.Producto;
import com.curso.service.ProductoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EliminarFormServlet
 * Gestiona la eliminación del producto
 */
public class EliminarFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long idP = (Long) req.getAttribute("idProducto");
			Producto eliminar = ProductoService.obtenerProductoById(idP);
			ProductoService.getProductos().remove(eliminar);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (Exception e) {

		}
	}

}
