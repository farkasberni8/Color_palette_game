import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.text.SimpleDateFormat;
import static javax.swing.JFileChooser.APPROVE_OPTION;

public class ColorPaletteGameFrame extends JFrame implements ActionListener, WindowListener {
    private JButton fileButton;
    private JButton homeButton;
    private JButton gameButton;
    private JButton helpButton;
    private JPanel homePanel;
    private JPanel cardPanel;
    private JPanel gamePanel;
    private JPanel filePanel;
    private JPanel helpPanel;
    private JPanel menuPanel;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private ColorGamePanel colorPanel;
    public static JLabel moveLabel;
    public static JLabel timeLabel;
    private JTextArea fileArea;
    public JTextField colorTextField;
    public static PrintStream logFile;
    public static int nrOfColors;
    private int nrOfGames=1;
    private boolean firstClick=true;
    private final Color color=new Color(63,63,63);
    private final Color color1=new Color(110, 110, 110);
    private Font h1;
    private Font h2;
    private Font h3;
    private Font h4;
    private Font h6;
    private Font h7;

    public ColorPaletteGameFrame() {
        super();
        setupFont();
        setupFrame();
        createMenuPanel();
        createHomePanel();
        createGamePanel();
        createFilePanel();
        createHelpPanel();
        createCardPanel();
        this.setVisible(true);
    }

