package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//백준 3190 뱀
public class prob3190 {
	private static int[][] map; 
	private static LinkedList<rotate> times;
	private static int N;
	private static int[][] delta = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
	
	private static class rotate{
        int time;
        String direction;
        public rotate(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }
	private static class Trace{
        int y, x, dir;
        public Trace(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }
	private static int simulation() {
		int time = 1;
        LinkedList<Trace> trace = new LinkedList<Trace>();
        trace.add(new Trace(1, 1, 1));     
        rotate rotateTime = times.removeFirst(); 
        int ny = 1, nx = 1, dir = 1;
        while(true) {
        	ny += delta[dir][0];
            nx += delta[dir][1];
            if(!(ny>=1 && ny <= N && nx >= 1 && nx <= N) || map[ny][nx] == 2)  break; 
            if(map[ny][nx] == 1) {
                trace.add(new Trace(ny, nx, dir));
                map[ny][nx] = 2;
            } else {
                trace.add(new Trace(ny, nx, dir));
                map[ny][nx] = 2;
                Trace tail  =  trace.removeFirst();
                map[tail.y][tail.x] = 0; 
            } 
            if(rotateTime.time == time) {
                if(rotateTime.direction.equals("D")) {
                    dir = (dir+1)%4;
                }else {
                    dir = (dir+3)%4;
                }
                if(times.size() > 0)
                    rotateTime = times.removeFirst();
            }
            time++;
        }
        return time;
    }
	public static void main(String args[]) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		int K = Integer.parseInt(bf.readLine());
		map = new int[N + 1][N + 1];	
		times = new LinkedList<>();
        map[1][1] = 2;
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = 1;
		}
		int r = Integer.parseInt(bf.readLine());
		for (int i = 0; i < r; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int time = Integer.parseInt(st.nextToken());
			String direction = st.nextToken();
			times.add(new rotate(time, direction));
		}
		int result = simulation();
		System.out.println(result);
	}
}
