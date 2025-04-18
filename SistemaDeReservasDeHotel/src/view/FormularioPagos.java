/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import controller.PagoDAO;
import controller.ReservaDAO;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Pago;
import model.Reserva;

/**
 *
 * @author louis
 */
public class FormularioPagos extends javax.swing.JDialog {

    /**
     * Creates new form FormularioPagos
     */
    private Pago pago;
    static private String Mode = "";
    static private long id = 0;
    private ListadoPagos parent;
    PagoDAO dao = new PagoDAO();
    ReservaDAO reserva_dao = new ReservaDAO();
    
    public FormularioPagos(ListadoPagos parent, boolean modal, String Mode, long id) {
        super(parent, modal);    
        this.parent = parent;
        initComponents();
        
        this.Mode = Mode;
        this.id = id;
        
        pago = new Pago();
        
        List<Long> idsReservas = reserva_dao.getAll()
            .stream()
            .map(reserva -> ((Reserva) reserva).getId())
            .collect(Collectors.toList());
        DefaultComboBoxModel<String> modelReservas = new DefaultComboBoxModel<>();
        for (Long itemIdsReservas : idsReservas) {
            modelReservas.addElement(Long.toString(itemIdsReservas));
        }
        
        if(Mode.equals("UPD") || Mode.equals("DLT")){
            pago = (Pago) dao.getById(id);
            modelReservas.setSelectedItem(Long.toString(pago.getReservaId()));
            txt_field_No_Factura.setText(pago.getNoFactura());
            txt_field_Rtn.setText(pago.getRtn());
            date_chooser_Fecha.setDate(pago.getFecha());
            txt_field_Monto.setText(Float.toString(pago.getMonto()));
        }
        
        txt_field_Reserva_Id.setModel(modelReservas);
        
        if(Mode.equals("INS")){
            btn_operacion.setText("Insertar");
        }else if(Mode.equals("UPD")){
            btn_operacion.setText("Modificar");
        }else if(Mode.equals("DLT")){
            btn_operacion.setText("Borrar");
        }
        
    }
    
