package com.curso.model;

/**
 * Clase que define las propiedades del pedido
 * @author DavidH
 *@version 1.0
 */

import java.time.LocalDateTime;

public final class Pedido {
	private String ref;
	private Producto producto;
	private int unidades;
	private LocalDateTime fechayHora;

	public Pedido(String ref, Producto producto, int unidades) {
		this.fechayHora = LocalDateTime.now();
		this.ref = ref;
		this.producto = producto;
		this.unidades = unidades;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public LocalDateTime getFechayHora() {
		return fechayHora;
	}

}
