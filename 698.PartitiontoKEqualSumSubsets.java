/*698. Partition to K Equal Sum Subsets
Medium: dp, recursive

Given an array of integers nums and a positive integer k, find whether 
it's possible to divide this array into k non-empty subsets whose sums 
are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), 
(2,3), (2,3) with equal sums.

*/

/*解题思路
这道题给了我们一个数组nums和一个数字k，问我们该数字能不能分成k个非空子集合(不需要连续)，
使得每个子集合的和相同。数组中的数字都是正数。这跟之前那道 Partition Equal Subset Sum 
很类似，但是那道题只让分成两个子集合。这道题我们可以用递归来做，首先我们还是求出数组的所有
数字之和sum，首先判断sum是否能整除k，不能整除的话直接返回false。然后需要一个visited数组来
记录哪些数组已经被选中了，然后调用递归函数，我们的目标是组k个子集合，使得每个子集合之和为
target = sum/k。我们还需要变量start，表示从数组的某个位置开始查找，curSum为当前子集合之和，
在递归函数中，如果k=1，说明此时只需要组一个子集合，那么剩余的就是了，直接返回true。如果curSum
等于target了，那么我们再次调用递归，此时传入k-1，start和curSum都重置为0，因为我们当前又找到了
一个和为target的子集合，要开始继续找下一个。否则的话就从start开始遍历数组，如果当前数字已经访问
过了则直接跳过，否则标记为已访问。然后调用递归函数，k保持不变，因为还在累加当前值，start传入i+1，
curSum传入curSum+nums[i]，因为要累加当前的数字，如果递归函数返回true了，则直接返回true。否则就
将当前数字重置为未访问的状态继续遍历：这个循环里最大的优化点就是判断当前sum是否大于 range，这样
剪枝之后可以去掉很多无意义的循环

这道题目中已经说明了所以数字会大于1.但是要注意，面试当中可能会问到例子中要注意sum = 0的情况,
比如可能 [-1,-1,0,0]

*/

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
    	int n = nums.length;
    	if(n==0) return false;
        int total = 0;
        for(int i: nums) total+=i;
            
        if(total % k !=0 || k<=0) return false;

        boolean[] visited = new boolean[n];
 		return helper(visited, nums, k, 0, 0, 0,total/k);
    }

    public boolean helper(boolean[] visited, int[]nums, int k, int sum, int cur_num, int start, int range){
    	if(k == 1) return true;
        if(sum>range) return false;
    	if(sum == range && cur_num >0) return helper(visited, nums, k-1,0, 0,0,range);
        
    	for(int i=start ; i<nums.length; i++){
    		if(!visited[i]){
    			visited[i] = true;
    			if(helper(visited, nums, k, sum+nums[i], cur_num+1, i+1, range)) return true;
    			visited[i] = false;
    		}
    	}
    	return false;
    }
}

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i : nums) sum += i;
            
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        int target = sum / k;
        int row = nums.length - 1;
        
        return helper(nums, new int[k], target, row);
    }

    public boolean helper(int[] nums, int[] sums, int sum, int row) {
        if (row < 0) {
            return true;
        }
        int v = nums[row--];
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] + v <= sum) {
                sums[i] += v;
                if (helper(nums, sums, sum, row)) {
                    return true;
                }
                sums[i] -= v;
            }
            if (sums[i] == 0) break;
        }
        return false;
    }
}