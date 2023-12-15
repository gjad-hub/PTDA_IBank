package pt.ua.ibank.interfaces;

import java.awt.Cursor;
import java.util.regex.Pattern;
import pt.ua.ibank.logic.LoginClientLogic;

public class LoginClientInterface extends javax.swing.JFrame {

    /**
     * Creates new form LoginInterface
     */
    LoginClientLogic clientLogic = null;

    public LoginClientInterface() {
        initComponents();
//make the login as focused
//in order to hold the input placeholders
        btnSubmit.grabFocus();
        panelErrorLogin.setVisible(false);
        panelRegister.setVisible(false);
        clientLogic = new LoginClientLogic();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLogIn = new javax.swing.JPanel();
        panelLoginTitle = new javax.swing.JPanel();
        lblMainTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        lblTextBox = new javax.swing.JLabel();
        panelLoginInput = new javax.swing.JPanel();
        panelErrorLogin = new javax.swing.JPanel();
        lblErrorMessage = new javax.swing.JLabel();
        LblEmail = new javax.swing.JLabel();
        jFieldEmail = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        jFieldPassword = new javax.swing.JPasswordField();
        lblCantLogin = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        panelLoginBackground = new javax.swing.JPanel();
        LblIcon = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();
        lblLoginWhiteBackground = new javax.swing.JLabel();
        panelRegister = new javax.swing.JPanel();
        panelRegisterInput = new javax.swing.JPanel();
        panelErrorRegister = new javax.swing.JPanel();
        lblErrorRegisterMessage = new javax.swing.JLabel();
        LblEmail1 = new javax.swing.JLabel();
        fieldFullName = new javax.swing.JTextField();
        lblPassword1 = new javax.swing.JLabel();
        fieldRegisterPassword = new javax.swing.JPasswordField();
        btnRegisterSubmit = new javax.swing.JButton();
        LblEmail2 = new javax.swing.JLabel();
        LblEmail3 = new javax.swing.JLabel();
        fieldRegisterEmail = new javax.swing.JTextField();
        fieldRegisterRepeatPassword = new javax.swing.JPasswordField();
        lblGoBack = new javax.swing.JLabel();
        panelRegisterTitle = new javax.swing.JPanel();
        lblMainTitle1 = new javax.swing.JLabel();
        lblSubtitle1 = new javax.swing.JLabel();
        lblTextBox1 = new javax.swing.JLabel();
        panelRegisterBackground = new javax.swing.JPanel();
        LblIRegistercon1 = new javax.swing.JLabel();
        lblLoginWhiteBackground1 = new javax.swing.JLabel();
        lblBackground1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iBank Login");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(655, 500));
        setName("mainFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(650, 458));
        setResizable(false);
        getContentPane().setLayout(null);

        panelLogIn.setOpaque(false);
        panelLogIn.setLayout(null);

        panelLoginTitle.setBackground(new java.awt.Color(204, 204, 204));
        panelLoginTitle.setLayout(null);

        lblMainTitle.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        lblMainTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMainTitle.setText("Welcome to iBank");
        panelLoginTitle.add(lblMainTitle);
        lblMainTitle.setBounds(10, 10, 270, 30);

        lblSubtitle.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblSubtitle.setText("Log In to get started!");
        panelLoginTitle.add(lblSubtitle);
        lblSubtitle.setBounds(10, 40, 150, 16);

        lblTextBox.setBackground(new java.awt.Color(204, 255, 153));
        lblTextBox.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://www.landfx.com/images/docs/kb/installation-errors/4019/blank%20welcome.PNG")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        lblTextBox.setText("..");
        lblTextBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        panelLoginTitle.add(lblTextBox);
        lblTextBox.setBounds(0, 0, 270, 70);

        panelLogIn.add(panelLoginTitle);
        panelLoginTitle.setBounds(260, 110, 270, 70);

        panelLoginInput.setBackground(new java.awt.Color(255, 255, 255));

        panelErrorLogin.setBackground(new java.awt.Color(255, 0, 51));
        panelErrorLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblErrorMessage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblErrorMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErrorMessage.setText("Error: Invalid Credentials");

        javax.swing.GroupLayout panelErrorLoginLayout = new javax.swing.GroupLayout(panelErrorLogin);
        panelErrorLogin.setLayout(panelErrorLoginLayout);
        panelErrorLoginLayout.setHorizontalGroup(
            panelErrorLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelErrorLoginLayout.createSequentialGroup()
                .addComponent(lblErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        panelErrorLoginLayout.setVerticalGroup(
            panelErrorLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblErrorMessage, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        LblEmail.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        LblEmail.setText("Email:");

        jFieldEmail.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jFieldEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jFieldEmail.setText("email@email.com");
        jFieldEmail.setToolTipText("email@email.com"); // NOI18N
        jFieldEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jFieldEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jFieldEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFieldEmailFocusGained(evt);
            }
        });

        lblPassword.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lblPassword.setText("Password:");

        jFieldPassword.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jFieldPassword.setText("jPasswordField1");
        jFieldPassword.setToolTipText("password");
        jFieldPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jFieldPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFieldPasswordFocusGained(evt);
            }
        });

        lblCantLogin.setFont(new java.awt.Font("Verdana", 2, 10)); // NOI18N
        lblCantLogin.setText("Can't login?");
        lblCantLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCantLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCantLoginMouseEntered(evt);
            }
        });

        btnSubmit.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnSubmit.setText("login");
        btnSubmit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSubmitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSubmitMouseEntered(evt);
            }
        });
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLoginInputLayout = new javax.swing.GroupLayout(panelLoginInput);
        panelLoginInput.setLayout(panelLoginInputLayout);
        panelLoginInputLayout.setHorizontalGroup(
            panelLoginInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginInputLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(panelLoginInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelErrorLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword)
                    .addGroup(panelLoginInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLoginInputLayout.createSequentialGroup()
                            .addComponent(lblCantLogin)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jFieldEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEmail, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelLoginInputLayout.setVerticalGroup(
            panelLoginInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginInputLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(panelErrorLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(panelLoginInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCantLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114))
        );

        panelLogIn.add(panelLoginInput);
        panelLoginInput.setBounds(0, 150, 230, 310);

        panelLoginBackground.setLayout(null);

        LblIcon.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        LblIcon.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://i.imgur.com/q6MNSNB.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        LblIcon.setText("Login");
        panelLoginBackground.add(LblIcon);
        LblIcon.setBounds(10, 0, 210, 100);

        lblBackground.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://i.imgur.com/8bzHM1G.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        lblBackground.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelLoginBackground.add(lblBackground);
        lblBackground.setBounds(230, 0, 420, 460);

        lblLoginWhiteBackground.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://www.landfx.com/images/docs/kb/installation-errors/4019/blank%20welcome.PNG")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        panelLoginBackground.add(lblLoginWhiteBackground);
        lblLoginWhiteBackground.setBounds(0, 0, 240, 460);

        panelLogIn.add(panelLoginBackground);
        panelLoginBackground.setBounds(0, 0, 650, 460);

        getContentPane().add(panelLogIn);
        panelLogIn.setBounds(0, 0, 650, 470);

        panelRegister.setPreferredSize(new java.awt.Dimension(900, 470));
        panelRegister.setLayout(null);

        panelRegisterInput.setBackground(new java.awt.Color(255, 255, 255));

        panelErrorRegister.setBackground(new java.awt.Color(255, 0, 51));
        panelErrorRegister.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblErrorRegisterMessage.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblErrorRegisterMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErrorRegisterMessage.setText("Error: Something else");
        lblErrorRegisterMessage.setMinimumSize(new java.awt.Dimension(170, 15));
        lblErrorRegisterMessage.setPreferredSize(new java.awt.Dimension(170, 12));

        javax.swing.GroupLayout panelErrorRegisterLayout = new javax.swing.GroupLayout(panelErrorRegister);
        panelErrorRegister.setLayout(panelErrorRegisterLayout);
        panelErrorRegisterLayout.setHorizontalGroup(
            panelErrorRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelErrorRegisterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblErrorRegisterMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelErrorRegisterLayout.setVerticalGroup(
            panelErrorRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelErrorRegisterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblErrorRegisterMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        LblEmail1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        LblEmail1.setText("Full Name:");

        fieldFullName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        fieldFullName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fieldFullName.setText("John Smith");
        fieldFullName.setToolTipText("email@email.com"); // NOI18N
        fieldFullName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        fieldFullName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        fieldFullName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldFullNameFocusGained(evt);
            }
        });

        lblPassword1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        lblPassword1.setText("Repeat Password:");

        fieldRegisterPassword.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        fieldRegisterPassword.setText("jFieldRegister2");
        fieldRegisterPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        fieldRegisterPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldRegisterPasswordFocusGained(evt);
            }
        });

        btnRegisterSubmit.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnRegisterSubmit.setText("Register");
        btnRegisterSubmit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnRegisterSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegisterSubmitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegisterSubmitMouseEntered(evt);
            }
        });
        btnRegisterSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterSubmitActionPerformed(evt);
            }
        });

        LblEmail2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        LblEmail2.setText("Email:");

        LblEmail3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        LblEmail3.setText("Password:");

        fieldRegisterEmail.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        fieldRegisterEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fieldRegisterEmail.setText("email@email.com");
        fieldRegisterEmail.setToolTipText("email@email.com"); // NOI18N
        fieldRegisterEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        fieldRegisterEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        fieldRegisterEmail.setPreferredSize(new java.awt.Dimension(110, 22));
        fieldRegisterEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldRegisterEmailFocusGained(evt);
            }
        });

        fieldRegisterRepeatPassword.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        fieldRegisterRepeatPassword.setText("jPasswordField1");
        fieldRegisterRepeatPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        fieldRegisterRepeatPassword.setPreferredSize(new java.awt.Dimension(78, 22));
        fieldRegisterRepeatPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldRegisterRepeatPasswordFocusGained(evt);
            }
        });

        lblGoBack.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        lblGoBack.setText("Already have an account? Sign in");
        lblGoBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGoBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblGoBackMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout panelRegisterInputLayout = new javax.swing.GroupLayout(panelRegisterInput);
        panelRegisterInput.setLayout(panelRegisterInputLayout);
        panelRegisterInputLayout.setHorizontalGroup(
            panelRegisterInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegisterInputLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(panelRegisterInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LblEmail3)
                    .addComponent(fieldRegisterEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblEmail2)
                    .addComponent(lblPassword1)
                    .addComponent(LblEmail1)
                    .addComponent(fieldFullName)
                    .addComponent(fieldRegisterPassword)
                    .addComponent(btnRegisterSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldRegisterRepeatPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addComponent(panelErrorRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(panelRegisterInputLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(lblGoBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRegisterInputLayout.setVerticalGroup(
            panelRegisterInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegisterInputLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(panelErrorRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblEmail1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(LblEmail2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldRegisterEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(LblEmail3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldRegisterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblPassword1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldRegisterRepeatPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnRegisterSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblGoBack)
                .addContainerGap())
        );

        panelRegister.add(panelRegisterInput);
        panelRegisterInput.setBounds(10, 80, 400, 370);

        panelRegisterTitle.setBackground(new java.awt.Color(204, 204, 204));
        panelRegisterTitle.setLayout(null);

        lblMainTitle1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        lblMainTitle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMainTitle1.setText("Welcome!");
        panelRegisterTitle.add(lblMainTitle1);
        lblMainTitle1.setBounds(10, 10, 140, 30);

        lblSubtitle1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblSubtitle1.setText("Glad you're here!");
        panelRegisterTitle.add(lblSubtitle1);
        lblSubtitle1.setBounds(10, 40, 140, 16);

        lblTextBox1.setBackground(new java.awt.Color(204, 255, 153));
        lblTextBox1.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://www.landfx.com/images/docs/kb/installation-errors/4019/blank%20welcome.PNG")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        lblTextBox1.setText("..");
        lblTextBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        panelRegisterTitle.add(lblTextBox1);
        lblTextBox1.setBounds(0, 0, 180, 70);

        panelRegister.add(panelRegisterTitle);
        panelRegisterTitle.setBounds(440, 40, 180, 70);

        panelRegisterBackground.setLayout(null);

        LblIRegistercon1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        LblIRegistercon1.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://i.imgur.com/q6MNSNB.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        LblIRegistercon1.setText("Login");
        panelRegisterBackground.add(LblIRegistercon1);
        LblIRegistercon1.setBounds(110, 0, 210, 80);

        lblLoginWhiteBackground1.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://www.landfx.com/images/docs/kb/installation-errors/4019/blank%20welcome.PNG")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        panelRegisterBackground.add(lblLoginWhiteBackground1);
        lblLoginWhiteBackground1.setBounds(0, 0, 410, 460);

        lblBackground1.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://i.imgur.com/jcMzfs9.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        panelRegisterBackground.add(lblBackground1);
        lblBackground1.setBounds(410, 0, 240, 460);

        panelRegister.add(panelRegisterBackground);
        panelRegisterBackground.setBounds(0, 0, 650, 460);

        getContentPane().add(panelRegister);
        panelRegister.setBounds(0, 0, 900, 470);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        if (jFieldEmail.getText().equals("email@email.com") || jFieldEmail.getText().isEmpty() || jFieldPassword.getText().isEmpty()
                || !jFieldEmail.getText().contains("@") && !jFieldEmail.getText().contains(".")) {
            showLoginError("Invalid credentials!");
            return;
        }
        
        //Do something ....
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void jFieldEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFieldEmailFocusGained
        jFieldEmail.setFocusable(true);
        clearTextBox(jFieldEmail, "email@email.com");
    }//GEN-LAST:event_jFieldEmailFocusGained

    private void jFieldPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFieldPasswordFocusGained
        jFieldEmail.setFocusable(true);
        clearTextBox(jFieldPassword, "jPasswordField1");
    }//GEN-LAST:event_jFieldPasswordFocusGained

    private void lblCantLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCantLoginMouseClicked

        changeUiToRegisterMode();
    }//GEN-LAST:event_lblCantLoginMouseClicked

    private void showRegisterError(String text) {
        panelErrorRegister.setVisible(true);
        lblErrorRegisterMessage.setText(text);
    }

    private void showLoginError(String text) {
        panelErrorLogin.setVisible(true);
        lblErrorMessage.setText(text);
    }

    private void changeUiToRegisterMode() {
        panelLogIn.setVisible(false);
        panelRegister.setVisible(true);
        panelErrorRegister.setVisible(false);
    }

    private void changeUiToLoginMode() {
        panelErrorLogin.setVisible(false);
        panelLogIn.setVisible(true);
        panelRegister.setVisible(false);

    }

    private void btnSubmitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubmitMouseEntered
        btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }//GEN-LAST:event_btnSubmitMouseEntered

    private void lblCantLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCantLoginMouseEntered
        lblCantLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblCantLoginMouseEntered

    private void btnSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubmitMouseClicked

    }//GEN-LAST:event_btnSubmitMouseClicked

    private void fieldFullNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldFullNameFocusGained
        clearTextBox(fieldFullName, "John Smith");
    }//GEN-LAST:event_fieldFullNameFocusGained

    private void fieldRegisterPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldRegisterPasswordFocusGained
        clearTextBox(fieldRegisterPassword, "jFieldRegister2");
    }//GEN-LAST:event_fieldRegisterPasswordFocusGained

    private void btnRegisterSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterSubmitMouseClicked

    }//GEN-LAST:event_btnRegisterSubmitMouseClicked

    private void btnRegisterSubmitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterSubmitMouseEntered

    }//GEN-LAST:event_btnRegisterSubmitMouseEntered

    private void btnRegisterSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterSubmitActionPerformed
        String fullName = fieldFullName.getText();
        String email = fieldRegisterEmail.getText();
        String password = new String(fieldRegisterPassword.getPassword());
        String repeatPassword = new String(fieldRegisterRepeatPassword.getPassword());

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            showRegisterError("Error: one or more input field empty!");
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        if (!emailPattern.matcher(email).matches() || !password.equals(repeatPassword)) {
            showRegisterError("Error: invalid email or password");
        } else {
            panelErrorRegister.setVisible(false);
            //now we need to make the confirmation here
        }
    }//GEN-LAST:event_btnRegisterSubmitActionPerformed

    private void fieldRegisterEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldRegisterEmailFocusGained
        clearTextBox(fieldRegisterEmail, "email@email.com");
    }//GEN-LAST:event_fieldRegisterEmailFocusGained

    private void fieldRegisterRepeatPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldRegisterRepeatPasswordFocusGained
        clearTextBox(fieldRegisterRepeatPassword, "jPasswordField1");
    }//GEN-LAST:event_fieldRegisterRepeatPasswordFocusGained

    private void lblGoBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGoBackMouseClicked
        changeUiToLoginMode();
    }//GEN-LAST:event_lblGoBackMouseClicked

    private void lblGoBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGoBackMouseEntered
        lblGoBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblGoBackMouseEntered

    private void sendRegisterRequest() {
        //clientLogic.createAnswerAsyncThread();
        // clientLogic.createAndSendObject(clientLogic.generateCredentials(jFieldEmail.getText(), jFieldPassword.getPassword()));

    }

    private void clearTextBox(javax.swing.JTextField textField, String placeHolderText) {
        if (textField.getText().equals(placeHolderText)) {
            textField.setText("");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginClientInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginClientInterface().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblEmail;
    private javax.swing.JLabel LblEmail1;
    private javax.swing.JLabel LblEmail2;
    private javax.swing.JLabel LblEmail3;
    private javax.swing.JLabel LblIRegistercon1;
    private javax.swing.JLabel LblIcon;
    private javax.swing.JButton btnRegisterSubmit;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JTextField fieldFullName;
    private javax.swing.JTextField fieldRegisterEmail;
    private javax.swing.JPasswordField fieldRegisterPassword;
    private javax.swing.JPasswordField fieldRegisterRepeatPassword;
    private javax.swing.JTextField jFieldEmail;
    private javax.swing.JPasswordField jFieldPassword;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackground1;
    private javax.swing.JLabel lblCantLogin;
    private javax.swing.JLabel lblErrorMessage;
    private javax.swing.JLabel lblErrorRegisterMessage;
    private javax.swing.JLabel lblGoBack;
    private javax.swing.JLabel lblLoginWhiteBackground;
    private javax.swing.JLabel lblLoginWhiteBackground1;
    private javax.swing.JLabel lblMainTitle;
    private javax.swing.JLabel lblMainTitle1;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPassword1;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblSubtitle1;
    private javax.swing.JLabel lblTextBox;
    private javax.swing.JLabel lblTextBox1;
    private javax.swing.JPanel panelErrorLogin;
    private javax.swing.JPanel panelErrorRegister;
    private javax.swing.JPanel panelLogIn;
    private javax.swing.JPanel panelLoginBackground;
    private javax.swing.JPanel panelLoginInput;
    private javax.swing.JPanel panelLoginTitle;
    private javax.swing.JPanel panelRegister;
    private javax.swing.JPanel panelRegisterBackground;
    private javax.swing.JPanel panelRegisterInput;
    private javax.swing.JPanel panelRegisterTitle;
    // End of variables declaration//GEN-END:variables
}
