package pt.ua.ibank.utilities;

import java.util.prefs.Preferences;
import pt.ua.ibank.DTO.Card;
import pt.ua.ibank.DTO.Client;
import pt.ua.ibank.DTO.Employee;
import pt.ua.ibank.IBank;

public class Configs {

    private static final Preferences prefs = Preferences.userNodeForPackage(IBank.class);
    public static boolean loginOK = false;

    public static Client LocalClient;
    public static Card LocalClientCard;
    public static Employee LocalEmployee;

    public static String Uname;
    public static boolean Usave;

    public static String Fname;
    public static boolean Fsave;

    public static final int SUCCESS_CODE = 1;
    public static final int ERROR_CODE = 2;
    public static final int EMAIL_ERROR_CODE = 3;

    public static final String NAME_REGEX = "^[a-zA-ZáéíóúÁÉÍÓÚäëïöüÄËÏÖÜãõÃÕñÑçÇ\\s'-]+$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final String NINE_DIGIT_REGEX = "^\\d{9}$";

    public static void saveU() {
        prefs.put("Uname", Uname);
        prefs.putBoolean("Usave", Usave);
    }

    public static void saveF() {
        prefs.put("Fname", Fname);
        prefs.putBoolean("Fsave", Fsave);
    }

    public static void loadU() {
        Uname = prefs.get("Uname", "");
        Usave = prefs.getBoolean("Usave", false);
    }

    public static void loadF() {
        Fname = prefs.get("Fname", "");
        Fsave = prefs.getBoolean("Fsave", false);
    }
}
