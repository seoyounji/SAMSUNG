package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//백준 12100 2048 (Easy)
public class prob12100 {
	private static int result;
	
	private static class point {
		int num; boolean flag;
		point(int num,boolean flag) {
			this.num = num;
			this.flag = flag;
		}
	}
	
	private static int[][] copy(int[][] map) {
		int n = map.length;
		int[][] ret = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ret[i][j] = map[i][j];
			}
		}
		return ret;
	}
	
	private static int findMax(int[][] map) {
		int n = map.length;
		int ret = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ret = Math.max(ret, map[i][j]);
			}
		}
		return ret;
	}
	
	private static void calculate(int[][] map,int time,int dir) {
		if(time==5 || dir==0) {
			result = Math.max(result, findMax(map));
			return;
		}
		int n = map.length;
		Stack<point> stack = new Stack<>();
		
		switch(dir) {
		case 1:
			int[][] d1 = copy(map);
			int[][] rd1 = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(d1[j][i]==0) continue;
					if(stack.isEmpty()) stack.add(new point(d1[j][i],false));
					else {
						point top = stack.peek();
						if(!top.flag && top.num == d1[j][i]) {
							stack.pop();
							stack.add(new point(2*d1[j][i],true));
						}
						else stack.add(new point(d1[j][i],false));
					}
				}
				if(!stack.isEmpty()) {
					int size = stack.size()-1;
					for (int j = size; j >= 0; j--) {
						rd1[j][i] = stack.pop().num;
					}
				}
			}
			calculate(rd1,time+1,0);
			calculate(rd1,time+1,1);
			calculate(rd1,time+1,2);
			calculate(rd1,time+1,3);
			calculate(rd1,time+1,4);
			break;
		case 2:
			int[][] d2 = copy(map);
			int[][] rd2 = new int[n][n];
			for (int j = 0; j < n; j++) {
				for (int i = n-1; i >= 0; i--) {
					if(d2[i][j]==0) continue;
					if(stack.isEmpty()) stack.add(new point(d2[i][j],false));
					else {
						point top = stack.peek();
						if(!top.flag && top.num == d2[i][j]) {
							stack.pop();
							stack.add(new point(2*d2[i][j],true));
						}
						else stack.add(new point(d2[i][j],false));
					}
				}
				if(!stack.isEmpty()) {
					int size = stack.size();
					for (int i = n-size; i < n; i++) {
						rd2[i][j] = stack.pop().num;
					}
				}
			}
			calculate(rd2,time+1,0);
			calculate(rd2,time+1,1);
			calculate(rd2,time+1,2);
			calculate(rd2,time+1,3);
			calculate(rd2,time+1,4);
			break;
		case 3:
			int[][] d3 = copy(map);
			int[][] rd3 = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(d3[i][j]==0) continue;
					if(stack.isEmpty()) stack.add(new point(d3[i][j],false));
					else {
						point top = stack.peek();
						if(!top.flag && top.num == d3[i][j]) {
							stack.pop();
							stack.add(new point(2*d3[i][j],true));
						}
						else stack.add(new point(d3[i][j],false));
					}
				}
				if(!stack.isEmpty()) {
					int size = stack.size()-1;
					for (int j = size; j >= 0; j--) {
						rd3[i][j] = stack.pop().num;
					}
				}
			}
			calculate(rd3,time+1,0);
			calculate(rd3,time+1,1);
			calculate(rd3,time+1,2);
			calculate(rd3,time+1,3);
			calculate(rd3,time+1,4);
			break;
		case 4:
			int[][] d4 = copy(map);
			int[][] rd4 = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = n-1; j >= 0; j--) {
					if(d4[i][j]==0) continue;
					if(stack.isEmpty()) stack.add(new point(d4[i][j],false));
					else {
						point top = stack.peek();
						if(!top.flag && top.num == d4[i][j]) {
							stack.pop();
							stack.add(new point(2*d4[i][j],true));
						}
						else stack.add(new point(d4[i][j],false));
					}
				}
				if(!stack.isEmpty()) {
					int size = stack.size();
					for (int j = n-size; j < n; j++) {
						rd4[i][j] = stack.pop().num;
					}
				}
			}
			calculate(rd4,time+1,0);
			calculate(rd4,time+1,1);
			calculate(rd4,time+1,2);
			calculate(rd4,time+1,3);
			calculate(rd4,time+1,4);
			break;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = 0;
		calculate(map,0,1);
		calculate(map,0,2);
		calculate(map,0,3);
		calculate(map,0,4);
		System.out.println(result);
	}
}
