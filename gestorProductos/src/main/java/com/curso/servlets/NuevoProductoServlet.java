package com.curso.servlets;

import java.io.IOException;
import java.util.Random;

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
						
						PedidoService.nuevoPedido(new Pedido(generarRef(),p, uds));
						// No funciona con dispacher!
						response.sendRedirect("index.jsp");
						
				}
			}
		}
	}
/**
 * Método que genera una REF nueva . Fuente -> StackOverFlow
 * @return String ref
 */
	private String generarRef() {
		   Random rand = new Random();
	        
	        // Generar tres caracteres aleatorios de la A-Z
	        StringBuilder caracteres = new StringBuilder();
	        for (int i = 0; i < 3; i++) {
	            char letra = (char) ('A' + rand.nextInt(26)); // Genera una letra aleatoria de la A-Z
	            caracteres.append(letra);
	        }
	        
	        // Generar dos números aleatorios
	        int numero1 = rand.nextInt(10); // Número entre 0 y 9
	        int numero2 = rand.nextInt(10); // Número entre 0 y 9
	        
	        // Combinar los caracteres y los números con un guion
	        return caracteres.toString() + "-" + numero1 + numero2;
	}

}
