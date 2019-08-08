/*410. Split Array Largest Sum
Hard
Given an array which consists of non-negative integers and an 
integer m, you can split the array into m non-empty continuous 
subarrays. Write an algorithm to minimize the largest sum among 
these m subarrays.
一句话，把一个数组切成m段然后使得此种切割方式产生和最大的那一段在所有切割方式中
最小。
*/

/*解题思路
这道题有两种解法，第一种是dp,第二种是binary search.
1. Binary Search
这种方法其实和lintcode 183. Wood Cut一模一样。方法也很简单，速度居然
也还很快。 我们可以假设m最大的时候，就是可以把每一个元素都分成是独立的
一个部分。当m最小的时候，那么整个数组就是一个元素。那么low我们就取得是
nums[]中最大的那个数。high我们取得就是整个array的sum. 然后每次取mid = 
(low+high)/2。看如果每个组别的sum在不超过这个数值的情况下，总共可以分
出来多少个组。如果组别数大于m，那么说明我们每一组必须要容纳更多的元素
才可以，所以就让low = mid+1。反之说明组别不够，那么我们需要减少每个组的
sum，那么就可以匀出来一些创造新的组别。最后取得low就是等于在满足有m个
组的情况下可以产生的最小的组别最大和。 记住，mid==组别和

*/

class Solution {
    public int splitArray(int[] nums, int m) {
        int high = 0, low = 0;
        for(int num: nums){
            high+=num;
            low = Math.max(low, num);
        }
        
        while(low <= high){
            int mid = low+(high-low)/2;
            int interval = 1, sum =0;
            boolean flag = false;
            for(int num: nums){
                sum+=num;
                if(sum > mid){
                    interval++;
                    sum = num;
                }
                if(interval >m){
                    flag = true;
                    break;
                }
            }
            if(flag){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return low;
    }
}

/*DP
这道题的动态规划的思路比较难想。还是借鉴网上的一个很好地解释
“，我们再来看一种DP的解法，相对来说，这种方法应该更容易理解一些。我们建立一
个二维数组dp，其中dp[i][j]表示将数组中前j个数字分成i组所能得到的最小的各个
子数组中最大值，初始化为整型最大值，如果无法分为i组，那么还是保持为整型最大值。
为了能快速的算出子数组之和，我们还是要建立累计和数组，难点就是在于要求递推公式
了。我们来分析，如果前j个数字要分成i组，那么i的范围是什么，由于只有j个数字，如果
每个数字都是单独的一组，那么最多有j组；如果将整个数组看为一个整体，那么最少有1组
，所以i的范围是[1, j]，所以我们要遍历这中间所有的情况，假如中间任意一个位置k
，dp[i-1][k]表示数组中前k个数字分成i-1组所能得到的最小的各个子数组中最大值，
而sums[j]-sums[k]就是后面的数字之和，我们取二者之间的较大值，然后和dp[i][j]原
有值进行对比，更新dp[i][j]为二者之中的较小值，这样k在[1, j]的范围内扫过一遍，
dp[i][j]就能更新到最小值，我们最终返回dp[m][n]即可，博主认为这道题所用的思想应
该是之前那道题Reverse Pairs中解法二中总结的分割重现关系(Partition Recurrence
 Relation)，由此看来很多问题的本质都是一样，但是披上华丽的外衣，难免会让人有些
 眼花缭乱了”
*/

//这个解法的速度就慢上很多，但是是一个很通用的解法
 class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n+1];
        int[][] dp = new int[m+1][n+1];
        for(int[] i: dp) Arrays.fill(i, Integer.MAX_VALUE);
        
        dp[0][0] = 0;
        for(int i=1; i<=n; i++){
            sum[i] = sum[i-1]+nums[i-1];
        }
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                for(int k=i-1; k<j; k++){
                    int val = Math.max(dp[i-1][k], sum[j]-sum[k]);
                    dp[i][j] = Math.min(dp[i][j], val);
                }
            }
        }
        return dp[m][n];
    }
}