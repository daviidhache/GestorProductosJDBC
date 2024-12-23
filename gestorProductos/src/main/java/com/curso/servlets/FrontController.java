package com.curso.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController Se encarga de dirigir hacia las
 * demás acciones
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion").toLowerCase();
		// Recuperamos el id del producto
		
		switch (opcion) {
		case "modificar":
			Long id = Long.valueOf(request.getParameter("id"));
			// Llamamos a modificar.FOrm para modificar
			request.setAttribute("idProducto", id);
			request.getRequestDispatcher("modificarForm.jsp").forward(request, response);

			break;
		case "eliminar":
			id = Long.valueOf(request.getParameter("id"));
			// Llamamos a otro servlet para eliminar
			request.setAttribute("idProducto", id);
			request.getRequestDispatcher("EliminarFormServlet").forward(request, response);
			break;
		case "nuevopedido":
			request.getRequestDispatcher("nuevoPedido.jsp").forward(request, response);
			// response.sendRedirect("nuevoPedido.jsp");
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
