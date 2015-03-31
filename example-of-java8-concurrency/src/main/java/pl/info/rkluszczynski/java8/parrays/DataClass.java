package pl.info.rkluszczynski.java8.parrays;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public
abstract class DataClass {
    protected int[] getData() {
        int copyCount = 20;
        try {
            File file = new File(
                    "example-of-java8-concurrency/" +
                            "src/main/resources/jozinek-20150326.jpg");
            BufferedImage image = ImageIO.read(file);
            int w = image.getWidth();
            int h = image.getHeight();
            int[] src = image.getRGB(0, 0, w, h, null, 0, w);
            int[] data = new int[src.length * copyCount];
            for (int i = 0; i < copyCount; i++) {
                System.arraycopy(
                        src, 0, data, i * src.length, src.length);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
