/*987. Vertical Order Traversal of a Binary Tree
Medium: 
链接: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively 
will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever 
the vertical line touches some nodes, we report the values of the nodes 
in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that 
is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every 
report will have a list of values of nodes.

Example 1:
       1
      /  \
     9   20
         / \
       15   7

Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).

Example 2:

      1
    /  \
   2    3
  / \   / \
 4   5 6   7

Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with value 5 and the node with value 6 have the same position 
according to the given scheme.However, in the report "[1,5,6]", the node 
value of 5 comes first since 5 is smaller than 6.

*/

/*解题思路
这道题目要求我们按照竖直方向找到二叉树节点的集合。比方说root和root.left.right这两个
节点应该在同一个list中。我们以root的位置(pos)作为0坐标，往左就-1, 往右就+1。将所有相同坐标的点
储存在HashMap中。遍历的同时记录一下往左最远和往右最远走了多远。这样在遍历hashmap的时候就
可以知道从何处开始遍历了。这道题比较麻烦的就是同一个pos内部的元素应该如何排列。所以我们除了记录
添加元素的位置，数值之外，我们还需要记录一下这个元素是在第几层出现的。因为假设两个元素的值一样的
话，出现的早的那个元素会被放在更加前面。下面是代码，2ms, beats 100%

*/


class Solution {
    int globalMin = 0, globalMax = 0;
    Map<Integer, List<int[]>> map;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        map = new HashMap<>();
        helper(root, 0, 0);
        
        for(int i= globalMin; i<=globalMax; i++){
            List<int[]> temp = map.get(i);
            Collections.sort(temp, new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    if(o1[0]!=o2[0]) return o1[0] -o2[0];
                    else
                        return o1[1] - o2[1];  
                }
            });
            List<Integer> r = new ArrayList<>();
            for(int[] j: temp){
                r.add(j[1]);
            }
            res.add(r);
        }
        return res;
    }
    
    public void helper(TreeNode root, int pos, int level){
        if(root == null) return;
        
        globalMin = Math.min(pos, globalMin);
        globalMax = Math.max(pos, globalMax);
        
        if(map.get(pos) == null) map.put(pos, new ArrayList());
        map.get(pos).add(new int[]{level, root.val});
        
        helper(root.left, pos-1, level+1);
        helper(root.right, pos+1, level+1);
    }
}


/*
3/29/2020 第二次写。
这次对于排列垂直list的顺序我使用了TreeMap的结构，这样遍历的时候就
会先按照X轴的位置排列了。相比上一次我用了一个int[]记录每个点的位置，
这一次我直接创建了一个Point class，更加直接易懂
*/


class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, List<Point>> coordinates = new TreeMap<>();
        
        formVerticalList(coordinates, root, 0, 0);
        return sortVerticalList(coordinates);
    }
    
    
    public void formVerticalList(TreeMap<Integer, List<Point>> coordinates,TreeNode root, int X, int Y){
        if(root == null) return;
        formVerticalList(coordinates, root.left, X-1, Y-1);
   
        if(!coordinates.containsKey(X)) coordinates.put(X, new ArrayList());
        Point newPoint = new Point(Y, root.val);
        coordinates.get(X).add(newPoint);
        
        formVerticalList(coordinates, root.right, X+1, Y-1);
    }
    
    public List<List<Integer>> sortVerticalList(TreeMap<Integer, List<Point>> coordinates){
        List<List<Integer>> res = new ArrayList<>();
        
        for(Map.Entry<Integer, List<Point>> entry : coordinates.entrySet()){
            List<Integer> output = new ArrayList<>();
            Collections.sort(entry.getValue(), new Comparator<Point>(){
                public int compare(Point A, Point B){
                    if(A.Y != B.Y)
                        return B.Y-A.Y;
                    else
                        return A.val - B.val;
                }
            }); 
            
            for(Point p:  entry.getValue()){
                output.add(p.val);
            }
            res.add(output);
        }

        return res;
    }
}

    
public class Point{
    int Y, val;
    public Point(int Y,  int val){
        this.Y = Y;
        this.val = val;
    } 
}