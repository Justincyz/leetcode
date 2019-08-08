/*733. Flood Fill
Easy: 
An image is represented by a 2-D array of integers, each integer 
representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel 
(row and column) of the flood fill, and a pixel value newColor, 
"flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus 
any pixels connected 4-directionally to the starting pixel of 
the same color as the starting pixel, plus any pixels connected 
4-directionally to those pixels (also with the same color as 
the starting pixel), and so on. Replace the color of all of 
the aforementioned pixels with the newColor.

At the end, return the modified image.

Example 1:
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.
*/

/*解题思路
题目大意是，在一个给定的矩阵中，每一个位置都有不同的数值。给定一个起始点。将和起始点相连的，
且数值与起始点相同的点全部变成一个newColor数值。这道题目就是一个BFS的题目，其实没什么。
有一点要注意，如果给的newColor 和原来给的点的数值一样，直接return。这算是一个坑。

*/

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor) return image;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc});
        int[][] dirs = {{1,0},{-1,0}, {0,-1}, {0,1}};
        int oldColor = image[sr][sc];
        while(!q.isEmpty()){
            int[] temp = q.poll();
            image[temp[0]][temp[1]] = newColor;
            for(int[] dir : dirs){
                int x = temp[0]+dir[0];
                int y = temp[1]+dir[1];
                if(x>=0 && y>=0 && x<image.length && y<image[0].length && image[x][y] == oldColor){
                    q.add(new int[]{x, y});
                }
            }
        }
        
        return image;
    }
}


/*

*/

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor) {
            return image;
        }
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    private void dfs(int[][] image, int row, int col, int oldColor, int newColor) {
        if(row < 0 || col < 0 || row >= image.length || col >= image[0].length || image[row][col] != oldColor) {
            return;
        }
        image[row][col] = newColor;
        dfs(image, row - 1, col, oldColor, newColor);
        dfs(image, row + 1, col, oldColor, newColor);
        dfs(image, row, col - 1, oldColor, newColor);
        dfs(image, row, col + 1, oldColor, newColor);
    } 
}