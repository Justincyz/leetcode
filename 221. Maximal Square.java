/*Maximal Square
Medium
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Output: 4
*/
/*
这道题是一道dp的题目。我们用一个二维数组来记录每一个可能的正方形的边长。
(PS: 其实直接记录面积也可以，但是每次要算一个square root就会时间比较久。
虽然来说time complexity是一样的)。如果当前 matrix[i][j]为1的话，那么我们
只需要从table[i-1][j], table[i][j-1]和table[i-1][j-1]中找到那个最小的
值，那个值代表的就是在加上当前位置的1之后，新可以组成的正方形的面积。为什么
只需要看那三个位置呢？画一张图就清楚了。 因为和当前位组合起来最大的正方形一定
是三条边长中最小的，否则的话计算出来的正方形中有0.就无法组合了。
*/

class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length==0) return 0;
        int max = 0, m = matrix.length, n=matrix[0].length;
        
        int[][] table = new int[m][n];
        
        for(int i=0; i<n; i++){
            table[0][i] = matrix[0][i]-'0';
            max = Math.max(max, table[0][i]);
        } 
        for(int i=0; i<m; i++){
            table[i][0] = matrix[i][0]-'0';
            max = Math.max(max, table[i][0]);
        } 
        
        for(int i=1; i<m; i++){
            for(int j=1; j<n;j++){
                int cell =matrix[i][j]-'0';
                if(table[i-1][j] !=0 && table[i][j-1] !=0 && table[i-1][j-1]!=0 && cell !=0){
                    int area = Math.min(Math.min(table[i-1][j], table[i][j-1]),table[i-1][j-1])+1;
                    //int len = (int)Math.sqrt(area);//预期记录面积不如直接记录边长，一样的，不需要改动很大
                    //area = (len+1)*(len+1);
                    table[i][j] = area;
                    max = Math.max(max, area);
                }else{
                    table[i][j]=cell;
                }
            }
        }
        return max*max;
    }
}

