package pa2;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.min;

public class ImageProcessor {
    public static Picture reduceWidth(int x, String pImage){

        Picture p = new Picture(pImage);
        int n = p.height();
        int m = p.width();

        for(; x > 0; x--){
            //Compute the importance matrix
            int[][] importance = computeImportance(p);

            //Find the pixels to be removed
            ArrayList<Tuple> widthCut = MatrixCuts.widthCut(importance);


            //=============================================================================
            // For each row, take all the pixels to the right of the one being removed and
            // move them left 1:            [a, b, c, X, e, f, g, h, i]
            //        ...                   [a, b, c, e, f, g, h, i, i]
            // then crop the image by 1:    [a, b, c, e, f, g, h, i]
            //
            // This mega inefficient but it's what we got ¯\_(ツ)_/¯
            //=============================================================================


            //Shift the pixels to the left
            for(int i = 0; i < widthCut.size()-1; i++){
                Tuple removed = widthCut.get(i+1);

                for(int j = removed.getY(); j < m-1; j++){
                    int shift = p.getRGB(j+1, i);
                    p.setRGB(j, i, shift);
                }
            }


            //Crop the last column off
            p = ImageStitch.crop(p, 1);
            m--;

            //Go again
        }


        return p;
    }

    static int[][] computeImportance(Picture p){

        int n = p.height();
        int m = p.width();

        int[][] importance = new int[n][m];

        //Compute importance for each pixel ----------------------
        for(int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {

                //Compute cost using the scheme laid out in the pdf
                if(j == 0){
                    Color color1 = p.get(j, i);
                    Color color2 = p.get(j+1, i);
                    importance[i][j] = ImageStitch.pixelDistance(color1, color2);
                }
                else if(j == m-1){
                    Color color1 = p.get(j, i);;
                    Color color2 = p.get(j-1, i);;
                    importance[i][j] = ImageStitch.pixelDistance(color1, color2);
                }
                else{
                    Color color1 = p.get(j-1, i);;
                    Color color2 = p.get(j+1, i);;
                    importance[i][j] = ImageStitch.pixelDistance(color1, color2);
                }
            }
        }

        return importance;
    }
}
