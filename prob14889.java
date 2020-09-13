package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 14889 스타트와 링크
public class prob14889 {
	private static int result = Integer.MAX_VALUE;
	private static void combi(int n,int r,int start,boolean[] visited,int[][] map) {
		if(r==0) {
			int A=0,B=0;
			for (int i = 1; i <= n; i++) {
				if(visited[i]) {
					for (int j = 1; j <= n; j++) {
						if(visited[j]) A += map[i][j];
					}
				} else {
					for (int j = 1; j <= n; j++) {
						if(!visited[j]) B+=map[i][j];
					}
				}
			}
			int diff = Math.abs(A-B);
			result = Math.min(result, diff);
			if(result==0) {
				System.out.println(result);
				System.exit(0);
			}
			return;
		}
		for (int i = start; i <= n; i++) {
			visited[i] = true;
			combi(n,r-1,i+1,visited,map);
			visited[i] = false;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[] visited = new boolean[N+1];
		combi(N,N/2,1,visited,map);
		System.out.println(result);
	}
}
