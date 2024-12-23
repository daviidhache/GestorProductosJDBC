package com.curso.model;

import com.curso.enums.Cat;

/**
 * Clase que gestiona la información de los productos
 * 
 * @author DavidH
 * @version 1.0
 */
public final class Producto {

	private Long id;
	private String nombre;
	private Cat cat;
	private double precio;
	private int stock;

	public Producto(Long id, String nombre, Cat cat, double precio, int stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cat = cat;
		this.precio = precio;
		this.stock = stock;
	}

	public Producto(String nombre, Cat cat, double precio, int stock) {
		this.nombre = nombre;
		this.cat = cat;
		this.precio = precio;
		this.stock = stock;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", cat=" + cat + ", precio=" + precio + ", stock=" + stock
				+ "]";
	}

}
