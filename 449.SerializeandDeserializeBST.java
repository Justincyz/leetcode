/*449. Serialize and Deserialize BST
Medium: 
Serialization is the process of converting a data structure or 
object into a sequence of bits so that it can be stored in a 
file or memory buffer, or transmitted across a network connection 
link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search 
tree. There is no restriction on how your serialization/deserialization 
algorithm should work. You just need to ensure that a binary search
 tree can be serialized to a string and this string can be deserialized 
 to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. 
Your serialize and deserialize algorithms should be stateless.

*/

/*解题思路:  类似题目, 297: Serialize and Deserialize Binary Tree
这道题目要我们序列化和反序列化一个BST。首先说明，所有的297题的方法在这道题上都通用。
因为只要是BST那就一定是binary tree。所以这里只写一个针对BST树优化的方法。首先有个小
知识点。对于一棵BST来说。如果知道[前序遍历，中序遍历，后序遍历]三种情况中的任意两种。
就都可以构建出来这个BST。LC中也有对应的题目来练习。对于这道题来说，如果我们构建出来后序遍历或者
前序遍历的话，其实也可以构建出来BST，只是因为缺少了另一个构建方式的辅助，要多做一些判断。
首先还是举一个例子，来看后序遍历的序列化和反序列化

    10
   /  \
  5    15
 / \    \
2   8    18

对于这个有规律的二叉树来说。 后序遍历生成的String是(2 8 5 18 15 10)因为BST的特性，我们无需关注空Node
我们如何对这个树做反序列化呢？
首先我们知道，后序遍历的情况下，root是最后被visit的。所以我们可以根据这个条件知道最后
一位就是我们的root。对于BST来说，任何一个节点的左子树比这个节点小，而右子树比这个节点大。
所以对于10右边的树来说，10就是这颗子树的下限。任何比10小的值应该都归为右树。
我们遍历的顺序正好和序列化时相反，变成了 root, root.right, root.left。
也就是从后往前遍历。所以下一位我们要遍历的就是15。此时
  10
    \
     15

我们判断的条件就是，如果下一位的值比当前位还小的话，那么说明当前节点这一边已经遍历完毕了。
为什么呢？因为我们读取这棵树的时候，先往右，再往左。右边的值一定会大于父节点和左边
的兄弟节点。在我们已经到了最右边的节点，也就是找到最大值18的时候，此时数组的下一位应该就
是它的左边兄弟节点(如果是Null的话也没关系，反正只会小)。如果我们知道了下一位比当前位小了，
说明这一边我们已经遍历完毕了。对于左边的遍历来说，也是一样。对于左边来说我们的初始值设置
为min_value, 这个值在左边遍历的时候会改变。

*/

//post-order 后序遍历
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        serialize(root.left, sb);
        serialize(root.right, sb);
        sb.append(Integer.valueOf(root.val)).append(" ");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] nodes = data.split(" ");
        int[] index = new int[] {nodes.length - 1};
        return deserialize(nodes, index, Integer.MIN_VALUE);
    }

    private TreeNode deserialize(String[] nodes, int[] index, int min) {
        if (index[0] < 0 || Integer.valueOf(nodes[index[0]]) <= min) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(nodes[index[0]--]));
        root.right = deserialize(nodes, index, root.val);
        root.left = deserialize(nodes, index, min);
        return root;
    }
}




//Pre-Order, 前序遍历----------------------------
public class Codec {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
	    if (root == null) {
	        return null;
	    }
	    StringBuilder sb = new StringBuilder();
	    serialize(root, sb);
	    return sb.toString();
	}

	private void serialize(TreeNode root, StringBuilder sb) {
	    if (root == null) {
	        return;
	    }
	    sb.append(root.val).append(" ");
	    serialize(root.left, sb);
	    serialize(root.right, sb);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
	    if (data == null || data.length() == 0) {
	        return null;
	    }
	    String[] nodes = data.split(" ");
	    int[] index = new int[] {0};
	    return deserialize(nodes, index, Integer.MAX_VALUE);
	}

	private TreeNode deserialize(String[] nodes, int[] index, int max) {
	    if (index[0] >= nodes.length || Integer.valueOf(nodes[index[0]]) >= max) {
	        return null;
	    }
	    TreeNode root = new TreeNode(Integer.valueOf(nodes[index[0]++]));
	    root.left = deserialize(nodes, index, root.val);
	    root.right = deserialize(nodes, index, max);
	    return root;
	}
}

