/*1229. Meeting Scheduler
链接：这道题目是prime才有的，所以只是找了别人帮我跑了一下代码
Medium: 
Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.  

It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.

Example 1:
Input: slots1 = [[10,50],[60,120],[140,210]], 
slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]

Example 2:
Input: slots1 = [[10,50],[60,120],[140,210]], 
slots2 = [[0,15],[60,70]], duration = 12
Output: []

Constraints:

1 <= slots1.length, slots2.length <= 10^4
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 10^9
1 <= duration <= 10^6 
*/

/*解题思路
https://www.bilibili.com/video/av71845446/
扫描线的做法
*/


// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {

        int[][] slot1 = {{10, 50},{60, 120},{140, 210}};
        int[][] slot2 = {{0, 15},{60, 70}};
        int duration = 8;
        List<Integer> res = solution(slot1, slot2, duration);
        System.out.print(res.get(0)+" "+res.get(1));
    }
    
    public static List<Integer> solution(int[][] slot1, int[][] slot2, int duration){
        List<int[]> list = new ArrayList<>();
        /*
		1代表遇到了一个起始点，0代表遇到了终点。如果某一个时刻count = 2，说明
		两个区间有重合
        */
        for(int[] s: slot1){
        	//将起始时间和结束时间分表标记
            int[] start = {s[0], 1};
            int[] end = {s[1], 0};
            list.add(start);
            list.add(end);
        }
        for(int[] s: slot2){
            int[] start = {s[0], 1};
            int[] end = {s[1], 0};
            list.add(start);
            list.add(end);
        }
        //按照时间从小到大排序
        Collections.sort(list, (a, b) -> a[0]-b[0]);

        int start =0, count = 0;
        List<Integer> res = new ArrayList<>();
        for(int[] interval : list){
            if(interval[1] == 1){
                count++;
                //开始计算一个新的起点，原来的起点就不需要了，因为两个起点只能取较后面的
                start=interval[0];
            }else{
                if(count ==2){
                    if(interval[0]-start>=duration){
                        res.add(start); res.add(start+duration);
                        break;
                    }
                }
                count--;
            }
            
        }
        return res;
    }
}

//另外一种方法
class Solution{
	public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration){
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[0]));
		for(int[] s: slots1){
			if(s[1]-s[0] >= duration) pq.offer(s);
		}
		for(int[] s: slots2){
			if(s[1]-s[0] >= duration) pq.offer(s);
		}

		while(pq.size() > 1){
			if(pq.poll()[1] >= pq.peek()[0]+duration){
				return Arrays.asList(pq.peek()[0], pq.peek()[0]+duration);
			}
		}

		return Arrays.asList();
	}
}
