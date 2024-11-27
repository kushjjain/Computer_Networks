import java.util.Scanner;

public class BellmanFord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of nodes: ");
        int n = sc.nextInt();
        System.out.println("Enter the weight matrix (enter 0 for no edge): ");
        int[][] weight = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                weight[i][j] = sc.nextInt();
            }
        }
        System.out.println("Enter the starting point (0 to " + (n - 1) + "): ");
        int source = sc.nextInt();
    
        int[] dist = new int[n];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE; 
            parent[i] = -1; 
        }
        dist[source] = 0; 

        if (bellmanFord(weight, dist, parent, n)) {
            System.out.println("New routing table for node " + source + ":");
            for (int i = 0; i < n; i++) {
                char node = (char) ('A' + i); 
                System.out.printf("To %c: %d %c\n", node, dist[i], parent[i] == -1 ? '-' : (char) ('A' + parent[i]));
            }
        } else {
            System.out.println("Graph contains a negative weight cycle. No solution.");
        }
    }

    public static boolean bellmanFord(int[][] weight, int[] dist, int[] parent, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (weight[u][v] != 0) {
                        if (dist[v] > dist[u] + weight[u][v]) {
                            dist[v] = dist[u] + weight[u][v]; 
                            parent[v] = u; 
                        }
                    }
                }
            }
        }
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (weight[u][v] != 0 && dist[v] > dist[u] + weight[u][v]) {
                    return false; 
                }
            }
        }
        return true; 
    }
}
