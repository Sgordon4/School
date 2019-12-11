import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import pa2.MatrixCuts;
import pa2.Tuple;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


/****************************************************************
 * Because Tuple is a private class (who decided that?), if this
 * test class is in a different package than your main files you
 * need to copy Tuple to this package as well to make a Tuple.
****************************************************************/
@RunWith(MockitoJUnitRunner.class)
public class CutTesting {

    int[][] M;
    int n = 5;
    int m = 5;

    @Before
    public void initializeMockito(){
        MockitoAnnotations.initMocks(this);

        //Set up our matrix
        M = new int[n][];
        M[0] = new int[]{5,  7,  9,  4,  5};
        M[1] = new int[]{2,  3,  1,  1,  2};
        M[2] = new int[]{2,  0,  49, 46, 8};
        M[3] = new int[]{3,  1,  1,  1,  1};
        M[4] = new int[]{50, 51, 25, 26, 1};

    }

    @Test
    public void testWidthCut(){
        //Set up our correct stuff
        ArrayList<Tuple> correct = new ArrayList<>();
        correct.add(new Tuple(15, -1));
        correct.add(new Tuple(0, 3));
        correct.add(new Tuple(1, 3));
        correct.add(new Tuple(2, 4));
        correct.add(new Tuple(3, 3));
        correct.add(new Tuple(4, 4));


        System.out.println("\n\n==================");
        System.out.println("Testing width cut:\n");

        int[][] pathCosts = MatrixCuts.computeWidthCosts(M);
        //Print the cost matrix
        System.out.println("Cost matrix: ");
        for(int a = 0; a < n; a++){
            for(int b = 0; b < m; b++){
                System.out.format("%2d, ", pathCosts[a][b]);
            }
            System.out.println();
        }
        System.out.println("\n");


        ArrayList<pa2.Tuple> computed = MatrixCuts.widthCut(M);

        //Print the arrays
        for(int i = 0; i < correct.size(); i++){
            System.out.println(correct.get(i));
        }
        System.out.println("---------------------------");
        for(int i = 0; i < computed.size(); i++){
            System.out.println(computed.get(i));
        }

        //Check equality
        assertEquals(correct.size(), computed.size());
        for(int i = 0; i < n; i++){
            Tuple corrTuple = correct.get(i);
            pa2.Tuple compTuple = computed.get(i);

            assertEquals(corrTuple.toString(), compTuple.toString());
        }

        System.out.println();
    }


    @Test
    public void testStitchCut(){
        //Set up our correct stuff
        ArrayList<Tuple> correct = new ArrayList<>();
        correct.add(new Tuple(10, -1));
        correct.add(new Tuple(0, 0));
        correct.add(new Tuple(1, 0));
        correct.add(new Tuple(2, 1));
        correct.add(new Tuple(3, 2));
        correct.add(new Tuple(3, 3));
        correct.add(new Tuple(4, 4));


        System.out.println("\n===================");
        System.out.println("Testing stitch cut:\n");

        int[][] pathCosts = MatrixCuts.computeStitchCosts(M);
        //Print the cost matrix
        System.out.println("Cost matrix: ");
        for(int a = 0; a < n; a++){
            for(int b = 0; b < m; b++){
                System.out.format("%2d, ", pathCosts[a][b]);
            }
            System.out.println();
        }
        System.out.println("\n");


        ArrayList<pa2.Tuple> computed = MatrixCuts.stitchCut(M);

        //Print the arrays
        for(int i = 0; i < correct.size(); i++){
            System.out.println(correct.get(i));
        }
        System.out.println("---------------------------");
        for(int i = 0; i < computed.size(); i++){
            System.out.println(computed.get(i));
        }

        //Check equality
        assertEquals(correct.size(), computed.size());
        for(int i = 0; i < n; i++){
            Tuple corrTuple = correct.get(i);
            pa2.Tuple compTuple = computed.get(i);

            assertEquals(corrTuple.toString(), compTuple.toString());
        }

        System.out.println();
    }
}
