/*240. Search a 2D Matrix II
Medium: 
Write an efficient algorithm that searches for a value in an m x n 
matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

Example:
Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
*/

/*解题思路
这道题目告诉我们，每一行每一列都是按照从小到大排列好的。让我们检查一个数字在不在这个
矩阵中。题目的提示是用二分法，一开始我发现的规律是这样的。对于每一个节点，我们看包括
这个节点的左上方的矩阵，比如说9这个数，那么左上方整个长方形的每一个数都比9小。那我们
每一次横纵坐标都取 (start+end)/2。还是以上面这个矩阵为例子，第一次横纵坐标都是原来的
1/2, 那么取到的就是9，和最右下角的30进行比较。如果这个值小于9，那么就在左上角的这个
矩阵中，如果是(9,30)之间的话那就要把除开左上角以9为顶点的矩阵的剩余的矩阵分割成三个
小矩阵来查找。这个方法倒是也可以，但是平均下来每次只能去掉1/4的面积。所以我们可以直接
用下面这个O(m+n)的办法来搜索这个矩阵。
首先，观察左下角的18我们发现，比18大的要往右边走，比18小的要往上面走。所以按照这个规律，
对于每一位我们都拿他和target作比较。然后往右上角移动一步。最后如果到达右上角还没有遇到
target就说明不在这个矩阵中。

*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length == 0) return false;
        
        int x = matrix.length-1, y =0;
        while(x>=0 && y<matrix[0].length){
            if(matrix[x][y] ==target) 
            	return true;
            else if(matrix[x][y] >target) 
            	x--;
            else 
            	y++;
        }
        return false;
    }
}

