/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.personal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class PerfilAccountActionButton extends javax.swing.JFrame {

    private JButton jButton;
    private JPopupMenu popupMenu;

    public PerfilAccountActionButton() {
        initComponents();
    }

    private void initComponents() {
        jButton = new JButton("Click me");
        popupMenu = new JPopupMenu();

        JMenuItem menuItem1 = new JMenuItem("Menu Item 1");

        // Add ActionListener to menu items if needed
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle menu item 1 click
                JOptionPane.showMessageDialog(PerfilAccountActionButton.this,
                                              "Menu Item 1 clicked");
            }
        });

        // Add menu items to the popup menu
        popupMenu.add(menuItem1);
        // Add ActionListener to the button to show the popup menu
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPopupMenu(e);
            }
        });

        getContentPane().setLayout(new java.awt.FlowLayout());
        getContentPane().add(jButton);

        pack();
    }

    private void showPopupMenu(ActionEvent e) {
        popupMenu.show(jButton, 0, jButton.getHeight());
    }
}
