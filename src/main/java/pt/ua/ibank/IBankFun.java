package pt.ua.ibank;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.util.Collections;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pt.ua.ibank.interfaces.funcionarioLoginDialog;
import pt.ua.ibank.interfaces.staffInterface;
import static pt.ua.ibank.interfaces.staffInterface.localStaffInterface;
import pt.ua.ibank.utilities.Configs;

public class IBankFun {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            FlatLightLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#79658C"));
            FlatLightLaf.setup();
            UIDefaults defaults = UIManager.getLookAndFeelDefaults();

            if (defaults.get("Table.alternateRowColor") == null) {
                defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
            }
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Erro ao carregar UI");
            System.exit(98);
        }

        funcionarioLoginDialog login = new funcionarioLoginDialog(null, true);
        login.setVisible(true);

        if (Configs.loginOK) {
            localStaffInterface = new staffInterface();
            localStaffInterface.setVisible(true);
        }

    }
}