    private boolean validar(){
        if(txt_field_Reserva_Id.getSelectedItem().equals(null)){
            JOptionPane.showMessageDialog(parent, "Debe ingresar un id de reserva.", "ERROR DE VALIDACIÓN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Long idDeReserva = Long.parseLong(txt_field_Reserva_Id.getSelectedItem().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Debe ingresar un número válido para el id de reserva.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(txt_field_No_Factura.getText().equals(null) || txt_field_No_Factura.getText().equals("")){
            JOptionPane.showMessageDialog(parent, "Debe ingresar un no. de factura.", "ERROR DE VALIDACIÓN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(txt_field_Rtn.getText().equals(null) || txt_field_Rtn.getText().equals("")){
            JOptionPane.showMessageDialog(parent, "Debe ingresar un RTN.", "ERROR DE VALIDACIÓN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(date_chooser_Fecha.getDate() == null || date_chooser_Fecha.getDate().equals(null)){
            JOptionPane.showMessageDialog(parent, "Debe ingresar una fecha.", "ERROR DE VALIDACIÓN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(txt_field_Monto.getText().equals(null) || txt_field_Monto.getText().equals("")){
            JOptionPane.showMessageDialog(parent, "Debe ingresar un monto.", "ERROR DE VALIDACIÓN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            float monto = Float.parseFloat(txt_field_Monto.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Debe ingresar un número válido para el monto.", "ERROR DE VALIDACIÓN", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Ha ocurrido un error inesperado.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Reserva_Id = new javax.swing.JLabel();
        txt_field_Reserva_Id = new javax.swing.JComboBox<>();
        label_No_Factura = new javax.swing.JLabel();
        txt_field_No_Factura = new javax.swing.JTextField();
        label_Rtn = new javax.swing.JLabel();
        txt_field_Rtn = new javax.swing.JTextField();
        label_Fecha = new javax.swing.JLabel();
        date_chooser_Fecha = new com.toedter.calendar.JDateChooser();
        label_Monto = new javax.swing.JLabel();
        txt_field_Monto = new javax.swing.JTextField();
        btn_operacion = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Pagos");
        setBackground(new java.awt.Color(204, 204, 255));
        setMaximumSize(new java.awt.Dimension(700, 450));
        setMinimumSize(new java.awt.Dimension(700, 450));
        setName("FormularioPagos"); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 450));
        setSize(new java.awt.Dimension(700, 450));

        panel_Background.setBackground(new java.awt.Color(204, 204, 255));
        panel_Background.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel_Background.setLayout(new java.awt.GridLayout(6, 2, 40, 40));

        label_Reserva_Id.setText("Id de reserva");
        label_Reserva_Id.setName("label_Reserva_Id"); // NOI18N
        panel_Background.add(label_Reserva_Id);

        txt_field_Reserva_Id.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-1" }));
        txt_field_Reserva_Id.setName("txt_field_Reserva_Id"); // NOI18N
        panel_Background.add(txt_field_Reserva_Id);

        label_No_Factura.setText("No. de factura");
        label_No_Factura.setName("label_No_Factura"); // NOI18N
        panel_Background.add(label_No_Factura);

        txt_field_No_Factura.setName("txt_field_No_Factura"); // NOI18N
        panel_Background.add(txt_field_No_Factura);

        label_Rtn.setText("Rtn");
        label_Rtn.setName("label_Rtn"); // NOI18N
        panel_Background.add(label_Rtn);

        txt_field_Rtn.setName("txt_field_Rtn"); // NOI18N
        panel_Background.add(txt_field_Rtn);

        label_Fecha.setText("Fecha");
        label_Fecha.setName("label_Fecha"); // NOI18N
        panel_Background.add(label_Fecha);

        date_chooser_Fecha.setName("date_chooser_Fecha"); // NOI18N
        panel_Background.add(date_chooser_Fecha);

        label_Monto.setText("Monto");
        label_Monto.setName("label_Monto"); // NOI18N
        panel_Background.add(label_Monto);

        txt_field_Monto.setText("0");
        txt_field_Monto.setName("txt_field_Monto"); // NOI18N
        panel_Background.add(txt_field_Monto);

        btn_operacion.setText("Operacion");
        btn_operacion.setName("btn_operacion"); // NOI18N
        btn_operacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_operacionActionPerformed(evt);
            }
        });
        panel_Background.add(btn_operacion);

        btn_cancelar.setText("Cancelar");
        btn_cancelar.setName("btn_cancelar"); // NOI18N
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        panel_Background.add(btn_cancelar);

        getContentPane().add(panel_Background, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_operacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_operacionActionPerformed
       if(Mode.equals("INS")){
            boolean validacion = validar();
            if(validacion){
                Pago newPago = new Pago();
                newPago.setReservaId(Long.parseLong(txt_field_Reserva_Id.getSelectedItem().toString()));
                newPago.setNoFactura(txt_field_No_Factura.getText());
                newPago.setRtn(txt_field_Rtn.getText());
                newPago.setFecha(new Date(date_chooser_Fecha.getDate().getTime()));
                newPago.setMonto(Float.parseFloat(txt_field_Monto.getText()));
                
                boolean operacion = dao.insert(newPago);
                if(operacion){
                    parent.refrescarDatos();
                    this.dispose();
                }
            }
        }
        if(Mode.equals("UPD")){
            boolean validacion = validar();
            if(validacion){
                pago.setReservaId(Long.parseLong(txt_field_Reserva_Id.getSelectedItem().toString()));
                pago.setNoFactura(txt_field_No_Factura.getText());
                pago.setRtn(txt_field_Rtn.getText());
                pago.setFecha(new Date(date_chooser_Fecha.getDate().getTime()));
                pago.setMonto(Float.parseFloat(txt_field_Monto.getText()));
                
                boolean operacion = dao.update(pago);
                if(operacion){
                    parent.refrescarDatos();
                    this.dispose();
                }
            }
        }
        if(Mode.equals("DLT")){
            boolean operacion = false;
            try {
                operacion = dao.delete(pago.getId());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, "Ha ocurrido un error: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            if(operacion){
                parent.refrescarDatos();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(parent, "No fue posible eliminar el objeto seleccionado", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_operacionActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

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
            java.util.logging.Logger.getLogger(FormularioPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioPagos dialog = new FormularioPagos(new ListadoPagos(), true, Mode, id);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_operacion;
    private com.toedter.calendar.JDateChooser date_chooser_Fecha;
    private javax.swing.JLabel label_Fecha;
    private javax.swing.JLabel label_Monto;
    private javax.swing.JLabel label_No_Factura;
    private javax.swing.JLabel label_Reserva_Id;
    private javax.swing.JLabel label_Rtn;
    private javax.swing.JPanel panel_Background;
    private javax.swing.JTextField txt_field_Monto;
    private javax.swing.JTextField txt_field_No_Factura;
    private javax.swing.JComboBox<String> txt_field_Reserva_Id;
    private javax.swing.JTextField txt_field_Rtn;
    // End of variables declaration//GEN-END:variables
}
