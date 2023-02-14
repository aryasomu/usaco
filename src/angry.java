import java.util.*;
import java.io.*;

public class angry {

	public static void main(String[] args) throws Exception {


		Scanner stdin = new Scanner(new File("angry.in"));
		int n = stdin.nextInt();
		int[] vals = new int[n];
		for (int i=0; i<n; i++)
            vals[i] = stdin.nextInt();

        Arrays.sort(vals);

        int res = 0;

        for (int i=0; i<n; i++) {

            boolean[] used = new boolean[n];
            used[i] = true;
            explode(used, vals, i, 1, true);
            explode(used, vals, i, 1, false);
            int tmp = 0;
            for (int j=0; j<n; j++) if (used[j]) tmp++;
            res = Math.max(res, tmp);

        }

		PrintWriter out = new PrintWriter(new FileWriter("angry.out"));
		out.println(res);
		out.close();
		stdin.close();
	}

	public static void explode(boolean[] used, int[] vals, int loc, int radius, boolean dir) {

	    if (dir) {

            int i = loc+1;
            while (i < vals.length && vals[i] - vals[loc] <= radius) {
                used[i] = true;
                i++;
            }
            i--;

            if (i > loc) explode(used, vals, i, radius+1, dir);

	    }

	    else {

            int i = loc-1;
            while (i >= 0 && vals[loc] - vals[i] <= radius) {
                used[i] = true;
                i--;
            }
            i++;

            if (i < loc) explode(used, vals, i, radius+1, dir);
	    }
	}
}

//correct