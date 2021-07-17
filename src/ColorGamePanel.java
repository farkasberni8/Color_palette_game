import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ColorGamePanel extends JPanel implements MouseListener {

    private List<ColorShape> fillablePixel;
    private List<ColorShape> colorBar;
    private List<Integer> colorPixel;
    private List<Color> palette;
    private final double barLength = 60;
    public static final int MOVES = 16;
    private int moveCount;
    private boolean paintGame = false;
    private boolean finalResult;
    private boolean result;
    private final Color color=new Color(63,63,63);
    private Font font;
    public static Player song;

    public ColorGamePanel(){
        setupFont();
        setupPanel();
        drawColorBar();
        startingColorBar();
        this.addMouseListener(this);
    }

    /**
     * Initialize font style
     */
    private void setupFont() {
        try {
            font = AddFont.createFont5();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing the Game Panel
     */
    private void setupPanel(){
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);
        this.setBackground(Color.white);
        this.setBounds(new Rectangle(500, 500));
    }

    /**
     * Drawing the colorBar(palette)
     */
    private void drawColorBar() {
        colorBar = new ArrayList<>();
        double x=180;
        double y=400;
        double space = 0;
        int colors=ColorPaletteGameFrame.nrOfColors;
        switch (colors) {
            case 7 -> {
                x = 40;
                y = 400;
            }

            case 6 -> {
                x = 65;
                y = 400;
            }
            case 5 -> {
                x = 100;
                y = 400;
            }
            case 4 -> {
                x = 125;
                y = 400;
            }
            case 3 -> {
                x = 150;
                y = 400;
            }
        }

        palette= new ArrayList<>();

        for(int i=0; i< colors; i++){
            int r = (int) (Math.random() * 254);
            int g = (int) (Math.random() * 254);
            int b = (int) (Math.random() * 254);
            palette.add(new Color(r, g, b));
        }

        for(Color color : palette){
            colorBar.add(new ColorShape(x, y, color, barLength));
            x += barLength + space;
        }
    }

    /**
     * Adding colors to colorBar(palette)
     */
    private void startingColorBar() {
        colorBar = new ArrayList<>();
        double x = 100;
        double y = 400;
        double space = 0;

        for(ColorEnum0 color : ColorEnum0.values()){
            colorBar.add(new ColorShape(x, y, color.getValue(), barLength));
            x += barLength + space;
        }
    }

    enum ColorEnum0{
        PINK(new Color(62, 62, 62)),
        GREEN(new Color(62, 62, 62)),
        BLUE(new Color(62, 62, 62)),
        MAGNETA(new Color(62, 62, 62)),
        ORANGE(new Color(62, 62, 62));

        private final Color value;
        ColorEnum0(Color value){
            this.value = value;
        }
        public Color getValue(){
            return this.value;
        }
    }

    /**
     * Starting the Game
     */
    public void initialiseGamePanel(){
        setupSound();
        this.removeAll();
        drawColorBar();
        ColorPaletteGameFrame.moveLabel.setText("   Moves left: " + MOVES);
        moveCount = MOVES;
        paintGame = true;
        finalResult=true;
        fillablePixel = new ArrayList<>();
        double x;
        double y = 25;
        int colorIndex;
        int maxIndex = ColorPaletteGameFrame.nrOfColors;

        for(int i=0; i<10; i++){
            x = 70;
            double pixelLength = 35;
            for(int j = 0; j<10; j++){
                colorIndex = (int) Math.floor(Math.random() * Math.floor(maxIndex));
                fillablePixel.add(new ColorShape(x, y, palette.get(colorIndex), pixelLength));
                x += pixelLength;
            }
            y += pixelLength;
        }
        repaint();
        colorPixel = new ArrayList<>();
        colorPixel.add(0);
        SetPlayableList();
    }

    /**
     * Starting the game's background sound
     */
    private void setupSound(){
        FileInputStream fis;
        try {
            fis = new FileInputStream("monkeys-spinning.mp3");
            song = new Player(fis);
            Thread threadSong = new Thread(() -> {
                try {
                    song.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });
            threadSong.start();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setting the fillable directions
     */
    private void SetPlayableList() {
        for(ColorShape cr : fillablePixel){
            int current = fillablePixel.indexOf(cr);
            if (colorPixel.contains(current)){
                int right = current + 1;
                right = checkRange(right);
                int bottom = current + 10;
                bottom = checkRange(bottom);
                int left = current - 1;
                left = checkRange(left);
                int top = current - 10;
                top = checkRange(top);
                boolean r = false,b = false,l = false, t=false;
                if(current%10 != 9 && right != -1)
                    r = checkNeighborColor(right, current);
                if(bottom != -1)
                    b = checkNeighborColor(bottom, current);
                if(current%10 != 0 && left != -1)
                    l = checkNeighborColor(left, current);
                if(top != -1)
                    t = checkNeighborColor(top, current);
				//System.out.println(r + " " +b + " "+l+ " "+t);
            }
        }
    }

    /**
     * Check if a pixel is between the 100 interval
     */
    private int checkRange(int pixel) {
        if( (pixel < 0) || (pixel > 100) || (pixel == 100))
            return -1;
        return pixel;
    }

    /**
     * Check the neighboured pixel color
     */
    private boolean checkNeighborColor(int neighbor, int current) {
        if(neighbor != -1 && (fillablePixel.get(neighbor).getColor() == fillablePixel.get(current).getColor())){
            if(!colorPixel.contains(neighbor))
                colorPixel.add(neighbor);
            return true;
        }
        return false;
    }

    /**
     * Check how many moves left
     */
    public int getLocalMoveCount() {
        return moveCount;
    }

    /**
     * Setting pixels' color
     */
    private void SetColor(Color color) {
        for(int curr : colorPixel){
            fillablePixel.get(curr).setColor(color);
        }
    }

    /**
     * Setting how many moves left( setting moveLABEL value)
     */
    private void SetGameMoves() {
        if(moveCount != 0){
            if(colorPixel.size() == fillablePixel.size()){
                StopTimer.stop();
                result=true;
                finalResult=false;
            }
        }
        if(moveCount == 0){
            if(colorPixel.size()!= fillablePixel.size()) {
                StopTimer.stop();
                result=false;
                finalResult=false;
            }
            if((colorPixel.size() == fillablePixel.size())&&(colorPixel.size()!=0)){
                StopTimer.stop();
                result=true;
                finalResult=false;
            }
        }
        ColorPaletteGameFrame.moveLabel.setText("   Moves left:  " + getLocalMoveCount());
    }

    /**
     * Setting the moveCount value
     */
    private void setMoveCount(Color color) {
        if(fillablePixel.get(0).getColor() != color){
            moveCount--;
        }
    }

    /**
     * Setting up the final result of the game
     */
    private void setUpResult(boolean result) throws LineUnavailableException {
        this.setBackground(color);
        this.setBounds(new Rectangle(400, 400));
        JLabel resultLabel;
        if(result){
            this.setBackground(color);
            startingColorBar();
            song.close();
            ColorPaletteGameFrame.logFile.println();
            ColorPaletteGameFrame.logFile.println(ColorPaletteGameFrame.timeLabel.getText());
            ColorPaletteGameFrame.logFile.println();
            ColorPaletteGameFrame.logFile.println("    You won! :)");
            resultLabel = new JLabel("    You won! :) ");
            resultLabel.setForeground(Color.white);
            File url= new File("won.wav");
            Clip clip= AudioSystem.getClip();
            AudioInputStream au;
            try {
                au = AudioSystem.getAudioInputStream(url);
                clip.open(au);
            } catch (UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
            clip.start();
        }else{
            this.setBackground(color);
            song.close();
            startingColorBar();
            ColorPaletteGameFrame.logFile.println();
            ColorPaletteGameFrame.logFile.println(ColorPaletteGameFrame.timeLabel.getText());
            ColorPaletteGameFrame.logFile.println();
            ColorPaletteGameFrame.logFile.println("    Oops. Try again! :(");
            resultLabel = new JLabel("   Ooops.Try again!");
            resultLabel.setForeground(Color.white);
            File url= new File("game_over.wav");
            Clip clip= AudioSystem.getClip();
            AudioInputStream au;
            try {
                au = AudioSystem.getAudioInputStream(url);
                clip.open(au);
            } catch (UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
            clip.start();
        }
        resultLabel.setFont(font);
        this.add(resultLabel,BorderLayout.CENTER);
        fillablePixel.clear();
        colorPixel.clear();
    }

    /**
     * Painting out the game field
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ColorShape s : colorBar)
            s.paint(g);
        if(paintGame){
            for(ColorShape s : fillablePixel)
                s.paint(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        for(ColorShape cr : colorBar){
            double x = cr.getX();
            double y = cr.getY();
            double size = cr.getLength();
            Rectangle r = new Rectangle((int)x, (int)y, (int)size, (int)size);
            double mouseX = e.getX();
            double mouseY = e.getY();
            Point p = new Point((int)mouseX, (int)mouseY);
            if(r.contains(p)){
                //System.out.println("Pressed color's RGB code: "+ cr.getColor().toString().split("java.awt.")[1]);
                SetPlayableList();
                setMoveCount(cr.getColor());
                SetColor(cr.getColor());
                SetGameMoves();
                SetPlayableList();
                SetGameMoves();
                ColorPaletteGameFrame.logFile.println();
                ColorPaletteGameFrame.logFile.println("    Number of filled pixel: " + colorPixel.size());
                ColorPaletteGameFrame.logFile.println("    You have " + getLocalMoveCount() + " moves left.");
                if(!finalResult){
                    try {
                        setUpResult(result);
                    } catch (LineUnavailableException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                }
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
