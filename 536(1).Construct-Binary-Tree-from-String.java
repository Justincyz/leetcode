/*536. Construct Binary Tree from String
链接：https://leetcode.com/problems/construct-binary-tree-from-string/
Medium: 

ou need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
Note:
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".
*/

/*解题思路
详见注释，第一个答案是比较快的O(n)解法4ms，第二个是我写的13ms,第一个
解法用的是global variable, 也不用做子字符串切割，所以速度相对快很多。

*/
public class Solution {
    int i = 0;
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) return null; 
        return helper(s.toCharArray());
    }
    
    private TreeNode helper(char[] s){
        // done
        if (i == s.length) return null; 
        
        // extract number
        StringBuilder num = new StringBuilder();
        while (i < s.length && s[i] != '(' && s[i] != ')') { 
            num.append(s[i]); 
            i++; 
        }
        
        // create new node
        TreeNode n = null;
        if (num.length() != 0){
            n = new TreeNode(Integer.parseInt(num.toString()));
        }
        // check parenthesis type
        if (i < s.length && s[i] == '('){
            // create left child node
            i++;
            n.left = helper(s);
            i++;
            
            if (i < s.length && s[i] == '('){
                // create right child node
                i++;
                n.right = helper(s);
                i++;
            }
        }
        // if meets ')', return to parent node
        return n;
    }
}

//这个是我第一次写的，虽然也是O(n)的解法，但是因为要获取substring啥的，所以总体会比较慢, 13ms
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
    public TreeNode str2tree(String s) {
        if(s.length() == 0) return null;
        int idx=0;
        
        //获取数字，因为有可能是负数
        while(idx < s.length() && s.charAt(idx) !='(') idx++;

        //创造节点
        TreeNode root = new TreeNode(Integer.valueOf(s.substring(0, idx)));

        //如果是最后一个,直接返回节点
        if(idx == s.length()) return root;
        
        //获取左子节点整棵树，所以要左右括号数量匹配
        int par = 1, tail=idx;
        
        //idx此时指向左括号，我们从左括号的下一位开始，因为子串最外边不带括号
        for(int i=idx+1; i< s.length(); i++){
            char c = s.charAt(i);
            if(c == '(') par++;
            else if(c == ')') par--;
            //左右括号数量匹配了
            if(par == 0){
                tail = i;
                break;
            }
        }
        
        //简历左子树
        root.left = str2tree(s.substring(idx+1, tail));

        //如果此时tail的右边还有的话，获取右子树的substring然后建立右子树
        if(tail <s.length()-1)
            root.right = str2tree(s.substring(tail+2, s.length()-1));
        
        return root;
    }
}