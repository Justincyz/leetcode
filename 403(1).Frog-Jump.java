/*403. Frog Jump
Hard: 
A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:
The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.

Example 1:
[0,1,3,5,6,8,12,17]
There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.

Example 2:
[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.
*/

/*解题思路
这道题目是根据九章算术DP章节学习到的，有任何疑惑可以参考九章视频。
题目中说青蛙如果上一次跳了k距离，那么下一次只能跳k-1, k, 或k+1的距离，那么青蛙跳到某个石头上可能有多种跳法，由于这道题只是让我们判断青蛙是否能跳到最后一个石头上，并没有让我们返回所有的路径，这样就降低了一些难度。 
做法还是比较显而易见的，我们维护一个Map, map的值是一个hashset, 代表的是石头i下一跳
可以到达的位置。每一次都遍历石头，并且计算下一跳是否可以跳跃到下一个石头上。
最后检查最后一个石头的hashset是否为空。如果是空的话说明没有任何一块石头可以跳跃到
最后一块石头上。
*/

class Solution {
    public boolean canCross(int[] stones) {
        int len = stones.length;
        if(len <=1) return true;
        if(stones[1]-stones[0] > 1) return false;
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
       
        for(int i=0; i<len; i++){
            map.put(stones[i], new HashSet());
        }
        
        map.get(stones[0]).add(0);
        
        for(int i=0; i<len; i++){
            
            HashSet<Integer> s = map.get(stones[i]);
            for(int k: s){
                for(int j=(k-1); j<=(k+1); j++){
                    if(j <=0) continue;
                    
                    if(map.containsKey(stones[i]+j))
                        map.get(j+stones[i]).add(j);           
                }
            }
        }

        return !map.get(stones[len-1]).isEmpty();
    }
}