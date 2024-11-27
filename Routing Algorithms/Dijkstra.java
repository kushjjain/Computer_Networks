import java.util.Scanner;

public class Dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of nodes:");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];
        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter the source node (0 to " + (n - 1) + "):");
        int source = sc.nextInt();

        int[] dist = new int[n];
        int[] parent = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        dist[source] = 0;

        for (int i = 0; i < n; i++) {
            int u = extractMin(dist, visited, n);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !visited[v]) {
                    if (dist[v] > dist[u] + graph[u][v]) {
                        dist[v] = dist[u] + graph[u][v];
                        parent[v] = u;
                    }
                }
            }
        }
        System.out.println("Node\tDistance\tParent");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t%d\t\t%s\n", i, dist[i], parent[i] == -1 ? "-" : parent[i]);
        }

        sc.close();
    }

    private static int extractMin(int[] dist, boolean[] visited, int n) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
