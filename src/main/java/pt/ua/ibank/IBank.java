package pt.ua.ibank;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.util.Collections;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pt.ua.ibank.interfaces.LoginClientInterface;
import pt.ua.ibank.interfaces.clientInterface;

public class IBank {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            FlatLightLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#3c79cf"));
            FlatLightLaf.setup();

            UIDefaults defaults = UIManager.getLookAndFeelDefaults();
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
}
