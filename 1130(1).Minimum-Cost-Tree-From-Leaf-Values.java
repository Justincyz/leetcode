/*1130. Minimum Cost Tree From Leaf Values
链接：https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
Medium: 

Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.

 
Example 1:
Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4
*/

/*解题思路
先贴一个大神的讲解: https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/339959/One-Pass-O(N)-Time-and-Space

这道题目问我们，给的一个整数数组，数组中的每一个元素都是顺序组成的二叉树的叶节点。
所有的中间节点值为左子树中最大的值乘以右子树中最大的值。问，所有可能中，最小的中间节点
和为多少。

这道题目大神给出了O(n)的解法，真是很厉害。首先我们观察一下全局数组，作为所有节点的根节点，一定是某一个值
和数组中最大数相乘得到的。因为不管怎么划分，最大的那个数一定会被划分到左边或者右边去。
而且在选择的时候，我们也一定要选择那个最大的值。相当于我们一定要保留这个最大的值。每一次
当我们选择a*b的时候，(a, b为相邻的两个值)。实际上我们都只需要保留较大的那个值。而较小的那个值在
当前组合结束后就不需要了(因为我们是用bottom up的方式构建树的)。
我们观察这个stack的结构，每一次都将比当前值小的数pop()出来，且当前被Pop()出来的值一定
小于当前值和stack.peek()的值。那我们就在这两个值之间取一个较小的值，和被pop()出来的值
相乘。因为我们在接下来的计算中不在需要这个较小的值，所以也没有关系。

我们看一个比较极端的情况，假设数组中的数都是递减的。那么构成树的最小和就是两两相乘，也
符合我们的算法。并且我们每一次相乘只是去掉了那个较小的数，较大的那个数还是被保存下来了。
*/


class Solution {
    public int mctFromLeafValues(int[] arr) {
        Stack<Integer> stack = new Stack();
        int res =0;
        stack.push(Integer.MAX_VALUE);
        for(int a: arr){
            while(a > stack.peek()){
                int top = stack.pop();
                res += Math.min(a, stack.peek())*top;
            }
            stack.push(a);
        }
        
        while(stack.size() >2){
            int top = stack.pop();
            res += stack.peek()*top;
        }
        return res;
    }
}


class Solution {
    public String lastSubstring(String s) {
        
        int n = s.length(), maxL=0, cand =0, len=0;
        char c = 'a';
        for(int i=0; i<n;i++){
            char cur = s.charAt(i);
            if(cur > c){
                maxL = i;
                c = cur;
                len =0;
                cand = 0; //just updated the maximum length, so there is no candidate to compete with it
            }else if(cand != -1){
                if(s.charAt(maxL+len) == cur){
                    len++;
                }else if(s.charAt(maxL+len) < cur){
                    maxL = cand;
                    c = s.charAt(cand);
                    cand = -1;
                }else{
                    cand = -1;
                }    
                
            }else if(cur == c){
                len = 1;
                cand = i;
            }
        }
        
        return s.substring(maxL);
    }
}