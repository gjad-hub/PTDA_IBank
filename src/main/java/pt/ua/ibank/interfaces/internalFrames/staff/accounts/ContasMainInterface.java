/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.accounts;

import pt.ua.ibank.DTO.Cliente;

public class ContasMainInterface extends javax.swing.JPanel {

    /**
     * Creates new form ContasMainInterface
     */
    private pt.ua.ibank.DTO.Cliente currCliente;
    private ContasTableModel ctm;

    public ContasMainInterface() {
        initComponents();
        currClienteDisplayPanel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        LayeredPaneContas = new javax.swing.JLayeredPane();
        currClienteDisplayPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblNoDepositos = new javax.swing.JLabel();
        lblNoCartoes = new javax.swing.JLabel();
        lblMorada = new javax.swing.JLabel();
        lblNIF = new javax.swing.JLabel();
        lblTelemovel = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblNumConta = new javax.swing.JLabel();
        lblNumeroCliente = new javax.swing.JLabel();
        lblNumeroClienteValue = new javax.swing.JLabel();
        lblNumContaValue = new javax.swing.JLabel();
        lblNomeValue = new javax.swing.JLabel();
        lblEmailValue = new javax.swing.JLabel();
        lblTelemovelValue = new javax.swing.JLabel();
        lblNIFValue = new javax.swing.JLabel();
        lblMoradaValue = new javax.swing.JLabel();
        lblNoCartoesValue = new javax.swing.JLabel();
        lblNoDepositosValue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ctm = new ContasTableModel();
        jTableAccountsContent = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        textFieldProcurarConta = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnProcurarConta = new javax.swing.JButton();
        comboBoxType = new javax.swing.JComboBox<>();

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel13.setText("Numero");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(859, 562));

        LayeredPaneContas.setPreferredSize(new java.awt.Dimension(776, 553));

        currClienteDisplayPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Detalhes");

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setText("Ver perfil Completo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(74, 74, 74)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        lblNoDepositos.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNoDepositos.setText("Depositos Feitos: ");

        lblNoCartoes.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNoCartoes.setText("No Cartões: ");

        lblMorada.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblMorada.setText("Morada:");

        lblNIF.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNIF.setText("NIF: ");

        lblTelemovel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTelemovel.setText("Telemovel: ");

        lblEmail.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblEmail.setText("Email: ");

        lblNome.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNome.setText("Nome: ");

        lblNumConta.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNumConta.setText("numConta: ");

        lblNumeroCliente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNumeroCliente.setText("Numero de Cliente:");

        lblNumeroClienteValue.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblNumeroClienteValue.setText("Numero");

        lblNumContaValue.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblNumContaValue.setText("Numero");

        lblNomeValue.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblNomeValue.setText("Numero");

        lblEmailValue.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblEmailValue.setText("Numero");

        lblTelemovelValue.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblTelemovelValue.setText("Numero");

        lblNIFValue.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblNIFValue.setText("Numero");

        lblMoradaValue.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblMoradaValue.setText("Numero");

        lblNoCartoesValue.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblNoCartoesValue.setText("Numero");

        lblNoDepositosValue.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblNoDepositosValue.setText("Numero");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblEmail)
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNumeroCliente)
                            .addComponent(lblNumConta, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblTelemovel)
                            .addGap(56, 56, 56)))
                    .addComponent(lblNome, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMorada)
                            .addComponent(lblNoCartoes))
                        .addGap(51, 51, 51))
                    .addComponent(lblNIF, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNoDepositos)
                        .addGap(10, 10, 10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumeroClienteValue, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNumContaValue, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNomeValue, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEmailValue, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTelemovelValue, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNIFValue, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMoradaValue, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNoCartoesValue, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNoDepositosValue, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroCliente)
                    .addComponent(lblNumeroClienteValue))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumConta)
                    .addComponent(lblNumContaValue))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(lblEmailValue))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelemovel)
                    .addComponent(lblTelemovelValue))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNIF)
                    .addComponent(lblNIFValue))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMorada)
                    .addComponent(lblMoradaValue))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoCartoes)
                    .addComponent(lblNoCartoesValue))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoDepositos)
                    .addComponent(lblNoDepositosValue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout currClienteDisplayPanelLayout = new javax.swing.GroupLayout(currClienteDisplayPanel);
        currClienteDisplayPanel.setLayout(currClienteDisplayPanelLayout);
        currClienteDisplayPanelLayout.setHorizontalGroup(
            currClienteDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(currClienteDisplayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        currClienteDisplayPanelLayout.setVerticalGroup(
            currClienteDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currClienteDisplayPanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableAccountsContent.setModel(ctm);
        jTableAccountsContent.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTableAccountsContent);
        jTableAccountsContent.getColumnModel().getColumn(6).setCellRenderer(
            new ContasActionRenderer());

        ContasActionEvent event = (int row) -> {
            currCliente = ctm.getAccount(row);

            currClienteDisplayPanel.setVisible(true);

            lblNumeroClienteValue.setText(Integer.toString(currCliente.numCliente));
            lblNumContaValue.setText(currCliente.numConta);
            lblNomeValue.setText(currCliente.nome);
            lblEmailValue.setText(currCliente.email);
            lblTelemovelValue.setText(currCliente.telemovel);
            lblNIFValue.setText(currCliente.nif);
            lblMoradaValue.setText(currCliente.morada);

            //lblNoCartoesValue.setText(
                //Integer.toString(
                    //   ctm.getCardAmountNumberFromID(
                        //   currCliente.numCliente
                        //    )));

            lblNoDepositosValue.setText(
                Integer.toString(
                    ctm.getDepositAmountNumberFromID(
                        currCliente.numCliente
                    )));

                };

                jTableAccountsContent.getColumnModel().getColumn(6).setCellEditor(
                    new ContasCellEditor(event));
                jTableAccountsContent.getColumnModel().getColumn(6).setMaxWidth(30);

                jPanel4.setBackground(new java.awt.Color(0, 0, 0));

                jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel15.setForeground(new java.awt.Color(255, 255, 255));
                jLabel15.setText("Lista de Contas");
                jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                jLabel15.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(203, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addContainerGap(202, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                );

                jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

                jPanel8.setBackground(new java.awt.Color(0, 0, 0));

                jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel17.setForeground(new java.awt.Color(255, 255, 255));
                jLabel17.setText("Procurar Conta");
                jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                jLabel17.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

                jButton3.setBackground(new java.awt.Color(204, 204, 204));
                jButton3.setText("Tirar filtros");
                jButton3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton3ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(41, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(42, 42, 42)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                );

                btnProcurarConta.setBackground(new java.awt.Color(0, 0, 0));
                btnProcurarConta.setForeground(new java.awt.Color(255, 255, 255));
                btnProcurarConta.setText("Procurar");
                btnProcurarConta.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnProcurarContaActionPerformed(evt);
                    }
                });

                comboBoxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Email", "NIF", "Morada" }));

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnProcurarConta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(textFieldProcurarConta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                );
                jPanel6Layout.setVerticalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldProcurarConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnProcurarConta)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                LayeredPaneContas.setLayer(currClienteDisplayPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
                LayeredPaneContas.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
                LayeredPaneContas.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
                LayeredPaneContas.setLayer(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

                javax.swing.GroupLayout LayeredPaneContasLayout = new javax.swing.GroupLayout(LayeredPaneContas);
                LayeredPaneContas.setLayout(LayeredPaneContasLayout);
                LayeredPaneContasLayout.setHorizontalGroup(
                    LayeredPaneContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LayeredPaneContasLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(LayeredPaneContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(LayeredPaneContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currClienteDisplayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                );
                LayeredPaneContasLayout.setVerticalGroup(
                    LayeredPaneContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LayeredPaneContasLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
                    .addGroup(LayeredPaneContasLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currClienteDisplayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(LayeredPaneContas, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LayeredPaneContas, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(62, Short.MAX_VALUE))
                );
            }// </editor-fold>//GEN-END:initComponents

    public javax.swing.JButton getOpenProfileButton() {
        return jButton2;
    }

    public Cliente getCurrCliente() {
        return currCliente;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnProcurarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarContaActionPerformed
        String selectedType =
               String.valueOf(comboBoxType.getSelectedItem());
        String input = textFieldProcurarConta.getText();
        ctm.searchForClient(input, selectedType);
        jTableAccountsContent.repaint();
        currClienteDisplayPanel.setVisible(false);
    }//GEN-LAST:event_btnProcurarContaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ctm.resetSearchFilters();
        jTableAccountsContent.repaint();
        jScrollPane1.repaint();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LayeredPaneContas;
    private javax.swing.JButton btnProcurarConta;
    private javax.swing.JComboBox<String> comboBoxType;
    private javax.swing.JPanel currClienteDisplayPanel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAccountsContent;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmailValue;
    private javax.swing.JLabel lblMorada;
    private javax.swing.JLabel lblMoradaValue;
    private javax.swing.JLabel lblNIF;
    private javax.swing.JLabel lblNIFValue;
    private javax.swing.JLabel lblNoCartoes;
    private javax.swing.JLabel lblNoCartoesValue;
    private javax.swing.JLabel lblNoDepositos;
    private javax.swing.JLabel lblNoDepositosValue;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNomeValue;
    private javax.swing.JLabel lblNumConta;
    private javax.swing.JLabel lblNumContaValue;
    private javax.swing.JLabel lblNumeroCliente;
    private javax.swing.JLabel lblNumeroClienteValue;
    private javax.swing.JLabel lblTelemovel;
    private javax.swing.JLabel lblTelemovelValue;
    private javax.swing.JTextField textFieldProcurarConta;
    // End of variables declaration//GEN-END:variables
}
