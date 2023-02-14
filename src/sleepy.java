import java.io.*;
import java.util.*;

public class sleepy {
	
	public static void main(String[] args) throws Exception {
		
		Scanner stdin = new Scanner(new File("sleepy.in"));
		int n = stdin.nextInt();
		int[] arr = new int[n];
		for (int i=0; i<n; i++) arr[i] = stdin.nextInt();
		
		int res = 0;
		for (int i=0; i<n-1; i++)
			if (arr[i] > arr[i+1])
				res = i+1;
		
		PrintWriter out = new PrintWriter(new FileWriter("sleepy.out"));
		out.println(res);
		out.close();
		stdin.close();
	}

}

//correct