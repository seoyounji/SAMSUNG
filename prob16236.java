package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//백준 16236 아기 상어
public class prob16236 {
	private static int N;
	private static int[][] map;
	private static int result = 0;
	private static point start;
	private static int[][] visited;
	
	private static class vertex {
		int x; int y; int index;
		vertex(int x,int y,int index) {
			this.x = x;
			this.y = y;
			this.index = index;
		}
	}
	
	private static class point {
		int x; int y; int value; int path; int times;
		point(int x,int y,int value,int path,int times) {
			this.x = x;
			this.y = y;
			this.value = value;
			this.path = path;
			this.times = times;
		}
	}
	private static void BFS(vertex s) {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};
		Queue<vertex> queue = new LinkedList<>();
		queue.offer(s);
		visited[s.x][s.y]= start.path;
		
		while(!queue.isEmpty()) {
			int kid = queue.size();
			int tmp_x = 100;
			int tmp_y = 100;
			int tmp_index = 0;
			for (int k = 0; k < kid; k++) {
				vertex current = queue.poll();
				for (int i = 0; i < 4; i++) {
					int x = current.x + dx[i];
					int y = current.y + dy[i];
					if(x>=0 && y>=0 && x<N && y<N && visited[x][y] < result) {
						//잡아먹을 수 있는 물고기를 만난 경우
						if(map[x][y]!=0 && map[x][y]<start.value) {
							if(tmp_x > x) {
								tmp_x = x;
								tmp_y = y;
							} else if(tmp_x == x) {
								if(tmp_y > y) {
									tmp_y = y;
								}
							}
							tmp_index = current.index+1;
						}
						else if(map[x][y]==0 || map[x][y]==start.value) {
							queue.offer(new vertex(x,y,current.index+1));
							visited[x][y] = result;
						}
					}
				}
			}
			if(tmp_x!=100 && tmp_y!=100) {
				start.x = tmp_x;
				start.y = tmp_y;
				start.path = tmp_index;
				start.times++;
				if(start.times==start.value) {
					start.value++;
					start.times = 0;
				}
				map[tmp_x][tmp_y] = 0;
				result += tmp_index;
				queue.clear();
				queue.offer(new vertex(start.x,start.y,0));
				visited[tmp_x][tmp_y] = result;
			}

		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		visited = new int[N][N];
		start = new point(0,0,0,0,0);

		int size = 2;
		for (int i = 0; i < N; i++) {
			String[] s = bf.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				visited[i][j] = -1;
				if(map[i][j]==9) {
					start.x = i;
					start.y = j;
					start.value = size;
					start.path = 0;
					start.times = 0;
					map[i][j] = 0;
				}
			}
		}
		BFS(new vertex(start.x,start.y,0));
		System.out.println(result);
	}
}
