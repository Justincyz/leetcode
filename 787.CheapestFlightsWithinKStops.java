/*787. Cheapest Flights Within K Stops
Medium: 
There are n cities connected by m flights. Each fight starts from city u 
and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and 
the destination dst, your task is to find the cheapest price from src to 
dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:

      0
      /\
  100/  \500
    /    \
   1-100--2

上面的例子如果把K换成0的话，只能经过(0,2)这条边了，所以结果就会是500


*/

/*解题思路
首先是最简单的办法，也就是先用map构建所有起始点到所有可到达点的graph, 
具体来说就是建立每个结点和其所有能到达的结点的集合之间的映射，然后就是用DFS来遍历这个图了，
用变量cur表示当前遍历到的结点序号，当前剩余的转机次数K，访问过的结点集合visited，当前累
计的价格spend。在递归函数中，首先判断如果当前cur为目标结点dst，那么结果res赋值为out，
并直接返回。你可能会纳闷为啥不是取二者中较小值更新结果res，而是直接赋值呢？原因是我们之后做
了剪枝处理，使得out一定会小于结果res。然后判断如果K小于0，说明超过转机次数了，直接返回。然后就
是遍历当前结点cur能到达的所有结点了，对于遍历到的结点，首先判断如果当前结点已经访问过了，直接跳
过。或者是当前价格out加上到达这个结点需要的价格之和大于结果res的话，那么直接跳过。这个剪枝能极
大的提高效率，是压线过OA的首要功臣。之后就是标记结点访问，调用递归函数，以及还原结点状态的常规操
作了。
*/

class Solution {
    int res = Integer.MAX_VALUE;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(src);
        for(int[] flight: flights){
            if(!map.containsKey(flight[0])) map.put(flight[0], new ArrayList());
            map.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        helper(map, src, dst, K, visited, 0);
        return res==Integer.MAX_VALUE ? -1: res;
    }

    public void helper(Map<Integer, List<int[]>> map, int cur, int dst, int K, Set<Integer> visited, int spend){
        if( cur == dst){
            res = spend;
            return;
        } 
        if(map.get(cur)==null || K <0) return;

        for(int[] temp : map.get(cur)){
            if(visited.contains(temp[0]) || (spend+temp[1] > res)) continue; //这一条再继续下去没办法比当前值小了
            visited.add(temp[0]);
            helper(map, temp[0], dst, K-1, visited, spend+temp[1]);
            visited.remove(temp[0]);
        }
    }
}



/*这个就是dp的算法，在这里也算是Bellman Ford算法的一种。很久没接触这个具体含义有点忘了，
大概意思就是先设置一个图的所有边长为MAX, 然后一步步的减小边长的值。摘抄一段代码的解释
这里我们使用一个二维DP数组，其中dp[i][j]表示最多飞i次航班到达j位置时的最少价格，那么dp[0][src]
初始化为0，因为飞0次航班的价格都为0，转机K次，其实就是飞K+1次航班，我们开始遍历，i从1到K+1，每次
dp[i][src]都初始化为0，因为在起点的价格也为0，然后即使遍历所有的航班x，更新dp[i][x[1]]，表示最
飞i次航班到达航班x的目的地的最低价格，用最多飞i-1次航班，到达航班x的起点的价格加上航班x的价格之和，
二者中取较小值更新即可*/
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //dp[i][j] denotes the cheapest price within i-1 stops, stop in j city
        long[][] dp = new long[K+2][n];//K+2指的是包含了起始点和终点
        for (long[] d : dp) Arrays.fill(d, Integer.MAX_VALUE);
        dp[0][src] = 0;
        for (int i = 1; i < K+2; i++) {
            dp[i][src] = 0;
            for (int[] f : flights) {
                dp[i][f[1]] = Math.min(dp[i][f[1]], dp[i-1][f[0]] + f[2]);
            }
        }
        return dp[K+1][dst] == Integer.MAX_VALUE ? -1 : (int)dp[K+1][dst];
    }
}


//在上述代码的基础上用了一个滚动数组
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //dp[i][j] denotes the cheapest price within i-1 stops, stop in j city
        long[] dp = new long[n];
       
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[src] = 0;
        for (int i = 1; i < K+2; i++) {
            long[] temp = new long[n];
            Arrays.fill(temp, Integer.MAX_VALUE);
            dp[src] =0;
            for (int[] f : flights) {
                temp[f[1]] = Math.min(temp[f[1]], dp[f[0]] + f[2]);
            }
            dp = temp;
        }
        return dp[dst] == Integer.MAX_VALUE ? -1 : (int)dp[dst];
    }
}
