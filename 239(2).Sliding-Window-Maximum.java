/*239. Sliding Window Maximum
链接：https://leetcode.com/problems/sliding-window-maximum/
Hard: 
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Follow up:
Could you solve it in linear time?

Example:
Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 

Constraints:
1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length
*/

/*解题思路
题目中给出一个数组和一个大小为k的window。 这个大小为k的window从左往右滑动，让我
们输出一个数组，其中数组中每一位代表每一个滑动window当中最大的数。

第一种方法
首先是最简单的办法，就是利用最大priorityqueue，快速获取每一个区间当中的最大元素。
接下来就是如何快速确定不在window当中的元素，我们要把他们从pq当中移除。我使用的
办法就是用hashmap来形成数字->频率 的映射关系，因为某一个元素可能在一个window档期
内出现多次。当l和r指针的距离是k+1的时候。 我们首先添加进新的元素，也就是r指针指向的元素。
然后对于l指针指向的元素从map当中减一，如果此时在map当中为0, 则remove这个元素。
现在我们要做的就是从pq当中把最左边的元素去除(这个不是在每一步都需要的)，只有当
最大值从当前window中被移除的时候，才需要这样做。 然后此时移动左指针l, 将pq顶端
的元素放入对应位置。继续移动右指针就可以了。

空间复杂度O(K), 时间复杂度O(nlogk)

*/
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int l =0, r = 0;
        
        int[] rangeMax = new int[nums.length-k+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        Map<Integer, Integer> inWindow = new HashMap<>();
        
        while(r <k){
            pq.add(nums[r]);
            inWindow.put(nums[r], inWindow.getOrDefault(nums[r], 0)+1);
            r++;
        }
        rangeMax[l] = pq.peek();
        
        while(r < nums.length){
            //先把下一位加进来
            pq.add(nums[r]);
            inWindow.put(nums[r], inWindow.getOrDefault(nums[r], 0)+1);
            inWindow.put(nums[l], inWindow.get(nums[l])-1);
            
            if(inWindow.get(nums[l]) ==0){
                inWindow.remove(nums[l]);
            }
            
            while(!inWindow.containsKey(pq.peek())) pq.poll();
            l++;
            rangeMax[l] = pq.peek();
            r++;
        }
        
        
        return rangeMax;
    }
}

/*
第二种方法时间复杂度和空间复杂度都是O(N)
其实这种办法很简单。利用一个deque保存元素的位置。 这个双向链表内指向的元素都是
当前window范围内的元素。链表头部代表了当前windows中最大的元素，往后元素
的大小依次递减。注意链表中的元素不一定是相邻的，而是呈现递减的态势。
*/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] rangeMax = new int[nums.length-k+1];
        
        Deque<Integer> q = new LinkedList<>();
        
        for(int i=0; i< nums.length; i++){
            
            //将deque最前端不属于范围K之内的元素去掉
            if(!q.isEmpty() && q.peekFirst() < i-k+1){
                q.pollFirst();
            }
            //从后往前去除deque中比当前元素小的所有元素
            while(!q.isEmpty() && nums[q.peekLast()] < nums[i]){
                q.pollLast();
            }
            //此时deque的头部是当前window下最大的元素
            q.addLast(i);

            //将范围内的元素添加进结果中
            if(i-k+1 >=0) rangeMax[i-k+1] = nums[q.peekFirst()];
           
        }
        
        return rangeMax;
    }
}