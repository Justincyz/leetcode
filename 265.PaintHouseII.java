/*
Hard
There are a row of n houses, each house can be painted with one of the k colors. 
The cost of painting each house with a certain color is different. You have to paint 
all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k 
cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0
; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the 
minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. 
Minimum cost: 1 + 4 = 5; 
Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5. 
Follow up:
Could you solve it in O(nk) runtime?
*/
/*
思路: 时间复杂度O(n*k)， 无空间开销
题目不算是一个很难的，但是有很多的细节要注意
1. min1 和 min2是指针，所以每一次只要比较指针的位置就可以了。因为直接比较值得话如果有重复
的最小值那么就会无法选择。每一次记录当前行最小的和次小的两个值。因为下一行一定每一个选择一定会在
这两个之间选择一个(原因是当前行的每一位都要选择上一行可能的最小值。如果上一行最小值刚好是当前指针
指向的这一位的上一个, table[i-1][min] ==table[i][j]，那就不能选，那么一定要选择次小的那一个)。
*/
class Solution {
    public int minCostII(int[][] costs) {
        if(costs.length==0 || costs[0].length==0) return 0;
        int n = costs.length, k = costs[0].length;
        int min1 = -1, min2 = -1;

        for(int i=0; i< n; i++){
            int last1 = min1, last2 = min2;
            min1 = -1; min2 = -1;
            for(int j=0; j<k; j++){
                if(j != last1){
                    costs[i][j] += last1<0 ? 0: costs[i-1][last1];  
                }else{
                    costs[i][j] += last2<0 ? 0: costs[i-1][last2];
                }
       
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1; min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[n-1][min1];
    }
}