/*277. Find the Celebrity
链接：https://leetcode.com/problems/find-the-celebrity/
Medium: 

Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

Input: graph = [
  [1,1,0],
  [0,1,0],
  [1,1,1]
]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.


Input: graph = [
  [1,0,1],
  [1,1,0],
  [0,1,1]
]
Output: -1
Explanation: There is no celebrity.
*/

/*解题思路
这道题让我们在一群人中寻找名人，所谓名人就是每个人都认识他，他却不认识任何人，限定了只有1个或0个名人，给定了一个 API 函数，输入a和b，用来判断a是否认识b，让我们尽可能少的调用这个函数，来找出人群中的名人。
设定候选人 res 为0，原理是先遍历一遍，对于遍历到的人i，若候选人 res 认识i，则将候选人 res 设为i，在验证的时候，分为两段，先验证候选者前面的所有人，若候选者认识任何人，或者任何人不认识候选者，直接返回 -1。再验证候选者后面的人，这时候只需要验证是否有人不认识候选者就可以了，因为我们在最开始找候选者的时候就已经保证了候选者不会认识后面的任何人
*/


/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int pos =0;
        for(int i=1; i< n; i++){
            if(knows(pos, i)) pos =i;
        }
        
        for(int i=pos+1; i< n; i++){
            if(!(knows(i, pos))) return -1;
        }
        
        for(int i=pos-1; i>=0; i--){
            if(knows(pos, i)) return -1;
        }
        
        return pos;
    }
}