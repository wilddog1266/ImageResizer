import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "\\Users\\12560\\OneDrive\\Рабочий стол\\src";
        String dstFolder = "\\Users\\12560\\OneDrive\\Рабочий стол\\dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int numOfThreads = 12;
        int filePerThread = files.length / numOfThreads;

        int remaining = files.length % numOfThreads;

        int startIndex = 0;

        for(int i = 0; i < numOfThreads; i++){
            int add = i < remaining ? 1 : 0;
            int length = filePerThread + add;

            File[] file = new File[length];
            System.arraycopy(files,startIndex,file,0,length);
            startIndex += length;

            ImageResizer resizer = new ImageResizer(file, newWidth, dstFolder, start);
            new Thread(resizer).start();
        }





    }
}
