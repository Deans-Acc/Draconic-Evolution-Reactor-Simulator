package app;
import java.awt.*;



public class Fonts {
    private static GraphicsEnvironment t = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private static Font[] fonts = t.getAllFonts();
    private static Font tempfont;
    private static Font font_big;
    private static Font font_middle;
    private static Font font_small;
    public static Font get_bigFont() {
        return font_big;
    }
    public static Font get_middleFont() {
        return font_middle;
    }
    public static Font get_smallFont() {
        return font_small;
    }
    public static void find_font() {
        for (Font temp : fonts) {
            System.out.println(temp.getName());
            if(temp.getPSName() ==  "Segoe UI") {
            
            tempfont = temp;
            }
        }
        font_big = tempfont.deriveFont(13F);
        font_middle = tempfont.deriveFont(12F);
        font_small = tempfont.deriveFont(10.25F);
    }
}
