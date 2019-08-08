/*494. Target Sum
Medium:

You are given a list of non-negative integers, a1, a2, ..., an, and a 
target, S. Now you have 2 symbols + and -. For each integer, you should 
choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal 
to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

/*解题思路
题目大意是给出一组数组，给数组中每个数字添加加号或者减号，看能不能组合成为一个target数字大小。
这道题首先最简单的就是用DFS或者用hashmap储存所有的中间状态的方法来做。这样的做法就跟1049:
Last stone Weight II 的做法是一样的了，但是在这道题目里速度要快很多，可能大家都用这种方法。
当然最快的还是第一种的dp的方法。这种做法很巧妙，因为我们要找到一个可以用dp来表达的式子。
这道题目其实可以转换成这种形式，我们将原数组分成两组，P(sum) 和 N(sum)。然后使得这两组和相减为S。
这样的话就有如下转换形式
P(sum) - N(sum) = S
P(sum) - N(sum) + P(sum) + N(sum) = S + P(sum) + N(sum)
2*P(sum) = S+ sums(sum)
按照原式我们要找的就是P(sum)的个数，因为求P(sum)的个数之后我们也可以对应的知道 N(sum)的个数。
所以在dp中 target是 (s+sum)/2.

*/



class Solution {
     public int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int n : nums) sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) /2); 
    }   

    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1]; 
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n]; 
        return dp[s];
    } 
}


class Solution {
     public int findTargetSumWays(int[] nums, int s) {
         Map<Integer, Integer> map  = new HashMap<>();
         map.put(0,1);
         for(int num: nums){
             Map<Integer, Integer> t = new HashMap<>();
             for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                 int sum = entry.getKey(), cnt = entry.getValue();
                 if(!t.containsKey(sum+num)) t.put(sum+num,0);
                 if(!t.containsKey(sum-num)) t.put(sum-num,0);
                 t.put(sum+num, t.get(sum+num)+cnt); 
                 t.put(sum-num, t.get(sum-num)+cnt); 
             }
             map = t;
         }
         return map.get(s) !=null? map.get(s): 0;
    } 
}



public class Solution {
    int result = 0;
    
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) return result;
        
        int n = nums.length;
        int[] sums = new int[n];
        sums[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            sums[i] = sums[i + 1] + nums[i];//主要是用来做剪枝处理
        
        helper(nums, sums, S, 0);
        return result;
    }
    public void helper(int[] nums, int[] sums, int target, int pos){
        if(pos == nums.length){
            if(target == 0) result++;
            return;
        }
        
        if (sums[pos] < Math.abs(target)) return;
        helper(nums, sums, target + nums[pos], pos + 1);
        helper(nums, sums, target - nums[pos], pos + 1);
    }
}

