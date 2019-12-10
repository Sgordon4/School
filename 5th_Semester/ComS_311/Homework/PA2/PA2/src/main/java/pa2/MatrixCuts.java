package pa2;

import java.util.ArrayList;

import static java.lang.Math.min;

public class MatrixCuts {

    public static int[][] computeWidthCosts(int[][] M){
        int n = M.length;
        int m = M[0].length;

        int[][] pathCosts = new int[n][m];

        //Add the entire first row of M to pathCosts
        for(int j = 0; j < n; j++){
            pathCosts[0][j] = M[0][j];
        }

        //Compute min-cost for each cell -------------------------
        for(int i = 1; i < n; i++){
            for(int j = 0; j < m; j++) {
                //Decide the least cost for this index      [1][2][3]
                //With width cut, look in these locations:     [X]

                //Set-up to find next minimum to add to path, watch for out-of-bounds
                int cost1 = (j == 0) ? Integer.MAX_VALUE: pathCosts[i - 1][j - 1];
                int cost2 = pathCosts[i - 1][j];
                int cost3 = (j == m-1) ? Integer.MAX_VALUE: pathCosts[i - 1][j + 1];
                int current = M[i][j];

                //Add the min cost to the matrix
                pathCosts[i][j] = min(min(cost1, cost2), cost3) + current;
            }
        }

        return pathCosts;
    }

    public static ArrayList<Tuple> widthCut(int[][] M) {

        int n = M.length;
        int m = M[0].length;

        int[][] pathCosts = computeWidthCosts(M);


        //Backtrack to find min-cost path ------------------------

        //Find min-cost cell in last row
        int j = 0;
        for(int x = 1; x < m; x++){
            if(pathCosts[n-1][x] < pathCosts[n-1][j]){
                j = x;
            }
        }

        ArrayList<Tuple> path = new ArrayList<>();
        //Add the cost
        path.add(new Tuple(pathCosts[n-1][j], -1));
        //And the last index
        path.add(new Tuple(n-1, j));


        //Backtrack from that index     [1][2][3]
        for(int i = n-1; i > 0; ){//       [X]

            //Set-up to find next minimum to add to path, watch for out-of-bounds
            int cost1 = (j == 0) ? Integer.MAX_VALUE: pathCosts[i - 1][j - 1];
            int cost2 = pathCosts[i - 1][j];
            int cost3 = (j == m-1) ? Integer.MAX_VALUE: pathCosts[i - 1][j + 1];

            //Add the min cost to the matrix
            if (cost1 <= cost3 || cost2 <= cost3) {
                if (cost1 <= cost2) {
                    path.add(1, new Tuple(--i, --j));
                } else {
                    path.add(1, new Tuple(--i, j));
                }
            } else {
                path.add(1, new Tuple(--i, ++j));
            }
        }

        return path;
    }





    public static int[][] computeStitchCosts(int[][] M){
        int n = M.length;
        int m = M[0].length;

        int[][] pathCosts = new int[n][m];

        //Add the entire first row of M to pathCosts
        for(int j = 0; j < n; j++){
            pathCosts[0][j] = M[0][j];
        }

        //Compute min-cost for each cell -------------------------
        for(int i = 1; i < n; i++){
            for(int j = 0; j < m; j++) {
                //Decide the least cost for this index      [1][2]
                //With width cut, look in these locations:  [3][X]

                //Set-up to find next minimum to add to path, watch for out-of-bounds
                int cost1 = (j == 0) ? Integer.MAX_VALUE: pathCosts[i - 1][j - 1];
                int cost2 = pathCosts[i - 1][j];
                int cost3 = (j == 0) ? Integer.MAX_VALUE: pathCosts[i    ][j - 1];
                int current = M[i][j];

                //Add the min cost to the matrix
                pathCosts[i][j] = min(min(cost1, cost2), cost3) + current;
            }
        }

        return pathCosts;
    }

    public static ArrayList<Tuple> stitchCut(int[][] M) {

        int n = M.length;
        int m = M[0].length;

        int[][] pathCosts = computeStitchCosts(M);


        //Backtrack to find min-cost path ------------------------

        //Find min-cost cell in last row
        int j = 0;
        for(int x = 1; x < m; x++){
            if(pathCosts[n-1][x] < pathCosts[n-1][j]){
                j = x;
            }
        }

        ArrayList<Tuple> path = new ArrayList<>();
        //Add the cost
        path.add(new Tuple(pathCosts[n-1][j], -1));
        //And the last index
        path.add(new Tuple(n-1, j));


        //Backtrack from that index     [1][2]
        for(int i = n-1; i > 0; ){//    [3][X]

            //Set-up to find next minimum to add to path, watch for out-of-bounds
            int cost1 = (j == 0) ? Integer.MAX_VALUE: pathCosts[i - 1][j - 1];
            int cost2 = pathCosts[i - 1][j];
            int cost3 = (j == 0) ? Integer.MAX_VALUE: pathCosts[i    ][j - 1];

            //Add the min cost to the matrix
            if (cost1 <= cost3 || cost2 <= cost3) {
                if (cost1 <= cost2) {
                    path.add(1, new Tuple(--i, --j));
                } else {
                    path.add(1, new Tuple(--i, j));
                }
            } else {
                path.add(1, new Tuple(i, --j));
            }
        }

        return path;
    }
}
