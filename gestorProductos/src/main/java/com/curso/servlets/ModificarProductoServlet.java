package com.curso.servlets;

import java.io.IOException;

import com.curso.enums.Cat;
import com.curso.model.Producto;
import com.curso.service.ProductoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificarProductoServlet Gestiona la
 * modificación del producto
 */
public class ModificarProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Hemos enviado la solicitud post del formulario y podemos tratar los datos
		// Recogemos parámetros
		String nombre = request.getParameter("nombre");
		String cat = request.getParameter("cat");
		String precio = request.getParameter("precio");
		String stock = request.getParameter("stock");
		String idProducto = request.getParameter("valorId");
		if ((nombre != null && !nombre.isBlank()) && (cat != null && !cat.isBlank()) && (precio != null)
				&& (stock != null && !stock.isBlank()) && (idProducto != null)) {
			Double precioP = Double.valueOf(precio);
			int stockP = Integer.valueOf(stock);
			if (stockP > 0 && precioP > 0) {
				// Procedemos a modificar
				Cat cate = Cat.valueOf(cat.toUpperCase());
				Long idP = Long.valueOf(idProducto);
				System.out.println("Id producto" + idP);
				Producto pro = ProductoService.obtenerProductoById(idP);
				if (pro != null) {
					ProductoService.modificarProducto(idP, nombre, cat, precio, stock);

				}

			}

		}
		// ¿Porqué si uso Dispacher para volver a index.jsp, aparecen los campos
		// duplicados ?
		// Usando sendRedirect, no. Resolver!
		response.sendRedirect("index.jsp");
	}

}
