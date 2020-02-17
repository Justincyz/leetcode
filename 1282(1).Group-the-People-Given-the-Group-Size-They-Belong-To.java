/*1282. Group the People Given the Group Size They Belong To
链接：https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
Medium: 
There are n people whose IDs go from 0 to n - 1 and each person belongs exactly to one group. Given the array groupSizes of length n telling the group size each person belongs to, return the groups there are and the people's IDs each group includes.

You can return any solution in any order and the same applies for IDs. Also, it is guaranteed that there exists at least one solution. 

Example 1:
Input: groupSizes = [3,3,3,3,3,1,3]
Output: [[5],[0,1,2],[3,4,6]]
Explanation: 
Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].

Example 2:
Input: groupSizes = [2,1,3,3,3,2]
Output: [[1],[0,5],[2,3,4]]
 
Constraints:
groupSizes.length == n
1 <= n <= 500
1 <= groupSizes[i] <= n
*/

/*解题思路
题目意思是，有这么一个array，第i位代表标号为i的人，第i位的值代表这个人所属群体
的人数有多少。但是并没有规定这个人要在那个群里。比如说例子1中，0,1,2,3,4,6号人
所属群体人数为3，但是可以自由组合没有限制说谁一定要跟谁在一起。让我们输出任意
一个List的组合代表了每个人所属的群体。首先当然要用map, key为团体的人数，value
为所有需要在团体个数为key的人。因为题目保证有解，所以不需要判断是否有结果。
下面是最简单的一种解法。


*/
class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0; i< groupSizes.length; i++){
            if(!map.containsKey(groupSizes[i])) map.put(groupSizes[i], new ArrayList());
            map.get(groupSizes[i]).add(i);
        }
        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
            int size = entry.getKey();
            for(int i=0; i<entry.getValue().size(); i+=size){
                List<Integer> t = new ArrayList<>();
                add(t, entry.getValue(), i, size);
                res.add(t);
            }
        }
        return res;
    }
    
    public void add(List<Integer> t, List<Integer> list, int i, int size){
        while(size -- >0){
            t.add(list.get(i+size));
        }
    }
}

//这种是在读取数组时就处理同一组人数的解法。快1ms
class Solution {
     public List<List<Integer>> groupThePeople(int[] arr) {
        int n = arr.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            int curr = arr[i];
            List<Integer> temp = new ArrayList<>();//要new一个list,因为有可能这个list就要加入结果中
            if(map.containsKey(curr)) temp = map.get(curr);
            temp.add(i);
            map.put(curr, temp);
            if(temp.size() == curr){
                ans.add(temp);
                map.remove(curr);
            }
        }
        
        return ans;
        
    }
}