package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 13458 시험 감독
public class prob13458 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[] num = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(bf.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long answer = N;
		for (int i = 0; i < N; i++) {
			int now = num[i] - B;
			if(now<=0) continue;
			int tmp = now%C;
			answer += (now/C);
			if(tmp>0) answer += 1;
		}
		System.out.println(answer);
	}

}
