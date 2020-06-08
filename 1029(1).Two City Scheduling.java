/*1029. Two City Scheduling
Easy
链接: https://leetcode.com/problems/two-city-scheduling/
There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.


Example 1:
Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 

Note:

1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000
*/



/*解题思路
这道题目挺有意思的。题目告诉们有2N个数的人，数组costs上面每一位代表每一个人。现在有两个城市，A和B。我们要保证这2N个人当中一半去了A城市，一半去了B城市。那么costs其中的每一位都代表了一个人去两个不同城市的机票票价。costs[i][0]代表了第i个人去A城市的票价，costs[i][1]代表了第i个人去B城市的票价。题目问我们，在保证了每个城市都有N个人去的情况下，如何分配这些人使得总的机票价格最小。

我们把这个问题换一个角度想一下。我们选择每一个人去A城市或者B城市的理由是，去某一个城市相对于去另外一个城市总的花销会小一些。这是一个相对的概念，所以我们应该先计算一下去两个城市的票价差别。比如说上面的例子里面，第一个人的票价差是10， 第二个人的票价差是170，第三个人的票价差是350，第四个人的票价差是10。我们应该先安排谁呢？应该先安排那些票价差别大的。还是那个问题，我们要安排一个人去某一个城市是因为相对于去另外一个城市来说更加便宜。所以上面四个人里面票价差最大的是2号，差价为350。我们在这个人的两个目的地里面选择一个票价低的。我们以此类推直到某一个城市的人数到达了N。那么剩下的人就都会去另外一个城市了。

SPACE: O(N)
TIME: O(NLOGN)
*/

class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int res =  0, N = costs.length/2;
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)-> b.costDiff-a.costDiff);
        
        for(int[] cost : costs){
            pq.add(new Node(cost[0], cost[1], Math.abs(cost[0]-cost[1])));
        }
        
        int countA = 0, countB = 0;
        while(countA <N && countB < N){
            Node node = pq.poll();
            if(node.costToA < node.costToB){
                countA++;
                res += node.costToA;
            }else{
                countB++;
                res += node.costToB;
            }
        }
        
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(countA == N) res += node.costToB;
            else res+=node.costToA;
        }
        
        return res;
    }
    
    class Node{
        int costToA;
        int costToB;
        int costDiff;
        public Node(int costToA, int costToB, int costDiff){
            this.costToA = costToA;
            this.costToB = costToB;
            this.costDiff = costDiff;
        }
    }
}

//网上一种更加简单的写法

class Solution {
    public int twoCitySchedCost(int[][] costs) {
        
        Arrays.sort(costs, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
               return o1[0] - o1[1] - (o2[0] - o2[1]);
            }
        });

        int total = 0;
        
        int n = costs.length/2;
        
        for(int i = 0; i<n; ++i) {
           
            total += costs[i][0] + costs[i+n][1];
        }
                

        return total;
        
    }
}