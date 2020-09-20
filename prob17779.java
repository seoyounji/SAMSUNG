package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 17779 게리맨더링 2
public class prob17779 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[][] map = new int[N+1][N+1];
		int[][] index = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = Integer.MAX_VALUE;
		
		for (int d1 = 1; d1 < N; d1++) { //d1
			for (int d2 = 1; d2 < N; d2++) { //d2
				for (int x = 1; x < N; x++) { //x
					for (int y = 1; y < N; y++) { //y
						if(x+d1+d2 > N) continue;
						if(y-d1 <= 0) continue;
						if(y+d2 > N) continue;
						for (int i = 1; i <= N; i++) {
							for (int j = 1; j <= N; j++) {
								index[i][j] = 0;
							}
						}
						int num1 = 0;
						int num2 = 0;
						int num3 = 0;
						int num4 = 0;
						int num5 = 0;
						for (int i = 0; i <= d1; i++) {
							index[x+i][y-i] = 5;
							index[x+d2+i][y+d2-i] = 5;
						}
						for (int i = 0; i <= d2; i++) {
							index[x+i][y+i] = 5;
							index[x+d1+i][y-d1+i] = 5;
						}
						for (int i = x+1; i < x+d1+d2; i++) {
							int index1 = 0,index2 = 0;
							for (int j = 1; j <= N; j++) {
								if(index[i][j]==5) {
									if(index1==0) index1=j;
									else index2=j;
								}
							}
							for (int j = index1; j <= index2; j++) {
								index[i][j] = 5;
							}
						}
						for (int i = 1; i < x+d1; i++) {
							for (int j = 1; j <= y; j++) {
								if(index[i][j]==0) {
									index[i][j] = 1;
									num1 += map[i][j];
								}
							}
						}
						for (int i = 1; i <= x+d2; i++) {
							for (int j = y+1; j <= N; j++) {
								if(index[i][j]==0) {
									index[i][j] = 2;
									num2 += map[i][j];
								}
							}
						}
						for (int i = x+d1; i <= N; i++) {
							for (int j = 1; j < y-d1+d2; j++) {
								if(index[i][j]==0) {
									index[i][j] = 3;
									num3 += map[i][j];
								}
							}
						}
						for (int i = x+d2; i <= N; i++) {
							for (int j = y-d1+d2; j <= N; j++) {
								if(index[i][j]==0) {
									index[i][j] = 4;
									num4 += map[i][j];
								}
							}
						}
						for (int i = 1; i <= N; i++) {
							for (int j = 1; j <= N; j++) {
								if(index[i][j]==5) num5 += map[i][j];
							}
						}
						int max = Integer.MIN_VALUE;
						int min = Integer.MAX_VALUE;
						max = Math.max(max, num1);
						max = Math.max(max, num2);
						max = Math.max(max, num3);
						max = Math.max(max, num4);
						max = Math.max(max, num5);
						min = Math.min(min, num1);
						min = Math.min(min, num2);
						min = Math.min(min, num3);
						min = Math.min(min, num4);
						min = Math.min(min, num5);
						result = Math.min(result, max-min);	
					}		
				}
			}
		}
		System.out.println(result);
	}

}
