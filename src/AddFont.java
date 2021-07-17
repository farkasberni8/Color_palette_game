import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class AddFont {

    private static Font font = null;
    private static Font newFont = null;
    private static InputStream myStream = null;
    private static final String FONT_PATH = "Arcade.ttf";

    public static Font createFont() {

            try {
            myStream = new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            newFont = font.deriveFont(Font.ITALIC, 30);
            } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
            }
            return newFont;
    }

    public static Font createFont2() {

        try {
            myStream = new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            newFont = font.deriveFont(Font.PLAIN, 56);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
        }
        return newFont;
    }

    public static Font createFont3() {

        try {
            myStream = new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            newFont = font.deriveFont(Font.BOLD, 20);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
        }
        return newFont;
    }

    public static Font createFont4() {

        try {
            myStream = new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            newFont = font.deriveFont(Font.ITALIC, 24);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
        }
        return newFont;
    }

    public static Font createFont5() {

        try {
            myStream = new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            newFont = font.deriveFont(Font.PLAIN, 48);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
        }
        return newFont;
    }

    public static Font createFont6() {

        try {
            myStream = new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            newFont = font.deriveFont(Font.BOLD, 30);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
        }
        return newFont;
    }

    public static Font createFont7() {

        try {
            myStream = new BufferedInputStream(new FileInputStream(FONT_PATH));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
            newFont = font.deriveFont(Font.ITALIC, 23);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Font not loaded.");
        }
        return newFont;
    }
}