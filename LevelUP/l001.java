package LevelUP;

import java.util.ArrayList;

//Level 2 class 1 =========DFS===============
public class l001 {

    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display(ArrayList<Edge>[] graph) {

        int N = graph.length;
        for (int i = 0; i < N; i++) {

            System.out.print(i + "-->");
            for (Edge e : graph[i]) {

                System.out.println("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {

        for (int i = 0; i < graph[u].size(); i++) {
            if (graph[u].get(i).v == v) {
                return i;
            }
        }
        return -1;
    }

    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        int idx1 = findEdge(graph, u, v);
        graph[u].remove(idx1);

        int idx2 = findEdge(graph, v, u);
        graph[v].remove(idx2);
    }

    // hasPath
    // O(E), where E is total no of edges in that particular component.
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            return true;
        }
        vis[src] = true;
        boolean res = false;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                res = res || hasPath(graph, e.v, dest, vis);
            }
        }
        return res;
    }

    // Find Path
    public static int printAllPath(ArrayList<Edge>[] graph, int src, int dest, String psf, int wsf, boolean[] vis) {
        if (src == dest) {
            System.out.println(psf + dest + "@" + wsf);
            return 1;
        }
        vis[src] = true;
        int cnt = 0;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                cnt += printAllPath(graph, e.v, dest, psf + src, wsf + e.w, vis);
            }
        }
        vis[src] = false;
        return cnt;
    }

    // Get connected component
    //O(E+V)
    public static int getConnectedComponent(ArrayList<Edge>[] graph){
        int N=graph.length;
        int comp=0;
        boolean[] vis=new boolean[N];
        for(int i=0;i<N;i++){
            if(!vis[i]){
                comp++;
                dfs_GCC(graph,i,vis);
            }
        }
        return comp;
    }

    public static void dfs_GCC(ArrayList<Edge>[] graph,int src,boolean[] vis){
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.v])
                dfs_GCC(graph,e.v,vis);
        }
    }
    public static void constructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);

        display(graph);
        boolean[] vis = new boolean[N];
        // System.out.println(dfs_findPath(graph, 0, 6, vis));
        System.out.println(printAllPath(graph, 0, 6, "", 0, vis));
    }

    public static void main(String[] args) {
        constructGraph();
    }
    

}
