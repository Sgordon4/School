import pa2.ImageProcessor;
import pa2.Picture;

public class ReduceWidthTest {

    public static void main(String[] args) {

        /*
        Picture p = ImageProcessor.reduceWidth(1, "https://labs.jensimmons.com/2016/examples/images/testscreen-large.jpg");
        //Picture p = new Picture("https://labs.jensimmons.com/2016/examples/images/testscreen-large.jpg");
        p.show();


        Picture p1 = new Picture("https://labs.jensimmons.com/2016/examples/images/testscreen-large.jpg");
        p1.show();

        Picture p1Modified1 = ImageProcessor.reduceWidth(1, "https://labs.jensimmons.com/2016/examples/images/testscreen-large.jpg");
        p1Modified1.show();

        Picture p1Modified10 = ImageProcessor.reduceWidth(10, "https://labs.jensimmons.com/2016/examples/images/testscreen-large.jpg");
        p1Modified10.show();

         */
        Picture p1Modified100 = ImageProcessor.reduceWidth(100, "https://labs.jensimmons.com/2016/examples/images/testscreen-large.jpg");
        p1Modified100.show();

    }
}
