package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 13460 구슬 탈출 2
public class prob13460 {

	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,-1,0,1};
	
	private static int BFS(int[][] map,int[] red,int[] blue,int N,int M) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		queue.offer(new int[] {red[0],red[1],blue[0],blue[1]});
		visited[red[0]][red[1]][blue[0]][blue[1]] = true;
		
		int answer = 1;
		while(!queue.isEmpty()) {
			if(answer>10) break;
			int size = queue.size();
			
			for (int s = 0; s < size; s++) {
				int[] now = queue.poll();
				outer2:
				for (int t = 0; t < 4; t++) {
					int rx = now[0];
					int ry = now[1];
					int bx = now[2];
					int by = now[3];
					//만약 둘이 같은 행에 있을 때
					if(rx==bx) {
						if(t==1||t==3) {
							//1이면 왼쪽에 있는(y가 더 작은)애가 먼저 움직여야함
							//3이면 오른쪽에 있는 애가 먼저 움직여야함
							if(t==1) {
								if(ry<by) {
									boolean flag = false;
									while(true) {
										rx += dx[t];
										ry += dy[t];
										if(map[rx][ry]==1) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
										if(map[rx][ry]==2) {
											flag = true;
											break;
										}
									}
									while(true) {
										bx += dx[t];
										by += dy[t];
										if(map[bx][by]==1) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
										if(map[bx][by]==2) continue outer2;
										if(rx==bx && ry==by) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
									}
									if(flag) return answer;
									if(!visited[rx][ry][bx][by]) {
										queue.offer(new int[] {rx,ry,bx,by});
										visited[rx][ry][bx][by] = true;
									}
								} else {
									while(true) {
										bx += dx[t];
										by += dy[t];
										if(map[bx][by]==1) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
										if(map[bx][by]==2) continue outer2;
									}
									while(true) {
										rx += dx[t];
										ry += dy[t];
										if(map[rx][ry]==1) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
										if(map[rx][ry]==2) return answer;
										if(rx==bx && ry==by) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
									}
									
									if(!visited[rx][ry][bx][by]) {
										queue.offer(new int[] {rx,ry,bx,by});
										visited[rx][ry][bx][by] = true;
									}
								}
							} else {
								if(ry>by) {
									boolean flag = false;
									while(true) {
										rx += dx[t];
										ry += dy[t];
										if(map[rx][ry]==1) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
										if(map[rx][ry]==2) {
											flag = true;
											break;
										}
									}
									while(true) {
										bx += dx[t];
										by += dy[t];
										if(map[bx][by]==1) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
										if(map[bx][by]==2) continue outer2;
										if(rx==bx && ry==by) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
									}
									if(flag) return answer;
									if(!visited[rx][ry][bx][by]) {
										queue.offer(new int[] {rx,ry,bx,by});
										visited[rx][ry][bx][by] = true;
									}
								} else {
									while(true) {
										bx += dx[t];
										by += dy[t];
										if(map[bx][by]==1) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
										if(map[bx][by]==2) continue outer2;
									}
									while(true) {
										rx += dx[t];
										ry += dy[t];
										if(map[rx][ry]==1) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
										if(map[rx][ry]==2) return answer;
										if(rx==bx && ry==by) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
									}
									
									if(!visited[rx][ry][bx][by]) {
										queue.offer(new int[] {rx,ry,bx,by});
										visited[rx][ry][bx][by] = true;
									}
								}
							}
						}else { //같은 행에 있긴 하지만 상관 없을때
							while(true) {
								bx += dx[t];
								by += dy[t];
								if(map[bx][by]==1) {
									bx -= dx[t];
									by -= dy[t];
									break;
								}
								if(map[bx][by]==2) continue outer2;
							}
							while(true) {
								rx += dx[t];
								ry += dy[t];
								if(map[rx][ry]==1) {
									rx -= dx[t];
									ry -= dy[t];
									break;
								}
								if(map[rx][ry]==2) return answer;
							}
							
							if(!visited[rx][ry][bx][by]) {
								queue.offer(new int[] {rx,ry,bx,by});
								visited[rx][ry][bx][by] = true;
							}
						}
					}
					//만약 둘이 같은 열에 있을 때
					else if(ry==by) {
						if(t==2||t==0) {
							//2면 아래에 있는 애가 (x가 더 큰) 먼저 움직여야함
							//0이면 위에 있는 애가 먼저
							if(t==2) {
								if(rx>bx) {
									boolean flag = false;
									while(true) {
										rx += dx[t];
										ry += dy[t];
										if(map[rx][ry]==1) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
										if(map[rx][ry]==2) {
											flag = true;
											break;
										}
									}
									while(true) {
										bx += dx[t];
										by += dy[t];
										if(map[bx][by]==1) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
										if(map[bx][by]==2) continue outer2;
										if(rx==bx && ry==by) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
									}
									if(flag) return answer;
									if(!visited[rx][ry][bx][by]) {
										queue.offer(new int[] {rx,ry,bx,by});
										visited[rx][ry][bx][by] = true;
									}
								} else {
									while(true) {
										bx += dx[t];
										by += dy[t];
										if(map[bx][by]==1) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
										if(map[bx][by]==2) continue outer2;
									}
									while(true) {
										rx += dx[t];
										ry += dy[t];
										if(map[rx][ry]==1) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
										if(map[rx][ry]==2) return answer;
										if(rx==bx && ry==by) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
									}
									
									if(!visited[rx][ry][bx][by]) {
										queue.offer(new int[] {rx,ry,bx,by});
										visited[rx][ry][bx][by] = true;
									}
								}
							} else {
								if(rx<bx) {
									boolean flag = false;
									while(true) {
										rx += dx[t];
										ry += dy[t];
										if(map[rx][ry]==1) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
										if(map[rx][ry]==2) {
											flag = true;
											break;
										}
									}
									while(true) {
										bx += dx[t];
										by += dy[t];
										if(map[bx][by]==1) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
										if(map[bx][by]==2) continue outer2;
										if(rx==bx && ry==by) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
									}
									if(flag) return answer;
									if(!visited[rx][ry][bx][by]) {
										queue.offer(new int[] {rx,ry,bx,by});
										visited[rx][ry][bx][by] = true;
									}
								} else {
									while(true) {
										bx += dx[t];
										by += dy[t];
										if(map[bx][by]==1) {
											bx -= dx[t];
											by -= dy[t];
											break;
										}
										if(map[bx][by]==2) continue outer2;
									}
									while(true) {
										rx += dx[t];
										ry += dy[t];
										if(map[rx][ry]==1) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
										if(map[rx][ry]==2) return answer;
										if(rx==bx && ry==by) {
											rx -= dx[t];
											ry -= dy[t];
											break;
										}
									}
									
									if(!visited[rx][ry][bx][by]) {
										queue.offer(new int[] {rx,ry,bx,by});
										visited[rx][ry][bx][by] = true;
									}
								}
							}
						}else { //같은 열이긴 하지만 상관없을 때
							while(true) {
								bx += dx[t];
								by += dy[t];
								if(map[bx][by]==1) {
									bx -= dx[t];
									by -= dy[t];
									break;
								}
								if(map[bx][by]==2) continue outer2;
							}
							while(true) {
								rx += dx[t];
								ry += dy[t];
								if(map[rx][ry]==1) {
									rx -= dx[t];
									ry -= dy[t];
									break;
								}
								if(map[rx][ry]==2) return answer;
							}
							
							if(!visited[rx][ry][bx][by]) {
								queue.offer(new int[] {rx,ry,bx,by});
								visited[rx][ry][bx][by] = true;
							}
						}
					}
					else {
						while(true) {
							bx += dx[t];
							by += dy[t];
							if(map[bx][by]==1) {
								bx -= dx[t];
								by -= dy[t];
								break;
							}
							if(map[bx][by]==2) continue outer2;
						}
						while(true) {
							rx += dx[t];
							ry += dy[t];
							if(map[rx][ry]==1) {
								rx -= dx[t];
								ry -= dy[t];
								break;
							}
							if(map[rx][ry]==2) return answer;
						}
						
						if(!visited[rx][ry][bx][by]) {
							queue.offer(new int[] {rx,ry,bx,by});
							visited[rx][ry][bx][by] = true;
						}
					}
				}
			}
			answer++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[] red = {0,0};
		int[] blue = {0,0};
		
		for (int i = 0; i < N; i++) {
			char[] tmp = bf.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if(tmp[j]=='#') map[i][j] = 1;
				if(tmp[j]=='R') {
					red[0] = i;
					red[1] = j;
				}
				if(tmp[j]=='B') {
					blue[0] = i;
					blue[1] = j;
				}
				if(tmp[j]=='O') map[i][j] = 2;
			}
		}
		
		System.out.println(BFS(map,red,blue,N,M));
		
	}

}
