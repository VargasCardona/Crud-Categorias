package com.vistas;

import com.controladores.ControladorCategorias;
import com.controladores.ControladorProductos;
import com.modelos.Categoria;
import com.modelos.Producto;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VistaPrincipal extends javax.swing.JFrame {

	ControladorProductos controladorProductos;
	ControladorCategorias controladorCategorias;
	VistaFiltrarCategoria vistaFiltrarCategoria;

	public VistaPrincipal() throws SQLException {
		controladorProductos = new ControladorProductos();
		controladorCategorias = new ControladorCategorias();

		this.setLocationRelativeTo(null);
		setTitle("Buscar Productos");

		initComponents();

		actualizarTablaProductos("");
		tblProductos.setDefaultEditor(Object.class, null); // Evitar ediciones en la tabla
		tblProductos.getTableHeader().setEnabled(false); // Evitar reorganizaciones de Headers en la tabla
		tblProductos.setCellSelectionEnabled(false); // Evitar selecciones en la tabla

		actualizarTablaCategorias();
		tblCategorias.setDefaultEditor(Object.class, null); // Evitar ediciones en la tabla
		tblCategorias.getTableHeader().setEnabled(false); // Evitar reorganizaciones de Headers en la tabla
		tblCategorias.setCellSelectionEnabled(false); // Evitar selecciones en la tabla
	
                setCmbxCategoria();
        }
        
        public void setCmbxCategoria() throws SQLException {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		cmbxCategorias.setModel(model);

		ResultSet categorias = controladorCategorias.obtenerCategorias();
		model.addElement("Seleccione una categoría"); // Agrega la opción predeterminada

		while (categorias.next()) {
			String id = categorias.getString("id");
			String nombre = categorias.getString("nombre");
			model.addElement(id + " - " + nombre); // Agrega los nombres de las categorías al ComboBoxModel
		}
	}
        
	public void actualizarTablaProductos(String where) {
		
		try {
			DefaultTableModel modelo = new DefaultTableModel();
			tblProductos.setModel(modelo);

			ResultSet rs = null;

			if (where == null || where.isEmpty()) {
				rs = controladorProductos.listarProductos();
			} else {
				rs = controladorProductos.buscarCoincidencias(where);
			}

			ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
			int cantidadAtributos = rsMd.getColumnCount();

			modelo.addColumn("SKU:");
			modelo.addColumn("Nombre:");
			modelo.addColumn("Precio:");
			modelo.addColumn("Distribuidor:");
			modelo.addColumn("Categoria:");

			int[] anchos = {50, 50, 50, 50, 50};
			for (int i = 0; i < tblProductos.getColumnCount(); i++) {
				tblProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[1]);
			}

			Object[] atributos = new Object[cantidadAtributos];
			while (rs.next()) {
				for (int i = 0; i < cantidadAtributos; i++) {
					atributos[i] = rs.getObject(i + 1);
				}
				modelo.addRow(atributos);
			}

			if (modelo.getRowCount() == 0) {
				lblResultados.setText("No se han encontrado coincidencias");
			} else {
				lblResultados.setText(" ");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actualizarTablaCategorias() {
		try {
			DefaultTableModel modelo = new DefaultTableModel();
			tblCategorias.setModel(modelo);

			ResultSet rs = controladorCategorias.listarCategorias();
			ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
			int cantidadAtributos = rsMd.getColumnCount();

			modelo.addColumn("ID:");
			modelo.addColumn("Nombre:");

			int[] anchos = {50, 50};
			for (int i = 0; i < tblCategorias.getColumnCount(); i++) {
				tblCategorias.getColumnModel().getColumn(i).setPreferredWidth(anchos[1]);
			}

			Object[] atributos = new Object[cantidadAtributos];
			while (rs.next()) {
				for (int i = 0; i < cantidadAtributos; i++) {
					atributos[i] = rs.getObject(i + 1);
				}
				modelo.addRow(atributos);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lblResultados = new javax.swing.JLabel();
        txtSku = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblSku = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDistribuidor = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNombreCategoria = new javax.swing.JTextField();
        btnActualizarCategoria = new javax.swing.JButton();
        btnEliminarCategoria = new javax.swing.JButton();
        btnRegistrarCategoria = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        cmbxCategorias = new javax.swing.JComboBox<>();
        btnVerPorCategoria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Categorías:");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, 280, 50));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt, null);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 370, 250));

        lblResultados.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblResultados.setForeground(new java.awt.Color(255, 0, 0));
        lblResultados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResultados.setText("{Resultados}");
        bg.add(lblResultados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 370, 30));

        txtSku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSkuKeyReleased(evt);
            }
        });
        bg.add(txtSku, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 340, 20));

        jLabel3.setText("SKU:");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 30, 20));
        bg.add(lblSku, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 200, 20));

        jLabel5.setText("Distribuidor:");
        bg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, -1, -1));

        jLabel6.setText("Precio:");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, -1, -1));

        jLabel7.setText("Categoría:");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, -1, -1));
        bg.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 200, -1));
        bg.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 200, -1));
        bg.add(txtDistribuidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, 200, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        bg.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 100, -1));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        bg.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 100, -1));

        jLabel8.setText("Nombre:");
        bg.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        btnRegistrar.setText("Registrar nuevo producto");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        bg.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 320, 240, -1));

        jLabel9.setText("Sku:");
        bg.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, -1, -1));

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCategorias);

        bg.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 370, 210));

        jLabel4.setText("id:");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, 30, 20));

        jLabel10.setText("Nombre:");
        bg.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 530, -1, -1));
        bg.add(txtNombreCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 530, 200, -1));

        btnActualizarCategoria.setText("Actualizar");
        btnActualizarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCategoriaActionPerformed(evt);
            }
        });
        bg.add(btnActualizarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 570, 100, -1));

        btnEliminarCategoria.setText("Eliminar");
        btnEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCategoriaActionPerformed(evt);
            }
        });
        bg.add(btnEliminarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 570, 100, -1));

        btnRegistrarCategoria.setText("Registrar nueva categoría");
        btnRegistrarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarCategoriaActionPerformed(evt);
            }
        });
        bg.add(btnRegistrarCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 600, 240, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Buscar por SKU");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 370, 50));
        bg.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 490, 200, 20));

        bg.add(cmbxCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 200, 20));

        btnVerPorCategoria.setText("Ver productos por categoría");
        btnVerPorCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPorCategoriaActionPerformed(evt);
            }
        });
        bg.add(btnVerPorCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSkuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSkuKeyReleased
		if (!txtSku.getText().equals("")) {
			actualizarTablaProductos(txtSku.getText());
		} else {
			actualizarTablaProductos("");
		}
    }//GEN-LAST:event_txtSkuKeyReleased

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
		String idCategoria = cmbxCategorias.getSelectedItem().toString().split(" - ")[0];
                try {
			controladorProductos.actualizarProducto(lblSku.getText(),
					txtNombre.getText(),
					txtPrecio.getText(),
					txtDistribuidor.getText(),
					idCategoria.strip());

			actualizarTablaProductos("");
			
			actualizarVistaFiltro();

			JOptionPane.showMessageDialog(null, "El producto se ha actualizado con exito");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
		try {
			controladorProductos.eliminarProducto(lblSku.getText());

			txtSku.setText("");
			actualizarTablaProductos("");

			limpiarCamposProducto();
			JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
			
			actualizarVistaFiltro();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
		try {
			VistaRegistrarProducto vista = new VistaRegistrarProducto(this);
			vista.setVisible(true);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
    }//GEN-LAST:event_btnRegistrarActionPerformed

    public void tblProductosMouseClicked(java.awt.event.MouseEvent evt, String skuRecibido) {//GEN-FIRST:event_tblProductosMouseClicked
		String sku = null;
		if (skuRecibido == null){
			sku = String.valueOf(tblProductos.getValueAt(tblProductos.getSelectedRow(), 0));
		} else {
			sku = skuRecibido;
		}
		
		
		Producto productoSeleccionado = controladorProductos.consultarSku(sku);

		lblSku.setText(productoSeleccionado.getSku());
		txtNombre.setText(productoSeleccionado.getNombre());
		txtPrecio.setText(String.valueOf(productoSeleccionado.getPrecio()));
		txtDistribuidor.setText(productoSeleccionado.getDistribuidor());
                
                int index = 0;
                for (int i = 0; i < cmbxCategorias.getItemCount(); i++) {
                    if (cmbxCategorias.getItemAt(i).startsWith(productoSeleccionado.getIdCategoria())) {
                        index = i;
                        break;
                    }
                }
                cmbxCategorias.setSelectedIndex(index);
    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriasMouseClicked
		String id = String.valueOf(tblCategorias.getValueAt(tblCategorias.getSelectedRow(), 0));
		Categoria categoriaSeleccionada = controladorCategorias.consultarId(id);

		lblId.setText(categoriaSeleccionada.getId());
		txtNombreCategoria.setText(categoriaSeleccionada.getNombre());
    }//GEN-LAST:event_tblCategoriasMouseClicked

    private void btnActualizarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCategoriaActionPerformed
		try {
			controladorCategorias.actualizarCategoria(lblId.getText(),
					txtNombreCategoria.getText());

			actualizarTablaCategorias();
                        actualizarTablaProductos(null);
                        txtSku.setText("");
                        setCmbxCategoria();
			JOptionPane.showMessageDialog(null, "La categoria se ha actualizado con exito");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }//GEN-LAST:event_btnActualizarCategoriaActionPerformed

    private void btnEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCategoriaActionPerformed
		try {
			controladorCategorias.eliminarCategoria(lblId.getText());

			actualizarTablaCategorias();

			limpiarCamposCategoria();
                        setCmbxCategoria();
			JOptionPane.showMessageDialog(null, "La categoria se ha eliminado con exito");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }//GEN-LAST:event_btnEliminarCategoriaActionPerformed

    private void btnRegistrarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarCategoriaActionPerformed
		VistaRegistrarCategoria vista = new VistaRegistrarCategoria(this);
		vista.setVisible(true);
    }//GEN-LAST:event_btnRegistrarCategoriaActionPerformed

    private void btnVerPorCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPorCategoriaActionPerformed
                VistaFiltrarCategoria vista = null;
                try {
                        vista = new VistaFiltrarCategoria(this);
                        vistaFiltrarCategoria = vista;
                } catch (SQLException ex) {
                        Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
		vista.setVisible(true);
    }//GEN-LAST:event_btnVerPorCategoriaActionPerformed

	private void limpiarCamposProducto() {
		lblSku.setText("");
		txtNombre.setText("");
		txtPrecio.setText("");
		txtDistribuidor.setText("");
                cmbxCategorias.setSelectedIndex(0);
	}

	private void limpiarCamposCategoria() {
		lblId.setText("");
		txtNombreCategoria.setText("");
	}
        
        public void actualizarVistaFiltro() {
                if (vistaFiltrarCategoria != null){
			vistaFiltrarCategoria.actualizarTablaProductos(null);
                        vistaFiltrarCategoria.limpiarCmbx();
		}
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnActualizarCategoria;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarCategoria;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrarCategoria;
    private javax.swing.JButton btnVerPorCategoria;
    private javax.swing.JComboBox<String> cmbxCategorias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblResultados;
    private javax.swing.JLabel lblSku;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtDistribuidor;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCategoria;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSku;
    // End of variables declaration//GEN-END:variables
}
