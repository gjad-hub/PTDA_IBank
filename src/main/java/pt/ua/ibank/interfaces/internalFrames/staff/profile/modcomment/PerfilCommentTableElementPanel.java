/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.modcomment;

import java.time.LocalDateTime;
import pt.ua.ibank.DTO.ModCommentProfile;

/**
 *
 * @author ricar
 */
public class PerfilCommentTableElementPanel extends javax.swing.JPanel {

    /**
     * Creates new form ClientProfileListElementPanel
     *
     * @param numCartao
     * @param dataValidade
     * @param estado
     * @param saldo
     */
    public PerfilCommentTableElementPanel() {
        initComponents();

        jXTextArea1.setLineWrap(true);
        jXTextArea1.setWrapStyleWord(true);
        jXTextArea1.setText("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
        lblmodName.setText("Administrator");
        lblDateTime.setText("01-02-24 5:20 PM");
    }

    public PerfilCommentTableElementPanel(String funcionarioID, String descricao,
            String date) {
        initComponents();

        jXTextArea1.setLineWrap(true);
        jXTextArea1.setWrapStyleWord(true);
        jXTextArea1.setText(descricao);
        //substituir por um metodo DAO que pega o nome da pessoa
        lblmodName.setText(funcionarioID);
        lblDateTime.setText(date);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textBoxes1 = new org.fit.cssbox.demo.TextBoxes();
        lblTtitle = new javax.swing.JLabel();
        lblmodName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDateTime = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTextArea1 = new org.jdesktop.swingx.JXTextArea();

        setBackground(new java.awt.Color(254, 254, 254));

        lblTtitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTtitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons_20/moderator-comment.png"))); // NOI18N

        lblmodName.setForeground(new java.awt.Color(102, 102, 102));
        lblmodName.setText("nomeModerador");

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("em");

        lblDateTime.setForeground(new java.awt.Color(102, 102, 102));
        lblDateTime.setText("data");

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setViewportView(jXTextArea1);

        jXTextArea1.setColumns(20);
        jXTextArea1.setLineWrap(true);
        jXTextArea1.setRows(5);
        jXTextArea1.setToolTipText("");
        jXTextArea1.setWrapStyleWord(true);
        jXTextArea1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jXTextArea1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jXTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblTtitle)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblmodName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTtitle))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmodName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(lblDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jXTextArea1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jXTextArea1PropertyChange

    }//GEN-LAST:event_jXTextArea1PropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTextArea jXTextArea1;
    private javax.swing.JLabel lblDateTime;
    private javax.swing.JLabel lblTtitle;
    private javax.swing.JLabel lblmodName;
    private org.fit.cssbox.demo.TextBoxes textBoxes1;
    // End of variables declaration//GEN-END:variables
}
