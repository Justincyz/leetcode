/*
Easy
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
Minimum cost: 2 + 5 + 3 = 10.

*/

class Solution {
    public int minCost(int[][] costs) {
        int[]table = new int[3];
        for(int[] level: costs){
            int a = table[0], b = table[1], c =table[2];//要先记录上一位的状态
            table[0] = Math.min(b,c)+level[0];
            table[1] = Math.min(a,c)+level[1];
            table[2] = Math.min(a,b)+level[2];      
        }
        int res = table[0] > table[1]? table[1]:table[0];
        return res > table[2]? table[2]: res;
    }
}