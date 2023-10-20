package pt.ua.ibank.logic;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class LoginClientLogic {

    public LoginClientLogic() {}

    public void createAnswerAsyncThread() {

        //createSocketChannel();
//        CompletableFuture.supplyAsync(() -> reciever.recieveIncomingInteger()).thenAccept((answer) -> {
//
//            System.out.println(String.format("Server response: %s\nActual response: %d", answer == 0 ? "correct" : "wrong password", answer));
//        });

    }

    public void showErrorMessage(String message) {
        JOptionPane optionPane = new JOptionPane(message);
        JDialog dialog = optionPane.createDialog("Error!");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);

        try {
            Thread.sleep(300);
            System.exit(0);
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
