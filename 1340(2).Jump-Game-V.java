/*1340. Jump Game V
链接：https://leetcode.com/problems/jump-game-v/
Hard: 
Given an array of integers arr and an integer d. In one step you can jump from index i to index:

i + x where: i + x < arr.length and 0 < x <= d.
i - x where: i - x >= 0 and 0 < x <= d.
In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).

You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.

Notice that you can not jump outside of the array at any time.

Example 1:
Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
Output: 4
Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9. You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
Similarly You cannot jump from index 3 to index 2 or index 1.

Example 2:
Input: arr = [3,3,3,3,3], d = 3
Output: 1
Explanation: You can start at any index. You always cannot jump to any index.

Example 3:
Input: arr = [7,6,5,4,3,2,1], d = 1
Output: 7
Explanation: Start at index 0. You can visit all the indicies. 

Example 4:
Input: arr = [7,1,7,1,7,1], d = 2
Output: 2

Example 5:
Input: arr = [66], d = 1
Output: 1
 

Constraints:
1 <= arr.length <= 1000
1 <= arr[i] <= 10^5
1 <= d <= arr.length
*/

/*解题思路 dp
视频可供参考
https://www.youtube.com/watch?v=y5hRO6NvOHg

题目给我们一个整形数组height和一个整数d，height[i]代表了当前位置i的高度。
告诉我们有这样的规则，我们可以从 height[i]跳到height[j]的位置上。但是要满足
以下两个条件。(1) j距离i最大不能超过d. (2) 在j和i之间的所有高度，包括height[j]
高度都不能等于或者超过height[i]的高度。让我们求出，在这个数组当中最大的跳跃次数
是多少次。

其实做起来并不是一个很难的题目。我自己写的就有两种解法，一种是bottom-up,一种是
top-down. 实际是一个从最低的位置往两边走，第二个是从最高的位置往两边走。
其实看代码就很容易明白了
*/

//从最低位开始计算
class Solution {
    public int maxJumps(int[] arr, int d) {
        int[] barMaxJump = new int[arr.length];
        int[][] heightToPos = new int[arr.length][2];
      
        for(int i=0; i< arr.length; i++){
            heightToPos[i] = new int[]{arr[i], i}; //{height, pos}
        }
        
        Arrays.sort(heightToPos, (a,b)-> a[0]-b[0]);
        int res =0;
        for(int i=0; i< heightToPos.length; i++){ //the height is rank from low to high
            int height = heightToPos[i][0], pos =  heightToPos[i][1];

            int maxJump = 1;
            for(int left = pos-1; left>=Math.max(pos-d, 0) && arr[left] < height; left--){
                maxJump = Math.max(maxJump, barMaxJump[left]+1);
            }
            for(int right = pos+1; right <=Math.min(pos+d, arr.length-1)&& arr[right] < height; right++){
                maxJump = Math.max(maxJump, barMaxJump[right]+1);
            }

            barMaxJump[pos] =maxJump;
            res = Math.max(res, barMaxJump[pos]);
        }
        
        return res;
    }
}

//从最高位开始计算
class Solution {
    public int maxJumps(int[] arr, int d) {
        //pq当中的 int[], array[0]代表位置，array[1]代表高度
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->b[1]-a[1]);
        int[] dp = new int[arr.length];
        
        for(int i=0; i< arr.length; i++){
            pq.add(new int[]{i, arr[i]});
        }
       
        int res = 1;
        while(!pq.isEmpty()){
            int[] max = pq.poll();
            int pos = max[0], height = max[1];
            
            for(int i = pos+1; i <= Math.min(pos+d, arr.length-1) && arr[i] < height; i++){
                dp[i] = Math.max(dp[i], dp[pos]+1);
            }
            for(int i=pos-1; i>=Math.max(pos - d, 0) && arr[i] < height; i--){
                dp[i] = Math.max(dp[i], dp[pos]+1);
            }
        }
        //最大值必须要最后更新
        for(int jump: dp) res = Math.max(res, jump+1);
       
        return res;
    }
}

//还可以用dfs来做
class Solution {
    public int maxJumps(int[] arr, int d) {
        int[] dp=new  int[arr.length];
        int result=0;
        for(int i=0; i<arr.length; i++) result=Math.max(result, dfs(i, arr, dp, d));
        return result;
    }
    
    private int dfs(int idx, int[] arr, int[] dp, int d) {
        if(dp[idx]!=0) return dp[idx];
        int result=1;
        int curr=idx;
        while(curr-1>=0 && idx-(curr-1)<=d && arr[curr-1]<arr[idx]) {
            curr--;
            result=Math.max(result, 1+dfs(curr, arr, dp, d));
        }
        curr=idx;
        while(curr+1<arr.length && curr+1-idx<=d && arr[curr+1]<arr[idx]) {
            curr++;
            result=Math.max(result, 1+dfs(curr, arr, dp, d));
        }
        dp[idx]=result;
        return result;
    }
}