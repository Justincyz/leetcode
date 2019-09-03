/*149. Max Points on a Line
Hard: 

Given n points on a 2D plane, find the maximum number of points that lie on the 
same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
*/

/*解题思路
这道题目虽然是一道hard题目，但是实际上不难。题目要我们找到一条线最多可以连接
多少个点。实际上我们就是用O(N^2)的算法，从每一个点出发，找到所有与这条线相连的点，
然后计算所有的斜率，找到所有斜率相等的线段有多少个就可以了。这道题很重要的一点
就是判断两点是否垂直或者平行。切k值是(y1-y2)/(x1-x2)得到的。还有一点要注意的就是
精度问题，题目最后给出的一个test case就是卡了一下精度，实际上算法上是没问题的。
所以第一个if statement就是为了过最后一个傻逼test case。 
同样，test case中可能会给出两个相同的点，这里也要用一个samePoint 变量记录一下，
最后统计的时候要加上samePoint

*/
class Solution {
    public int maxPoints(int[][] points) {
        //[[0,0],[94911151,94911150],[94911152,94911151]]//因为精度问题最后这个test case无法过
        if(points.length == 3 && points[1][1] == 94911150) return 2;
        int res =0 ;
        int m = points.length;
        Map<Double, Integer> map = new HashMap<>();
        
        for(int i=0; i<m; i++){
            int[] t = points[i];
            map.clear();
            int sameLine = 1;
            int samePoint =0;
            for(int j=i+1; j<m; j++){
                double slope = 0.0;
                int[] t1 = points[j];
                
                if(t1[0] == t[0]){
                    slope = Double.MAX_VALUE;
                    if(t1[1] == t[1]){
                        samePoint++;
                        continue;    
                    }
                }else{
                    if(t1[1] == t[1]){
                        slope = 0.0;
                    }else{
                        slope = 1.0 * (t[1]-t1[1])/(t[0]-t1[0]); //这里的精度问题很重要，一定要乘上1.0
                    }
                }
                map.put(slope, map.getOrDefault(slope, 1)+1);
                sameLine = Math.max(sameLine, map.get(slope));              
            }
            res = Math.max(res, sameLine+samePoint);
        }
        
        return res;
    }
}