/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.utilities;

import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 * Private class used to replace the standard DesktopManager for JDesktopPane.
 * Used to provide scrollbar functionality.
 */
public class MDIDesktopManager extends DefaultDesktopManager {

    private final MDIDesktopPane desktop;

    public MDIDesktopManager(MDIDesktopPane desktop) {
        this.desktop = desktop;
    }

    @Override
    public void endResizingFrame(JComponent f) {
        super.endResizingFrame(f);
        resizeDesktop();
    }

    @Override
    public void endDraggingFrame(JComponent f) {
        super.endDraggingFrame(f);
        resizeDesktop();
    }

    public void setNormalSize() {
        JScrollPane scrollPane = getScrollPane();
        int x = 0;
        int y = 0;
        Insets scrollInsets = getScrollPaneInsets();

        if (scrollPane != null) {
            Dimension d = scrollPane.getVisibleRect().getSize();
            if (scrollPane.getBorder() != null) {
                d.setSize(d.getWidth() - scrollInsets.left - scrollInsets.right,
                          d.getHeight()
                          - scrollInsets.top - scrollInsets.bottom);
            }

            d.setSize(d.getWidth() - 20, d.getHeight() - 20);
            desktop.setAllSize(x, y);
            scrollPane.invalidate();
            scrollPane.validate();
        }
    }

    private Insets getScrollPaneInsets() {
        JScrollPane scrollPane = getScrollPane();
        if (scrollPane == null) {
            return new Insets(0, 0, 0, 0);
        } else {
            return getScrollPane().getBorder().getBorderInsets(scrollPane);
        }
    }

    private JScrollPane getScrollPane() {
        if (desktop.getParent() instanceof JViewport) {
            JViewport viewPort = (JViewport) desktop.getParent();
            if (viewPort.getParent() instanceof JScrollPane) {
                return (JScrollPane) viewPort.getParent();
            }
        }
        return null;
    }

    protected void resizeDesktop() {
        int x = 0;
        int y = 0;
        JScrollPane scrollPane = getScrollPane();
        Insets scrollInsets = getScrollPaneInsets();

        if (scrollPane != null) {
            JInternalFrame allFrames[] = desktop.getAllFrames();
            for (JInternalFrame allFrame : allFrames) {
                if (allFrame.getX() + allFrame.getWidth() > x) {
                    x = allFrame.getX() + allFrame.getWidth();
                }
                if (allFrame.getY() + allFrame.getHeight() > y) {
                    y = allFrame.getY() + allFrame.getHeight();
                }
            }
            Dimension d = scrollPane.getVisibleRect().getSize();
            if (scrollPane.getBorder() != null) {
                d.setSize(d.getWidth() - scrollInsets.left - scrollInsets.right,
                          d.getHeight()
                          - scrollInsets.top - scrollInsets.bottom);
            }

            if (x <= d.getWidth()) {
                x = ((int) d.getWidth()) - 20;
            }
            if (y <= d.getHeight()) {
                y = ((int) d.getHeight()) - 20;
            }
            desktop.setAllSize(x, y);
            scrollPane.invalidate();
            scrollPane.validate();
        }
    }
}
