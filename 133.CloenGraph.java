/*133. Clone Graph
Medium: DFS, BFS, Graph

Given a reference of a node in a connected undirected graph, return a deep copy 
(clone) of the graph. Each node in the graph contains a val (int) and a list 
(List[Node]) of its neighbors.

example:
1----2
|    |
|    |
4----3
Input:
{"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},
{"$id":"3","neighbors":[{"$ref":"2"},
"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},
{"$ref":"4"}],"val":1}

Explanation:
Node 1's value is 1, and it has two neighbors: Node 2 and 4.
Node 2's value is 2, and it has two neighbors: Node 1 and 3.
Node 3's value is 3, and it has two neighbors: Node 2 and 4.
Node 4's value is 4, and it has two neighbors: Node 1 and 3.
*/

/*解题思路
这道无向图的复制问题和之前的 Copy List with Random Pointer 有些类似，那道题的难点是如何处
理每个结点的随机指针，这道题目的难点在于如何处理每个结点的 neighbors，由于在深度拷贝每一个结点后，
还要将其所有 neighbors 放到一个 List 中，而如何避免重复拷贝呢？这道题好就好在所有结点值不同，
所以我们可以使用 HashMap 来对应原图中的结点和新生成的克隆图中的结点。对于图的遍历的两大基本方法是
深度优先搜索 DFS 和广度优先搜索 BFS，这里我们先使用深度优先搜索DFS来解答此题，在递归函数中，首先判
空，然后再看当前的结点是否已经被克隆过了，若在 HashMap 中存在，则直接返回其映射结点。否则就克隆当
前结点，并在 HashMap 中建立映射，然后遍历当前结点的所有 neighbor 结点，调用递归函数并且加到克隆
结点的 neighbors 数组中即可

仔细总结这两种方法，很经典的DFS和BFS
*/
//DFS
class Solution {   
    public Node cloneGraph(Node node) {
        HashMap<Node, Node> map = new HashMap<>();
        return helper(node, map);
    }

    public Node helper(Node root, HashMap<Node, Node> map){
        if(map.containsKey(root)) return map.get(root);
        Node newNode = new Node(root.val, new ArrayList());
        map.put(root, newNode);//储存的是原节点和当前节点的键值对，这两个节点值一样
        for(Node node: root.neighbors){
            newNode.neighbors.add(helper(node,map));
        }
        return newNode;
    }
}

//BFS
class Solution {   
    public Node cloneGraph(Node node) {
        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        Node newNode = new Node(node.val, new ArrayList());
        map.put(node, newNode);

        while(!q.isEmpty()){
            Node cur = q.poll();   
            for(Node temp: cur.neighbors){
                if(!map.containsKey(temp)){
                    Node n= new Node(temp.val, new ArrayList());
                    map.put(temp, n);
                    q.add(temp);
                }
                map.get(cur).neighbors.add(map.get(temp));
            }
        }
        return newNode;
    }
}


