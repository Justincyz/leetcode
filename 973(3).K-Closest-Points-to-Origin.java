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
这道题目是一个比较高频的大厂考试题目。谷歌，FB都有考过。题目让我们找距离原点最近的K的点在哪里。这道题最简单的办法就是用一个自定义的pq来找到这K个点。自定义的pq就是计算每一个点距离远点的距离来排序的。距离小的排在前面，打的排在后面。这样的做法是最简单的。

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


/*
 这个解法是使用了类似quick sort的办法。每一次我们先找到一个pivot number，然后将比pivot number小的元素放在前面，把比pivot number大的元素放在后面。最后将我们的pivot number放在中间对应的位置。这样我们就可以快速的知道我们选择的这个pivot number是在原数组的什么位置。如果这个位置正好为K的话，我们直接把前K个元素返回就可以了，因为不需要在意顺序，只要知道是前K个元素就可以了。如果这个pivot number所在的位置比K小的话，我们就可以缩小搜索的范围。在后半截元素当中另外找到一个pivot number, 再进行sort。我们重复这个过程直到找到结果。这个搜索的复杂度理论上是O(N)。和有一道题目，找到数组中第K个数字的做法一样。但是最坏的情况下会变成O(N^2)
*/
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