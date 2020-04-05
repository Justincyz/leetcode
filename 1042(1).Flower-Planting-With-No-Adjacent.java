/*1042. Flower Planting With No Adjacent
链接：https://leetcode.com/problems/flower-planting-with-no-adjacent/
Easy: 
You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.

paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.

Also, there is no garden that has more than 3 paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.

 

Example 1:
Input: N = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]

Example 2:
Input: N = 4, paths = [[1,2],[3,4]]
Output: [1,2,1,2]

Example 3:
Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
Output: [1,2,3,4]
 

Note:
1 <= N <= 10000
0 <= paths.size <= 20000
No garden has 4 or more paths coming into or leaving it.
It is guaranteed an answer exists.
*/

/*解题思路  硬解
这道题目告诉我们有N个花园从1到N编号。 给出一个数组paths, paths[i]代表了两个
相邻花园的通道 paths[i][0]和paths[i][1]代表了两个不同的花园。告诉我们总共有四种不同的花，
所有相邻的花园不能种植同一种花。每个花园最多和另外三个花园相邻，其实就是告诉我们一定
有解。问，给出任意一种花的排列方式。
这道题目其实只能硬解，每一个花园最多只有三个相邻的，有四种颜色。排列组合3x4=12种方式，
所以最后的结果还是O(N)的。


*/

class Solution {
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[] res = new int[N];

        /*全部初始化为1，因为花的编号是以1为底的。在一些特殊情况下，比
        如说有N个花园，没有任何花园相连的话，那么结果就是输出长度为N
        的，每一位都是1的数组。
        */
        Arrays.fill(res, 1);

        //每一个花园和其相邻的花园构建一个map
        Map<Integer, List<Integer>> gardens = new HashMap<>();
        
        for(int[] path: paths){
            int garden1 = path[0], garden2 = path[1];
            if(!gardens.containsKey(garden1)) gardens.put(garden1, new ArrayList());
            if(!gardens.containsKey(garden2)) gardens.put(garden2, new ArrayList());
            gardens.get(garden1).add(garden2);
            gardens.get(garden2).add(garden1);
        }
        
        //第一个花园是1，我们从第二个花园开始
        for(int i=2; i<=N; i++){
            List<Integer> neighbors = gardens.get(i);
            if(neighbors == null) continue; //如果这个garden没有和其他任何gardens项链
            
            for(int color=1; color<=4; color++){
                boolean existColor = false;
                for(int neighbor: neighbors){
                	//如果有任何一个相连的花园使用了这个颜色，mark existColor = true
                    if(res[neighbor-1] == color){
                        existColor = true;
                        break;
                    }
                }

                //如果这个颜色没有被用在任何相连的花园里
                if(!existColor){
                    res[i-1] = color;
                    break;
                } 
            }
        }
        
        return res;
    }
}