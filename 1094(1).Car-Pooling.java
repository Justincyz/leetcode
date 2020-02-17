/*1094. Car Pooling
链接：https://leetcode.com/problems/car-pooling/
Medium: 
You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips. 


Example 1:
Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false

Example 2:
Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true

Example 3:
Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true

Example 4:
Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true
 
 
Constraints:
trips.length <= 1000
trips[i].length == 3
1 <= trips[i][0] <= 100
0 <= trips[i][1] < trips[i][2] <= 1000
1 <= capacity <= 100000
*/

/*解题思路 扫描线做法，meeting room II做法
题目告诉我们有这样一辆车，车子只能往东边一个方向开(东面，其实就是
坐标轴往右边开)。这辆车子有一个限载人数，一路上会有不同数量的客人，
用一个数组表示。比如说[3,2,4],代表的就是在位置2上，会有三个客人上车。
他们会坐车到位置4上。那么题目问，给出所有乘客的上下车为止，以及游客
数量。这辆车是否在不超载的情况下可以完成所有的行程。

其实这就是和meeting room一样的做法。我第一个是用了扫描线做法。很简单

*/

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        List<int[]> list = new ArrayList<>();
        
        for(int[] t: trips){
        	//上车位置是游客数量乘以-1,说明被占用了t[0]个位置
            int[] start = {t[1], -1*t[0]};
            int[] end = {t[2], t[0]};
            list.add(start);
            list.add(end);
        }
        
        Collections.sort(list, new Comparator<>(){
            public int compare(int[] a, int[] b){
                if(a[0] != b[0]){
                    return a[0]-b[0];
                }else{
                	/*这个比较的语句的意思是，同一站的乘客，先下后上。
                	否则先上可能导致座位不够*/
                    return b[1]-a[1];
                }
            }
        });
        
        for(int i=0; i< list.size(); i++){    
            int[] t = list.get(i);
            capacity+= t[1];
            if(capacity < 0) return false;
        }
        
        return true;
    }
}



/*
或者用treemap来做，直接给输入的节点排序，就不用自定义compare了
*/
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> m = new TreeMap<>();
        for (int[] t : trips) {
            m.put(t[1], m.getOrDefault(t[1], 0) + t[0]);
            m.put(t[2], m.getOrDefault(t[2], 0) - t[0]);
        }
        for (int v : m.values()) {
            capacity -= v;
            if (capacity < 0) {
                return false;
            }
        }
        return true;
    }
}

/*
因为题目说了trips.length <= 1000， 所以可以用一种比较
取巧的做法，使时间达到名义上的O(N).就是建立一个长度为N的
array
*/
public boolean carPooling(int[][] trips, int capacity) {    
  int stops[] = new int[1001]; 
  for (int t[] : trips) {
      stops[t[1]] += t[0];
      stops[t[2]] -= t[0];
  }
  for (int i = 0; capacity >= 0 && i < 1001; ++i) capacity -= stops[i];
  return capacity >= 0;
}