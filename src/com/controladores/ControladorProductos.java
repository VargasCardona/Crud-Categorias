package com.controladores;

import com.excepciones.CamposVaciosException;
import com.excepciones.DoubleInvalidoException;
import com.excepciones.ElementoNoSeleccionadoException;
import com.modelos.Producto;
import com.singleton.DatabaseSingleton;
import com.utils.GeneralUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import org.mariadb.jdbc.Connection;

public class ControladorProductos {

	private Connection connection;

	public ControladorProductos() {
		connection = DatabaseSingleton.getInstance().getConnection();
	}
	
	public void insertarProducto(String nombre, String precio, String distribuidor, String categoria) {
		if (GeneralUtils.estaVacio(nombre)
				|| GeneralUtils.estaVacio(precio)
				|| GeneralUtils.estaVacio(distribuidor)
				|| categoria.equals("Seleccione una categoría")) {
			throw new CamposVaciosException();
		}

		if (!GeneralUtils.esDouble(precio)
				|| Double.valueOf(precio) < 0) {
			throw new DoubleInvalidoException();
		}

		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO productos (sku, nombre, precio, distribuidor, id_categoria) VALUES (?, ?, ?, ? ,?)");
			ps.setString(1, GeneralUtils.generarSku(nombre));
			ps.setString(2, nombre);
			ps.setString(3, precio);
			ps.setString(4, distribuidor);
			ps.setString(5, categoria);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public ResultSet listarProductos() {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT p.sku, p.nombre, p.precio, p.distribuidor, c.nombre FROM productos as p INNER JOIN Categorias as c ON p.id_categoria = c.id");

			return ps.executeQuery();
		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public ResultSet buscarCoincidencias(String where) {
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT p.sku, p.nombre, p.precio, p.distribuidor, c.nombre FROM productos as p INNER JOIN Categorias as c ON p.id_categoria = c.id WHERE sku LIKE CONCAT('%',?,'%')");
			ps.setString(1, where);

			return ps.executeQuery();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}
	
	public ResultSet filtrarCategoria(String idCategoria){
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT p.sku, p.nombre, p.precio, p.distribuidor, c.nombre FROM productos as p INNER JOIN Categorias as c ON p.id_categoria = c.id WHERE c.id = ?");
			ps.setString(1, idCategoria);

			return ps.executeQuery();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public Producto consultarSku(String sku) {
		try {

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM productos WHERE sku = ?");
			ps.setString(1, sku);

			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();

			int cantidadColumnas = rsMd.getColumnCount();

			Object[] atributos = new Object[cantidadColumnas];
			while (rs.next()) {
				for (int i = 0; i < cantidadColumnas; i++) {
					atributos[i] = rs.getObject(i + 1);
				}
			}

			return new Producto((String) atributos[0], (String) atributos[1], (Double) atributos[2], (String) atributos[3], (Integer) atributos[4], (String) atributos[5]);

		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public void actualizarProducto(String sku, String nombre, String precio, String distribuidor, String categoria) {
		if (GeneralUtils.estaVacio(sku)) {
			throw new ElementoNoSeleccionadoException();
		}

		if (GeneralUtils.estaVacio(nombre)
				|| GeneralUtils.estaVacio(precio)
				|| GeneralUtils.estaVacio(distribuidor)
				|| categoria.equals("Seleccione una categoría")) {
			throw new CamposVaciosException();
		}

		if (!GeneralUtils.esDouble(precio)
				|| Double.valueOf(precio) < 0) {
			throw new DoubleInvalidoException();
		}

		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE productos SET nombre = ?, precio = ?, distribuidor = ?, id_categoria = ? WHERE sku = ?");
			ps.setString(1, nombre);
			ps.setString(2, precio);
			ps.setString(3, distribuidor);
			ps.setString(4, categoria);
			ps.setString(5, sku);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public void eliminarProducto(String sku) {
		if (GeneralUtils.estaVacio(sku)) {
			throw new ElementoNoSeleccionadoException();
		}

		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM productos WHERE sku = ?");
			ps.setString(1, sku);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}
}
