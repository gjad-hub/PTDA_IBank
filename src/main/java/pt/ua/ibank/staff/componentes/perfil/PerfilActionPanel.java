/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pt.ua.ibank.staff.componentes.perfil;

import java.awt.event.ActionEvent;
import pt.ua.ibank.staff.componentes.perfil.PerfilTableActionEvent;

/**
 *
 * @author ricar
 */
public class PerfilActionPanel extends javax.swing.JPanel {

    /**
     * Creates new form ClientProfilePanelAction
     */
    String data;

    public PerfilActionPanel() {
        initComponents();
        data = "";
        jTextFieldNewValue.setVisible(false);
        cmdCancel.setVisible(false);
        cmdSave.setVisible(false);
        cmdEdit.setLocation(cmdCancel.getLocation());
    }

    public PerfilActionPanel(String initialValue) {
        initComponents();
        this.data = initialValue;
        jTextFieldNewValue.setVisible(false);
        cmdCancel.setVisible(false);
        cmdSave.setVisible(false);
        cmdEdit.setLocation(cmdCancel.getLocation());
    }

    public void InitEvent(PerfilTableActionEvent event, int row) {
        cmdEdit.addActionListener((ActionEvent e) -> {
            jTextFieldNewValue.setText(data);
            event.onEdit(row);

        });
        cmdSave.addActionListener((ActionEvent e) -> {
            String newValue = jTextFieldNewValue.getText();
            data = newValue;
            event.onSave(row, newValue);
        });
        cmdCancel.addActionListener((ActionEvent e) -> {
            event.onCancel(row);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdEdit = new pt.ua.ibank.staff.componentes.perfil.PerfilActionButton();
        cmdCancel = new pt.ua.ibank.staff.componentes.perfil.PerfilActionButton();
        cmdSave = new pt.ua.ibank.staff.componentes.perfil.PerfilActionButton();
        jTextFieldNewValue = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons_20/edit.png"))); // NOI18N
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        cmdCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons_20/cancel.jpg"))); // NOI18N
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        cmdSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons_20/tick.png"))); // NOI18N
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });

        jTextFieldNewValue.setToolTipText("eeeee");
        jTextFieldNewValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNewValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldNewValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldNewValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cmdCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        cmdCancel.setVisible(true);
        cmdSave.setVisible(true);
        cmdEdit.setVisible(false);
        jTextFieldNewValue.setVisible(true);
    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        cmdCancel.setVisible(false);
        cmdSave.setVisible(false);
        cmdEdit.setVisible(true);
        jTextFieldNewValue.setVisible(false);
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        cmdCancel.setVisible(false);
        cmdSave.setVisible(false);
        cmdEdit.setVisible(true);
        jTextFieldNewValue.setVisible(false);
    }//GEN-LAST:event_cmdSaveActionPerformed

    private void jTextFieldNewValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNewValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNewValueActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pt.ua.ibank.staff.componentes.perfil.PerfilActionButton cmdCancel;
    private pt.ua.ibank.staff.componentes.perfil.PerfilActionButton cmdEdit;
    private pt.ua.ibank.staff.componentes.perfil.PerfilActionButton cmdSave;
    private javax.swing.JTextField jTextFieldNewValue;
    // End of variables declaration//GEN-END:variables
}