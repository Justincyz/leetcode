/*719. Find K-th Smallest Pair Distance
https://leetcode.com/problems/find-k-th-smallest-pair-distance/submissions/

Hard: 
Given an integer array, return the k-th smallest distance among all 
the pairs. The distance of a pair (A, B) is defined as the absolute 
difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0 
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
*/

/*解题思路
https://www.youtube.com/watch?v=WHfljqX61Y8&t=59s
首先我们知道，找到所有对子需要的时间是 n*(n-1)/2，因为确定第一个位置有n种可能性，
第二个位置有 n-1种可能性，同时去掉重复的排列，比如说(1,3)和(3,1)，也就是还要除以2。

这道题目首先有一个简单的做法，首先给数组排序，找到最大值和最小值组成的pair距离是多少。
我们根据距离创建k个桶，然后O(N^2)的时间复杂度找到所有的对子的差值，将差值放入对应
的桶中。最后遍历所有的桶，如果是 bucket[i] - k > 0 了，那么第k位的差值就为 i。在
这里K是被累减的。
*/

//解法一 C++
class Solution {
public:
    int smallestDistancePair(vector<int>& nums, int k) {
        int n = nums.size(), N = 1000000;
        vector<int> cnt(N, 0);
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                ++cnt[abs(nums[i] - nums[j])];
            }
        }
        for (int i = 0; i < N; ++i) {
            if (cnt[i] >= k) return i;
            k -= cnt[i];
        }
        return -1;
    }
};

/*
上面的解法只是将将的没有TLE， 下面这种解法用的就是二分法，可以把运行时间降低至O(nlogn)。
我们的目标是快速定位出第k小的距离，那么很适合用二分法来快速的缩小查找范围，然而最大的难点
就是如何找到判定依据来折半查找，即如果确定搜索目标是在左半边还是右半边。做过 Kth Smallest
 Element in a Sorted Matrix 和 Kth Smallest Number in Multiplication Table 
这两道题的同学应该对这种搜索方式并不陌生。核心思想是二分确定一个中间数，然后找到所有小于等于
这个中间数的距离个数，用其跟k比较来确定折半的方向。具体的操作是，我们首先要给数组排序，二分搜
索的起始 left 为0，结束位置 right 为最大pair的距离，即排序后的数字最后一个元素减去首元素。
然后进入 while 循环，算出中间值 mid，此外我们还需要两个变量 count 和 start，其中 count
 是记录小于等于 mid 的距离个数，i 是较小数字的位置，均初始化为0，然后我们遍历整个数组，
先进行 while 循环，如果 j 未越界，并且当前数字减去 i 指向的数组之差大于 mid，说明此时距离
太大了，我们增加减数大小，通过将 i 右移一个，那么 while 循环退出后，就有 j - i 个距离小于
等于 mid，将其加入 count 中，举个栗子来说：

1    2    3    3    5

i              j

mid = 2
首先当i处于0位，j已经遍历到第三位且第三位为3时，如果继续往下遍历的话 (5-1)就大于我们找到的
Mid值2了。所以当i为0时，pair之间小于Mid的对数为三对[1,2][1,3][1,3]。
将这三对加入到count中，然后j的位置可以保持不变，但是i此时再往后挪一位，指向2。但是j无法再往后
挪一位了，因为(5-2) >2了。所以此时我们再累加count的值，count+=2 (j-i = 2)。以此类推直到
完成一次遍历。这样的一次遍历是O(N)的时间，因为用的是两根指针。此时我们用 k和count数数量进行对比，
如果k > count了，就说明我们count取少了，相对应的我们要提高mid的值，也就是把lower bound 
l = mid+1。反之使 r = mid。最后我们返回l就是第k对的pair差值了。
*/
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0, r = nums[n-1] -nums[0];
        while(l<r){
            int count =0, j=0;
            int mid = l+ (r-l)/2;
            for(int i=0; i<n; i++){
                while(j < n && nums[j] - nums[i] <= mid) j++;
                count+= (j-i-1);
            }
            if(count >=k) 
                r = mid; 
            else 
                l = mid+1;
        }
        
        return l;
    }
}

