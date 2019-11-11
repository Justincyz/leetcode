/*840. Magic Squares In Grid
Easy
链接: 

A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).

 

Example 1:

Input: [[4,3,8,4],
        [9,5,1,9],
        [2,7,6,2]]
Output: 1
Explanation: 
The following subgrid is a 3 x 3 magic square:
438
951
276

while this one is not:
384
519
762

In total, there is only one magic square inside the given grid.


*/

/*解题思路
这样的暴力解法已经是最优解了

*/

class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int total =0, n = grid.length, m = grid[0].length;
        for(int i=0; i+2< n; i++){
            for(int j=0; j+2 < m; j++){
                if(check(grid, i, j)) total++;
            }
        }
        return total;
    }
    
    public boolean check(int[][] grid, int i, int j){
       Set<Integer> set = new HashSet<>();
        int b1 = grid[i][j]+grid[i][j+1]+grid[i][j+2];
        
        int b2 = grid[i+1][j]+grid[i+1][j+1]+grid[i+1][j+2];
         if(b1 != b2) return false;
        
        int b3 = grid[i+2][j]+grid[i+2][j+1]+grid[i+2][j+2];
         if(b2 != b3) return false;
        
        int b4 = grid[i][j]+grid[i+1][j]+grid[i+2][j];
         if(b3 != b4) return false;
        
        int b5 = grid[i][j+1]+grid[i+1][j+1]+grid[i+2][j+1];
         if(b4 != b5) return false;
        
        int b6 = grid[i][j+2]+grid[i+1][j+2]+grid[i+2][j+2];
         if(b5 != b6) return false;
        
        int b7 = grid[i][j]+grid[i+1][j+1]+grid[i+2][j+2];
         if(b6 != b7) return false;
        
        int b8 = grid[i+2][j]+grid[i+1][j+1]+grid[i][j+2];
         if(b7 != b8) return false;
        
        for(int p =i; p<=i+2; p++){
            for(int q = j; q<=j+2; q++){
                if(grid[p][q] >9 || grid[p][q] < 1) return false;
                set.add(grid[p][q]);
            }
        }
        if(set.size() <9) return false;
        return true;
    }
}