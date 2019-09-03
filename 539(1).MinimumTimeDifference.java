/*539. Minimum Time Difference
Midium: 
Given a list of 24-hour clock time points in "Hour:Minutes" format, 
find the minimum minutes difference between any two time points in the list.
Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.
*/

/*解题思路
这道题目问最短的两个世界刻度的差值是多少。我们首先对链表进行排序，让时间小的在前面，时间大的
在后面。然后就是两个两个的想比较。第一次写的时候担心两个数如果小于一个一个小时的计算和大于一个小时
的计算会不会有什么不一样? 其实不会，比如说 13:46 和14:17，在小时方面两个值相差了60分钟，在分钟方面
两个值相差了 (17-46) = -29分钟，所以最后结果还是60+(-29) = 31分钟。
最后对于判断第一个和最后一个，只需要给第一个加上24小时就可以了。

*/

class Solution {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        
        int res = Integer.MAX_VALUE;
        for(int i=1; i< timePoints.size(); i++) {
            res = Math.min(res, compare(timePoints.get(i-1), timePoints.get(i), false));
        }
        res = Math.min(res, compare(timePoints.get(timePoints.size()-1), timePoints.get(0), true));
        
        return res;
    }
    
    public int compare(String a, String b, boolean flag){
        String[] s1 = a.split(":");
        String[] s2 = b.split(":");
        if(!flag)
            return (Integer.valueOf(s2[0]) - Integer.valueOf(s1[0]))*60 + (Integer.valueOf(s2[1])-Integer.valueOf(s1[1]));
        else 
            return (Integer.valueOf(s2[0])+24 - Integer.valueOf(s1[0]))*60 + (Integer.valueOf(s2[1])-Integer.valueOf(s1[1]));
    }
}


/*骚做法，因为最多只有24*60个不同的时间，所以直接就创建一个24*60的boolean array[].
每一次记录前一位的值是什么，然后直接想减，同时还要储存数组第一次出现数值的位置和最后一次出现数值的
位置用来最后对比一次头尾的大小。
*/
public class Solution {
    public int findMinDifference(List<String> timePoints) {
        boolean[] mark = new boolean[24 * 60];
        for (String time : timePoints) {
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if (mark[h * 60 + m]) return 0;
            mark[h * 60 + m] = true;
        }
        
        int prev = 0, min = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        for (int i = 0; i < 24 * 60; i++) {
            if (mark[i]) {
                if (first != Integer.MAX_VALUE) {
                    min = Math.min(min, i - prev);
                }
                first = Math.min(first, i);
                last = Math.max(last, i);
                prev = i;
            }
        }
        
        min = Math.min(min, (24 * 60 - last + first));
        
        return min;
    }
}