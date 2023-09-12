package com.controladores;

import com.excepciones.CamposVaciosException;
import com.excepciones.DoubleInvalidoException;
import com.excepciones.ElementoNoSeleccionadoException;
import com.utils.ConexionUtils;
import com.utils.Utils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ControladorGeneral {

	public void insertarTabla(String nombre, String precio, String distribuidor, String categoria) {
		if (Utils.estaVacio(nombre)
				|| Utils.estaVacio(precio)
				|| Utils.estaVacio(distribuidor)
				|| categoria.equals("Seleccione una categoría")) {
			throw new CamposVaciosException();
		}

		if (!Utils.esDouble(precio)) {
			throw new DoubleInvalidoException();
		}

		try {
			PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement("INSERT INTO productos (sku, nombre, precio, distribuidor, id_categoria) VALUES (?, ?, ?, ? ,?)");
			ps.setString(1, Utils.generarSku(nombre));
			ps.setString(2, nombre);
			ps.setString(3, precio);
			ps.setString(4, distribuidor);
			ps.setString(5, categoria);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public ResultSet listarTabla() {
		try {
			PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement("SELECT * FROM productos");

			return ps.executeQuery();
		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public ResultSet buscarCoincidencias(String where) {
		try {
			PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement("SELECT * FROM productos WHERE sku LIKE CONCAT('%',?,'%')");
			ps.setString(1, where);

			return ps.executeQuery();
		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public ResultSet consultarSku(String sku) {
		try {
			PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement("SELECT * FROM productos WHERE sku = ?");
			ps.setString(1, sku);

			return ps.executeQuery();
		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public void actualizarTabla(String sku, String nombre, String precio, String distribuidor, String categoria) {
		if (Utils.estaVacio(sku)) {
			throw new ElementoNoSeleccionadoException();
		}

		if (Utils.estaVacio(nombre)
				|| Utils.estaVacio(precio)
				|| Utils.estaVacio(distribuidor)
				|| Utils.estaVacio(categoria)) {
			throw new CamposVaciosException();
		}

		if (!Utils.esDouble(precio)) {
			throw new DoubleInvalidoException();
		}

		try {
			PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement("UPDATE productos SET nombre = ?, precio = ?, distribuidor = ?, categoria = ? WHERE sku = ?");
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

	public void eliminarTabla(String sku) {
		if (Utils.estaVacio(sku)) {
			throw new ElementoNoSeleccionadoException();
		}

		try {
			PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement("DELETE FROM productos WHERE sku = ?");
			ps.setString(1, sku);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}
}