    /**
     * Initialize font style
     */
    private void setupFont() {
        try {
            h1 = AddFont.createFont();
            h2 = AddFont.createFont2();
            h3 = AddFont.createFont3();
            h4 = AddFont.createFont4();
            h6 = AddFont.createFont6();
            h7 = AddFont.createFont7();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the game frame
     */
    private void setupFrame(){
        this.setTitle("Color Palette Game");
        this.setFont(h1);
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(600, 740);
        this.setBackground(color);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this);
    }

    /**
     * Creating a CardLayout as menu
     */
    private void createCardPanel() {
        CardLayout layout = new CardLayout();
        cardPanel.setLayout(layout);
        cardPanel.setSize(500, 740);

        cardPanel.add(homePanel, "HOME");
        cardPanel.add(gamePanel, "GAME");
        cardPanel.add(filePanel,"FILE");
        cardPanel.add(helpPanel,"HELP");

        this.add(cardPanel);

        gameButton.addActionListener(e -> layout.show(cardPanel,"GAME"));
        homeButton.addActionListener(e -> layout.show(cardPanel,"HOME"));
        fileButton.addActionListener(e -> layout.show(cardPanel,"FILE"));
        helpButton.addActionListener(e -> layout.show(cardPanel,"HELP"));
    }

    /**
     * Creating the menu's buttons
     */
    private void createMenuPanel() {
        menuPanel.setLayout(new GridLayout(4,1));
        menuPanel.setSize(100, 740);
        menuPanel.setBackground(color);
        mainPanel.setBackground(color);

        ImageIcon homeIcon = new ImageIcon("images\\first.png");
        Image image = homeIcon.getImage();
        Image newhome = image.getScaledInstance(20, 26,  java.awt.Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(newhome);
        homeButton.setIcon(homeIcon);
        homeButton.setText("Home");
        homeButton.setBackground(color1);
        homeButton.setForeground(Color.WHITE);
        homeButton.setFont(h3);
        ImageIcon gameIcon = new ImageIcon("images\\game.png");
        Image game = gameIcon.getImage();
        Image newgame = game.getScaledInstance(20, 26,  java.awt.Image.SCALE_SMOOTH);
        gameIcon = new ImageIcon(newgame);
        gameButton.setIcon(gameIcon);
        gameButton.setText("Game");
        gameButton.setBackground(color1);
        gameButton.setForeground(Color.WHITE);
        gameButton.setFont(h3);
        ImageIcon fileIcon = new ImageIcon("images\\file.png");
        Image file = fileIcon.getImage();
        Image newfile = file.getScaledInstance(20, 26,  java.awt.Image.SCALE_SMOOTH);
        fileIcon = new ImageIcon(newfile);
        fileButton.setIcon(fileIcon);
        fileButton.setText("File");
        fileButton.setBackground(color1);
        fileButton.setForeground(Color.WHITE);
        fileButton.setFont(h3);
        ImageIcon helpIcon = new ImageIcon("images\\help.png");
        Image help = helpIcon.getImage();
        Image helpgame = help.getScaledInstance(20, 26,  java.awt.Image.SCALE_SMOOTH);
        helpIcon = new ImageIcon(helpgame);
        helpButton.setIcon(helpIcon);
        helpButton.setText("Help");
        helpButton.setBackground(color1);
        helpButton.setForeground(Color.WHITE);
        helpButton.setFont(h3);

        menuPanel.add(homeButton);
        menuPanel.add(gameButton);
        menuPanel.add(fileButton);
        menuPanel.add(helpButton);
        this.add(menuPanel,BorderLayout.WEST);
    }

    /**
     * Creating the Game Page
     */
    private void createGamePanel() {
        gamePanel.setBackground(color);
        gamePanel.setLayout(new BorderLayout());
        createTitleLabel();
        createMoveLabelAndTimeLabel();

        JPanel colorTextFieldPanel = new JPanel();
        colorTextFieldPanel.setBackground(color);
        colorTextFieldPanel.setLayout(new FlowLayout());
        JLabel colorTextLabel = new JLabel("   Number of colors: ");
        colorTextLabel.setForeground(Color.white);
        colorTextLabel.setFont(h7);
        colorTextField = new JTextField("3-7");
        colorTextField.setColumns(3);
        colorTextField.setBackground(Color.WHITE);
        colorTextFieldPanel.add(colorTextLabel);
        colorTextFieldPanel.add(colorTextField);

        titlePanel.add(colorTextFieldPanel);
        gamePanel.add(titlePanel, BorderLayout.NORTH);

        createColorPanel();
        createButtonPanel();
    }

    /**
     * Creating the Home Page
     */
    private void createHomePanel() {
        homePanel.setBackground(color);
        homePanel.setLayout(new BorderLayout());

        JPanel welcome= new JPanel(new GridLayout(5,1));
        welcome.setBackground(color);
        JLabel t= new JLabel("");
        JLabel ti= new JLabel("");
        JLabel tit= new JLabel("");
        JLabel title= new JLabel(" Welcome to Color Palette Picker!");
        title.setForeground(Color.white);
        title.setFont(h1);
        welcome.add(t);
        welcome.add(ti);
        welcome.add(tit);
        welcome.add(title);
        homePanel.add(welcome,BorderLayout.NORTH);

        JLabel imageLabel= new JLabel();
        ImageIcon imageIcon = new ImageIcon("images\\home.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(340, 400,  Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        imageLabel.setVerticalAlignment(SwingConstants.TOP);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setIcon(imageIcon);
        homePanel.add(imageLabel,BorderLayout.CENTER);

        JLabel enjoy= new JLabel("ENJOY:)");
        enjoy.setFont(h2);
        enjoy.setForeground(Color.white);
        enjoy.setHorizontalAlignment(SwingConstants.CENTER);
        homePanel.add(enjoy,BorderLayout.SOUTH);

        this.add(homePanel);
    }

    /**
     * Creating the LogFile Page
     */
    private void createFilePanel() {
        filePanel.setBackground(color);
        filePanel.setLayout(new BorderLayout());

        JPanel openPanel= new JPanel();
        openPanel.setLayout(new BorderLayout());
        openPanel.setBackground(color);
        fileArea = new JTextArea(50,10);
        fileArea.setBackground(color1);
        fileArea.setForeground(Color.white);
        //fileArea.;
        JScrollPane scrollPane= new JScrollPane(fileArea);
        scrollPane.getVerticalScrollBar().setBackground(color1);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor=Color.WHITE;
                this.trackColor=color1;
            }
        });
        fileArea.setEditable(false);
        openPanel.add(scrollPane, BorderLayout.CENTER);

        JLabel label= new JLabel("                ");
        JLabel label1= new JLabel("                ");
        JLabel label2= new JLabel("                ");
        JLabel label3= new JLabel("                ");
        openPanel.add(label, BorderLayout.WEST);
        openPanel.add(label1, BorderLayout.EAST);
        openPanel.add(label2, BorderLayout.NORTH);
        openPanel.add(label3, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.setBackground(color);

        JButton openButton= new JButton("Open LogFile");
        openButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        openButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        openButton.setBackground(Color.WHITE);
        openButton.setForeground(color);
        openButton.setFont(h6);
        openButton.addActionListener(this);
        buttonPanel.add(openButton);
        filePanel.add(buttonPanel, BorderLayout.SOUTH);

        filePanel.add(openPanel,BorderLayout.CENTER);
        this.add(filePanel);
    }

    /**
     * Creating the Help Page
     */
    private void createHelpPanel() {
        helpPanel.setLayout(new BorderLayout());
        helpPanel.setBackground(color);

        createTopPanel("images\\1.png", "images\\2.png", "images\\3.png", "images\\4.png",BorderLayout.NORTH);
        createCenterPanel();
        createTopPanel("images\\5.png", "images\\6.png", "images\\7.png", "images\\8.png",BorderLayout.SOUTH);
        this.add(helpPanel);
    }

    /**
     * Creating the Help Page Center field
     */
    private void createCenterPanel() {
        JPanel centerPanel= new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBounds(140,280,210,130);
        centerPanel.setBackground(color);


        JButton help=new JButton("How to Play");
        help.setAlignmentX(Component.CENTER_ALIGNMENT);
        help.setBackground(Color.WHITE);
        help.setForeground(color);
        help.setFont(h6);
        help.addActionListener(this);

        JButton hint=new JButton("Get hints");
        hint.setAlignmentX(Component.CENTER_ALIGNMENT);
        hint.setBackground(Color.WHITE);
        hint.setForeground(color);
        hint.setFont(h6);
        hint.addActionListener(this);

        centerPanel.add(help,BorderLayout.NORTH);
        centerPanel.add(hint,BorderLayout.SOUTH);


        helpPanel.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Creating the Help Top/Bottom Center field
     */
    private void createTopPanel(String s, String s2, String s3, String s4, String north) {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4));
        topPanel.setBackground(color);
        addImage(s, topPanel);
        addImage(s2, topPanel);
        addImage(s3, topPanel);
        addImage(s4, topPanel);
        helpPanel.add(topPanel, north);
    }

