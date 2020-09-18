package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 17144 미세먼지 안녕!
public class prob17144 {
	private static void wind(int[][] map,int R,int C,int air1,int air2) {
		int[][] tmp = new int[R][C];
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]>0) {
					int direction = 4;
					int mise = 0;
					for (int k = 0; k < 4; k++) {
						int x = i + dx[k];
						int y = j + dy[k];
						if(x<0 || y<0 || x>=R || y>=C) {
							direction--;
							continue;
						}
						if(map[x][y]==-1) {
							direction--;
							continue;
						}
						mise = map[i][j] / 5;
						tmp[x][y] += mise;
					}
					tmp[i][j] -= (mise*direction);
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] += tmp[i][j];
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		for (int i = 0; i <= air1; i++) {
			for (int j = 0; j < C; j++) {
				if(i==0) {
					if(j==0) { //0,0
						map[i+1][j] = tmp[i][j];
					} else { //맨 윗줄. 왼쪽으로 밀어야한다
						map[i][j-1] = tmp[i][j];
					}
				} else if(j==0) {
					if(i==air1) { //왼쪽 아래 모서리. 공기청정기에서 나가는 바람이므로 무조건 0
						map[i][j+1] = 0;
					} else { //맨 왼쪽줄. 아래로 밀어야한다
						if(i+1!=air1) map[i+1][j] = tmp[i][j];
					}
				} else if(i==air1) {
					if(j==C-1) { //오른쪽 아래 모서리. 위로 밀어야한다
						map[i-1][j] = tmp[i][j];
					} else { //맨 아랫줄. 오른쪽으로 밀어야한다
						map[i][j+1] = tmp[i][j];
					}
				} else if(j==C-1) { //맨 오른쪽줄. 위로 밀어야한다
					map[i-1][j] = tmp[i][j];
				}
			}
		}
		for (int i = air2; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(i==air2) {
					if(j==0) { //왼쪽 위 모서리. 공기청정기에서 나가는 바람이므로 무조건 0
						map[i][j+1]=0;
					} else if(j==C-1) { //오른쪽 위 모서리. 아래로 밀어야한다
						map[i+1][j] = tmp[i][j];
					} else { //맨 윗줄. 오른쪽으로 밀어야한다 
						map[i][j+1] = tmp[i][j];
					}
				} else if(j==C-1) {
					if(i==R-1) { //오른쪽 아래 모서리. 왼쪽으로 밀어야한다
						map[i][j-1] = tmp[i][j];
					} else { //맨 오른쪽줄. 아래로 밀어야한다
						map[i+1][j] = tmp[i][j];
					}
				} else if(i==R-1) {
					if(j==0) { //왼쪽 아래 모서리. 위로 밀어야한다.
						map[i-1][j] = tmp[i][j];
					} else { //맨 아랫줄. 왼쪽으로 밀어야한다
						map[i][j-1] = tmp[i][j];
					}
				} else if(j==0) { //맨 왼쪽줄. 위로 밀어야한다
					if(i-1!=air2) map[i-1][j] = tmp[i][j];
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		int air1=0,air2=0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) {
					if(air1==0) air1=i;
					else air2=i;
				}
			}
		}
		for (int i = 0; i < T; i++) {
			wind(map,R,C,air1,air2);			
		}
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum+=map[i][j];
			}
		}
		sum += 2;
		System.out.println(sum);
	}
}
