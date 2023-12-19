package pt.ua.ibank;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pt.ua.ibank.interfaces.clientInterface;

public class IBank {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            FlatLightLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#ffb8fd"));
            FlatLightLaf.setup();

            UIDefaults defaults = UIManager.getLookAndFeelDefaults();
            setUIFont (new javax.swing.plaf.FontUIResource("SF Pro Display",Font.PLAIN,14));


            if (defaults.get("Table.alternateRowColor") == null) {
                defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
            }
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Erro ao carregar UI");
            System.exit(98);
        }

//        LoginClientInterface lclient = new LoginClientInterface();
//        lclient.setVisible(true);
        clientInterface ci = new clientInterface();

        ci.setVisible(true);
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}
