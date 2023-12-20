/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.utilities.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author miguel
 */
/**
 * Menu component that handles the functionality expected of a standard
 * "Windows" menu for MDI applications.
 */
public class WindowMenu extends JMenu {

    private MDIDesktopPane desktop;

    private final JMenuItem cascade = new JMenuItem("Cascade");
    private final JMenuItem tile = new JMenuItem("Tile");
    private final JMenuItem fechar = new JMenuItem("Fechar Tudo");

    public WindowMenu(MDIDesktopPane desktop) {
        this.desktop = desktop;
        setText("Janelas");

        fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JInternalFrame[] array = desktop.getAllFrames();
                for (JInternalFrame jInternalFrame : array) {
                    jInternalFrame.dispose();
                }
            }
        });
        cascade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                WindowMenu.this.desktop.cascadeFrames();
            }
        });
        tile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                WindowMenu.this.desktop.tileFrames();
            }
        });
        addMenuListener(new MenuListener() {
            @Override
            public void menuCanceled(MenuEvent e) {
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                removeAll();
            }

            @Override
            public void menuSelected(MenuEvent e) {
                buildChildMenus();
            }
        });
    }
    
    /* Sets up the children menus depending on the current desktop state */
    private void buildChildMenus() {
        int i;
        ChildMenuItem menu;
        JInternalFrame[] array = desktop.getAllFrames();

        add(fechar);
        addSeparator();
        add(cascade);
        add(tile);
        if (array.length > 0) {
            addSeparator();
        }
        cascade.setEnabled(array.length > 0);
        tile.setEnabled(array.length > 0);

        for (i = 0; i < array.length; i++) {
            menu = new ChildMenuItem(array[i]);
            menu.setState(i == 0);
            menu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    JInternalFrame frame = ((ChildMenuItem) ae.getSource()).getFrame();
                    //a janela de log não fecha, apenas esconde e assim funciona
                    try {
                        frame.setVisible(true);
                    } catch (Exception e) {
                    }
                    frame.moveToFront();
                    try {
                        frame.setSelected(true);
                    } catch (PropertyVetoException e) {
                        e.printStackTrace();
                    }
                }
            });
            menu.setIcon(array[i].getFrameIcon());
            add(menu);
        }
    }

    /*
   * This JCheckBoxMenuItem descendant is used to track the child frame that
   * corresponds to a give menu.
     */
    class ChildMenuItem extends JCheckBoxMenuItem {

        private JInternalFrame frame;

        public ChildMenuItem(JInternalFrame frame) {
            super(frame.getTitle());
            this.frame = frame;
        }

        public JInternalFrame getFrame() {
            return frame;
        }
    }
}
