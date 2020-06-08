/* 1428. Leftmost Column with at Least a One
链接：https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
Medium:
A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.

You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).
BinaryMatrix.dimensions() returns a list of 2 elements [n, m], which means the matrix is n * m.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes you're given the binary matrix mat as input in the following four examples. You will not have access the binary matrix directly.

**Example 1:**
Input: mat = [[0,0],[1,1]]
Output: 0

**Example 2:**
Input: mat = [[0,0],[0,1]]
Output: 1

**Example 3:**
Input: mat = [[0,0],[0,0]]
Output: -1


**Example 4:**
Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
Output: 1

**Constraints:**
- 1 <= mat.length, mat[i].length <= 100
- mat[i][j] is either 0 or 1.
- mat[i] is sorted in a non-decreasing way.
*/

/*解题思路
这道题目给我们一个二维数组，数组当中都是0和1。特别之处在于我们不能直接操作这个数组，而是只能通过调用 BinaryMatrix()这一个方法来间接的访问数组内部元素。第一个方法 dimension()可以知道这个数组的长宽。第二个方法get(int x, int y)可以知道数组当中某一个特定的位置是什么数字。数组有一个特殊性，就是每一行都是非递减数组。通俗的说就是0不可能在1的后面。现在题目让我们找到这个数组当中最靠近左端的含有1的列的位置在哪里。


*/

/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int x, int y) {}
 *     public List<Integer> dimensions {}
 * };
 */
/*解法一:
首先我想到的最简单的办法就是对每一层都做一个二分法，通过二分法找到每一层最左边的1出现在哪里。如果我们可以找到1的话，我们就和我们最终结果更新一下。我们每一层都做二分，所以每一层的时间复杂度都是O(log(m)).

最后判断一下我们是否找到了这一列，否则的话返回-1.
TIME:O(nlogm)
SPACE: O(1)
*/
 class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int n = dim.get(0), m = dim.get(1);
        int res = m;
        for(int i=0; i<n; i++){
            int left =0, right = m-1;
            while(left <right){
                int mid = left+(right-left)/2;
                if(binaryMatrix.get(i, mid) == 0){
                    left = mid+1;
                }else{
                    right = mid;
                }
            }
            if(binaryMatrix.get(i, left) == 1) res = Math.min(res, left);
        }
        return res == m?-1:res;
    }
}

/*这里还有一种更加快的linear time的解法。我们从matrix的右上角出发，首先往下走，如果遇到1的时候，我们就往左走。直到遇到0为止，我们继续往下走。这样的原因是因为每一行都是Non-decreasing的，我们假设最左边的1出现在第x行。通过我们的算法，我们一定会遇到第x行，因为在这之上所有行数最左边的1都会出现在第X行1的右边。当我们遇到第X行的时候，我们又会一直走到第X行的最右边。所以我们一定可以遍历到最早出现1的column.
*/
class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int n = dim.get(0), m = dim.get(1);
        int res = m, x = 0, y = m-1;
        while(x < n && y>=0 ){
            if(binaryMatrix.get(x, y) == 1){
                res = y;
                y--;
            }else{
                x++;
            }
        }
        
        return res == m?-1:res;
    }
}