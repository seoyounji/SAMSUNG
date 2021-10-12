package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 16234 인구 이동
public class prob16234 {
	private static int[][] result;
	private static int[][] map;
	private static int L,R,N;
	
	private static class point {
		int r; int c; 
		point(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
	private static void BFS(int r,int c,int insert) {
		int cnt = 0;
		int sum = 0;
		Queue<point> queue = new LinkedList<>();
		queue.add(new point(r,c));
		cnt++;
		sum += map[r][c];
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};
		result[r][c] = insert;
		while(!queue.isEmpty()) {
			point tmp = queue.poll();
			for (int i = 0; i < 4; i++) {
				int x = tmp.r + dx[i];
				int y = tmp.c + dy[i];
				if(x<0 || y<0 || x>=N || y>=N) continue;
				if(result[x][y]!=0) continue;
				if(L<=Math.abs(map[tmp.r][tmp.c]-map[x][y]) && Math.abs(map[tmp.r][tmp.c]-map[x][y])<=R) {
					result[x][y] = insert;
					queue.add(new point(x,y));
					cnt++;
					sum += map[x][y];
				}
			}
		}
		int tmp = sum / cnt;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(result[i][j] == insert) map[i][j] = tmp; 
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		int count = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			result = new int[N][N];
			int tmp = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(result[i][j]!=0) continue;
					BFS(i,j,tmp);
					
					tmp++;
				}
			}
			
			if(tmp-1==(N*N)) break;
			count++;
			
		}
		System.out.println(count);
	}
}
