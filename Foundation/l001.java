package Foundation;

import java.util.ArrayList;

class l001 {
    public static class Edge {
        int v;
        int w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public String toString() {
            return "(" + this.v + "," + this.w + ") ";
        }
    }

    public static int N = 7;
    public static ArrayList<Edge>[] graph = new ArrayList[N];//we cant create array of arraylist without warning in java

    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));

        
    }

    public static void display(){

        for(int i=0;i<N;i++){
            System.out.print(i+" ,");
            for(Edge e: graph[i]){
                System.out.print(e);
            }
            System.out.println();
        }
    
    }

    public static int find(int u,int v){

        for(int i=0;i<graph[u].size();i++)
        {
            if(graph[u].get(i).v == v){
                return i;
            }
        }
        return -1;
    }
    public static void removeEdge(int u,int v){

        int idx1=find(u,v);
        int idx2=find(v,u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVtx(int u){
        for(Edge e: graph[u]){
            removeEdge(u,e.v);
        }

       while(graph[u].size()>0){
           int n=graph[u].size();
           int v=graph[u].get(n-1).v;
           removeEdge(u,v);
       }
    }

    //why we ain't using vis[src]=false in line 83 ??

    public static boolean hasPath(int src,int dest,boolean[] vis){
        if(src==dest){
            return true;
        }

        vis[src]=true;
        boolean res=false;
        for(Edge e:graph[src]){
            if( ! vis[e.v])
                res=res||hasPath(e.v,dest,vis);
        }

        return res;
    }



    public static void main(String[] args) {
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        addEdge(3, 4, 10);
        addEdge(4, 5, 10);
        addEdge(4, 6, 10);
        addEdge(5, 6, 10);

        display();
        boolean vis[]=new boolean [graph.length];
        System.out.println(find(0,3));
        System.out.println(hasPath(0,6,vis));

    }

}