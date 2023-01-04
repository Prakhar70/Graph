package Foundation;

import java.util.ArrayList;
import java.util.PriorityQueue;

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

    public static int CountAllPath(int src,int dest,boolean[] vis,String str){
        if(src == dest){
            
            System.out.println(str+src);
            return 1;
        }

        vis[src]=true;
        int cnt=0;
        
        for(Edge e:graph[src]){
            if( !vis[e.v])
                cnt+=CountAllPath(e.v,dest,vis,str+src);
        }
        vis[src]=false;

        return cnt;
    }

    public static void printPreOrder(int src, boolean[] vis,String str, int sumWt){

        System.out.println(src+"-->"+str+src+"@"+sumWt);
        vis[src]=true;
        for(Edge e: graph[src]){

            if(!vis[e.v]){
                printPreOrder(e.v, vis, str+src , sumWt+e.w);
            }
        }
        vis[src]=false;
    }

    public static void printPostOrder(int src, boolean[] vis, String str, int sumWt){

        
        vis[src]=true;
        for(Edge e: graph[src]){

            if(!vis[e.v]){
                printPostOrder(e.v, vis, str+src , sumWt+e.w);
            }
        }
        System.out.println(src+"-->"+str+src+"@"+sumWt);
        vis[src]=false;
    }

    static class Pair{
        String longestPath="";
        String shortestPath="";
        int longestPathWt = -(int)1e9;
        int shortestPathWt= (int)1e9;
        int floor = -(int)1e9;
        int ceil= (int)1e9;
    }

    
    public static void allSolution(int src, int dest, boolean[] vis, String psf, int wsf, Pair p, int givenWeight){
        if(src==dest){
            if(wsf>p.longestPathWt){
                p.longestPathWt=wsf;
                p.longestPath=psf+src;
            }
            if(wsf<p.shortestPathWt){
                p.shortestPathWt=wsf;
                p.shortestPath=psf+src;
            }
            if(wsf<givenWeight){
                p.floor=Math.max(p.floor,wsf);
            }
            if(wsf>givenWeight){
                p.ceil=Math.min(p.ceil,wsf);
            }

        }

        vis[src]=true;
        for(Edge e:graph[src]){

            if(!vis[e.v]){
                allSolution(e.v, dest, vis,psf+src, wsf+e.w,p,givenWeight);
            }
        }


        vis[src]=false;
    }

    static class pqPair{
        int wsf;
        String ssf;
        pqPair(int wsf,String ssf){
            this.wsf=wsf;
            this.ssf=ssf;
        }
    }

    public static void allSolution_(int src, int dest, boolean[] vis, String psf, int wsf, PriorityQueue<pqPair> pq,int k){
        if(src==dest){
            System.out.println(psf+src+"@"+wsf);
            pq.add(new pqPair(wsf, psf+src));
            if(pq.size()>k){
                pq.remove();
            }

        }

        vis[src]=true;
        for(Edge e:graph[src]){

            if(!vis[e.v]){
                allSolution_(e.v, dest, vis,psf+src, wsf+e.w, pq,k);
            }
        }
        vis[src]=false;
    }

    public static void dfs(int src, boolean[] vis,ArrayList<Integer> ans){
        vis[src]=true;
        ans.add(src);
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                dfs(e.v,vis,ans);
            }
        }
    }
    public static ArrayList<ArrayList<Integer>> getConnectedComp(){
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        boolean [] vis=new boolean [N];
        
        for(int i=0;i<N;i++){
            if(vis[i]==false)
            {
                ArrayList<Integer> smallAns= new ArrayList<>();
                dfs(i,vis,smallAns);
                ans.add(smallAns);
                
            }
                
        }
        return ans;
    }

    //day 3
    //leetcode flood fill
    public static int   numIslands(char[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int cnt=0;
        int[][] dir= {{0,1},{1,0},{0,-1},{-1,0}};
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]=='1'){
                    cnt++;
                    dfs(grid,i,j,dir);
                }
            }
        }
        return cnt;
        
    }
    public static void dfs(char[][] grid,int sr,int sc,int[][] dir){
       int n=grid.length;
       int m=grid[0].length;
        grid[sr][sc]='0';
        for(int[] d:dir){
            int r=sr+d[0];
            int c=sc+d[1];
            if(r>=0 && r<n && c>=0 && c<m && grid[r][c]!='0'){
                dfs(grid,r,c,dir);
            }
        }
    }

    public static boolean isGraphConnected(char[][] grid){
        return numIslands(grid)==1;
    }

    public static void hamintonian_dfs (int src, int osrc,boolean[] vis,int NoofEdges,String psf){
        if(NoofEdges == N-1){
            System.out.print(psf+src);
            int idx=find(src,osrc);
            if(idx!=-1){
                System.out.print("*");
            }
            System.out.println();
            return;

        }
        vis[src]=true;
        for(Edge e: graph[src]){
            if(!vis[e.v]){
                hamintonian_dfs (e.v, osrc, vis, NoofEdges+1, psf+src);
            }
        }

        vis[src]=false;
    }
    public static void hamintonian_Path(){

        boolean[] vis=new boolean[N];
        hamintonian_dfs(0, 0, vis, 0, "");
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
        addEdge(2, 5, 10);
        addEdge(0, 6, 10);

         display();
        // boolean vis[]=new boolean [graph.length];
        // System.out.println(find(0,3));
        //System.out.println(hasPath(0,6,vis));
        // System.out.println(CountAllPath(0, 6, vis, ""));
        //printPreOrder(0, vis, "", 0);
        // Pair p=new Pair();
        // allSolution(0,6,vis,"",0,p,40);
        // System.out.println("Longest Path "+ p.longestPath+"@"+p.longestPathWt);
        // System.out.println("Shortest Path "+ p.shortestPath+"@"+p.shortestPathWt);
        // System.out.println("floor "+p.floor);
        // System.out.println("Ceil "+p.ceil);
        /* 
        PriorityQueue<pqPair> pq=new PriorityQueue<>((a,b)->{
            return a.wsf-b.wsf;
        });
        allSolution_(0, 6, vis, "", 0, pq, 2);
        System.out.println(pq.peek().ssf+"@"+pq.peek().wsf);
        */

        /********* Get Connected comp *********/
        // removeEdge(3, 4);
        // ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        // list=getConnectedComp();
        // System.out.println(list);
        hamintonian_Path();

    }

}