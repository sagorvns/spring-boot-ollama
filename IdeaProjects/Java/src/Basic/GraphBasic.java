package Basic;

import java.util.*;
import java.util.Queue;

//For Youtube Lecture
public class PrintAllPaths {
    static class Edge {
        int src;
        int dest;
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }
    static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));
        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));
        graph[6].add(new Edge(6, 5));
    }
    public static void printAllPaths(ArrayList<Edge> graph[], int src, int tar, String
            path, boolean vis[]) {
        if(src == tar) {
            System.out.println(path);
            return;
        }
        for(int i=0; i<graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            if(!vis[e.dest]) {
                vis[e.dest] = true;
                printAllPaths(graph, e.dest, tar, path+"->"+e.dest, vis);
                vis[e.dest] = false;
            }
        }
    }
    
    public static void bfs(ArrayList<Edge> graph[], int V) {
boolean visited[] = new boolean[V];
Queue<Integer> q = new LinkedList<>();
q.add(0); //Source = 0
while(!q.isEmpty()) {
int curr = q.remove();
if(!visited[curr]) {
System.out.print(curr+" ");
visited[curr] = true;
for(int i=0; i<graph[curr].size(); i++) {
Edge e = graph[curr].get(i);
q.add(e.dest);
}
}
}
System.out.println();
}

public static void dfs(ArrayList<Edge> graph[], int curr, boolean visited[]) {
if(visited[curr]) {
return;
}
System.out.print(curr+" ");
visited[curr] = true;
for(int i=0; i<graph[curr].size(); i++) {
Edge e = graph[curr].get(i);
dfs(graph, e.dest, visited);
}
}

    public static void main(String args[]) {
        int V = 7;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        int src = 0;
        int tar = 5;
        boolean vis[] = new boolean[V];
        vis[src] = true;
        printAllPaths(graph, src, tar, ""+src, vis);
        bfs(graph, V);
        dfs(graph, 0, new boolean[V]);
    }
}