/*1028. Recover a Tree From Preorder Traversal
Hard
链接: https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/

We run a preorder depth first search on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)

If a node has only one child, that child is guaranteed to be the left child.

Given the output S of this traversal, recover the tree and return its root.

     1
    / \
   2   5
  / \  /\
 3  4 6  7

Input: "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]

     1
    /  \
   2    5
  /    /
 3    6
/    /
4   7

Input: "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]

     1
    /
   401
  /   \
 349  88
 /
90

Input: "1-401--349---90--88"
Output: [1,401,null,349,88,90]
*/

/*解题思路
题目给了我们一个字符串，然后告诉我们，每一个数字前的dash数量代表了这个数字
所在的层数，且这个树是一个pre-order的结构构造的。意思就是先root, 再左边，再右边。
其实这道题目不算是hard级别的。从root往leaf传递一个level, 如果dash的数目和level是
一致的，那么说明当前值应该属于这一层。否则的话直接返回null, 因为肯定是遇到了比当前
层数少的dash，说明此时应该构建右子树了。我们设置一个global的index值，因为我们始终
是在往后遍历这个String, 为了不影响index的取值，每一次我们再新设置一个numdash和
index结合先检查dash的数量是否和层数相等，如果相等的话再将index+numDash的值赋给
index, 就算遍历完毕了。


*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //这个global index最重要
    int index =0; 
    public TreeNode recoverFromPreorder(String S) {
        if(S.length() == 0) return null;
        return helper(S, 0);
    }
    
    public TreeNode helper(String S, int level){
   
        int numDash =0;
        while(index+numDash<S.length() && S.charAt(index+numDash) =='-') numDash++;
        if(numDash != level) return null;
        
        int numVal = index+numDash;
        while(numVal<S.length() && S.charAt(numVal) !='-') numVal++;
        
        int val = Integer.valueOf(S.substring(index+numDash, numVal));
        index = numVal;
        TreeNode root = new TreeNode(val);
        //这里是先序遍历，所以先左边再右边
        root.left = helper(S, level+1);
        root.right = helper(S, level+1);

        return root;
    }
}


/*利用iterative来解决问题， 借助了stack*/
class Solution {
    public TreeNode recoverFromPreorder(String S) {
        if(S.length() == 0) return null;
        Stack<TreeNode> s = new Stack<>();
        int index = 0;
        while(index < S.length() && S.charAt(index) !='-') index++;
        TreeNode root = new TreeNode(Integer.valueOf(S.substring(0, index)));
        s.push(root);
        
        while(index < S.length()){
            int numDash = 0;
            while(numDash+index < S.length() && S.charAt(numDash+index) =='-') numDash++;
            int numVal = numDash+index;
            while(numVal < S.length() && S.charAt(numVal) !='-') numVal++;
            TreeNode temp = new TreeNode(Integer.valueOf(S.substring(numDash+index, numVal)));
            index = numVal;
            //将一边的子树先全部pop()直到根节点，然后再遍历另外一边的子树
            while(!s.isEmpty() && numDash < s.size()) s.pop();

            //子节点赋值
            if(s.peek().left == null) s.peek().left = temp;
            else s.peek().right = temp;
            s.push(temp);
        }
        return root;
    }
}