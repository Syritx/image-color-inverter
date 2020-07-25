import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {

    static String fileName = "Hello.jpg";
    static String fileFormat = "jpg";

    public static void main(String[] args) {
        BufferedImage image = null;
        File file = null;

        try {
            file = new File(fileName);
            image = ImageIO.read(file);
        }catch (IOException e) {}

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {

                int rgb = image.getRGB(x,y);

                int alpha = (rgb>>24)&0xff;
                int red = (rgb>>16)&0xff;
                int green = (rgb>>8)&0xff;
                int blue = rgb&0xff;

                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;

                rgb = (alpha<<24) | (red<<16) | (green<<8) | blue;
                image.setRGB(x, y, rgb);
            }
        }

        try {
            file = new File(fileName);
            ImageIO.write(image,fileFormat,file);
        }catch(IOException e) {}
    }
}