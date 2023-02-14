import java.util.*;
import java.io.*;

public class bcs {

	public static int n;
	public static int numPieces;
	public static boolean[][] art;
	public static boolean[][][] pieceArray;

	public static void main(String[] args) throws Exception {

		BufferedReader stdin = new BufferedReader(new FileReader("bcs.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		n = Integer.parseInt(tok.nextToken());
		numPieces = Integer.parseInt(tok.nextToken());
		art = new boolean[n][n];
		int origOn = 0;

		for (int i=0; i<n; i++) {
			String tmp = stdin.readLine();
			for (int j=0; j<n; j++) {
 				art[i][j] = (tmp.charAt(j) == '#');
				if (art[i][j]) origOn++;
			}
		}

		int[] pieceOn = new int[numPieces];

		pieceArray = new boolean[numPieces][n][n];
		for (int loop=0; loop<numPieces; loop++) {
			for (int i=0; i<n; i++) {
				String tmp = stdin.readLine();
				for (int j=0; j<n; j++) {
					pieceArray[loop][i][j] = (tmp.charAt(j) == '#');
					if (pieceArray[loop][i][j]) pieceOn[loop]++;
				}
			}
		}

		int result = -1;

		for (int i=0; i<numPieces; i++) {

			boolean[][] left = sub(art, pieceArray[i]);
			if (left == null) continue;

			boolean found = false;

			for (int j=0; j<numPieces; j++) {
				if (j == i || origOn != pieceOn[i]+pieceOn[j]) continue;

				boolean[][] res = sub(left, pieceArray[j]);
				if (res == null) continue;

				if (isFalse(res)) {
					found = true;
					result = i < j ? numPieces*i+j : numPieces*j+i;
					break;
				}
			}

			if (found) break;
		}

		PrintWriter out = new PrintWriter(new FileWriter("bcs.out"));
		out.println( (result/numPieces+1)+" "+(result%numPieces+1) );
		out.close();
		stdin.close();
	}

	public static boolean[][] sub(boolean[][] orig, boolean[][] piece) {

		int[] origLoc = getTopLeft(orig);
		int[] pieceLoc = getTopLeft(piece);

		boolean[][] result = new boolean[n][];
		for (int i=0; i<n; i++)
			result[i] = Arrays.copyOf(orig[i], n);

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {

				int checkX = i-pieceLoc[0]+origLoc[0];
				int checkY = j-pieceLoc[1]+origLoc[1];

				if (piece[i][j] && !inbounds(checkX, checkY))
					return null;

				if (piece[i][j] && !orig[checkX][checkY])
					return null;

				if (piece[i][j]) result[checkX][checkY] = false;
			}
		}

		return result;
	}

	public static int[] getTopLeft(boolean[][] piece) {

		int[] res = new int[2];

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (piece[i][j]) {
					res[0] = i;
					res[1] = j;
					return res;
				}
			}
		}

		return res;
	}

	public static boolean isFalse(boolean[][] piece) {
		for (int i=0; i<n; i++)
			for (int j=0; j<n; j++)
				if (piece[i][j])
					return false;

		return true;
	}

	public static boolean inbounds(int x, int y) {
		return x >= 0 && x < n && y >=0 && y < n;
	}
}

//wrong!