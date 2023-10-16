mport java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of vertices and edges (in same order) - ");
		int vertices = sc.nextInt();
		int edges = sc.nextInt();

		ArrayList<ArrayList<Integer>> vec = new ArrayList<>();

		for (int i = 0; i < edges; i++) {
			System.out.print("Enter src node, destination node and weight - ");
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();

			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(u);
			temp.add(v);
			temp.add(w);

			vec.add(temp);
		}
		System.out.println("Graph is - ");
		System.out.println(vec);
		System.out.println("Shortest paths are - ");
		System.out.println(dijkstra(vec, vertices, edges, 0));
		sc.close();
	}

	public static ArrayList<Integer> dijkstra(ArrayList<ArrayList<Integer>> vec, int vertices, int edges, int source) {
		ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
		for (int i = 0; i < vertices; i++)
			graph.add(new ArrayList<>());

		for (ArrayList<Integer> edge : vec) {
			graph.get(edge.get(0)).add(new Pair(edge.get(1), edge.get(2)));
			graph.get(edge.get(1)).add(new Pair(edge.get(0), edge.get(2)));
		}

		PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.dist - y.dist);


		int[] vis = new int[vertices];
		int[] parents = new int[vertices];

		for (int i = 0; i < vis.length; i++)
			vis[i] = Integer.MAX_VALUE;

		pq.add(new Pair(source, 0));
		vis[source] = 0;

		while (!pq.isEmpty()) {
			Pair curr = pq.poll();

			int currD = curr.dist;
			int node = curr.node;

			ArrayList<Pair> children = graph.get(node);
			for (Pair p : children) {
				int newD = currD + p.dist;
				if (newD < vis[p.node]) {
					vis[p.node] = newD;
					parents[p.node] = node;
					pq.add(new Pair(p.node, newD));
				}
			}
		}
		ArrayList<Integer> ans = new ArrayList<>();
		for (int index = 0; index < vis.length; index++) {
			ans.add(vis[index]);
		}
		System.out.print("Parents of each node - ");
		System.out.println(Arrays.toString(parents));
		return ans;
	}
}
