package LevelUP;

import java.util.LinkedList;

public class dfsQuestion {
    static int[][] dir={{1,0},{0,1},{-1,0},{0,-1}};
   
    public int orangesRotting(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        
        int freshOranges=0;
        LinkedList<Integer> que= new LinkedList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==2){
                    
                    que.addLast(i*m+j);
                }
                else if(grid[i][j]==1){
                    freshOranges++;
                }
            }
        }
       
        if(freshOranges==0){
            return 0;
        }
        int time=0;
        while(que.size()>0){
            int size=que.size();
           
            while(size-->0){
                int rVal=que.removeFirst();
                for(int[] d:dir){
                    int r=(rVal/m)+d[0];
                    int c=(rVal%m)+d[1];
                    if(r>=0 && r<n && c>=0 && c<m && grid[r][c]==1){
                        if(--freshOranges==0){
                            return time+1;
                        }
                        grid[r][c]=2;
                        que.addLast(r*m+c);
                    }
                }
            }
            time++;
        }
       
        return -1;
    }
    public int[][] updateMatrix(int[][] mat) {
        int[][] dir={{1,0},{0,1},{-1,0},{0,-1}};
        LinkedList<Integer> que=new LinkedList<>();
        
        int n=mat.length;
        int m=mat[0].length;

        boolean[][] vis=new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(mat[i][j]==0){
                    que.addLast(i*m+j);
                    vis[i][j]=true;
                }
            }
        }
        int level=0;
        while(que.size()>0){
            int size=que.size();
            while(size-->0){
                int rVtx=que.removeFirst();
                int r=rVtx/m;
                int c=rVtx%m;
                mat[r][c]=level;
                for(int[] d:dir){
                    int row=r+d[0];
                    int col=c+d[1];
                    if(row>=0 && col>=0 && row<n && col<m && vis[row][col]==false){
                        
                        que.addLast(row*m+col);
                        vis[row][col]=true;
                    }
                }
            }
            level++;
        }
        return mat;

    }
    //https://www.lintcode.com/problem/663/
    

}