    /**
     * Adding image the Help Page
     */
    private void addImage(String path, JPanel panel){
        JLabel imageLabel= new JLabel();
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(100, 132,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        imageLabel.setVerticalAlignment(SwingConstants.TOP);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setIcon(imageIcon);
        panel.add(imageLabel);
        helpPanel.add(panel);


    }

    /**
     * Creating the Game's Start button
     */
    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.setBackground(color);

        JLabel buttonLabel= new JLabel(" ");
        JLabel buttonLabel1= new JLabel(" ");
        JButton start = new JButton("Start/Restart");
        start.setFont(h3);
        start.setForeground(color);
        start.setBackground(Color.white);
        start.setBounds(80,100,60,40);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setAlignmentY(Component.CENTER_ALIGNMENT);
        start.addActionListener(this);

        buttonPanel.add(start);
        buttonPanel.add(buttonLabel);
        buttonPanel.add(buttonLabel1);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Creating the Game's field
     */
    private void createColorPanel() {
        colorPanel = new ColorGamePanel();
        colorPanel.setBackground(color);
        gamePanel.add(colorPanel, BorderLayout.CENTER);
    }

    /**
     * Creating the Game's countdown
     */
    private void createMoveLabelAndTimeLabel() {
        int moveCount = ColorGamePanel.MOVES;
        moveLabel = new JLabel("   Moves:  " + moveCount);
        moveLabel.setFont(h4);
        moveLabel.setForeground(Color.white);
        titlePanel.add(moveLabel);

        timeLabel=new JLabel();
        timeLabel.setFont(h4);
        timeLabel.setForeground(Color.white);
        titlePanel.add(timeLabel);
    }

    /**
     * Creating the Game's title
     */
    private void createTitleLabel() {
        JLabel title= new JLabel("");
        JLabel titleLabel = new JLabel("   Fill the panel with one color!  ");
        titleLabel.setFont(h1);
        titleLabel.setForeground(Color.white);
        titlePanel = new JPanel();
        titlePanel.setBackground(color);
        titlePanel.setLayout(new GridLayout(5, 1));
        titlePanel.add(title);
        titlePanel.add(titleLabel);
        gamePanel.add(titlePanel, BorderLayout.NORTH);
    }

    /**
     * Starting the Game's time countdown
     */
    private void startTimer() {
        Thread thread=new Thread(() -> {
            StopTimer.start();
            while(true){
                String time= new SimpleDateFormat("  mm : ss : SSS").format(StopTimer.getElapsedTime());
                timeLabel.setText("   Time: " + time);
            }
        });
        thread.start();
    }

    /**
     * Starting the Game's LogFile
     */
    private void startLogFile(){
        String title="logfiles\\ColorPaletteGame".concat(String.valueOf(nrOfGames)).concat(".txt");
        nrOfGames++;

        try {
            logFile= new PrintStream(new FileOutputStream(title));
            logFile.println("\n    Welcome to the ColorPaletteGame! :)\n\n");
            logFile.println("    Fill the panel with one color only with 16 moves!");
            logFile.println("    The panel has 100 fillable pixel.\n");
            logFile.println("    Good Luck!\n");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    /**
     * Opening the Game's Logfile
     */
    private void openLogFile(){
        File selectedFile;
        JFileChooser fc=new JFileChooser();
        fc.setCurrentDirectory(new File("C:\\Users\\Bernadett\\Desktop\\Egyetem\\5.félév\\Haladó szintű programozási nyelv\\Projekt\\ColorPaletteGame\\logfiles\\"));

        if(fc.showOpenDialog(fc)== APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();
            String fileName=selectedFile.getAbsolutePath();
            try{
                FileReader reader= new FileReader(fileName);
                BufferedReader br= new BufferedReader(reader);
                fileArea.read(br,null);
                br.close();
                fileArea.requestFocus();
            } catch ( Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String arg = e.getActionCommand();
        if(arg.equals("Start/Restart") )
        {
            if(!firstClick){
                ColorGamePanel.song.close();
            }
            if(colorTextField.getText().equals("3-7")) {
                nrOfColors = 5;
                startTimer();
                startLogFile();
                colorPanel.initialiseGamePanel();
            }else if( Integer.parseInt(colorTextField.getText())>7 || Integer.parseInt(colorTextField.getText())<3 ) {
                ImageIcon imageIcon = new ImageIcon("images\\sad.png");
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
                JOptionPane.showMessageDialog(
                        null,
                        "\nError interval for number of colors!\n The number must be between 3-7",
                        "Number of colors",
                        JOptionPane.ERROR_MESSAGE,
                        imageIcon);
            }else{
                nrOfColors= Integer.parseInt(colorTextField.getText());
                startTimer();
                startLogFile();
                colorPanel.initialiseGamePanel();
            }
            firstClick=false;
        }
        if(arg.equals("How to Play") )
        {
            ImageIcon imageIcon = new ImageIcon("images\\smiley.png");
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            JOptionPane.showMessageDialog(
                    null,
                    "\n1.Game menu:\n" +
                            "Start and click the colors from the palette(bottom) to fill \n" +
                            "the top left pixel (and others like it) with the same color. \n" +
                            "You can choose a number for how many colors want to play with.\n" +
                            "You have a limited number (16) of moves to paint the board\n" +
                            "and win the game!\n\n" +
                            "2.File menu:\n" +
                            "Chose from the files with name ColorPaletteGame.txt and\n" +
                            "check your logfile(includes information about your game)!\n\n" +
                            "Enjoy it!!!\n",
                    "How to play",
                    JOptionPane.ERROR_MESSAGE,
                    imageIcon);

        }
        if(arg.equals("Get hints") )
        {
            ImageIcon imageIcon = new ImageIcon("images\\smiley.png");
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            JOptionPane.showMessageDialog(
                    null,
                    "\n1.If there are many different colors Restart to get a new palette\n" +
                            "2.If a palette has very similar colors chose another one\n" +
                            "3.Try to fill more pixels with one color, check the possibilities\n" +
                            "4.Select less number for colors" +
                            "\n\n" +
                            "Try some of them!\n",
                    "Get hints",
                    JOptionPane.ERROR_MESSAGE,
                    imageIcon);
        }
        if(arg.equals("Open LogFile") )
        {
            openLogFile();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        File folder= new File("C:\\Users\\Bernadett\\Desktop\\Egyetem\\5.félév\\Haladó szintű programozási nyelv\\Projekt\\ColorPaletteGame\\logfiles\\");
        File[] files= folder.listFiles();
        if(files != null){
            for(File f: files){
                if(f.isFile()) {
                    f.delete();
                }
            }
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
