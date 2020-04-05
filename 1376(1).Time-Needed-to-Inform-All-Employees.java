/*1376. Time Needed to Inform All Employees
链接：https://leetcode.com/problems/time-needed-to-inform-all-employees/
Medium: 
A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company has is the one with headID.

Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also it's guaranteed that the subordination relationships have a tree structure.

The head of the company wants to inform all the employees of the company of an urgent piece of news. He will inform his direct subordinates and they will inform their subordinates and so on until all employees know about the urgent news.

The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e After informTime[i] minutes, all his direct subordinates can start spreading the news).

Return the number of minutes needed to inform all the employees about the urgent news.

 
Example 1:
Input: n = 1, headID = 0, manager = [-1], informTime = [0]
Output: 0
Explanation: The head of the company is the only employee in the company.

Example 2:
          2
       /  / \ \ \
       0 1  3  4 5

Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
Output: 1
Explanation: The head of the company with id = 2 is the direct manager of all the employees in the company and needs 1 minute to inform them all.
The tree structure of the employees in the company is shown.

Example 3:
  6 
   \
    5
     \
      4
       \
        3
         \
          2
           \
            1
             \
              0
Input: n = 7, headID = 6, manager = [1,2,3,4,5,6,-1], informTime = [0,6,5,4,3,2,1]
Output: 21
Explanation: The head has id = 6. He will inform employee with id = 5 in 1 minute.
The employee with id = 5 will inform the employee with id = 4 in 2 minutes.
The employee with id = 4 will inform the employee with id = 3 in 3 minutes.
The employee with id = 3 will inform the employee with id = 2 in 4 minutes.
The employee with id = 2 will inform the employee with id = 1 in 5 minutes.
The employee with id = 1 will inform the employee with id = 0 in 6 minutes.
Needed time = 1 + 2 + 3 + 4 + 5 + 6 = 21.


Example 4:
Input: n = 15, headID = 0, manager = [-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6], informTime = [1,1,1,1,1,1,1,0,0,0,0,0,0,0,0]
Output: 3
Explanation: The first minute the head will inform employees 1 and 2.
The second minute they will inform employees 3, 4, 5 and 6.
The third minute they will inform the rest of employees.


Example 5:
Input: n = 4, headID = 2, manager = [3,3,-1,2], informTime = [0,0,162,914]
Output: 1076

*/

/*解题思路 topic: 多叉树
和道题看起来很长很复杂，实际上是一道很直接的题目。题目当中给出了一个manager array和
一个informTime array.每一个员工有一个自己的manager, 同时这个员工也可能是别的员工
的manager。一个manager可以管理多个员工，一个员工只有一个自己的manager。题目问，如果
一个消息从headID开始向下属通知，最多过多长时间所有的员工都会知道。
这个题目实际上可以抽象成一棵多叉树，最顶端的根节点是最开始给出来的headID. 每一条边都有一个权重也
就是informTime代表的值。一个manager 节点到其所有的子节点的informTime都是一样的。
所以这道题就变成了，找到多叉树当中，从根节点出发到叶子节点的路径，权重和的最大值
是多少。

首先第一种bfs的办法来做，我先用了一个Map managerToEmployee 储存每个manager和其手下员工的映射关系。然后使用level order的思想，每一次将manager-employee
这条权重带入下一个层级运算，然后每一次都更新一个较大的值赋予globalMax，代表了从root到
这一个Leaf的权重和。最后返回globalMax的值。

Space & Time  = O(N)
*/


class Solution {
    int globalMax = 0;
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> managerToEmployee = new HashMap<>();

        for(int employeeID=0; employeeID<manager.length; employeeID++){
            int managerID = manager[employeeID];
            if(!managerToEmployee.containsKey(managerID)) managerToEmployee.put(managerID, new ArrayList());
            managerToEmployee.get(managerID).add(employeeID);
        }
        
        levelOrderTraverse(managerToEmployee, headID, manager, informTime, 0);
        return globalMax;
    }
    
    public void levelOrderTraverse(Map<Integer, List<Integer>> managerToEmployee, int headID, int[] manager, int[] informTime, int totalTime){
        if(managerToEmployee.get(headID) == null){
            globalMax = Math.max(globalMax, totalTime);
            return;
        }
        List<Integer> employeeList = managerToEmployee.get(headID);
        for(int employee: employeeList){
        	//第二个参数，当前的employee就是下一层的manager
            levelOrderTraverse(managerToEmployee, employee, manager, informTime, totalTime+informTime[headID]);
        }
    }
}
//这是网友写的BFS的办法，更加简洁一些
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] managerToEmployee = new List[n];
        for (int i = 0; i < n; i++) managerToEmployee[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) if (manager[i] != -1) managerToEmployee[manager[i]].add(i);
        Queue<int[]> q = new LinkedList<>(); // Since it's a tree, we don't need `visited` array
        q.offer(new int[]{headID, 0});
        int res = 0;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int managerID = top[0], weight = top[1];
            res = Math.max(weight, res);
            
            for (int employee : managerToEmployee[managerID]) q.offer(new int[]{employee, weight + informTime[managerID]});
        }
        return res;
    }
}

/*
还有一种dfs的办法, 是bottom-up的一个遍历，每一个manager向上返回一个从leaf node到他自己
最大的path sum。这个最大path sum就是所需最长的通知时间
*/

class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] managerToEmployee = new List[n];
        for (int i = 0; i < n; i++) managerToEmployee[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) if (manager[i] != -1) managerToEmployee[manager[i]].add(i);
        return dfs(managerToEmployee, headID, informTime);
    }
    private int dfs(List<Integer>[] managerToEmployee, int managerID, int[] informTime) {
        int max = 0;
        for (int employee : managerToEmployee[managerID])
            max = Math.max(max, dfs(managerToEmployee, employee, informTime));
        return max + informTime[managerID];
    }
}