package pt.ua.ibank.utilities;

import java.util.prefs.Preferences;
import pt.ua.ibank.DTO.Cartao;
import pt.ua.ibank.DTO.Cliente;
import pt.ua.ibank.DTO.Funcionario;
import pt.ua.ibank.IBank;

public class Configs {

    private static final Preferences prefs = Preferences.userNodeForPackage(
                                     IBank.class);
    public static boolean loginOK = false;

    public static Cliente LocalClient;
    public static Cartao LocalClientCard;
    public static Funcionario LocalFuncionario;

    public static String Uname;
    public static boolean Usave;

    public static final int CODIGO_SUCESSO = 1;
    public static final int CODIGO_ERRO = 2;
    public static final int CODIGO_ERRO_EMAIL = 3;

    public static final String NOME_REGEX =
                               "^[a-zA-ZáéíóúÁÉÍÓÚäëïöüÄËÏÖÜãõÃÕñÑçÇ\\s'-]+$";
    public static final String EMAIL_REGEX =
                               "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final String NINE_DIGIT_REGEX = "^\\d{9}$";

    public static void save() {
        prefs.put("Uname", Uname);
        prefs.putBoolean("Usave", Usave);
    }

    public static void load() {
        Uname = prefs.get("Uname", "");
        Usave = prefs.getBoolean("Usave", false);
    }
}
