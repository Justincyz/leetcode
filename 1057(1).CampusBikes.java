/*1057. Campus Bikes
Medium: https://leetcode.com/problems/campus-bikes/

On a campus represented as a 2D grid, there are N workers and M bikes, 
with N <= M. Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes 
and workers, we choose the (worker, bike) pair with the shortest Manhattan 
distance between each other, and assign the bike to that worker. (If there
 are multiple (worker, bike) pairs with the same shortest Manhattan distance,
  we choose the pair with the smallest worker index; if there are multiple
   ways to do that, we choose the pair with the smallest bike index). We 
   repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = 
|p1.x - p2.x| + |p1.y - p2.y|.

Return a vector ans of length N, where ans[i] is the index (0-indexed) of 
the bike that the i-th worker is assigned to.

Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
Explanation: 
Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 
is assigned Bike 1. So the output is [1, 0].
(此处应该有图，回原题看)

Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation: 
Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance 
to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. 
So the output is [0,2,1].
(此处应该有图，回原题看)
*/

/*解题思路
这是一道谷歌高频题
题目告诉我们在一个二维的矩阵中，每个点代表一个坐标。输入两组数组，workers数组代表的
是所有worker的坐标，出现的顺序代表每个worker的编号(例如worker[2]代表的意思是第二个
worker处于坐标中的位置)。同样，每个bike也有自己的编号和坐标。根据题目的意思，我们需要给
所有的bike和worker配对(bike的数量大于等于worker的数量)。配对的方式是就近配对。如果一辆
bike和两个workers的距离一样的话，那么编号小的那个worker可以获得单车。同样如果一个worker
和两个Bikes的距离一样的话，那么worker会选择编号小的那个bike。
其实这道题目实现起来蛮容易的，我们先用一个priorityqueue重写compare()按照上述方法将元素排序好。
里面的元素就是所有单车和所有工人的配对，总共有O(MN)个元素。然后建立
两个hashset。我们然后就开始遍历我们的priorityqueue，每一次取得的顶部值都是既没有被获得过的
bike和也没有单车的worker。同时还是剩余没有配对成功的里面距离最小的一对。当我们取出这一对，加入到
结果中之后。再将Bike和worker各自放入对应的hashset中，代表下一次worker不能再取，bike也不能再
被获得一次了。

*/

//自写算法 beats 60% 550ms
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            public int compare(Node a, Node b){
                if(a.distance != b.distance) return a.distance-b.distance;
                else if(a.workerId != b.workerId) return a.workerId - b.workerId;
                else return a.bikeId - b.bikeId;
            }    
        });
        
        for(int i=0; i< workers.length; i++){
            for(int j=0; j< bikes.length; j++){
                int dis = Math.abs(workers[i][0]-bikes[j][0])+Math.abs(workers[i][1]-bikes[j][1]);
                Node node = new Node(i, j, dis);
                pq.add(node);
            }
        }
        
        Set<Integer> worker = new HashSet<>();
        Set<Integer> bike = new HashSet<>();
        int[] res = new int[workers.length];

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int b = node.bikeId, w = node.workerId;
            if(worker.contains(w) || bike.contains(b) ) continue;
            res[w] = b;
            worker.add(w);
            bike.add(b);
        }
        
        return res;
    }
}

class Node{
    int workerId;
    int bikeId;
    int distance;
    public Node(int workerId, int bikeId, int distance){
        this.workerId = workerId;
        this.bikeId = bikeId;
        this.distance = distance;
    }
}

//非常快的一种算法， beats 99% 50ms。稍微有一点投机取巧
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        List<int[]>[] buckets = new List[2001];
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dis = Math.abs(bikes[j][0] - workers[i][0]) + Math.abs(bikes[j][1] - workers[i][1]);
                if (buckets[dis] == null) {
                    buckets[dis] = new ArrayList<>();
                }
                buckets[dis].add(new int[] {i, j});
            }
        }
        int[] res = new int[workers.length];
        Arrays.fill(res, -1);
        Set<Integer> assignedBike = new HashSet<>();
        for (int i = 0; i < buckets.length && assignedBike.size() < workers.length; i++) {
            if (buckets[i] != null) {
                for (int[] pair : buckets[i]) {
                    if (res[pair[0]] < 0 && !assignedBike.contains(pair[1])) {
                        res[pair[0]] = pair[1];
                        assignedBike.add(pair[1]);
                    }
                }
            }
        }
        return res;
    }
}