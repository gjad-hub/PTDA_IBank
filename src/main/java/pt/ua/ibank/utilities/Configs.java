package pt.ua.ibank.utilities;

import java.util.prefs.Preferences;
import pt.ua.ibank.DTO.Cartao;
import pt.ua.ibank.DTO.Cliente;
import pt.ua.ibank.IBank;

public class Configs {

    private static final Preferences prefs = Preferences.userNodeForPackage(IBank.class);
    public static boolean loginOK = false;
    
    public static Cliente LocalClient;
    public static Cartao LocalClientCard;
    
    public static String Uname;
    public static boolean Usave;

    public static void save() {
        prefs.put("Uname", Uname);
        prefs.putBoolean("Usave", Usave);
    }

    public static void load() {
        Uname = prefs.get("Uname", "");
        Usave = prefs.getBoolean("Usave", false);
    }
}
