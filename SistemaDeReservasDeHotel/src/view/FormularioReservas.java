/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import controller.HabitacionDAO;
import controller.HuespedDAO;
import controller.ReservaDAO;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Reserva;
import java.util.stream.Collectors;
import model.Habitacion;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import model.Huesped;

/**
 *
 * @author louis
 */
public class FormularioReservas extends javax.swing.JDialog {

    /**
     * Creates new form FormularioReservas
     */
    private Reserva reserva;
    static private String Mode = "";
    static private long id = 0;
    private ListadoReservas parent;
    ReservaDAO dao = new ReservaDAO();
    HabitacionDAO habitacion_dao = new HabitacionDAO();
    HuespedDAO huesped_dao = new HuespedDAO();
    
    public FormularioReservas(ListadoReservas parent, boolean modal, String Mode, long id) {
        super(parent, modal);    
        this.parent = parent;
        initComponents();
        
        this.Mode = Mode;
        this.id = id;
        
        reserva = new Reserva();
        
        List<Long> idsHabitaciones = habitacion_dao.getAll()
            .stream()
            .map(habitacion -> ((Habitacion) habitacion).getId())
            .collect(Collectors.toList());
        DefaultComboBoxModel<String> modelHabitaciones = new DefaultComboBoxModel<>();
        for (Long itemIdsHabitaciones : idsHabitaciones) {
            modelHabitaciones.addElement(Long.toString(itemIdsHabitaciones));
        }

        List<Long> idsHuespedes = huesped_dao.getAll()
            .stream()
            .map(huesped -> ((Huesped) huesped).getId())
            .collect(Collectors.toList());
        DefaultComboBoxModel<String> modelHuespedes = new DefaultComboBoxModel<>();
        for (Long itemIdsHuespedes : idsHuespedes) {
            modelHuespedes.addElement(Long.toString(itemIdsHuespedes));
        }
        
        if(Mode.equals("UPD") || Mode.equals("DLT")){
            reserva = (Reserva) dao.getById(id);
            date_chooser_Fecha_De_Inicio.setDate(reserva.getFechaDeInicio());
            txt_field_Horas.setText(Integer.toString(reserva.getHoras()));
            modelHabitaciones.setSelectedItem(reserva.getHabitacionId());
            modelHuespedes.setSelectedItem(reserva.getHuespedId());
        }
        
        txt_field_Habitacion_Id.setModel(modelHabitaciones);
        txt_field_Huesped_Id.setModel(modelHuespedes);
        
        if (Mode.equals("INS")){
            btn_operacion.setText("Insertar");
        }else if(Mode.equals("UPD")){
            btn_operacion.setText("Modificar");
        }else if(Mode.equals("DLT")){
            btn_operacion.setText("Borrar");
        }
    }
    
