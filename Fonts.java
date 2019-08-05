package app;
import java.awt.*;
import javax.swing.*;

public class Fonts {
  //  private static GraphicsEnvironment t = GraphicsEnvironment.getLocalGraphicsEnvironment();
   // private static Font[] fonts = t.getAllFonts();
    private static Font tempfont = new JLabel().getFont();
    private static Font font_big = tempfont.deriveFont(13F);
    private static Font font_middle = tempfont.deriveFont(12F);
    private static Font font_small = tempfont.deriveFont(10.25F);
    public static Font get_bigFont() {
        return font_big;
    }
    public static Font get_middleFont() {
        return font_middle;
    }
    public static Font get_smallFont() {
        return font_small;
    }
    

}