package pa2;

import java.awt.Color;
import java.util.*;


/*
 
 Use this program two stich images. Makes calls to stitch cut method from
 the class MatxiCuts.
 
 */
public class ImageStitch {
	
	
	
	public static void main(String[] args) {
		
		Picture p1 = new Picture("iastate1.jpg");
		Picture p2 = new Picture("iastate2.jpg");
		Picture p = stitch(p1, p2);
		p1.show();
		p2.show();
		p.show();
		
		
	}
	
	
	
	
	
	
	
	
	public static Picture stitch(Picture p1, Picture p2) {
		
		ArrayList<Tuple> tempSeam = computeSeamAtBestOffSet(p1, p2);
		
		
		ArrayList<Tuple> seamP1 = new ArrayList<Tuple>();
		
		int off = tempSeam.get(tempSeam.size()-1).getX();
		ArrayList<Tuple> newSeam = new ArrayList<Tuple>();
		
		System.out.println("Computing Seam for Image 1");
		for(int i =1; i<tempSeam.size()-1; i++){
			Tuple t = tempSeam.get(i);
			int r = t.getX();
			int c = t.getY();
			Tuple t1 = new Tuple(r, c+off);
			seamP1.add(t1);
			
		}
		System.out.println("Computing Seam for Image  2");
		ArrayList<Tuple> seamP2 = new ArrayList<Tuple>();
		for(int i = 1; i<tempSeam.size()-1; i++) {
			Tuple t = tempSeam.get(i);
			int r = t.getX();
			int c = t.getY();
			Tuple t2 = new Tuple(r, c);
			seamP2.add(t2);
		}
		
		int y1 = seamP1.get(0).getY();
		int y2 = seamP2.get(0).getY();
		int w = p2.width();
		int tt = y1+(w-y2)+1;
		int height = p1.height();
		int width = p2.width();
		int Totwidth = tt;
		
		
		Picture p = new Picture (Totwidth, height);
		System.out.println("stitching");
		for (int i =0; i<seamP1.size(); i++) {
			//System.out.print(i+ " ");
			Tuple t = seamP1.get(i);
			Tuple t2 = seamP2.get(i);
			
			int rowNum = t.getX();
			int colNum = t.getY();
			int rowNum2 = t2.getX();
			int colNum2 = t2.getY();
			Color black = new Color(0, 0, 0);
			p1.set(colNum,  rowNum, black);
			p2.set(colNum2,  rowNum2, black);
			
			for (int j = 0; j<=colNum; j++) {
				Color c= p1.get(j, rowNum);
				p.set(j, rowNum, c);
			}
			int k =0;
			for(int j = colNum2+1; j<width; j++) {
				
				k++;
				Color c= p2.get(j, rowNum);
				p.set(colNum+k, rowNum, c);
				
			}
		
			
			
		}
		
		
		return p;
	}
	
	
	
	public static ArrayList<Tuple> computeSeamAtBestOffSet(Picture p1, Picture p2) {
		int width = p1.width();
		int bestCut = Integer.MAX_VALUE;
		int bestOff= -1;
		ArrayList<Tuple> l = new ArrayList<Tuple>();
		for (int offset=0; offset<width; offset++) {
			Picture n = crop(p1,offset);
			int[][] mat = penaltyMatrix(n,p2);
			ArrayList<Tuple> current = MatrixCuts.stitchCut(mat);
			int cutVal = current.get(0).getX();
			if (cutVal<bestCut) {
				bestCut = cutVal;
				bestOff=offset;
				l = current;
			}
			
		}
		l.add(new Tuple(bestOff, -2));
		return l;
		
		
	
	}
	
	
	public static int[][] penaltyMatrix(Picture p1, Picture p2) {
		
		int rows = p1.height();
		int cols = (int) Math.min(p1.width(), p2.width());
		
		int[][] matrix = new int[rows][cols];
		
		
		for (int i = 0; i<rows; i++) {
			for (int j = 0; j<cols; j++) {
				matrix[i][j] = pixelDistance(p1.get(j, i), p2.get(j,  i));
				
			}
		}
		
		
		
		
		
		return matrix;
	}
	
	
	
	

	
	
	public static int pixelDistance(Color c1, Color c2) {
		
		int r1 = c1.getRed();
		int g1 = c1.getGreen();
		int b1 = c1.getBlue();
		
		int r2 = c2.getRed();
		int g2 = c2.getGreen();
		int b2 = c2.getBlue();
		
		int r = (r1-r2)*(r1-r2);
		int g = (g1-g2)*(g1-g2);
		int b = (b1-b2)*(b1-b2);
		
		return (r+g+b);
			
	}
	
	public static Picture crop(Picture p, int offset) {
		
		int height = p.height();
		int width = p.width();
		Picture n = new Picture(width-offset, height);
		for(int r = 0; r<height; r++) {
			for (int c = offset; c<width; c++) {
				Color col = p.get(c, r);
				n.set(c-offset, r, col);
			}
		}
		return n;
		
	}

}