    private boolean validar(){
        if(date_chooser_Fecha_De_Inicio.getDate() == null || date_chooser_Fecha_De_Inicio.getDate().equals(null)){
            JOptionPane.showMessageDialog(parent, "Debe ingresar una fecha de inicio.", "ERROR DE VALIDACIÓN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(txt_field_Horas.getText().equals(null) || txt_field_Horas.getText().equals("")){
            JOptionPane.showMessageDialog(parent, "Debe ingresar una cantidad de horas.", "ERROR DE VALIDACIÓN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            int horas = Integer.parseInt(txt_field_Horas.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Debe ingresar un número válido para la cantidad de horas.", "ERROR DE VALIDACIÓN", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Ha ocurrido un error inesperado.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(txt_field_Habitacion_Id.getSelectedItem().equals(null)){
            JOptionPane.showMessageDialog(parent, "Debe ingresar un id de habitacion.", "ERROR DE VALIDACIÓN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Long idDeHabitacion = Long.parseLong(txt_field_Habitacion_Id.getSelectedItem().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Debe ingresar un número válido para el id de habitacion.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(txt_field_Huesped_Id.getSelectedItem().equals(null)){
            JOptionPane.showMessageDialog(parent, "Debe ingresar un id de huesped.", "ERROR DE VALIDACIÓN", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Long idDeHuesped = Long.parseLong(txt_field_Huesped_Id.getSelectedItem().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Debe ingresar un número válido para el id de huesped.", "ERROR", JOptionPane.ERROR_MESSAGE);
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

        jSpinner1 = new javax.swing.JSpinner();
        panel_Background = new javax.swing.JPanel();
        label_Fecha_De_Inicio = new javax.swing.JLabel();
        date_chooser_Fecha_De_Inicio = new com.toedter.calendar.JDateChooser();
        label_Horas = new javax.swing.JLabel();
        txt_field_Horas = new javax.swing.JTextField();
        label_Habitacion_Id = new javax.swing.JLabel();
        txt_field_Habitacion_Id = new javax.swing.JComboBox<>();
        label_Huesped_Id = new javax.swing.JLabel();
        txt_field_Huesped_Id = new javax.swing.JComboBox<>();
        btn_operacion = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Reservas");
        setBackground(new java.awt.Color(204, 204, 255));
        setMaximumSize(new java.awt.Dimension(700, 450));
        setMinimumSize(new java.awt.Dimension(700, 450));
        setName("FormularioReservas"); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 450));
        setSize(new java.awt.Dimension(700, 450));

        panel_Background.setBackground(new java.awt.Color(204, 204, 255));
        panel_Background.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel_Background.setLayout(new java.awt.GridLayout(5, 2, 40, 30));

        label_Fecha_De_Inicio.setText("Fecha de inicio");
        label_Fecha_De_Inicio.setName("label_Fecha_De_Inicio"); // NOI18N
        panel_Background.add(label_Fecha_De_Inicio);
        label_Fecha_De_Inicio.getAccessibleContext().setAccessibleName("Fecha_De_Inicio");

        date_chooser_Fecha_De_Inicio.setName("date_chooser_Fecha_De_Inicio"); // NOI18N
        panel_Background.add(date_chooser_Fecha_De_Inicio);

        label_Horas.setText("Horas");
        label_Horas.setName("label_Horas"); // NOI18N
        panel_Background.add(label_Horas);

        txt_field_Horas.setText("0");
        txt_field_Horas.setName("txt_field_Horas"); // NOI18N
        panel_Background.add(txt_field_Horas);

        label_Habitacion_Id.setText("Id de habitacion");
        label_Habitacion_Id.setName("label_Habitacion_Id"); // NOI18N
        panel_Background.add(label_Habitacion_Id);
        label_Habitacion_Id.getAccessibleContext().setAccessibleName("Habitacion_Id");

        txt_field_Habitacion_Id.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-1" }));
        txt_field_Habitacion_Id.setName("txt_field_Habitacion_Id"); // NOI18N
        panel_Background.add(txt_field_Habitacion_Id);

        label_Huesped_Id.setText("Id de huesped");
        label_Huesped_Id.setName("label_Huesped_Id"); // NOI18N
        panel_Background.add(label_Huesped_Id);
        label_Huesped_Id.getAccessibleContext().setAccessibleName("Huesped_Id");

        txt_field_Huesped_Id.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-1" }));
        txt_field_Huesped_Id.setName("txt_field_Huesped_Id"); // NOI18N
        panel_Background.add(txt_field_Huesped_Id);

        btn_operacion.setText("Operación");
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

        getContentPane().add(panel_Background, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_operacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_operacionActionPerformed
// TODO add your handling code here:
        if(Mode.equals("INS")){
            boolean validacion = validar();
            if(validacion){
                Reserva newReserva = new Reserva();
                newReserva.setFechaDeInicio(Timestamp.from(date_chooser_Fecha_De_Inicio.getDate().toInstant()));
                newReserva.setHoras(Integer.parseInt(txt_field_Horas.getText()));
                newReserva.setHabitacionId(Long.parseLong(txt_field_Habitacion_Id.getSelectedItem().toString()));
                newReserva.setHuespedId(Long.parseLong(txt_field_Huesped_Id.getSelectedItem().toString()));
                
                boolean operacion = dao.insert(newReserva);
                if(operacion){
                    parent.refrescarDatos();
                    this.dispose();
                }
            }
        }
        if(Mode.equals("UPD")){
            boolean validacion = validar();
            if(validacion){
                reserva.setFechaDeInicio(Timestamp.from(date_chooser_Fecha_De_Inicio.getDate().toInstant()));
                reserva.setHoras(Integer.parseInt(txt_field_Horas.getText()));
                reserva.setHabitacionId(Long.parseLong(txt_field_Habitacion_Id.getSelectedItem().toString()));
                reserva.setHuespedId(Long.parseLong(txt_field_Huesped_Id.getSelectedItem().toString()));
                
                boolean operacion = dao.update(reserva);
                if(operacion){
                    parent.refrescarDatos();
                    this.dispose();
                }
            }
        }
        if(Mode.equals("DLT")){
            boolean operacion = false;
            try {
                operacion = dao.delete(reserva.getId());
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
            java.util.logging.Logger.getLogger(FormularioReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioReservas dialog = new FormularioReservas(new ListadoReservas(), true, Mode, id);
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
    private com.toedter.calendar.JDateChooser date_chooser_Fecha_De_Inicio;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel label_Fecha_De_Inicio;
    private javax.swing.JLabel label_Habitacion_Id;
    private javax.swing.JLabel label_Horas;
    private javax.swing.JLabel label_Huesped_Id;
    private javax.swing.JPanel panel_Background;
    private javax.swing.JComboBox<String> txt_field_Habitacion_Id;
    private javax.swing.JTextField txt_field_Horas;
    private javax.swing.JComboBox<String> txt_field_Huesped_Id;
    // End of variables declaration//GEN-END:variables
}
