import java.util.*;
import java.io.*;

public class billboard {

	public static void main(String[] args) throws Exception {

		Scanner stdin = new Scanner(new File("billboard.in"));

		boolean[][] see = new boolean[2001][2001];
		for (int i=0; i<2; i++) {

			int x1 = stdin.nextInt()+1000;
			int y1 = stdin.nextInt()+1000;
			int x2 = stdin.nextInt()+1000;
			int y2 = stdin.nextInt()+1000;
			for (int x=x1; x<x2; x++)
				for (int y=y1; y<y2; y++)
					see[x][y] = true;
		}

		int x1 = stdin.nextInt()+1000;
		int y1 = stdin.nextInt()+1000;
		int x2 = stdin.nextInt()+1000;
		int y2 = stdin.nextInt()+1000;
		for (int x=x1; x<x2; x++)
			for (int y=y1; y<y2; y++)
				see[x][y] = false;

		int res = 0;
		for (int i=0; i<=2000; i++)
			for (int j=0; j<2000; j++)
				if (see[i][j])
					res++;

		PrintWriter out = new PrintWriter(new FileWriter("billboard.out"));
		out.print(res);
		out.close();
		stdin.close();
	}
}

//correct