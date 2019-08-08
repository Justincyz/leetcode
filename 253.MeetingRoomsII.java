/*253. Meeting Rooms II
Medium
Given an array of meeting time intervals consisting of start
 and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum
 number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
*/

/*解题思路，总共有三种方法
这个做法很巧妙，把starting time 和ending time 拆成两个不同的部分，
分别进行sort。然后以starting time的array 作为标准，如果在ending pointer
之前有starting time, 则增加一个新的房间，否则同时向后移动.这个理解起来
可以用划线法来理解，首先在每个ending time之前一定会有起码一个的starting time,
 如果在这个时间段之内有新的starting time, 那么我们就需要一个新的房间。
一旦确定了starting time, 我们就只需要确定离他最近的ending time就可以了。
这道题除了用两个array组合起来，还可以用PriorityQueue 和 TreeMap来坐。详见代码
*/
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for(int i=0; i<n; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        
        Arrays.sort(start);
        Arrays.sort(end);
        int i=0, j=0, total=0;
        while(i<n){
            if(start[i]<end[j]){
                total++;
            }else{
                j++;  
            } 
            i++;
        }
        return total;        
    }
}

/*原来题目的接口稍微有点不一样，但是不碍事
用pq 的做法是这样的。首先还是对起始时间进行排序。
当我们遍历起始时间的时候，已经可以确定当前遍历的点是起始时间最早的点。假设为A。
那么在pq当中我们也获取一个屋子最早的结束时间，B，两下比较一下。如果B的结束时间在
A的起始时间前面，那么我们就不需要一个新的屋子，同时我们更新一下B的结束时间使之等于
A的结束时间。再放回pq内。
*/
public int minMeetingRooms(Interval[] intervals) {
    if (intervals == null || intervals.length == 0)
        return 0;
        
    // Sort the intervals by start time
    Arrays.sort(intervals, new Comparator<Interval>() {
        public int compare(Interval a, Interval b) { return a.start - b.start; }
    });
    
    // Use a min heap to track the minimum end time of merged intervals
    PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
        public int compare(Interval a, Interval b) { return a.end - b.end; }
    });
    
    // start with the first meeting, put it to a meeting room
    heap.offer(intervals[0]);
    
    for (int i = 1; i < intervals.length; i++) {
        //获得最早结束的那个房间
        Interval interval = heap.poll();
        
        if (intervals[i].start >= interval.end) {
            // if the current meeting starts right after 
            // there's no need for a new room, merge the interval
            interval.end = intervals[i].end;
        } else {
            // otherwise, this meeting needs a new room
            heap.offer(intervals[i]);
        }
        //对当前房间进行操作后要放回去
        heap.offer(interval);
    }
    
    return heap.size();
}

/*
这是用treeMap来做的，treemap是以红黑树作为结构实现的。所以整棵树是有序的。
我们遍历时间区间，对于起始时间，映射值自增1，对于结束时间，映射值自减1，然
后我们定义结果变量 res，和房间数 rooms，我们遍历 TreeMap，时间从小到大，
房间数每次加上映射值，然后更新结果 res，遇到起始时间，映射是正数，则房间数
会增加，如果一个时间是一个会议的结束时间，也是另一个会议的开始时间，则映射
值先减后加仍为0，并不用分配新的房间，而结束时间的映射值为负数更不会增加房间数，
记住，最后结果是遍历的过程中累加的最大数目

*/
public int minMeetingRooms(Interval[] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (Interval itl : intervals) {
            map.put(itl.start, map.getOrDefault(itl.start, 0) + 1);
            map.put(itl.end, map.getOrDefault(itl.end, 0) - 1);
        }
        int room = 0, k = 0; 
        for (int v : map.values()) 
            k = Math.max(k, room += v); 
        
        return k; 
}