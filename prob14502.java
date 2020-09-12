package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 14502 연구소
public class prob14502 {
	private static int[][] map;
	private static int N,M;
	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,1,0,-1};
	private static Queue<point> position = new LinkedList<>();
	private static Queue<point> queue = new LinkedList<>();
	private static boolean[] v;
	private static int re;
	
	private static class point {
		int x; int y;
		point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static void find() {
		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==0&&!visit[i][j]) {
					point tmp = new point(i,j);
					position.offer(tmp);
					visit[i][j]=true;
				}
			}
		}
	}
	private static boolean isInBound(int x,int y,int N,int M) {
		return x>=0 && y>=0 && x<N && y<M;
	}
	private static void BFS(point start,boolean[][] visited,int[][] map) {
		Queue<point>queue = new LinkedList<>();
		queue.offer(start);
		visited[start.x][start.y] = true;
		while(!queue.isEmpty()) {
			point current = queue.poll();
			for (int i = 0; i < 4; i++) {
				int x = current.x+dx[i];
				int y = current.y+dy[i];
				if(isInBound(x,y,N,M)&&!visited[x][y]&&map[x][y]==0) {
					point t = new point(x,y);
					queue.offer(t);
					visited[x][y] = true;
					map[x][y]=2;
				}
			}
		}
	}
	private static void combi(point[] arr,boolean[] visited,int index,int n,int r) {
		if(r==0) {
			print(arr,visited,n); 
			return;
		}
		if(index==n) {
			return;
		}
		visited[index]=true;
		combi(arr,visited,index+1,n,r-1);
		visited[index]=false;
		combi(arr,visited,index+1,n,r);
	}
	private static void print(point[] arr,boolean[] visited,int n) {
		for (int i = 0; i < n; i++) {
			if(visited[i]==true) {
				queue.offer(arr[i]);
			}
		}
		re++;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		find();
		int size = position.size();
		point[] posible = new point[size];
		v = new boolean[size];
		for (int i = 0; i < size; i++) {
			posible[i] = position.poll();
		}
		combi(posible,v,0,size,3);
		int sol = 0;
		for (int i = 0; i < re; i++) {
			int[][] tmp_map = new int[N][M];
			boolean[][] vi = new boolean[N][M];
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					tmp_map[j][j2] = map[j][j2];
				}
			}
			for (int j = 0; j < 3; j++) {
				point tmp = queue.poll();
				tmp_map[tmp.x][tmp.y] = 1;
			}
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					if(tmp_map[j][j2]==2 && !vi[j][j2]) {
						point t = new point(j,j2);
						BFS(t,vi,tmp_map);
					}
				}
			}
			int result = 0;
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					if(tmp_map[j][j2]==0) result++;
				}
			}
			sol = Math.max(sol, result);
		}
		System.out.println(sol);
	}
}
