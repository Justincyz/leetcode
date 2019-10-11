/*391. Number of Airplanes in the Sky

链接：
Medium: 
给出飞机的起飞和降落时间的列表，用序列 interval 表示. 请计算出天上同时最多有多少架飞机？
如果多架飞机降落和起飞在同一时刻，我们认为降落有优先权。

Example
样例 1:

输入: [(1, 10), (2, 3), (5, 8), (4, 7)]
输出: 3
解释: 
第一架飞机在1时刻起飞, 10时刻降落.
第二架飞机在2时刻起飞, 3时刻降落.
第三架飞机在5时刻起飞, 8时刻降落.
第四架飞机在4时刻起飞, 7时刻降落.
在5时刻到6时刻之间, 天空中有三架飞机.
样例 2:

输入: [(1, 2), (2, 3), (3, 4)]
输出: 1
解释: 降落优先于起飞.
*/

/*解题思路


*/

class Solution {
    public int countOfAirplanes(List<Interval> airplanes) { 
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        //this round of sort is to make sure landing takes place before flying, if
        //they happen at the same time
        Collections.sort(airplanes, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start- i2.start;
            }
        });
        Point[] points = new Point[airplanes.size()*2];
        int[] points = new int[airplanes.size()*2];
        for (int i = 0; i < airplanes.size(); i++) {
            points[i*2] = new Point(airplanes.get(i).start, true);
            points[i*2 + 1] = new Point(airplanes.get(i).end, false);
            
        }
        Arrays.sort(points, new Comparator<Point>(){
            public int compare(Point i1, Point i2) {
                return Integer.compare(i1.time, i2.time);
            }
        });
        int res = 0;
        int cur = 0;
        for (Point p : points) {
            if (!p.isStart) {
                cur--;
            } else {
                cur++;
                res = Math.max(res, cur);
            }
        }
        return res;
    }
     
    class Point {
        int time;
        boolean isStart;
        public Point(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }
}


//空间换时间的做法
class Solution {
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) { 
        // write your code here
        int max = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        for (Interval interval: airplanes) {
            for (int i = interval.start; i < interval.end; ++i) {
                if (hashMap.containsKey(i)) {
                    hashMap.put(i, hashMap.get(i) + 1);
                } else {
                    hashMap.put(i, 1);
                }
                max = Math.max(hashMap.get(i), max);
            }
        }
        return max;
    }
}