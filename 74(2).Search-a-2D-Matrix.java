/*74. Search a 2D Matrix
Medium
链接: https://leetcode.com/problems/search-a-2d-matrix/
Write an efficient algorithm that searches for a value in an m x n matrix. 
This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the 
previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false


*/

/*解题思路
这道题要求搜索一个二维矩阵，由于给的矩阵是有序的，所以很自然的想到要用二分查找法、
因为题目给出了上一行的最后一个数字一定小于下一行的第一个数字，所以我们可以把整个
矩阵当做一个大的1-D 数组来二分查找。这里的一个小难点是如何确定横纵坐标。我们可以通过
取除数和取余数的方式来找到对应的坐标位置。这道题目还有一个比较麻烦的就是边界的确定。
以下是两种取边界的方法。

*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0) return false;
        int n = matrix.length, m= matrix[0].length;
        int low = 0, high = m*n;
        while(low < high){
            int mid = low+(high-low)/2;
            int r = mid/m , c = mid%m;
           
            int num = matrix[r][c];
            if(num == target) return true;
            else if(num < target){
                low = mid+1;
            }else{
                high = mid;
            }
        }
        return false;
    }
}

//这个boundary的取值比较费脑子，这里的boundary取值是我一开始写的，我也不知道为什么high要加1
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0) return false;
        int n = matrix.length, m= matrix[0].length;
        int low = 0, high = m*n-1;//这里和上面不一样
        while(low <= high){//这里和上面不一样
            int mid = low+(high-low)/2;
            int r = mid/m , c = mid%m;
           
            int num = matrix[r][c];
            if(num == target) return true;
            else if(num < target){
                low = mid+1;
            }else{
                high = mid-1;//这里和上面不一样
            }
        }
        return false;
    }
}




class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int start=0; int end = matrix.length-1;
        while(end>= start){
            int mid = start + (end-start)/2;  
            if(matrix[mid][0] == target) return true;
            if( matrix[mid][0] > target){
                end = mid-1;    
            } else {
                start = mid+1;
            }
        }
        
        if(start == 0) return false;
        int start2 =0; int end2 = matrix[0].length-1;
        
        while(start2 <= end2){
            int mid = start2 + (end2-start2)/2;
            //System.out.println(matrix[start][mid]);
            if(matrix[start-1][mid] == target){
               return true;
            }
            if(matrix[start-1][mid] < target){
                start2 = mid+1;
            }else{
                end2 = mid-1;
            }
        }
        
        return false;
    }
}