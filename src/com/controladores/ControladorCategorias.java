package com.controladores;

import com.excepciones.CamposVaciosException;
import com.excepciones.ElementoNoSeleccionadoException;
import com.utils.ConexionUtils;
import com.utils.Utils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class ControladorCategorias {

	public void insertarTabla(String nombre) {
		if (Utils.estaVacio(nombre)) {
			throw new CamposVaciosException();
		}

		try {
			PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement("INSERT INTO categorias (id, nombre) VALUES (?, ?)");
			ps.setString(1, Utils.generarSku(nombre));
			ps.setString(2, nombre);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public ResultSet listarTabla() {
		try {
			PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement("SELECT * FROM categorias");

			return ps.executeQuery();
		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public ResultSet consultarId(String sku) {
		try {
			PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement("SELECT * FROM categorias WHERE id = ?");
			ps.setString(1, sku);

			return ps.executeQuery();
		} catch (SQLException ex) {
			System.err.print(ex);
		}
		return null;
	}

	public void actualizarTabla(String id, String nombre) {
		if (Utils.estaVacio(id)) {
			throw new ElementoNoSeleccionadoException();
		}

		if (Utils.estaVacio(nombre)) {
			throw new CamposVaciosException();
		}

		try {
			PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement("UPDATE categorias SET nombre = ? WHERE id = ?");
			ps.setString(1, nombre);
			ps.setString(5, id);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}

	public void eliminarTabla(String id) {
		if (Utils.estaVacio(id)) {
			throw new ElementoNoSeleccionadoException();
		}

		try {
			PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement("DELETE FROM categorias WHERE id = ?");
			ps.setString(1, id);

			ps.execute();

		} catch (SQLException ex) {
			System.err.print(ex);
		}
	}
        
    public ArrayList<String> getAllCategories() {
        ArrayList<String> categorias = new ArrayList<>();

        try {            
            ResultSet rs;

            String query = "SELECT * FROM categorias";

            PreparedStatement ps = ConexionUtils.realizarConexion().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                categorias.add(id);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return categorias;
    }
}
