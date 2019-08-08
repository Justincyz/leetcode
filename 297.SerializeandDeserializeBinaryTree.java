/*297. Serialize and Deserialize Binary Tree
Hard: 
Serialization is the process of converting a data structure or 
object into a sequence of bits so that it can be stored in a 
file or memory buffer, or transmitted across a network connection
 link to be reconstructed later in the same or another computer 
 environment.

Design an algorithm to serialize and deserialize a binary tree. 
There is no restriction on how your serialization/deserialization
 algorithm should work. You just need to ensure that a binary tree 
 can be serialized to a string and this string can be deserialized 
 to the original tree structure.

Example: 
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"


*/

/*解题思路
这道题让我们对二叉树进行序列化和去序列化的操作。这道题目的简单版是 449. Serialize and Deserialize BST. 
那道题只需要是BST。所以可以用preorder转化来做这道题目将树泛化成为了一般形式.
序列化就是将一个数据结构或物体转化为一个位序列，可以存进一个文件
或者内存缓冲器中，然后通过网络连接在相同的或者另一个电脑环境中被还原，还原的过程叫做去序列化。现在让我们来序列化和
去序列化一个二叉树。这题有两种解法，分别为先序遍历的递归解法和层序遍历的非递归解法。先来看先序遍历的递归解法，
非常的简单易懂，对于序列化，我们从根节点开始，如果节点存在，则将值存入stringbuffer，然后分别对其左右子节点递
归调用序列化函数即可。对于去序列化，我们先读入第一个字符，以此生成一个根节点，然后再对根节点的左右子节点递归调用
去序列化函数即可、

再来看看层序遍历的非递归办法。
*/


public class Codec {
    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
       // Deque<String> nodes = new LinkedList<>();
        Queue<String> q = new LinkedList<>();
        q.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(q);
    }
    
    private TreeNode buildTree(Queue<String> q) {
        String val = q.remove();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(q);
            node.right = buildTree(q);
            return node;
        }
    }
}

/*
我们这里用的是queue来做(下面有用stack来遍历的做法)。因为在序列化的情况下，我们读取的顺序直接就是
first-in-first-out。而且每一层直接也不需要额外的字符来分割。因为第一，我们输出的是一个string。
第二，我们每一次插入的都是有效的节点。而且有一点在序列化和逆序列化的时候都要注意，每一次我们操作的都
是下一层的节点。这样才知道哪一些节点是空节点，哪一些节点是有效的。对于空节点我们不需要再向下补全了。
在去序列化的时候我们就需要用指针来判断当前遍历到的节点是哪一个了。注意要先生成根节点，再向下遍历。
在遍历的时候怎么判断换层呢？假设当前层的节点个数是n个，那么下一层的节点数一定是2n个(包括了有效
节点和储存的用"X"代替的空值)。当然下一层的有效节点可能小于2n个。所以其实我们就不需要额外的分隔符了。

*/

/*这是第二版的BFS层遍历，用的主要数据结构是queue。速度比第一版的BFS用两个stack快了不少
。但是速度最快的还是recursive来做*/
public class Codec {
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuffer sb = new StringBuffer();
        
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        sb.append(root.val).append(" ");

        while(!q.isEmpty()){
            TreeNode n = q.poll();
            if(n.left != null){
                sb.append(n.left.val).append(" ");
                q.add(n.left);
            }else{
                sb.append("X").append(" ");
            }
            if(n.right !=null){
                sb.append(n.right.val).append(" ");
                q.add(n.right);
            }else{
                sb.append("X").append(" ");
            }
        }

        return sb.toString();
    }

     // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        String[] list = data.split(" ");

        TreeNode root = new TreeNode(Integer.valueOf(list[0]));
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        int pos = 1;
        while(!q.isEmpty()){
            Queue<TreeNode> nextQu=new LinkedList<>();
            while (!q.isEmpty()) {
                TreeNode n = q.poll();
                if(list[pos].equals("X")){
                    n.left = null;
                }else{
                    TreeNode l = new TreeNode(Integer.valueOf(list[pos]));
                    n.left = l;
                    nextQu.add(l);
                }
                pos++;
                if(list[pos].equals("X")){
                    n.right = null;
                }else{
                    TreeNode r = new TreeNode(Integer.valueOf(list[pos]));
                    n.right = r;
                    nextQu.add(r);
                }
                pos++;
            }
            q =nextQu;
        }
        return root;
    }
}



//这是第一版的BFS, 用的主要结构是Stack<>。速度比较慢，只是算是提供一个思路而已。
public class Codec {
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuffer sb = new StringBuffer();
        Stack<TreeNode> s = new Stack();
        s.push(root);
        sb.append(root.val).append(" ");
        sb.append("#").append(" ");
        while(!s.isEmpty()){
            Stack<TreeNode> temp = new Stack();
            while(!s.isEmpty()){
                TreeNode n = s.pop();
                if(n.left != null){
                    sb.append(n.left.val).append(" ");
                    temp.push(n.left);
                }else{
                    sb.append("X").append(" ");
                }
                if(n.right !=null){
                    sb.append(n.right.val).append(" ");
                    temp.push(n.right);
                }else{
                    sb.append("X").append(" ");
                }
            }
            sb.append("#").append(" ");
            s = temp;
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        String[] list = data.split(" ");

        TreeNode root = new TreeNode(Integer.valueOf(list[0]));
        Stack<TreeNode> s = new Stack();
        s.push(root);
        int pos = 1;
        while(!s.isEmpty()){
            pos++;
            Stack<TreeNode> temp = new Stack();
           
            while(!list[pos].equals("#")){
                TreeNode n = s.pop();
                if(list[pos].equals("X")){
                    n.left = null;
                }else{
                    TreeNode l = new TreeNode(Integer.valueOf(list[pos]));
                    n.left = l;
                    temp.push(l);
                }
                pos++;
                if(list[pos].equals("X")){
                    n.right = null;
                }else{
                    TreeNode r = new TreeNode(Integer.valueOf(list[pos]));
                    n.right = r;
                    temp.push(r);
                }
                pos++;
            }
            s = temp;
             
        }
        return root;
    }
}
