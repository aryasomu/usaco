import java.util.*;
import java.io.*;

public class paint {

	public static void main(String[] args) throws Exception {
        
		BufferedReader stdin = new BufferedReader(new FileReader("paint.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		int[] john = new int[2];
		for (int i=0; i<2; i++)
            john[i] = Integer.parseInt(tok.nextToken());
        tok = new StringTokenizer(stdin.readLine());
		int[] bessie = new int[2];
		for (int i=0; i<2; i++)
            bessie[i] = Integer.parseInt(tok.nextToken());

        if (john[1] > bessie[1]) {
            int[] tmp = john;
            john = bessie;
            bessie = tmp;
        }

        int res = john[1] <= bessie[0] ? (john[1]-john[0])+(bessie[1]-bessie[0]) : bessie[1] - Math.min(john[0], bessie[0]);
        
		FileWriter fout = new FileWriter(new File("paint.out"));
        fout.write(res+"\n");
		fout.close();
	}
}

//correct