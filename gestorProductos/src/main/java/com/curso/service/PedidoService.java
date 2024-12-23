package com.curso.service;

import java.util.ArrayList;
import java.util.List;

import com.curso.enums.Cat;
import com.curso.model.Pedido;
import com.curso.model.Producto;

public class PedidoService {
	static List<Pedido> listPedidos = new ArrayList<>();
	static {
		Pedido p1 = new Pedido("JHDK-12", new Producto("Patinete Eléctrico", Cat.OCIO, 120, 12), 3);
		Pedido p2 = new Pedido("FKLDI-21", new Producto("Mesa Auxiliar", Cat.HOGAR, 100, 7), 10);
		Pedido p3 = new Pedido("OOIIW-4", new Producto("Mesa billar", Cat.OCIO, 120, 100), 1);
		listPedidos.add(p1);
		listPedidos.add(p2);
		listPedidos.add(p3);
	}

	public static List<Pedido> getListPedidos() {
		return listPedidos;
	}

	public Pedido obtenerPedidoByRef(String referencia) {
		return listPedidos.stream().filter(pe -> pe.getRef().equalsIgnoreCase(referencia)).toList().get(0);
	}

	public static void nuevoPedido(Pedido p) {
		listPedidos.add(p);
	}

}
