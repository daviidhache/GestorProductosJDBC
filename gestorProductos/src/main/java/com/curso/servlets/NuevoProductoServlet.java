package com.curso.servlets;

import java.io.IOException;

import com.curso.model.Pedido;
import com.curso.model.Producto;
import com.curso.service.PedidoService;
import com.curso.service.ProductoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NuevoProductoServlet Gestiona la creación de un
 * nuevo pedido
 */
public class NuevoProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ((request.getParameter("pedidoNuevo") != null) && request.getParameter("unidades") != null) {
			// Procedemos a realizar comprobaciones de un pedido
			Long id = Long.valueOf(request.getParameter("pedidoNuevo"));
			int uds = Integer.valueOf(request.getParameter("unidades"));
			// Verificar si el producto existe en el almacén
			Producto p = ProductoService.obtenerProductoById(id);
			if (p != null) {
				// Existe. Comprobamos si las unidades seleccionadas no exceden de las disponibles
				if(p.getStock() >= uds ) {
						//Modificamos stock y realizamos el pedido.
						p.setStock(p.getStock() - uds);
						PedidoService.nuevoPedido(new Pedido(generarRef(),p, uds));
				}
			}
		}
	}

	private String generarRef() {
		// TODO Auto-generated method stub
		return null;
	}

}
