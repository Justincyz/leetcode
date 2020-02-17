/*1319. Number of Operations to Make Network Connected
链接：https://leetcode.com/problems/number-of-operations-to-make-network-connected/
Medium: 
There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.

Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to make all the computers connected. If it's not possible, return -1. 

(彩图例子详见题目链接)
Example 1:
0-- 1          0---1
|  /    --->   |   |
| /            |   |
2   3          2   3
Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.


Example 3:
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.

Example 4:
Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
Output: 0

*/

/*解题思路
这道题目告诉我们有n台计算机，互相之间被不同的网线链接(并一定所有的
计算机都在同一个网络内)。题目让我们找到是否可以利用已有的，且多余的
网线，将不同网络群中的计算机都链接在同一片网络内。定义多余的网线如例子1
中，因为[0,1,2]三台计算机互相链接，所以其实不需要三条线，只需要两条
线就可以把这三台主机链接在要一起，多余的一条可以链接这个集群和三号计算机。这样我们就可以将所有计算机都链接在一起，且使用的网线数量最少。
这道题其实就是一道典型的union find的题型。我们先把属于一个集群的计算机
连接在一起，如果某两个计算机已经在同一个集群当中的话，那么说明可以
省下来一条网线。
se
接下来要做的就是可以使用一个set,找到不同的公共祖先有多少个，
两两之间需要一条线，所以我们最后需要公共祖先数量-1个数的线把
所有的集群链接在一起。如果这个数目超过了我们拥有的线缆的个数，则
返回-1
*/
class Solution {
    public int makeConnected(int n, int[][] connections) {
        int[] graph = new int[n];
        for(int i=0; i< n; i++) graph[i] = i;

        int count = 0;
        for(int[] connect: connections){
            int a1 = search(connect[0], graph);
            int a2 = search(connect[1], graph);
            if(a1 == a2) count++;
            else graph[a2] = a1;
        }
       
        Set<Integer> set = new HashSet<>();
        for(int i=0; i< n; i++){
            int c = graph[i];
            int root = search(c, graph);
            set.add(root);
        }
        
        return set.size()-1 > count ? -1: set.size()-1;
    }
    
    public int search(int c, int[] graph){
        while(graph[c] != c){
            graph[c] = graph[graph[c]];
            c = graph[c];
        }
        return c;
    }
}

/*
当然，计算公共祖先个数其实只需要查看有多少graph[i]= i就可以了，
说明这个节点是祖先节点，不需要再用search去查找了。
*/

class Solution {
    public int makeConnected(int n, int[][] connections) {
        int[] graph = new int[n];
        for(int i=0; i< n; i++) graph[i] = i;

        int count = 0;
        for(int[] connect: connections){
            int a1 = search(connect[0], graph);
            int a2 = search(connect[1], graph);
            if(a1 == a2) count++;
            else graph[a2] = a1;
        }
       

        int components =0 ;
        for(int i = 0; i < n; i++) if(graph[i] == i) components++;
        
        return components-1 > count ? -1: components-1;
    }
    
    public int search(int c, int[] graph){
        while(graph[c] != c){
            graph[c] = graph[graph[c]];
            c = graph[c];
        }
        return c;
    }
}