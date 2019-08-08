/*974. Subarray Sums Divisible by K
Medium
Given an array A of integers, return the number of 
(contiguous, non-empty) subarrays that have a sum divisible by K.

Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
*/

/*解法
这道题一开始就知道要用pre-sum的解法，但是一下子没想出来如何在O(n)的
时间复杂度内求解。后来想到应该是这样子。 如果 (X-Y)%K = 0, 那么 X%K -Y%K=0
所以 X%K = Y%K。知道了这个方法后就很简单了。每一次存入到map中的值其实
是 (pre-sum)%K的值。每次只要寻找在这之前出现过几次一样的经过mod后的数就可以了。 

*/

class Solution {
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
 
        map.put(0, 1);
        int count = 0, sum = 0;
        for(int a : A) {
            sum +=a; //不需要先建立一个pre-sum数组
            int temp = sum%K;
            /*这一段有点不太清楚，先记住吧。之所以要把负数加K是因为否则的话 (-1,2,9)当K=2时过不了*/
            if(temp < 0) temp += K;  // Because -1 % 5 = -1, but we need the positive mod 4
            count += map.getOrDefault(temp, 0);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        return count;
    }
}

