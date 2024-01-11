package pt.ua.ibank.interfaces.internalFrames;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pt.ua.ibank.DAO.PaymentsDAO;
import static pt.ua.ibank.utilities.Configs.LocalClient;
import pt.ua.ibank.DTO.PagamentoServicosCompras;
import static pt.ua.ibank.interfaces.clientInterface.localClientInterface;
import pt.ua.ibank.utilities.RoundedShadowPanel;

public class ManageReferences extends javax.swing.JInternalFrame {

    private final Color green = new Color(63, 153, 87);
    private final Color red = new Color(230, 45, 9);
    private final int selectedEntCl = 0;
    private final int selectedRefCl = 1;
    private final int selectedStateCl = 3;
    private final int selectedPaidCl = 4;

    public ManageReferences() {
        initComponents();
        updateInfo();
    }

    private void updateInfo() {
        f_ent.setText(LocalClient.entidade.toString());
        popular(PaymentsDAO.getAllServicos(LocalClient.numCliente));
    }

    private void popular(ArrayList<PagamentoServicosCompras> lservices) {
        DefaultTableModel modelo = (DefaultTableModel) ref_table.getModel();
        modelo.setNumRows(0);

        if (lservices != null) {
            for (PagamentoServicosCompras servicosCompras : lservices) {
                modelo.addRow(new Object[]{
                    servicosCompras.entidade,
                    servicosCompras.referencia,
                    servicosCompras.valor,
                    servicosCompras.cancelada ? "Cancelada" : "Ativa",
                    servicosCompras.pago
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backdrop = new RoundedShadowPanel(5);
        see_cards = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ref_table = new javax.swing.JTable();
        cancel_card = new javax.swing.JButton();
        status = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        f_ent = new javax.swing.JTextField();
        create_ref = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gerir Referências");

        backdrop.setBackground(new java.awt.Color(255, 255, 255));

        see_cards.setBackground(new java.awt.Color(255, 255, 255));

        ref_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entidade", "Referencia", "Valor", "Estado", "Pago"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ref_table);

        cancel_card.setText("Cancelar Entidade Referência");
        cancel_card.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_cardActionPerformed(evt);
            }
        });

        jLabel1.setText("A sua Entidade:");

        f_ent.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        f_ent.setEnabled(false);

        create_ref.setText("Criar nova Referência");
        create_ref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_refActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout see_cardsLayout = new javax.swing.GroupLayout(see_cards);
        see_cards.setLayout(see_cardsLayout);
        see_cardsLayout.setHorizontalGroup(
            see_cardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
            .addGroup(see_cardsLayout.createSequentialGroup()
                .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancel_card))
            .addGroup(see_cardsLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(f_ent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(create_ref)
                .addContainerGap())
        );
        see_cardsLayout.setVerticalGroup(
            see_cardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(see_cardsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(see_cardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(f_ent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(create_ref))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(see_cardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel_card))
                .addContainerGap())
        );

        javax.swing.GroupLayout backdropLayout = new javax.swing.GroupLayout(backdrop);
        backdrop.setLayout(backdropLayout);
        backdropLayout.setHorizontalGroup(
            backdropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backdropLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(see_cards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        backdropLayout.setVerticalGroup(
            backdropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backdropLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(see_cards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backdrop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backdrop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancel_cardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_cardActionPerformed
        status.setForeground(red);
        status.setText("");

        int selectedRowIndex = ref_table.getSelectedRow();
        selectedRowIndex = ref_table.convertRowIndexToModel(selectedRowIndex);

        Integer selectedEnt = null;
        Integer selectedRef = null;
        String state = null;
        Boolean paid = null;

        if (selectedRowIndex >= 0) {
            selectedEnt = (Integer) ref_table.getValueAt(selectedRowIndex, selectedEntCl);
            selectedRef = (Integer) ref_table.getValueAt(selectedRowIndex, selectedRefCl);
            state = (String) ref_table.getValueAt(selectedRowIndex, selectedStateCl);
            paid = (Boolean) ref_table.getValueAt(selectedRowIndex, selectedPaidCl);
        }

        if (!Objects.isNull(state) && state.equals("Cancelada")) {
            status.setText("Não pode cancelar algo que já está cancelado !");
            return;
        }

        if (!Objects.isNull(paid) && paid) {
            status.setText("Não pode cancelar uma referencia paga !");
            return;
        }

        if (!Objects.isNull(selectedEnt) && !Objects.isNull(selectedRef)) {
            int reply = JOptionPane.showConfirmDialog(null, "Deseja cancelar a referencia ? Não tem caminho de volta !", title, JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                if (PaymentsDAO.cancelPayment(selectedEnt, selectedRef)== PaymentsDAO.codigoSucesso) {
                    status.setForeground(green);
                    status.setText("Referencia cancelada !");
                    updateInfo();
                } else {
                    status.setText("Algo de inesperado aconteceu !");
                }
            }
        }
    }//GEN-LAST:event_cancel_cardActionPerformed

    private void create_refActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_refActionPerformed
        localClientInterface.addWindow(new CreateRef());
        this.dispose();
    }//GEN-LAST:event_create_refActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backdrop;
    private javax.swing.JButton cancel_card;
    private javax.swing.JButton create_ref;
    private javax.swing.JTextField f_ent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable ref_table;
    private javax.swing.JPanel see_cards;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
