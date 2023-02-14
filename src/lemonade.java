import java.util.*;
import java.io.*;

public class lemonade {

	public static void main(String[] args) throws Exception {

		BufferedReader stdin = new BufferedReader(new FileReader("lemonade.in"));
		int n = Integer.parseInt(stdin.readLine().trim());

		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		ArrayList<Integer> vals = new ArrayList<Integer>();
		for (int i=0; i<n; i++)
			vals.add(Integer.parseInt(tok.nextToken()));
		Collections.sort(vals);
		Collections.reverse(vals);

		int res = 0;
		for (int i=0; i<n; i++)
			if (vals.get(i) >= res)
				res++;

		PrintWriter out = new PrintWriter(new FileWriter("lemonade.out"));
		out.println(res);
		out.close();
		stdin.close();
	}
}

//correct