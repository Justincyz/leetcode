/*729. My Calendar I
链接：https://leetcode.com/problems/my-calendar-i/
Medium: 

Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)

Example 1:

MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
Explanation: 
The first event can be booked.  The second can't because time 15 is already booked by another event.
The third event can be booked, as the first event takes every time less than 20, but not including 20.
*/

/*解题思路
这道题让我们设计一个日历类，里面有一个book函数，需要给定一个起始时间和结束时间，与Google Calendar不同的是，我们的事件事件上不能重叠，实际上这道题的本质就是检查区间是否重叠。
我用了一个list，每一个元素保存了这一个时间的起始时间和结束时间。使用list因为这样可以比较容易插入新的值。首先我做的就是添加了一个最大值和最小值，
这样即使是第一个插入的区间我们也可以做二分操作了，否则的话会多一些corner case.
首先我们还是按照标准的二分来操作。首先获取中间那一个的区间值，假设是m = list.get(mid)。因为是开区间，所以只要当前
被插入的区间结束时间小于等于m的起始时间，那么我们就可以取左边的一半list，否则就要取右半边的list。如果mid -1 这个位置的区间结束时间正好在新插入区间的开始时间之前，那我们就可以直接插入。否则的话
我们还要在左半边继续找。最后如果啥都没找到，就返回-1，否则在二分时就判断可以插入并且返回。

*/

//自己写的， beats 91%,  估计这题没办法做到O(1)时间，O(nlogn)是最快的

class MyCalendar {
    List<int[]> list;
    public MyCalendar() {
        list = new ArrayList<>();
        //先添加两个区间便于使用二分搜索
        list.add(new int[]{0,0});
        list.add(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE});
    }
    
    public boolean book(int start, int end) {
        
        int s = 0, e = list.size();
        while(s <e){
            int mid = s + (e-s)/2;
            int[] m = list.get(mid);
            if(end <= m[0]){
            	//如果mid-1 和 mid之间正好可以容纳这个新的区间，直接添加
                if(list.get(mid-1)[1]<= start){
                    list.add(mid, new int[]{start, end});
                    return true;
                }
                //如果无法容纳的话，继续二分
                else{
                    e = mid;
                }
            }else{
                s = mid+1;
            }
        }

        return false;
    }
}

/*
这个更加简单，直接用了treemap的性质，但是会比上面直接用二分稍微慢一些。
这里记录一下treemap的floorEntry(), lowerEntry() 的区别
lowerEntry(K key)
Returns a key-value mapping associated with the greatest key strictly less than the given key, or null if there is no such key.

floorEntry(K key)
Returns a key-value mapping associated with the greatest key less than or equal to the given key, or null if there is no such key.
*/

class MyCalendar {
    TreeMap tm;

    public MyCalendar() {
        tm = new TreeMap<Integer, Integer>();
    }
    
    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> entry = tm.lowerEntry(end);
        if (entry != null && entry.getValue() > start) return false;
        tm.put(start, end);
        return true;
    }
}

