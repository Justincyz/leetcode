/*742. Closest Leaf in a Binary Tree
Medium: 
Given a binary tree where every node has a unique value, and a 
target key k, find the value of the nearest leaf node to target 
k in the tree.

Here, nearest to a leaf means the least number of edges travelled 
on the binary tree to reach any leaf of the tree. Also, a node is 
called a leaf if it has no children.

In the following examples, the input tree is represented in 
flattened form row by row. The actual root tree given will be

Example 1:
Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)
Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.

Example 2:
Input:
root = [1], k = 1
Output: 1
Explanation: The nearest leaf node is the root node itself.

Example 3:
Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

Output: 3
Explanation: The leaf node with value 3 (and not the leaf node 
with value 6) is nearest to the node with value 2.
*/

/*解题思路
这道题目要求我们找到二叉树给定节点和所有叶节点中的最短距离。比如例子二中，距离节点2最短的
leaf node就是3了。题目中告诉我们，每一个节点的值都是唯一的，所以我们只要找到一个就可以。

第一种方法是bottom up的方法，首先新建一个Node节点，里面储存的是距离当前节点最近的叶节点
是哪一个，距离此叶节点的距离，是否有遇到target node, 同时距离target node有多远。从低往上
判断的条件比较复杂。假如自己本身是叶子节点，那么先把Node.leaf标为自己。如果自己不是
叶子节点的话，那么就比较左右子树中哪一个叶子节点距离自己最近。另外再判断，假如当前节点
是target node的话，还是先初始化。假设左右子树中存在target node的话，首先更新自己节点和
target node的距离，然后计算当前节点距离target node 的距离和最近的leaf node的距离总和，
如果比之前遇到的总和距离短，那么就更新一下全局变量 shortest, 同时更新全局变量 shortestNode。
最后返回的就是 shortestNode.val。

*/

// 1ms
class Solution {
    int shortest = Integer.MAX_VALUE;
    TreeNode shortestLeaf = null;
    public int findClosestLeaf(TreeNode root, int k) {
        helper(root, k);
        return shortestLeaf.val;
    }
    
    public Node helper(TreeNode root, int k){
        if(root == null) return new Node(null);

        Node l = helper(root.left, k);
        Node r = helper(root.right, k);
        
        Node cur = new Node(root);
        if(l.leaf == null && r.leaf == null){    
            cur.d1 = 0;
            cur.leaf = root;
        }else{
            cur.d1 = l.d1 >= r.d1 ? r.d1+1: l.d1+1;
            cur.leaf = l.d1 >= r.d1 ? r.leaf: l.leaf;
        }
        
        if(root.val ==k){
            shortestLeaf = cur.leaf;
            shortest = cur.d1;
            cur.find = true;
            cur.d2 =0;
        }
        else if(l.find || r.find){
            cur.d2 = l.find == true? l.d2+1 : r.d2+1;
            cur.find = true;
            if(cur.d2+cur.d1 < shortest){
                shortest = cur.d2+cur.d1;
                shortestLeaf = cur.leaf;
            }
        }

        return cur;
    }
}

class Node{
    TreeNode leaf;
    int d1 = Integer.MAX_VALUE; // distance to leaf
    int d2 = Integer.MAX_VALUE; //distance to target node
    boolean find = false;
    public Node(TreeNode leaf){
        this.leaf = leaf;
    }
}



/* 7ms
 第二种办法就是先找到target node, 同时遍历全部二叉树，找到子节点和父节点的映射关系，便于
 之后往根节点回溯。然后从target node开始，做BFS。这样遇到的第一个leaf node就是最短的
 leaf node。记住在这个同时要用hashset记住遍历过的节点。
 */
class Solution {
    
    private static TreeNode targetNode;
    private static int k;
    
    public int findClosestLeaf(TreeNode root, int k) {
        targetNode = null;
        this.k = k;
        
        /* Map child node to parent node. */
        Map<TreeNode, TreeNode> childToParent = new HashMap<>();
        buildParentMap(root, childToParent);
        
        /* BFS to get closest leaf. */
        Deque<TreeNode> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(targetNode);
        visited.add(targetNode.val);
        
        while (!queue.isEmpty()) {          
            TreeNode curr = queue.poll();
            
            /* Check if current polled treenode is a leaf. */
            if (curr.left == null && curr.right == null)
                return curr.val;
            
            /* Add current node's children to queue. */
            if (curr.left != null && !visited.contains(curr.left.val)) {
                visited.add(curr.left.val);
                queue.offer(curr.left);
            }
            if (curr.right != null && !visited.contains(curr.right.val)) {
                visited.add(curr.right.val);
                queue.offer(curr.right);
            }
            
            /* Add current node's parent to queue. */
            TreeNode parent = childToParent.get(curr);
            if (parent != null && !visited.contains(parent.val)) {
                visited.add(parent.val);
                queue.offer(parent);                
            }                
        }
        
        return -1;
    }
    
    private void buildParentMap(TreeNode root, Map<TreeNode, TreeNode> childToParent) {
        if (root == null)
            return;
        
        buildParentMap(root.left, childToParent);
        buildParentMap(root.right, childToParent);
        
        /* Check if current root is the target node. */
        if (root.val == k)
            targetNode = root;
        
        if (root.left != null)
            childToParent.put(root.left, root);
        if (root.right != null)
            childToParent.put(root.right, root);
    }
}
