/*55. Jump Game
Medium: 
Given an array of non-negative integers, you are initially positioned 
at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:
Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
jump length is 0, which makes it impossible to reach the last index.
*/

/*解题思路
这道题目给了我们一个一位数组，数组中的每一个值代表了当前位置可以跳跃的最大距离。问，从第一位
开始跳跃，我们有没有可能跳到最后一位。首先就是最简单的解法，虽然是O(n^2)但是还是过了。
我们先建立一个一维的boolean数组，从起始点开始遍历，每一次遍历当前位置前面的所有位置。
如果遍历过程中有任意一个可以跳到当前位的点，那么我们就mark当前位为true。如果遍历完当前位置
所有的点都不可以到达当前位置，那么说明当前位置不可达，那么更别说在这个之后的位置了。直接return false.

第二种快很多的解法，在O(n)的时间内就可以被解出来。其实我们关注的不是每个点可以到达的最远
位置，而是前面所有的点可以到达的最远位置能不能覆盖到当前位置。我们维护一个一位数组dp，
其中dp[i]表示达到i位置时剩余的步数，到达当前位置的剩余步数跟什么有关呢，其实是跟上一个位置
的剩余步数和上一个位置的跳力有关。这里的跳力就是原数组中每个位置的数字，因为其代表了以当前位
置为起点能到达的最远位置。所以当前位置的剩余步数（dp值）和当前位置的跳力中的较大那个数决定了
当前能到的最远距离，而下一个位置的剩余步数（dp值）就等于当前的这个较大值减去1，因为需要花一
个跳力到达下一个位置，所以我们就有状态转移方程了：dp[i] = max(dp[i - 1], nums[i - 1]) - 1，
如果当某一个时刻dp数组的值为负了，说明无法抵达当前位置，则直接返回false，最后循环结束后直接返
回true即可

*/

//2ms
class Solution {
    public boolean canJump(int[] nums) {
        int[] jumps = new int[nums.length];
        jumps[0] = nums[0];
        for(int i= 1; i<nums.length; i++){    
            jumps[i] = Math.max(jumps[i-1], nums[i-1])-1;
            if(jumps[i] <0) return false;
           
        }
        return true;
    }
}

/*
这道题目还有一种greedy的解法，算是最快的解法了。因为只需要一个reach变量。表示最远能到达的位置。
首先分析当前点是否可达，或者是否已经到了最后一个点。也就是说我们只对最远能到达的位
置感兴趣，所以我们维护一个变量reach。遍历数组中每一个数字，如果当前坐标大于reach或者reach已
经抵达最后一个位置则跳出循环，否则就更新reach的值为其和i + nums[i]中的较大值，
其中i + nums[i]表示当前位置能到达的最大位置：
*/
//1ms
class Solution {
    public boolean canJump(int[] nums) {

        int reach = nums[0];
        for(int i= 1; i<nums.length; i++){    
            if(i> reach || reach >= (nums.length-1)) break;
            reach = Math.max(reach, i+nums[i]);
           
        }
        return reach >= (nums.length-1) ? true: false;
    }
}

// 162ms beats 23% O(N^2)
class Solution {
    public boolean canJump(int[] nums) {
        boolean[] jumps = new boolean[nums.length];
        jumps[0] = true;
        for(int i= 1; i<nums.length; i++){    
            for(int j=0; j<i; j++){
                if(jumps[j] == true && (nums[j]+j >= i)){
                    jumps[i] = true;
                    break;
                } 
            }
            if(jumps[i] == false) return false;
        }
        return jumps[nums.length-1];
    }
}