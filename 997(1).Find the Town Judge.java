/*997. Find the Town Judge
Easy
链接: https://leetcode.com/problems/find-the-town-judge/
In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.

If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.


Example 1:
Input: N = 2, trust = [[1,2]]
Output: 2

Example 2:
Input: N = 3, trust = [[1,3],[2,3]]
Output: 3

Example 3:
Input: N = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1

Example 4:
Input: N = 3, trust = [[1,2],[2,3]]
Output: -1

Example 5:
Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
Output: 3
 

Note:
1 <= N <= 1000
trust.length <= 10000
trust[i] are all different
trust[i][0] != trust[i][1]
1 <= trust[i][0], trust[i][1] <= N
*/



/*解题思路
题目告诉我们在一个town上有一个法官，法官对于其余人都不相信，但是所有人都相信法官。并且告诉我们满足这个条件的法官只有一个人。问，这个人是谁。
最简答的办法就是创建两个数组，因为不会出现重复的情况，比如说同一个人信任同一个法官两次。所以第一个数组我们用来给所有被受信任的人进行累加。这样谁的被信任值到达了N-1, 谁就有可能成为法官。第二个数组是为了满足法官不信任任何人这一个条件的。如果某人信任了别人，就将对应的位置变成true。最后法官的位置肯定是false因为他不信任任何人。
我们最后遍历一次两个数组，只要谁满足了这两个条件，谁就是法官。

*/

class Solution {
    public int findJudge(int N, int[][] trust) {
        if(trust.length == 0) return 1;
        
        int[] trustByOthers = new int[N+1];
        boolean[] trustOthers = new boolean[N+1];
        for(int[] t:trust){
            trustByOthers[t[1]]++;
            trustOthers[t[0]] = true;
        }
        
        for(int i=1; i<=N; i++){
            if(trustByOthers[i]==(N-1) && !trustOthers[i]) return i;
        }
        
        return -1;
    }
}