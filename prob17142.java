package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

//백준 17142 연구소 3
public class prob17142 {
	private static int[][] map;
	private static int result = -1;
	private static int spread;
	private static List<List<point>> combi;
	
	private static class point {
		int x; int y;
		point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static void combination(List<point> arr,boolean[] visited,int n,int r,int start) {
		if(r==0) {
			List<point> output = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if(visited[i]) output.add(arr.get(i));
			}
			combi.add(output);
			return;
		}
		for (int i = start; i < n; i++) {
			visited[i]=true;
			combination(arr,visited,n,r-1,i+1);
			visited[i]=false;
		}
	}
	private static boolean BFS(int[][] map,List<point> input,int N,int remain) {
		Queue<point> queue = new LinkedList<>();
		for (int i = 0; i < input.size(); i++) {
			queue.offer(input.get(i));
		} 
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};
		spread = 1;
		int size = 0;
		while(!queue.isEmpty()) {
			size = queue.size();
			for (int i = 0; i < size; i++) {
				point current = queue.poll();
				for (int j = 0; j < 4; j++) {
					int x = current.x + dx[j];
					int y = current.y + dy[j];
					if(x<0 || y<0 || x>=N || y>=N) continue;
					if(map[x][y]>0) continue;
					if(map[x][y]==0) {
						queue.offer(new point(x,y));
						map[x][y] = spread;
						remain--;
						if(remain==0) return true;
					}
					if(map[x][y]==-3) {
						queue.offer(new point(x,y));
						map[x][y] = -2;
					}
				}
			}
			spread++;
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		combi = new ArrayList<List<point>>();
		List<point> arr = new ArrayList<>();
		int virus = 0;
		int remain = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) remain++;
				if(map[i][j]==2) {
					arr.add(new point(i,j));
					virus++;
					map[i][j] = -3;
				}
				if(map[i][j]==1) map[i][j]=-1;
			}
		}
		boolean[] visited = new boolean[virus];
		combination(arr,visited,virus,M,0);
		int[][] mapClone = new int[N][N];
		if(remain==0) {
			result = 0;
		} else {
			for (int i = 0; i < combi.size(); i++) {
				List<point> tmp = new ArrayList<>();
				tmp = combi.get(i);
				for (int j = 0; j < N; j++) {
					mapClone[j] = map[j].clone();
				}				
				for (int j = 0; j < tmp.size(); j++) {
					mapClone[tmp.get(j).x][tmp.get(j).y] = -2;
				}
				
				int temp = remain;
				if(BFS(mapClone,tmp,N,temp)) {
					if(result!=-1) {
						result = Integer.min(result, spread);
					} else {
						result = spread;	
					}				
				}
				
			}
		}
		System.out.println(result);
	}
}
