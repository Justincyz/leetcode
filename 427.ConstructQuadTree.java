/*427. Construct Quad Tree
Medium: 
We want to use quad trees to store an N x N boolean grid.
 Each cell in the grid can only be true or false. The root 
 node represents the whole grid. For each node, it will be 
 subdivided into four children nodes until the values in the 
 region it represents are all the same.

Each node has another two boolean attributes : isLeaf and val. 
isLeaf is true if and only if the node is a leaf node. The val
 attribute for a leaf node contains the value of the region it
  represents.

Your task is to use a quad tree to represent a given grid. 
The following example may help you understand the problem better:

Given the 8 x 8 grid below, we want to construct the corresponding 
quad tree:
*/

/*解题思路 
这道题目本来有三个关于Quad Tree很重要的图形，还是有必要回原题目看一下。
定义是这样的。假设一个NxN的2^n长度的矩阵中散落着很多的点。那么如果把这个矩阵
每一次都从中间分为左上，右上，左下，右下四个区间的话，不停地通过这种分法
就可以构建一棵树，树中的每个leaf都包括所有数值相同的子矩阵。举个例子:

0 0 1 0
0 0 0 1
1 1 0 0 
1 1 0 0
     				(false, *)
     /    		|  		 | 			  \
 topleft    topright   bottomleft  bottomright
(true,false)  (false, *)   (true,true)    (true, false) 

//第一个代表是否是叶节点，第二个代表是否是1。对于topright还可以细分，
其余的三个就不需要了。只要区域内值一致，就是叶节点。
(太难画了，子树中还可以细分，还是直接看题目的例子吧)
只有区间内的值不同时，才需要四等分，否则整体就当作一个叶结点。
所以我们需要check四等分区间内的值是否相同，一个比较好的选择是用坐标变量
来控制等分数组的范围，我们只需要一个起始点坐标，和区间的长度，就可以精确定
位一个区间了。比如说对于例子中的整个二维数组数组来说，知道起始点坐标 (0, 0)
还有长度4，就知道表示的是哪个区间。那么我们就新建一个结点，这里的左上，左下
，右上，和右下四个子结点就需要用过调用递归函数来实现了，实现原理都一样，
重要的地方就是确定每个四分区间的起点和长度，长度好确定，都是当前长度的一半
，然后就是把各个区间的起点确定好，这个也不难，就是细心一点，不要写错了
就可以了，另外，对于非叶结点，结点值可以是true或者false都没问题。如果某个
区间上所有值均相同，那么就生成一个叶结点，结点值就跟区间值相同，
isLeaf是true，四个子结点均为NULL即可，


*/

class Solution {
    public Node construct(int[][] grid) {
        int n = grid.length;
        return helper(grid,0,0,n);
    }
    
    public Node helper(int[][] grid, int x, int y, int len){
        if(len == 1){
            return new Node(grid[x][y]!=0, true, null,null,null,null);
        }
        Node node = new Node();
        Node topLeft = helper(grid, x, y, len/2);
        Node topRight = helper(grid, x, y+len/2, len/2);
        Node bottomLeft = helper(grid, x+len/2, y, len/2);
        Node bottomRight = helper(grid, x+len/2, y+len/2, len/2);
        
        if(topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf&&!topLeft.val && !topRight.val&& !bottomLeft.val&& !bottomRight.val){
            node.isLeaf = true;
            node.val = false;
            return node;
        }
        else if(topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf&&topLeft.val && topRight.val && bottomLeft.val && bottomRight.val ){
            node.isLeaf = true;
            node.val = true;
            return node;
        } 
        
        node.topLeft = topLeft;
        node.topRight = topRight;
        node.bottomLeft = bottomLeft;
        node.bottomRight = bottomRight;

        return node;
    }
}

/*参考链接
https://www.cnblogs.com/grandyang/p/9649348.html
*/