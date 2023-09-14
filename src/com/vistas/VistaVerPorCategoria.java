package com.vistas;

import com.controladores.ControladorCategorias;
import com.controladores.ControladorProductos;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class VistaVerPorCategoria extends javax.swing.JFrame {

	ControladorProductos controladorProductos;
	ControladorCategorias controladorCategorias;

	public VistaVerPorCategoria() throws SQLException {
		controladorProductos = new ControladorProductos();
		controladorCategorias = new ControladorCategorias();

		this.setLocationRelativeTo(null);
		setTitle("Buscar Productos");

		initComponents();

		actualizarTablaProductos(null);
		tblProductos.setDefaultEditor(Object.class, null); // Evitar ediciones en la tabla
		tblProductos.getTableHeader().setEnabled(false); // Evitar reorganizaciones de Headers en la tabla
		tblProductos.setCellSelectionEnabled(false); // Evitar selecciones en la tabla
	
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
        
	public void actualizarTablaProductos(ResultSet resultados) {
		try {
			DefaultTableModel modelo = new DefaultTableModel();
			tblProductos.setModel(modelo);

			ResultSet rs = (resultados == null) 
                                ? controladorProductos.listarProductos()
                                : resultados;

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

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lblResultados = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbxCategorias = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 370, 250));

        lblResultados.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblResultados.setForeground(new java.awt.Color(255, 0, 0));
        lblResultados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResultados.setText("{Resultados}");
        bg.add(lblResultados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 370, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Buscar por categoría");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 370, 50));

        cmbxCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbxCategoriasItemStateChanged(evt);
            }
        });
        bg.add(cmbxCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 370, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
    }//GEN-LAST:event_tblProductosMouseClicked

    private void cmbxCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbxCategoriasItemStateChanged
        if (cmbxCategorias.getSelectedIndex() == 0) {
            actualizarTablaProductos(null);
            return;
        }
        
        String idCategoria = cmbxCategorias.getSelectedItem().toString().split(" - ")[0].strip();
        ResultSet filtrarCategoria = controladorProductos.filtrarCategoria(idCategoria);
        actualizarTablaProductos(filtrarCategoria);
    }//GEN-LAST:event_cmbxCategoriasItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JComboBox<String> cmbxCategorias;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblResultados;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
