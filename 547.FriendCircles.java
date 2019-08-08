/*547. Friend Circles 
Tag: Union Find, DFS
Medium

There are N students in a class. Some of them are friends, while some are not. 
Their friendship is transitive in nature. For example, if A is a direct friend of 
B, and B is a direct friend of C, then A is an indirect friend of C. And we defined
 a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the 
class. If M[i][j] = 1, then the ith and jth students are direct friends with each 
other, otherwise not. And you have to output the total number of friend circles 
among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a 
friend circle. The 2nd student himself is in a friend circle. So return 2.

Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students 
are direct friends, so the 0th and 2nd students are indirect friends. All of 
them are in the same friend circle, so return 1.
*/

/*解题思路， 
这道题可以用DFS, Union Find等方法来做。第一个我先用了Union Find来做。
Union Find
(1)因为每个学生肯定和自己是朋友，所以对角线上的值一定是1.而且总共有m个学生的话，这个matrix就是
m*m 的矩阵
(2)这个matrix M是延对角线对称的。意思是说，如果(1,2)==1，那么(2,1) 也是等于1。因为1跟2是朋友。
所以我们遍历的时候只需要遍历延对角线一半的矩阵就可以了。
首先假设每个人都是独立的，(自己跟自己是朋友)。所以我们最多有m个friend circle。
每一次如果发现矩阵中有1出现，那么就说明(i, j)两个人是朋友。如果他们两个是属于不同的组别，
我们对他们要进行一个union find的操作。使他们成为一组。每一次找到两个不同组别的人，我们一开始m
个friend circle就要做一次减一的操作因为我们把两个circles 合并到一个了。最后遍历完看看有几个circle
没有办法被聚合。就输出

DFS
援引网上的一个解释:https://www.cnblogs.com/grandyang/p/6686983.html
*/

//已经可以beast 100%了
class Solution {
    public int findCircleNum(int[][] M) {
        int m = M.length;
        int n = M[0].length;
        int[] circle = new int[n];
        int count=m;
        for(int i=0; i< n;i++) circle[i]=i;
            
        for(int i=0; i< m; i++){
            for(int j=i; j< n; j++){
                if(M[i][j]==0) continue;
                if(i==j){
                    continue;
                } 
                int x = helper(circle, i);
                int y = helper(circle, j);
                if(x !=y){
                    circle[x] = y;
                    count--;
                }
            }
        }
        
        return count;
    }
    
    public int helper(int[] circle, int id){
        while(circle[id]!=id){
            circle[id] = circle[circle[id]];//路径压缩，加快速度
            id = circle[id];
        }
        return id;
    }
}


