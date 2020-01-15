/*73. Set Matrix Zeroes
Medium: 

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input: 
[ [1,1,1],
  [1,0,1],
  [1,1,1]]
Output: 
[ [1,0,1],
  [0,0,0],
  [1,0,1]]
Example 2:

Input: 
[ [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]]
Output: 
[ [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

/*解题思路
这道题乍一看上去很简单，但是要注意不可以直接在原数组上去翻转有0的行和列，因为回破坏原来的结构。
比如说同一行中有两个0，这两个0对应的列都需要被翻转。但是如果直接翻转之后就不知道原来的数组中
有多少0了。所以按照题目的原来意思，我们可以建立一个新的matrix，然后在新的matrix中进行翻转。
但是这样会带来O(MN)的空间开销。一个改进的办法就是用两个boolean数组来记录行跟列出现0的位置。如果
同一行出现了一次'0',那么这一行就标记为true。这个需要我们先遍历整个matrix。最后再遍历boolean数组
来炸掉行和列。此种方法已经可以beats 99%了。
根据题目的要求，我们最好可以把空间复杂度再降到O(1)，继续往下
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for(int i=0; i< m; i++){
        	for(int j=0; j< n; j++){
        		if(matrix[i][j] !=0) continue;
        		//这个坐标的行列都需要被标记需要被炸掉
        		row[i] = true;
        		col[j] = true;
        	}
        }

        for(int i=0; i<m; i++){
        	if(row[i] == true){
        		for(int j=0; j<n; j++) matrix[i][j] = 0;
        	}
        }
        for(int i=0; i<n; i++){
        	if(col[i] == true){
        		for(int j=0; j<m; j++) matrix[j][i] = 0;
        	}
        }

    }
}

/*
这是一个空间复杂度为O(1)的解法，实际上就是用了这个数组的第一行和第一列来当做上一个方法的两个
Boolean[] array一样。每一次都像剥洋葱一样，剥掉的都是最外面的一行和一列。然后记录这一行和
这一列的0的个数，再映射到最外层上面。就代表这一行这一列有炸弹。因为这样会破坏掉最外层的结构，
也就是说不清楚在映射到最外层之后，原来的最外层到底有多少或者原来有没有0.所以我们要一开始记录第一行
和第一列的炸弹数量的同时，用两个变量来代表原来有没有0.总体结构就是这样，空间复杂度也变成了O(1)
*/

class Solution {
    public void setZeroes(int[][] matrix) {
    	int m = matrix.length, n = matrix[0].length;
    	boolean colZero = false, rowZero = false;
        
    	for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) colZero = true;
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) rowZero = true;
        } 
        //这里是每一次排除最左边和最上边的两层
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowZero) {
            for (int i = 0; i < n; ++i) matrix[0][i] = 0;
        }
        if (colZero) {
            for (int i = 0; i < m; ++i) matrix[i][0] = 0;
        }

    }
}


//这是 12/10/2019年第二次做，用了stack来做，最简单的办法
class Solution {
    public void setZeroes(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        Stack<int[]> stack = new Stack();
        for(int i=0; i < n; i++){
            for(int j = 0; j<m; j++){
                if(matrix[i][j] == 0)
                    stack.add(new int[]{i, j});
            }
        }
        while(!stack.isEmpty()){
            int[] p = stack.pop();
            int r = p[0], c = p[1];
            for(int i=0; i<m; i++){
                if(matrix[r][i] !=0) matrix[r][i] = 0;
            }
            for(int i=0; i<n; i++){
                if(matrix[i][c] !=0) matrix[i][c] = 0;
            }
        }
        
        
    }
}

