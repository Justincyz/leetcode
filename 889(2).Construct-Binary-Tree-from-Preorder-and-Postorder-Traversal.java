/*889. Construct Binary Tree from Preorder and Postorder Traversal
Easy: 
Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

Example 1:
Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
*/

/*解题思路
这道题目相对于其他两道姐妹题目 105和106来说简单一些。
让我们同归preorder 和 postorder来构建一棵树。注意这里没有重复元素。
我们还是先用map把Post order每个数字和位置记录下来。
因为pre order和post order的根节点分别在区间段的头部和尾部，所以每一次
我们在preorder中找到一个区间段的头部之后，可以直接在postorder中找到
这个区间段的个数，因为root的值是出现在这个区间段的最后一个的。所以这个
子树元素的个数就是 map.get(pre[preStart+1])-postStart+1
pre[preStart+1]是子树的root节点的值，因为Pre order的遍历顺序是根-左-右
所以每一个跟的下一个节点可以当作是左子树的root节点。
map.get(pre[preStart+1])我们可以获得这个在post=order中这个子树的根节点的
位置，因为这个节点会是左子树中最后出现的，所以这个元素减去posStart再加一
就是子树的节点数目

*/

class Solution {
    Map<Integer, Integer> map;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        map = new HashMap<>();
        for(int i=0; i< post.length; i++){
            map.put(post[i], i);
        }
        
        return helper(pre, 0, pre.length-1, post, 0, post.length-1);
    }
    
    public TreeNode helper(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd){
        if(preStart > preEnd || postStart > postEnd) return null;
        
        TreeNode root = new TreeNode(pre[preStart]);

        if(preStart+1 > preEnd ) return root;
        int numLeft =  map.get(pre[preStart+1])-postStart+1;
        root.left = helper(pre, preStart+1, preStart+numLeft, post, postStart, postStart+numLeft-1);
        root.right = helper(pre, preStart+numLeft+1, preEnd, post, postStart+numLeft, postEnd-1);
        return root;
    }
}