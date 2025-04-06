/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.UsuarioDAO;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author louis
 */
public class ListadoUsuarios extends javax.swing.JFrame {
    
    private DefaultTableModel modeloTabla;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    /**
     * Creates new form FormularioEmpleado
     */
    public ListadoUsuarios() {  
        initComponents();
        modeloTabla = (DefaultTableModel) tablaUsuarios.getModel();
        cargarDatos();
    }
    
    public void refrescarDatos(){
        cargarDatos();
    }
    
    private void cargarDatos(){
        modeloTabla.setRowCount(0);
        List<Object> usuarios = usuarioDAO.getAll();
        for(Object usuario_uncast : usuarios){
            Usuario usuario = (Usuario) usuario_uncast;
            modeloTabla.addRow(new Object[]{ usuario.getId(), usuario.getUsername(), usuario.getRol() });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Center_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        Top_panel = new javax.swing.JPanel();
        lab_Titulo = new javax.swing.JLabel();
        Bottom_panel = new javax.swing.JPanel();
        btn_editar = new javax.swing.JButton();
        btn_Insertar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();

        setTitle("Listado de Usuarios");
        setBackground(new java.awt.Color(204, 204, 255));
        setMaximumSize(new java.awt.Dimension(600, 300));
        setMinimumSize(new java.awt.Dimension(600, 300));
        setName("ListadoUsuarios"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 300));
        setSize(new java.awt.Dimension(600, 300));

        Center_panel.setBackground(new java.awt.Color(204, 204, 255));
        Center_panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Center_panel.setName("Center_panel"); // NOI18N
        Center_panel.setLayout(new java.awt.BorderLayout());

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Usuario", "Rol"
            }
        ));
        tablaUsuarios.setName("tablaUsuarios"); // NOI18N
        tablaUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaUsuarios);

        Center_panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(Center_panel, java.awt.BorderLayout.CENTER);

        Top_panel.setBackground(new java.awt.Color(204, 204, 255));
        Top_panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Top_panel.setName("Top_panel"); // NOI18N
        Top_panel.setPreferredSize(new java.awt.Dimension(452, 50));
        Top_panel.setLayout(new java.awt.BorderLayout());

        lab_Titulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lab_Titulo.setText("Listado de Usuarios");
        lab_Titulo.setName("lab_Titulo"); // NOI18N
        Top_panel.add(lab_Titulo, java.awt.BorderLayout.CENTER);

        getContentPane().add(Top_panel, java.awt.BorderLayout.NORTH);

        Bottom_panel.setBackground(new java.awt.Color(204, 204, 255));
        Bottom_panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Bottom_panel.setName("Bottom_panel"); // NOI18N
        Bottom_panel.setLayout(new java.awt.GridLayout(1, 3, 10, 0));

        btn_editar.setText("Editar");
        btn_editar.setName("btn_editar"); // NOI18N
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        Bottom_panel.add(btn_editar);

        btn_Insertar.setText("Insertar");
        btn_Insertar.setName("btn_Insertar"); // NOI18N
        btn_Insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InsertarActionPerformed(evt);
            }
        });
        Bottom_panel.add(btn_Insertar);

        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.setName("btn_Eliminar"); // NOI18N
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        Bottom_panel.add(btn_Eliminar);

        getContentPane().add(Bottom_panel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InsertarActionPerformed
        // TODO add your handling code here:
        FormularioUsuarios p1 = new FormularioUsuarios(this, true, "INS", 0);
        p1.setLocationRelativeTo(null);
        p1.setVisible(true);
    }//GEN-LAST:event_btn_InsertarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        // TODO add your handling code here:
        int filaSeleccionado = tablaUsuarios.getSelectedRow();
        if(filaSeleccionado == -1){
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        long id = (long) modeloTabla.getValueAt(filaSeleccionado, 0);
        FormularioUsuarios p1 = new FormularioUsuarios(this, true, "UPD", id);
        p1.setLocationRelativeTo(null);
        p1.setVisible(true);
        
        
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        // TODO add your handling code here:
        int filaSeleccionado = tablaUsuarios.getSelectedRow();
        if(filaSeleccionado == -1){
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        long id = (long) modeloTabla.getValueAt(filaSeleccionado, 0);
        FormularioUsuarios p1 = new FormularioUsuarios(this, true, "DLT", id);
        p1.setLocationRelativeTo(null);
        p1.setVisible(true);
    }//GEN-LAST:event_btn_EliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListadoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListadoUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bottom_panel;
    private javax.swing.JPanel Center_panel;
    private javax.swing.JPanel Top_panel;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Insertar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lab_Titulo;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}
