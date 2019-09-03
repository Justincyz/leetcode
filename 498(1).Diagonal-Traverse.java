/*498. Diagonal Traverse
链接：https://leetcode.com/problems/diagonal-traverse/
Medium: 
Given a matrix of M x N elements (M rows, N columns), return all elements 
of the matrix in diagonal order as shown in the below image.

Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]

*/

/*解题思路
这道题给了我们一个mxn大小的数组，让我们进行对角线遍历，先向右上，然后左下，再右上，以此类推直至遍历完整个数组，题目中的例子和图示也能很好的帮我们理解。可以链接回原题看图。
一开始想用的是BFS的算法，但是因为corner case太多，遂放弃。
由于移动的方向不再是水平或竖直方向，而是对角线方向，那么每移动一次，横纵坐标都要变化，向右上移动的话要坐标加上[-1, 1]，向左下移动的话要坐标加上[1, -1]，那么难点在于我们如何处理越界情况，越界后遍历的方向怎么变换。向右上和左下两个对角线方向遍历的时候都会有越界的可能，但是除了左下角和右上角的位置越界需要改变两个坐标之外，其余的越界只需要改变一个。那么我们就先判断要同时改变两个坐标的越界情况，即在右上角和左下角的位置。如果在右上角位置还要往右上走时，那么要移动到它下面的位置的，那么如果col超过了n-1的范围，那么col重置为n-1，并且row自增2，然后改变遍历的方向。同理如果row超过了m-1的范围，那么row重置为m-1，并且col自增2，然后改变遍历的方向。然后我们再来判断一般的越界情况，如果row小于0，那么row重置0，然后改变遍历的方向。同理如果col小于0，那么col重置0，然后改变遍历的方向。
注意在每个位置都需要判断四个方向有没有越界，因为左上角和右上角需要重置两个位置。
 
*/
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int[][] move = {{-1,1},{1,-1}};
        int m = matrix.length, n = matrix[0].length, k=0;
        int[] res = new int[m*n];
        int r = 0, c = 0;
        
        for(int i=0; i<m*n ; i++){
            res[i] = matrix[r][c];
           
            r += move[k][0];
            c += move[k][1];
            if(r >= m){
                r = m-1;
                c +=2;
                k = (k+1)%2;
            }if(c >= n){
                c = n-1;
                r +=2;
                k = (k+1)%2;
            }if(r <0){
                k = (k+1)%2;
                r = 0;
            }if(c <0){
                k = (k+1)%2;
                c=0;
            }
        }
        
        return res;
    }
}