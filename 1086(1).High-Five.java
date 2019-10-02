/*1086. High Five
Easy
链接: https://leetcode.com/problems/high-five/

Given a list of scores of different students, return the average score of 
each student's top five scores in the order of each student's id.

Each entry items[i] has items[i][0] the student's id, and items[i][1] the 
student's score.  The average score is calculated using integer division.

 

Example 1:

Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]
Explanation: 
The average of the student with id = 1 is 87.
The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.
 

Note:

1 <= items.length <= 1000
items[i].length == 2
The IDs of the students is between 1 to 1000
The score of the students is between 1 to 100
For each student, there are at least 5 scores

*/

/*解题思路


*/

class Solution {
    public int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<int[]>> map = new HashMap<>();
        int id = Integer.MAX_VALUE;
        for(int[] item: items){
            if(!map.containsKey(item[0])){
                map.put(item[0], new PriorityQueue(new Comparator<int[]>(){
                    public int compare(int[] a, int[] b){
                        return b[1] - a[1];
                    }
                }));
            }
            if(id > item[0]) id = item[0];
            map.get(item[0]).add(item);
           // if(map.get(item[0]).size() > 5) map.get(item[0]).poll();
        }
        //我这个做法考虑到了id不连续的情况
        int[][] res = new int[map.size()][];
        int total = map.size(), pt = 0;
        while(total>0){
            if(map.containsKey(id)){
                int average = 0, num = 0;
                //虽然题目告诉我们每个学生最少有五个成绩，但是我的写法还是考虑到了不到5个成绩的做法
                while(!map.get(id).isEmpty() && num <5){
                    average+=map.get(id).poll()[1];
                    num++;
                }
                res[pt++] = new int[]{id, average/num};
                total--;
            }
            id++;
        }
        return res;
    }
    
}