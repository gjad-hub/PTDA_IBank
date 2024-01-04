package pt.ua.ibank.staff;

public class StaffMainInterface extends javax.swing.JInternalFrame {

    public StaffMainInterface() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTop = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanelNavBar = new javax.swing.JPanel();
        jBtnContas = new javax.swing.JButton();
        jBtnTransacoes = new javax.swing.JButton();
        jBtnInicio = new javax.swing.JButton();
        jPanelContent = new javax.swing.JPanel();
        LayeredPaneEditarConta = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ferramentas de Moderação");
        setFrameIcon(null);
        setMinimumSize(new java.awt.Dimension(859, 485));

        jPanelTop.setBackground(new java.awt.Color(76, 88, 160));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("iBank Ferramentas de Moderação");

        jPanel4.setBackground(new java.awt.Color(76, 88, 160));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuário");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Administrador");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addGap(336, 336, 336)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
        );

        jPanelNavBar.setMinimumSize(new java.awt.Dimension(0, 40));
        jPanelNavBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnContas.setBackground(new java.awt.Color(76, 88, 160));
        jBtnContas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBtnContas.setForeground(new java.awt.Color(255, 255, 255));
        jBtnContas.setText("Contas");
        jBtnContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnContasActionPerformed(evt);
            }
        });
        jPanelNavBar.add(jBtnContas, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 6, 145, -1));

        jBtnTransacoes.setBackground(new java.awt.Color(76, 88, 160));
        jBtnTransacoes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBtnTransacoes.setForeground(new java.awt.Color(255, 255, 255));
        jBtnTransacoes.setText("Transações");
        jBtnTransacoes.setPreferredSize(new java.awt.Dimension(72, 27));
        jPanelNavBar.add(jBtnTransacoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 6, 145, 30));

        jBtnInicio.setBackground(new java.awt.Color(76, 88, 160));
        jBtnInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBtnInicio.setForeground(new java.awt.Color(255, 255, 255));
        jBtnInicio.setText("Inicio");
        jBtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnInicioActionPerformed(evt);
            }
        });
        jPanelNavBar.add(jBtnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 145, -1));

        jPanelContent.setBackground(new java.awt.Color(255, 255, 255));

        LayeredPaneEditarConta.setPreferredSize(new java.awt.Dimension(776, 553));

        javax.swing.GroupLayout LayeredPaneEditarContaLayout = new javax.swing.GroupLayout(LayeredPaneEditarConta);
        LayeredPaneEditarConta.setLayout(LayeredPaneEditarContaLayout);
        LayeredPaneEditarContaLayout.setHorizontalGroup(
            LayeredPaneEditarContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 859, Short.MAX_VALUE)
        );
        LayeredPaneEditarContaLayout.setVerticalGroup(
            LayeredPaneEditarContaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelContentLayout = new javax.swing.GroupLayout(jPanelContent);
        jPanelContent.setLayout(jPanelContentLayout);
        jPanelContentLayout.setHorizontalGroup(
            jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LayeredPaneEditarConta, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
        );
        jPanelContentLayout.setVerticalGroup(
            jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LayeredPaneEditarConta, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanelNavBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelNavBar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(0, 0, 859, 676);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnContasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnContasActionPerformed

    private void jBtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnInicioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LayeredPaneEditarConta;
    private javax.swing.JButton jBtnContas;
    private javax.swing.JButton jBtnInicio;
    private javax.swing.JButton jBtnTransacoes;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelNavBar;
    private javax.swing.JPanel jPanelTop;
    // End of variables declaration//GEN-END:variables
}