package com.modelos;

public class Producto {
	
	private String sku;
	private String nombre;
	private double precio;
	private String distribuidor;
	private int existencias;
	private String idCategoria;

	public Producto(String sku, String nombre, double precio, String distribuidor, int existencias, String idCategoria) {
		this.sku = sku;
		this.nombre = nombre;
		this.precio = precio;
		this.distribuidor = distribuidor;
		this.existencias = existencias;
		this.idCategoria = idCategoria;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

}
