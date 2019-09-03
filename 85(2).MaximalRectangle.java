/*85. Maximal Rectangle
链接： https://leetcode.com/problems/maximal-rectangle/
Hard: 
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
*/

/*解题思路
此题是之前那道的 Largest Rectangle in Histogram 的扩展，这道题的二维矩阵每一层向上都可以看做一个直方图，输入矩阵有多少行，就可以形成多少个直方图，对每个直方图都调用 Largest Rectangle in Histogram 中的方法，就可以得到最大的矩形面积。那么这道题唯一要做的就是将每一层都当作直方图的底层，并向上构造整个直方图，由于题目限定了输入矩阵的字符只有 '0' 和 '1' 两种，所以处理起来也相对简单。方法是，对于每一个点，如果是 ‘0’，则赋0，如果是 ‘1’，就赋之前的 height 值加上1。具体参见代码如下：

*/


class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        int res =0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j] != '0') heights[j] +=1;
                else heights[j] = 0;
            }
           
            Stack<Integer> stack = new Stack<>();

            for(int j=0; j<=n; j++){
                int h = j == n ? 0 : heights[j];
                while(!stack.isEmpty() && h< heights[stack.peek()]){
                    int height = heights[stack.pop()];
                    int start = stack.isEmpty()? -1: stack.peek();
                    int area = height*(j-start -1);
                    res = Math.max(res, area);
                }
                stack.push(j);
            }
        }
        
        return res;
    }
}