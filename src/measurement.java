import java.util.*;
import java.io.*;

public class measurement {

	public static void main(String[] args) throws Exception {

		Scanner stdin = new Scanner(new File("measurement.in"));

		cow[] list = new cow[3];
		list[0] = new cow("Bessie", 7);
		list[1] = new cow("Mildred", 7);
		list[2] = new cow("Elsie", 7);

		int n = stdin.nextInt();
		day[] updates = new day[n];
		for (int i=0; i<n; i++) {
			int id = stdin.nextInt();
			String s = stdin.next();
			String tmp = stdin.next();
			if (tmp.charAt(0) == '+') tmp = tmp.substring(1);
			int d = Integer.parseInt(tmp);
			updates[i] = new day(id,s,d);
		}
		Arrays.sort(updates);

		boolean[] win = new boolean[3];
		Arrays.fill(win, true);

		int res = 0;
		for (int i=0; i<n; i++) {

			for (int j=0; j<3; j++)
				if (updates[i].name.equals(list[j].name))
					list[j].milk += updates[i].delta;

			int max = list[0].milk;
			for (int j=1; j<3; j++)
				if (list[j].milk > max)
					max = list[j].milk;

			boolean[] newwin = new boolean[3];
			for (int j=0; j<3; j++)
				if (list[j].milk == max)
					newwin[j] = true;

			if (!eq(win, newwin)) res++;

			win = newwin;
		}

		PrintWriter out = new PrintWriter(new FileWriter("measurement.out"));
		out.println(res);
		out.close();
		stdin.close();
	}

	public static boolean eq(boolean[] a, boolean[] b) {
		for (int i=0; i<a.length; i++)
			if (a[i] != b[i])
				return false;
		return true;
	}
}

class day implements Comparable<day> {

	public int id;
	public String name;
	public int delta;

	public day(int d, String s, int change) {
		id = d;
		name = s;
		delta = change;
	}

	public int compareTo(day other) {
		return this.id - other.id;
	}
}

class cow {

	public String name;
	public int milk;

	public cow(String s, int m) {
		name = s;
		milk = m;
	}
}

//correct
