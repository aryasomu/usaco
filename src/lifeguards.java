
//SILVER 2018 JANUARY LIFEGUARDS PROBLEM

import java.util.*;
import java.io.*;

public class lifeguards {

	public static int n;
	public static int k;
	public static int[] on;
	public static int[] off;

	public static void main(String[] args) throws Exception {

        BufferedReader stdin = new BufferedReader(new FileReader("lifeguards.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		n = Integer.parseInt(tok.nextToken());
		on = new int[n];
		off = new int[n];

		TreeSet<Integer> set = new TreeSet<Integer>();
		for (int i=0; i<n; i++) {
			tok = new StringTokenizer(stdin.readLine());
			on[i] = Integer.parseInt(tok.nextToken());
			off[i] = Integer.parseInt(tok.nextToken());
			set.add(on[i]);
			set.add(off[i]);
		}

		int id = 0;
		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();

		ArrayList<Integer> gaps = new ArrayList<Integer>();
		int prev = 0;
		boolean iter = false;
		while (set.size() > 0) {
			int next = set.pollFirst();
			map.put(next, id++);
			if (iter) gaps.add(next-prev);
			prev = next;
			iter = true;
		}

		int[] freq = new int[map.size()];
		for (int i=0; i<n; i++) {
			freq[map.get(on[i])]++;
			freq[map.get(off[i])]--;
		}

		for (int i=1; i<freq.length; i++)
			freq[i] += freq[i-1];

		int[] onlyOne = new int[map.size()];

		int max = 0;
		for (int i=0; i<freq.length-1; i++) {

			if (freq[i] > 0) max += gaps.get(i);

			onlyOne[i+1] = onlyOne[i];
			if (freq[i] == 1) onlyOne[i+1] += gaps.get(i);
		}

		int res = 0;

		for (int i=0; i<n; i++) {

			int left = map.get(on[i]);
			int right = map.get(off[i]);

			int sub = onlyOne[right] - onlyOne[left];

			res = Math.max(res, max-sub);
		}

		PrintWriter out = new PrintWriter(new FileWriter("lifeguards.out"));
		out.println(res);
		out.close();
		stdin.close();
	}
}

//correct