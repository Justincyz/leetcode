/*918. Maximum Sum Circular Subarray
Medium
链接: https://leetcode.com/problems/maximum-sum-circular-subarray/

Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.

Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)

Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)


Example 1:
Input: [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3

Example 2:
Input: [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10

Example 3:
Input: [3,-1,2,-1]
Output: 4
Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4

Example 4:
Input: [3,-2,2,-3]
Output: 3
Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3

Example 5:
Input: [-2,-3,-1]
Output: -1
Explanation: Subarray [-1] has maximum sum -1
 

Note:
-30000 <= A[i] <= 30000
1 <= A.length <= 30000
*/


/*解题思路
这道题目有点意思，给我们一个数组，让我们找到这个数组当中最大的sub-array和。同时我们可以把这个数组当做是一个循环的数组。意思就是我们可以取一段尾部和一段头部拼接在一起也算是sub-array。但是注意我们当然不可以循环两次这个数组。比如说上面的例子2当中我们就是取了最后一个数和第一个数拼接在一起形成一个循环的数组。

要找到非循环数组的最大的sub-array是很简单的，我们使用两个变量，max代表了到目前为止我们可以找到的最大的sub-array的和是多大。这个maxPreSum的意思是，我们截止到目前这一位数为止，在这之前可以连上的最大的subarray的和是多大。每一次我们都要将maxpresum的值和maxpresum+当前值进行对比。如果maxpresum+当前值比当前值本身小了，那么对于后面的数字来说直接加上当前的值就好了，我们就更新maxpresum的值。否则如果加上当前的值可以凑出一个更大的sub-array的值，那么我们的maxpresum = maxpresum + 当前值。

比如说输入是 [1,-2,3,-2]
max和maxPreSum的变化是这样的。
      max: 1,  1, 3, 3 
maxPreSum: 1, -1, 3, 1

那么我们怎么来看这个前后这样连接的subarray呢。我想到的办法就是，如果前后相连的话，实际上就是中间会有一段是断开的。那么中间哪一段断开可以使得前后相连是最小的呢？答案是找到中间最小的一段subarray的值，然后用整个array的和去减这个最小值。那么反过来我们不就可以找到一个最大值了吗？

这里有一点要注意，如果数组当中所有的元素都是负数的情况下。我们用total - MinimumSubArray的值会是0。此时只能取我们正常非循环的max的值。
*/

class Solution {
    public int maxSubarraySumCircular(int[] A) {
        int max = A[0], maxPreSum = A[0];
        for(int i=1 ;i < A.length; i++){
            max = Math.max(A[i], max);
            max = Math.max(max, A[i]+maxPreSum);
            maxPreSum = Math.max(A[i], maxPreSum+A[i]);
        }
        int total = 0;
        
        for(int a : A) total+=a;
        
        int min = A[0], minPreSum = A[0];
        for(int i=1 ;i < A.length; i++){
            min = Math.min(A[i], min);
            min = Math.min(min, A[i]+minPreSum);
            minPreSum = Math.min(A[i], minPreSum+A[i]);
        }
        if(total == min) return max;
        max = Math.max(max, total-min);
        
        return max;
    }
}

