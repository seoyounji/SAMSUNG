package samsung;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class point {
	int x; int y;
	point(int x, int y){ 
		this.x = x;
		this.y = y;
	}
}

//백준 15686 치킨 배달
public class prob15686 {
	private static int N,M;
	private static List<point> home;
	private static List<point> chicken;
	private static boolean[] visited;
	private static List<List<point>> finalCombi;
	private static int result = Integer.MAX_VALUE;
	
	private static void combination (List<point> list,boolean[] visited,int i,int n,int r) {
		if(r==0) {
			save(list,visited,n);
			return;
		}
		if(i==n) return;
		visited[i] = true;
		combination(list,visited,i+1,n,r-1);
		visited[i] = false;
		combination(list,visited,i+1,n,r);
	}
	
	private static void save(List<point> list,boolean[] visited,int n) {
		List<point> result = new ArrayList<point>();
		for (int i = 0; i < n; i++) {
			if(visited[i] == true) {
				result.add(new point(list.get(i).x,list.get(i).y));
			}
		}
		finalCombi.add(result);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		home = new ArrayList<point>();
		chicken = new ArrayList<point>();
		finalCombi = new ArrayList<List<point>>();

		int num = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tmp = sc.nextInt();
				if(tmp==1) {
					home.add(new point(i+1,j+1));
				}
				if(tmp==2) {
					chicken.add(new point(i+1,j+1));
					num++;
				}
			}
		}
		visited = new boolean[num];
		combination(chicken,visited,0,num,M);

		for (int i = 0; i < finalCombi.size(); i++) {
			List<point> tmp = finalCombi.get(i); //치킨집 주소 리스트
			int chickenPath = 0;
			for (int j = 0; j < home.size(); j++) {
				point home1 = new point(home.get(j).x,home.get(j).y);
				int path = Integer.MAX_VALUE;
				for (int j2 = 0; j2 < tmp.size(); j2++) {
					int p = Math.abs(home1.x-tmp.get(j2).x) + Math.abs(home1.y-tmp.get(j2).y);
					path = Math.min(path, p);
				}
				chickenPath += path;
			}
			result = Math.min(chickenPath, result);
		}
		System.out.println(result);
	}
}
