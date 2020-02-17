/*134. Gas Station
Medium
链接: https://leetcode.com/problems/gas-station/
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.

Example 1:

Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:

Input: 
gas  = [2,3,4]
cost = [3,4,3]

Output: -1

Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.
*/

/*解题思路
这道题目看着好像很复杂，我来简化一下。让我们找到一个出发点，绕数组一圈
之后还能回到出发点，且中途汽油的值不能小于0.每一个位置上汽油量为gas[i]，
到达下一个位置i+1需要消耗掉汽油cost[i]。我们首先检查一圈，看看总的消耗量
total是否大于0，如果总的汽油入不敷出小于0的话，那么说明不管那一个点都没办法
走回到原来的出发点，因为总是会在某一个地方汽油量小于0的。
当我们完成这个检测之后，假设开始设置起点start = 0, 并从这里出发，如果当前的
gas值大于cost值，就可以继续前进，此时到下一个站点，剩余的gas加上当前的gas再减去
cost，看是否大于0，若大于0，则继续前进。当到达某一站点时，若这个值小于0了，则说
明从起点到这个点中间的任何一个点都不能作为起点。此时重设起点为下一个点，继续遍历。当遍历完整个环时，当前保存的起点即为所求。(题目说明如果有结果的话肯定是unique的)

*/

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, total=0;
        int[] path = new int[n];
        for(int i=0; i< n; i++){
            path[i] = gas[i]-cost[i];
            total+= (gas[i]-cost[i]);
        }
        if(total<0) return -1;
        int sum = 0, res =0;
        for(int i=0; i<n;i++){
            sum+= path[i];
            if(sum < 0){
                //重新设置起点下一位，因为当前位肯定无法达到
                res = i+1;
                sum = 0;
            }
        }
        
        return sum<0?-1:res;
    }
}