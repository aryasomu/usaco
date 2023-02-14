import java.util.*;
import java.io.*;

public class snowboots_silver {

	public static int n;
	public static int[] deep;
	public static int nBoots;
	public static boot[] boots;

	public static void main(String[] args) throws Exception {

		Scanner stdin = new Scanner(new File("snowboots.in"));

		n = stdin.nextInt();
		nBoots = stdin.nextInt();
		deep = new int[n];
		for (int i=0; i<n; i++)
			deep[i] = stdin.nextInt();
		boots = new boot[nBoots];
		for (int i=0; i<nBoots; i++) {
			int d = stdin.nextInt();
			int s = stdin.nextInt();
			boots[i] = new boot(d, s);
		}

		PrintWriter out = new PrintWriter(new FileWriter("snowboots.out"));
		out.println(bfs());
		out.close();
		stdin.close();
	}

	public static int bfs() {

		boolean[][] used = new boolean[n][nBoots];
		Arrays.fill(used[0], true);

		LinkedList<Integer> q = new LinkedList<Integer>();
		for (int i=0; i<nBoots; i++) q.offer(i);

		while (q.size() > 0) {

			int cur = q.poll();
			int step = cur/nBoots;
			int bNum = cur%nBoots;

			for (int i=1; step+i<n && i<=boots[bNum].maxStep; i++) {
				if (deep[step+i] <= boots[bNum].depth && !used[step+i][bNum]) {
					q.offer(nBoots*(step+i)+bNum);
					used[step+i][bNum] = true;
				}
			}

			for (int i=bNum+1; i<nBoots; i++) {
				if (boots[i].depth >= deep[step] && !used[step][i]) {
					q.offer(nBoots*step+i);
					used[step][i] = true;
				}
			}
		}

		for (int i=0; i<nBoots; i++)
			if (used[n-1][i])
				return i;

		return -1;

	}
}

class boot {

	public int depth;
	public int maxStep;

	public boot(int d, int s) {
		depth = d;
		maxStep = s;
	}
}

//correct