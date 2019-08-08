/*428. Serialize and Deserialize N-ary Tree
Hard: 
Serialization is the process of converting a data structure or object 
into a sequence of bits so that it can be stored in a file or memory 
buffer, or transmitted across a network connection link to be 
reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. 
An N-ary tree is a rooted tree in which each node has no more than 
N children. There is no restriction on how your serialization/deserialization 
algorithm should work. You just need to ensure that an N-ary tree can 
be serialized to a string and this string can be deserialized to 
the original tree structure.

For example, you may serialize the following 3-ary tree

     1
   /  |   \  
  3   2    4
/   \
5   6


*/

/*解题思路
题目要我们对一个多叉树做序列化和反序列化。这道题目其实和二叉树的字符串化和反字符串化
那道题目很像，但是稍微难一点，因为不方便做遍历首先推荐第一种做法，简单有效。第二种方法自己
做的，也还可以。先来看看第一种做法。
这种做法的精髓在于，序列化的时候，每一个节点后面再添加上这个节点的children 数组的长度。
这样在反序列化的时候会非常的好用。对每个节点我们直接生成一个长度固定的list，然后继续递归子
数组就好了。
对于上面例子中的树在序列化之后就变成了 1-3-3-2-5-0-6-0-2-0-4-0

*/
class Codec {
    String spliter = ",";
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    private void serialize(Node node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.val);
        sb.append(spliter);
        sb.append(node.children.size());
        sb.append(spliter);
        for (Node next: node.children) {
            serialize(next, sb);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] nodes = data.split(spliter);
        Deque<String> queue = new ArrayDeque<>(Arrays.asList(nodes));
        return deserialize(queue);
    }
    
    private Node deserialize(Deque<String> queue) {
        int val = Integer.valueOf(queue.pollFirst());
        int size = Integer.valueOf(queue.pollFirst());
        Node node = new Node(val);
        node.children = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            node.children.add(deserialize(queue));
        }
        return node;
    }
}

/*
这个做法就是比较常规的pre-order的做法，还是用例子来说明吧。
比如上面那个树在序列化之后就会变成 1(4()2()3(5()6()))
然后反序列化的时候还是要借助stack。判断清楚在什么情况下将子节点加入到父节点中(当遇到反括号的时候)。
最后返回root就可以了
*/

class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) return "";
        Stack<String> stack = new Stack<>();
        StringBuffer sb = new StringBuffer();
        encode(stack, root);
        for(String i: stack) sb.append(i+" ");
        
        return sb.toString();
    }
    
    public void encode(Stack<String> stack, Node root){
        if(root == null) return;
        
        stack.push(String.valueOf(root.val));
        stack.push("(");
        for(Node n: root.children){
            encode(stack, n);
        }
        stack.push(")");
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.length() == 0) return null;
        String[] res = data.split(" ");
        Stack<Node> s = new Stack<>();
        Node root = null;
        for(int i=0; i< res.length; i++){
            String top = res[i];
            if(!top.equals(")")&&!top.equals("(")){
               s.push(new Node(Integer.valueOf(top), new ArrayList())); 
            }else if(top.equals(")")){
                Node child = s.pop();
                if(s.isEmpty()){
                    root = child;
                    break;
                } 
                s.peek().children.add(child);
            }else continue;
        }
        return root;

    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));