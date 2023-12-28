package pt.ua.ibank;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.util.Collections;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pt.ua.ibank.DTO.Cliente;
import pt.ua.ibank.interfaces.clientInterface;
import pt.ua.ibank.interfaces.dialogLogin;
import pt.ua.ibank.utilities.Configs;

public class IBank {

    public static Cliente client = null;
    public static clientInterface interf;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            FlatLightLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#79658C"));
            FlatLightLaf.setup();

            //setUIFont (new javax.swing.plaf.FontUIResource("SF Pro Display",Font.PLAIN,12));
            UIDefaults defaults = UIManager.getLookAndFeelDefaults();

            if (defaults.get("Table.alternateRowColor") == null) {
                defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
            }
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Erro ao carregar UI");
            System.exit(98);
        }
        
        dialogLogin login = new dialogLogin(null, true);
        login.setVisible(true);

        if (Configs.loginOK) {
            interf = new clientInterface();
            interf.setVisible(true);
        }

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
