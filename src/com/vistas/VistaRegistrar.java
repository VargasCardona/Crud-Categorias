package com.vistas;

import com.controladores.ControladorCategorias;
import com.controladores.ControladorGeneral;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VistaRegistrar extends javax.swing.JFrame {

	ControladorGeneral controlador;
        ControladorCategorias controladorCategorias;
	VistaPrincipal vistaPrincipal;

	public VistaRegistrar(VistaPrincipal vistaPrincipal) {
		controlador = new ControladorGeneral();
                controladorCategorias = new ControladorCategorias();
		this.vistaPrincipal = vistaPrincipal;

		this.setLocationRelativeTo(null);
		setTitle("Registrar Producto");

		initComponents();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar unicamente la ventana actual
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDistribuidor = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        cmbxCategorias = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registrar Producto");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 360, 40));

        jLabel4.setText("Nombre:");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel5.setText("Distribuidor:");
        bg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel6.setText("Precio:");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel7.setText("Categoría:");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));
        bg.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 240, -1));
        bg.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 240, -1));
        bg.add(txtDistribuidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 240, -1));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        bg.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 320, 30));

        bg.add(cmbxCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 180, 240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
		try {
			controlador.insertarTabla(txtNombre.getText(),
					txtPrecio.getText(),
					txtDistribuidor.getText(),
					cmbxCategorias.getSelectedItem().toString());

			this.dispose();
			vistaPrincipal.actualizarTablaProductos("");
			JOptionPane.showMessageDialog(null, "Producto registrado");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void setCbxCategoria() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cmbxCategorias.setModel(model);

        ArrayList<String> categorias = controladorCategorias.getAllCategories();
        model.addElement("Seleccione una categoría"); // Agrega la opción predeterminada

        for (String categoria : categorias) {
            model.addElement(categoria); // Agrega los nombres de las categorías al ComboBoxModel
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cmbxCategorias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtDistribuidor;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
