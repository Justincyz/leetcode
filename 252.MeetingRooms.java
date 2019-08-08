/*252. Meeting Rooms
Easy

Given an array of meeting time intervals consisting of start 
and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a 
person could attend all meetings.

Example 1:
Input: [[0,30],[5,10],[15,20]]
Output: false

Example 2:
Input: [[7,10],[2,4]]
Output: true

*/

/*解题思路
先按照起始时间来排序，如果任意一个会议的起始时间早于上一场会议的结束时间，那么
肯定没办法参加完所有的会议。

*/

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0]-b[0];
            }
        });
        
        for(int i=1; i< intervals.length;i++){
            if(intervals[i][0] < intervals[i-1][1]) return false;
        }
        
        return true;
    }
}