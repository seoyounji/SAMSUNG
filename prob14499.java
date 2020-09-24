package samsung;

import java.util.ArrayList;
import java.util.Scanner;

//백준 14499 주사위 굴리기
public class prob14499 {

	private static int[] dx = {0,0,0,-1,1};
	private static int[] dy = {0,1,-1,0,0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int K = sc.nextInt();
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int[] order = new int[K];
		for (int i = 0; i < K; i++) {
			order[i] = sc.nextInt();
		}
		
		ArrayList<ArrayList<ArrayList<int[]>>> list = new ArrayList<>();
		for (int i = 0; i <= 6; i++) {
			ArrayList<ArrayList<int[]>> tmp = new ArrayList<>();
			for (int j = 0; j <= 6; j++) {
				ArrayList<int[]> t = new ArrayList<>();
				tmp.add(t);
			}
			list.add(tmp);
		}
		list.get(1).get(5).add(new int[] {0,4,3,5,2});
		list.get(1).get(3).add(new int[] {0,5,2,3,4});
		list.get(1).get(2).add(new int[] {0,3,4,2,5});
		list.get(1).get(4).add(new int[] {0,2,5,4,3});
		list.get(2).get(1).add(new int[] {0,4,3,1,6});
		list.get(2).get(3).add(new int[] {0,1,6,3,4});
		list.get(2).get(6).add(new int[] {0,3,4,6,1});
		list.get(2).get(4).add(new int[] {0,6,1,4,3});
		list.get(3).get(5).add(new int[] {0,1,6,5,2});
		list.get(3).get(6).add(new int[] {0,5,2,6,1});
		list.get(3).get(2).add(new int[] {0,6,1,2,5});
		list.get(3).get(1).add(new int[] {0,2,5,1,6});
		list.get(4).get(5).add(new int[] {0,6,1,5,2});
		list.get(4).get(1).add(new int[] {0,5,2,1,6});
		list.get(4).get(2).add(new int[] {0,1,6,2,5});
		list.get(4).get(6).add(new int[] {0,2,5,6,1});
		list.get(5).get(3).add(new int[] {0,6,1,3,4});
		list.get(5).get(1).add(new int[] {0,3,4,1,6});
		list.get(5).get(4).add(new int[] {0,1,6,4,3});
		list.get(5).get(6).add(new int[] {0,4,3,6,1});
		list.get(6).get(5).add(new int[] {0,3,4,5,2});
		list.get(6).get(4).add(new int[] {0,5,2,4,3});
		list.get(6).get(2).add(new int[] {0,4,3,2,5});
		list.get(6).get(3).add(new int[] {0,2,5,3,4});
		
		int[] dice = {0,0,0,0,0,0,0};
		int up = 1;
		int front = 5;
		
		for (int i = 0; i < K; i++) {
			int now = order[i];
			int xx = x+dx[now];
			int yy = y+dy[now];
			if(xx<0 || yy<0 || xx>=N || yy>=M) continue;
			x = xx;
			y = yy;
			
			int tmpup = up;
			int tmpfront = front;
			
			up = list.get(tmpup).get(tmpfront).get(0)[now];
			if(now==1 || now==2) front = list.get(tmpup).get(tmpfront).get(0)[3];
			else if (now==3) front = 7-tmpup;
			else front = tmpup;
			
			System.out.println(dice[up]);
			
			if(map[x][y]==0) {
				map[x][y] = dice[7-up];
			}
			else {
				dice[7-up] = map[x][y];
				map[x][y] = 0;
			}
		}
	}

}
