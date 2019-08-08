/*
Given a non-empty 2D matrix matrix and an integer k, find the 
max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Input: matrix = [[1, 0,1],
                 [0,-2,3]]  k = 2
Output: 2 
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
*/
/*
这道题的核心思想和我原来想的一样，就是一个2-D array的pre-sum解法。
时间复杂度为 O[min(m,n)^2 * max(m,n) * log(max(m,n))]
空间复杂度为 O(max(m, n))

最外面的for loop是作为每次循环的基底。以列为单位，一列一列的累加，同时，
对于累加起来的这一列。我们从上到下扫描他，找出其中连续的一段子数列，他的
和最大。这就是大矩形下的一个子矩形， 他的和最大。再和当前最大的和比较，
如果更大，那么就更新当前最大和，同时更新当前最大矩阵的边界。

其实细节部分一看代码基本就清楚了。
重点解释一下TreeSet的部分。TreeSet有这个 ceiling() function，目的是找到
比当前数小但是最接近当前数的值。每一次我们都要找 cur, which represents
the pre-sum value that included the current number, 然后减去k。 假设这个数为v, 那么
v+k <= cur， 但是最接近cur, 那么反过来，cur - v 就是等于小于k但是最接近k的
那个长方形面积了。 
视频参考: https://www.youtube.com/watch?v=yCQN096CwWM
*/
class Solution {
     public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix.length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int res = Integer.MIN_VALUE;

        for (int left = 0; left < n; left++) {
            int[] sums = new int[m];
            for (int right = left; right < n; right++) {
                for (int i = 0; i < m; i++) {
                    sums[i] += matrix[i][right];
                }
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int cur = 0;
                for (int sum : sums) {
                    cur += sum;
                    Integer num = set.ceiling(cur - k);
                    if (num != null) {
                        res = Math.max(res, cur - num);
                    }
                    set.add(cur);
                }
            }
        }
        return res;
    }
}