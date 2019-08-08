/*300. Longest Increasing Subsequence
Medium

Given an unsorted array of integers, find the length of longest 
increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], 
therefore the length is 4. 
*/

/*解题思路
第一种办法就是用dp, 对于每一个节点i，用指针j从前往后扫到，如果nums[i]>nums[j], 
那么就更新一下 dp[i] = max(dp[i], dp[j]+1)。这种办法类似于brute-force的解法了。
时间上只能beats 20%。

第二种方法时间很快，用的是binary search的办法。稍微有点tricky。先上一段网络上的解释
“思路是，我们先建立一个数组，把首元素放进去并且用指针end记录，然后比较原数组之后的元素，如果遍历到的
新元素比ends数组中的首元素小的话，替换首元素为此新元素，如果遍历到的新元素比
数组中的末尾元素还大的话，将此新元素添加到数组末尾(注意不是覆盖原末尾元素)。
如果遍历到的新元素比数组首元素大，比尾元素小时，此时用二分查找法找到第一个
不小于此新元素的位置，覆盖掉位置的原来的数字，以此类推直至遍历完整个原数组，
此时ends的长度就是我们要求的LIS的长度，特别注意的是新数组内的值可能不是一
个真实的LIS，比如若输入数组nums为{4, 2， 4， 5， 3， 7}，那么算完后的新数组
为{2， 3， 5， 7}，可以发现它不是一个按原来元素位置排序的LIS，只是长度相等而已，千万要注意
这点。”这里解释一下为什么要用二分查找去找到数组中间的这么一位数，如果我们有这样一组
数组，[10,9,2,5,3,4]。那么当我们遍历到3的时候，ends数组中会是[2,5,0...]。此时
如果我们想要之后的数组中出现最长连续的subsequence, 那么就需要降低ends数组中每个元素的
最小值。就需要找到比3大又最接近3的那个数，那就是5。将5替换成3后我们才可能加入4。
用数学原理证明的话是这样。当我们遍历到3时。如果3之后可能出现的最大连续序列的最小值大于5的话
，那么是否替换掉5就无所谓。但是如果3之后可能出现的最大连续序列的最小值小于5大于3的话我们
降低到了3，那就可以匹配上之后可能出现的3到5范围内的数字。
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length==0) return 0;
        int n = nums.length;        
        int[] dp = new int[n];
        
        int max = 0;
        for(int i=0; i<n; i++){
            dp[i] =1;
            for(int j=0; j<i; j++){
               if(nums[i]>nums[j])
                dp[i] = Math.max(dp[i], dp[j]+1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

//binary search
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length==0) return 0;
        int n = nums.length;
        int[] res = new int[n+1];
        int end =1; //相当于记录了一个最长lens
        res[end] = nums[0];
        for(int num: nums){
            if(num > res[end])  res[++end]= num;
            else if(num < res[0]) res[0] = num;
            else{
                int start = 0, tail = end;
                while(start < tail){
                    int mid = start + (tail-start)/2;
                    if(res[mid] < num) start = mid+1;
                    else tail = mid;
                }
                res[tail] = num;
            }
        }
        return end;
    }
}

//类似的binary search的解法还有几种，不过都是大同小异
public int lengthOfLIS(int[] nums) {
    int[] tails = new int[nums.length];
    int size = 0;
    for (int x : nums) {
        int i = 0, j = size;
        while (i != j) {
            int m = (i + j) / 2;
            if (tails[m] < x)
                i = m + 1;
            else
                j = m;
        }
        tails[i] = x;
        if (i == size) ++size;
    }
    return size;
}