package LevelUP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Question {
    
    /*
     * 200
     * 695
     * 463
     * 130
     */
    //lc 200
    // https://leetcode.com/problems/number-of-islands/description/
    public int numIslands(char[][] grid) {
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
    //lc 695
    // https://leetcode.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int ans=0;
        int[][] dir={{0,1},{1,0},{0,-1},{-1,0}};
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    ans=Math.max(ans,maxAreaOfComp(grid,i,j,dir));
                }
            }
        }
        return ans;
    }
    public int maxAreaOfComp(int[][] grid,int sr, int sc,int[][] dir){
        int m=grid.length;
        int n=grid[0].length;
        grid[sr][sc]=0;
        int area=0;
        for(int[] d:dir){
            int r=sr+d[0];
            int c=sc+d[1];
            if(r>=0 && r<m && c>=0 && c<n && grid[r][c]==1){
                area+=maxAreaOfComp(grid,r,c,dir);
            }
        }
        return area+1;

    }
    // lc 463 
    //https://leetcode.com/problems/island-perimeter/
    public int islandPerimeter(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int ans=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    ans+=4;
                    if(i-1>=0 && grid[i-1][j]==1){
                        ans--;
                    }
                    if(i+1<n && grid[i+1][j]==1){
                        ans--;
                    }
                    if(j-1>=0 && grid[i][j-1]==1){
                        ans--;
                    }
                    if(j+1 <m && grid[i][j+1]==1){
                        ans--;
                    }
                }
            }
        }
        return ans;
    }

    //leetcode 130
    // https://leetcode.com/problems/surrounded-regions/
    public void solve(char[][] board) {
        int[][] dir={{0,1},{1,0},{-1,0},{0,-1}};
        int n=board.length;
        int m=board[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 || j==0||i==n-1||j==m-1){
                    dfs_(board,i,j,dir);
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]=='$')
                    board[i][j]='O';
                else 
                    board[i][j]='X';
            }
        }
        
    }
    public void dfs_(char[][] board, int sr,int sc,int[][] dir){
        int n=board.length;
        int m=board[0].length;
        board[sr][sc]='$';
        for(int[] d:dir){
            int r=sr+d[0];
            int c=sc+d[1];
            if(r>=0 && r<n && c>=0 && c<m && board[r][c]=='O'){
                dfs_(board,r,c,dir);
            }
        }
    }

    // https://www.hackerrank.com/challenges/journey-to-the-moon/problem

    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (List<Integer> list : astronaut) {
            System.out.println(list);
            int u = list.get(0);
            int v = list.get(1);

            graph[u].add(v);
            graph[v].add(u);

        }

        ArrayList<Long> num_comp_list = new ArrayList<>();
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (vis[i] == false) {
                long noc = dfs(graph, vis, i);
                num_comp_list.add(noc);
            }
        }

        long sum = 0;
        long ans = 0;
        for (int i = num_comp_list.size() - 1; i >= 0; i--) {
            long curr_ele = num_comp_list.get(i);
            ans = ans + (sum * curr_ele);
            sum += curr_ele;

        }
        return ans;
    }

    static long dfs(ArrayList<Integer> graph[], boolean[] vis, int src) {
        long cnt = 0;
        vis[src] = true;
        for (int i : graph[src]) {
            if (!vis[i]) {
                cnt += dfs(graph, vis, i);
            }
        }
        return cnt + 1;
    }


    /************ Number of distict island ******************/
    //https://leetcode.com/problems/number-of-distinct-islands/
    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    String[] dirS = { "D", "U", "R", "L" };

    StringBuilder sb;
    int n, m;

    public void numDistinctIslands(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        for (int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                sb.append(dirS[d]);
                numDistinctIslands(grid, r, c);
                sb.append("b");
            }
        }
    }

    public int numDistinctIslands(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    sb = new StringBuilder();
                    numDistinctIslands(grid, i, j);
                    set.add(sb.toString());
                }
            }
        }

        return set.size();
    }
    // https://leetcode.com/problems/count-sub-islands/description/
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        
        int n=grid2.length;
        int m=grid2[0].length;
        int[][] dir={{1,0},{0,1},{-1,0},{0,-1}};
        int cnt=0;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid2[i][j]==1){
                   
                    boolean isComp=dfs(grid1,grid2,i,j,dir);
                    if(isComp==true){
                        cnt++;
                    }

                    
                }
            }
        }
        return cnt;
    }
    public boolean dfs(int[][] grid1,int[][] grid2,int sr, int sc,int[][] dir){

        int n=grid1.length;
        int m=grid1[0].length;
        boolean res=true;

        grid2[sr][sc]=0;
        
        for(int[] d:dir){
            int r=sr+d[0];
            int c=sc+d[1];
            if(r>=0 && r<n && c>=0 && c<m && grid2[r][c]==1){

                res=dfs(grid1,grid2,r,c,dir) && res; 
            }
        }
        return res && grid1[sr][sc]==1;
        

    }


}
