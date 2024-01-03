package pt.ua.ibank.interfaces.internalFrames;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import pt.ua.ibank.DTO.Transacoes;
import pt.ua.ibank.DAO.TransacoesDAO;
import static pt.ua.ibank.DTO.Cliente.LocalClient;
import static pt.ua.ibank.interfaces.clientInterface.localClientInterface;
import pt.ua.ibank.utilities.RoundedShadowPanel;

public class DashBoard extends javax.swing.JInternalFrame {

    public DashBoard() {
        initComponents();
        popular(TransacoesDAO.getTransacoes(LocalClient.numCliente));
        saldo.setText(LocalClient.saldo.toString() + " EUR");
        iban.setText(maskString(LocalClient.numConta, 10));
        nomeTitularCartao.setText(LocalClient.nome);
        numeroCartao.setText("0000 0000 0000 0000");
        dataValidade.setText("12/28");
    }
    
    private String maskString(String string, int char_visible){
        return string.substring(0, char_visible) + "*".repeat(string.length() - char_visible);
    }

    private void popular(ArrayList<Transacoes> ltransacoes) {
        DefaultTableModel modelo = (DefaultTableModel) t_table.getModel();
        modelo.setNumRows(0);

        if (ltransacoes != null) {
            for (Transacoes tr : ltransacoes) {
                modelo.addRow(new Object[]{
                    tr.data,
                    tr.descricao,
                    (tr.valor > 0 ? "+" + tr.valor : tr.valor) + " €"
                });
            }
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

        jPanel2 = new javax.swing.JPanel();
        transacao = new RoundedShadowPanel(10);
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        stat = new RoundedShadowPanel(10);
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        saldo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        iban = new javax.swing.JLabel();
        see_iban = new javax.swing.JCheckBox();
        cards = new RoundedShadowPanel(10);
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        tipoCartãoPrincipal = new javax.swing.JLabel();
        logoOuNome = new javax.swing.JLabel();
        nomeTitularCartao = new javax.swing.JLabel();
        numeroCartao = new javax.swing.JLabel();
        dataValidade = new javax.swing.JLabel();
        bgcardImage = new javax.swing.JLabel();
        cards1 = new RoundedShadowPanel(10);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("DashBoard");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons_20/dashboard.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        transacao.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel1.setText("Transações");

        jScrollPane2.setBorder(null);

        t_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Descrição", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        t_table.setColumnSelectionAllowed(true);
        t_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(t_table);
        t_table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (t_table.getColumnModel().getColumnCount() > 0) {
            t_table.getColumnModel().getColumn(0).setMinWidth(130);
            t_table.getColumnModel().getColumn(0).setMaxWidth(130);
            t_table.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        jButton1.setText("Atualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout transacaoLayout = new javax.swing.GroupLayout(transacao);
        transacao.setLayout(transacaoLayout);
        transacaoLayout.setHorizontalGroup(
            transacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transacaoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(transacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, transacaoLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addGap(15, 15, 15))
        );
        transacaoLayout.setVerticalGroup(
            transacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(transacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(transacaoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        stat.setBackground(new java.awt.Color(255, 255, 255));
        stat.setPreferredSize(new java.awt.Dimension(178, 170));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Saldo Atual");

        jButton2.setText("Atualizar");

        saldo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        saldo.setForeground(new java.awt.Color(0, 0, 51));
        saldo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Disponível");

        jButton3.setText("Transferencia");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Pagar Serviços e compras");

        iban.setForeground(new java.awt.Color(0, 0, 51));

        see_iban.setText("Ver");
        see_iban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                see_ibanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statLayout = new javax.swing.GroupLayout(stat);
        stat.setLayout(statLayout);
        statLayout.setHorizontalGroup(
            statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(statLayout.createSequentialGroup()
                        .addComponent(iban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(see_iban))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statLayout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator3))
                .addGap(12, 12, 12))
        );
        statLayout.setVerticalGroup(
            statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iban, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(see_iban, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addGap(12, 12, 12))
        );

        cards.setBackground(new java.awt.Color(255, 255, 255));
        cards.setPreferredSize(new java.awt.Dimension(287, 170));
        cards.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        cards.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 34, 270, 10));

        jLabel2.setText("Cartão Principal");
        cards.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 124, -1));

        tipoCartãoPrincipal.setText("Débito");
        cards.add(tipoCartãoPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 12, -1, -1));

        logoOuNome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        logoOuNome.setForeground(new java.awt.Color(255, 255, 255));
        logoOuNome.setText("IBank");
        cards.add(logoOuNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, -1));

        nomeTitularCartao.setForeground(new java.awt.Color(255, 255, 255));
        nomeTitularCartao.setText("Nome Titular");
        cards.add(nomeTitularCartao, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        numeroCartao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        numeroCartao.setForeground(new java.awt.Color(255, 255, 255));
        numeroCartao.setText("Número Cartão");
        cards.add(numeroCartao, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        dataValidade.setForeground(new java.awt.Color(255, 255, 255));
        dataValidade.setText("Validade");
        cards.add(dataValidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        bgcardImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/270wchipcontactless.png"))); // NOI18N
        cards.add(bgcardImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 50, -1, -1));

        cards1.setBackground(new java.awt.Color(255, 255, 255));
        cards1.setPreferredSize(new java.awt.Dimension(287, 170));

        javax.swing.GroupLayout cards1Layout = new javax.swing.GroupLayout(cards1);
        cards1.setLayout(cards1Layout);
        cards1Layout.setHorizontalGroup(
            cards1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
        cards1Layout.setVerticalGroup(
            cards1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(transacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(stat, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(cards, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(cards1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cards1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(cards, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(stat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(transacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        popular(TransacoesDAO.getTransacoes(LocalClient.numCliente));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void see_ibanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_see_ibanActionPerformed
        if (see_iban.isSelected()) {
            iban.setText(LocalClient.numConta);
        }else{
            iban.setText(maskString(LocalClient.numConta, 10));
        }
    }//GEN-LAST:event_see_ibanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        localClientInterface.addWindow(new TransferPage());
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgcardImage;
    private javax.swing.JPanel cards;
    private javax.swing.JPanel cards1;
    private javax.swing.JLabel dataValidade;
    private javax.swing.JLabel iban;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel logoOuNome;
    private javax.swing.JLabel nomeTitularCartao;
    private javax.swing.JLabel numeroCartao;
    private javax.swing.JLabel saldo;
    private javax.swing.JCheckBox see_iban;
    private javax.swing.JPanel stat;
    private javax.swing.JTable t_table;
    private javax.swing.JLabel tipoCartãoPrincipal;
    private javax.swing.JPanel transacao;
    // End of variables declaration//GEN-END:variables
}
