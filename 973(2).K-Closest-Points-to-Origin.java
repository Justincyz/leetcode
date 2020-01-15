/*973. K Closest Points to Origin
链接：https://leetcode.com/submissions/detail/206164544/
Medium: 
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
*/

/*解题思路
题目让我们找距离原点最近的K的点在哪里。这道题最简单的办法就是用一个
自定义的pq来找到这K个点。
还有一种做法就是用partition sort的办法来做。找到某个点，这个点
左边有K-1个元素都比自己小。从0到K-1就是距离原点最近的K个点，当然
这K个点本身是无序的，因为题目也没有要求说要有序才行。

*/


class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return  a[0]*a[0] + a[1]*a[1]-b[0]*b[0] - b[1]*b[1];
            }
        });
        
        for(int i=0; i< points.length; i++){
            pq.add(points[i]);
        }
        
        int[][] res = new int[K][2];
        while(K-- >0){
            res[K] = pq.poll();
        }
        return res;
    }
}


//看起来好像很复杂，实际上不复杂，且相对会快一些。
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int s = 0, e = points.length - 1;
        
        while(true) {
            int pos = partition(points, s, e);
            if(pos == K - 1) break;
            if(pos < K - 1) s = pos + 1;
            else e = pos - 1;
        }
        int[][] res = new int[K][2];
        //取前K个点就可以了
        for(int i = 0; i < K; i++) res[i] = points[i];
        return res;        
    }
    
    private int partition(int[][] points, int s, int e) {      
        int dis = getDis(points[e]);
       
        for(int j = s; j <= e - 1; j++) {
            if(getDis(points[j]) < dis) {
                swap(points, s, j);
                s++;
            }
        }
        swap(points, s, e);
        return s;
    }
    
    private int getDis(int[] p) {
        return p[0] * p[0] + p[1] * p[1];        
    }
    
    private void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }
}