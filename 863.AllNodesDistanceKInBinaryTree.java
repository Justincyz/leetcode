/* 863. All Nodes Distance K in Binary Tree
Medium
We are given a binary tree (with root node root), a target node, and an integer 
value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 
Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
Output: [7,4,1]
Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.
图例
      3
    /   \
   5     1
  / \   / \
6    2 0   8
    / \
   7   4 


*/

/*解释
这道题要找的是某个节点出发所有在距离K上的TreeNode. 有两种方法，基本思路都差不多。第一种是自己写的，
没有用到第二种的hashmap。beats 100%
1)这道题分成找两个部分的距离集合。第一个是找target TreeNode往下距离K的所有TreeNode。这一
部分很容易找。直接从target往下搜索就可以了。每到下一层就K-1。储存所有当K==0的时候的Node。
第二部分比较麻烦的是找taregt TreeNode往上距离K的值。如上面的例子讲到的。首先我们要找到从root到
target TreeNode 的Path。距离为K的TreeNode会存在在这条path的另外一棵子树which可以在K范围到达的
节点上。如果某一个节点是target 节点，那么则返回K-1。否则返回-1。那么某一个节点如果左右子树返回值不
为-1, 那么说明target就在以当前节点为root的子树中。每一次向上递归我们可以知道两个信息，第一是target
是从左边返回的还是右边返回的，如果是左边节点返回值 !=-1。那么说明是从左边返回的，那么我们要遍历
右边子树，用我们剩下的距离(K-(当前root到target的距离))。反之我们遍历左边子树。如果返回值为0, 那么我们要
存上root 节点。如果target不在以当前节点为root的子树中，则返回-1，表示不在这一边。

*/
class Solution {
    List<Integer> list = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) { 
        find(root, target, K);
        search(target, K);
        return list;
    }

    public int find(TreeNode root, TreeNode target, int K){
        if(root == null) return -1;
        if(root == target) return K-1; //当我们找到了这个节点，

        int left = find(root.left, target, K);
        int right = find(root.right, target, K);
        //System.out.println("Root value: "+root.val+" left "+left+ " right "+right);
        if(left==0 || right ==0){
            search(root, 0);
            return -1;
        }
        else if(left != -1){
            search(root.right, left-1);//target在左边我们要遍历右边的子树
            return left-1; //注意这里返回的不是K-1, 而是从下往上返回的 left-1
        }else if(right != -1){
            search(root.left, right-1);
            return right-1;
        }else 
            return -1;
    }

    public void search(TreeNode root, int level){
        if(root == null) return;
        if(level==0) list.add(root.val);
        search(root.left, level-1);
        search(root.right, level-1);
    }
}


//用hashmap储存，也差不多是一个意思
class Solution {
    Map<TreeNode, Integer> map = new HashMap<>();
        
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new LinkedList<>();
        find(root, target);
        dfs(root, target, K, map.get(root), res);
        return res;
    }
    
    // find target node first and store the distance in that path that we could use it later directly
    private int find(TreeNode root, TreeNode target) {
        if (root == null) return -1;
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        int left = find(root.left, target);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
        int right = find(root.right, target);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }
    
    private void dfs(TreeNode root, TreeNode target, int K, int length, List<Integer> res) {
        if (root == null) return;
        if (map.containsKey(root)) length = map.get(root);
        if (length == K) res.add(root.val);
        dfs(root.left, target, K, length + 1, res);
        dfs(root.right, target, K, length + 1, res);
    }
}