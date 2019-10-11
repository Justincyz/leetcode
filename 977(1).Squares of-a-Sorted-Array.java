/*977. Squares of a Sorted Array
链接：https://leetcode.com/problems/squares-of-a-sorted-array/
Easy: 

Given an array of integers A sorted in non-decreasing order, 
return an array of the squares of each number, also in sorted 
non-decreasing order.

 
Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
*/

/*解题思路
问题让我们给一个包括正负数的array的所有平方结果进行排序。
双指针从两头出发，从大到小赋值给数组，注意结束条件为 s == e, 不要忽略了
最后一个数字。

*/
class Solution {
    public int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        int s =0, e = A.length-1, idx =A.length-1;
        while(s <= e){
            if(A[s]*A[s] < A[e]*A[e]){
                res[idx] = A[e]*A[e];
                e--;
            }else{
                res[idx] = A[s]*A[s];
                s++;
            }
            idx--;
        }
        return res;
    }
}