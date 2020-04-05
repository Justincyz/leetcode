/*94. Binary Tree Inorder Traversal
链接：https://leetcode.com/problems/binary-tree-inorder-traversal/
Medium: 
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
*/

/*解题思路
这道题目让我们in-order的顺序遍历二叉树，
跟这道题目类似的还有: 144. Binary Tree Preorder Traversal 和 145. Binary Tree Postorder Traversal。
都是一个系列的。Inorder就是先遍历根节点左子树所有节点，然后根节点，最后右子树节点。
循环的做法非常简单，我们主要来看下面迭代的做法。

*/

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderRes = new ArrayList<>();
        recursiveHelper(root, inorderRes);
        return inorderRes;
    }
    
    public void recursiveHelper(TreeNode root, List<Integer> inorderRes){
        if(root == null) return;
        recursiveHelper(root.left, inorderRes);
        inorderRes.add(root.val);
        recursiveHelper(root.right, inorderRes);
    }
}


/**
iterative的做法
我们需要一个stack来储存遍历的节点。遍历的顺序是先走到根节点的最左边的节点。
储存当前节点，然后让root等于root.right。如果此时root.right不为空，那么
自然而然的我们就做的是inorder的遍历。如果root.rigth为空，那么第二次循环
的时候就不会进入嵌套的while loop, 而是pop上一次储存节点的父节点出来。
往后不记得的话可以很简单的画一个图就记得了。
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderRes = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        while(!stack.isEmpty() || root !=null){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            inorderRes.add(root.val);
            root = root.right;
        }
        return inorderRes;
    }
}

/*
在这里我们还有一种做法，就是大名鼎鼎的Morris Traversal, 这样时间复杂度还是O(N)，
但是空间复杂度降为了O(1)
"引用"
在介绍这种方法之前，我们先来引入一种新型树，叫 Threaded binary 
tree(https://en.wikipedia.org/wiki/Threaded_binary_tree)，这个还不太好翻译，
我第一眼看上去以为是叫线程二叉树，但是感觉好像又跟线程没啥关系，后来看到网上有人翻
译为螺纹二叉树，但本人认为这翻译也不太敢直视，很容易让人联想到为计划生育做出突出贡献
的某世界著名品牌，但是苦于找不到更合理的翻译方法，就暂且叫螺纹二叉树吧。我们先来看看
维基百科上关于它的英文定义：

A binary tree is threaded by making all right child pointers that would 
normally be null point to the inorder successor of the node (if it exists), 
and all left child pointers that would normally be null point to the inorder
 predecessor of the node.

就是说螺纹二叉树实际上是把所有原本为空的右子节点指向了中序遍历顺序之后的那个节点，
把所有原本为空的左子节点都指向了中序遍历之前的那个节点，具体例子可以点击这里。那么这
道题跟这个螺纹二叉树又有啥关系呢？由于我们既不能用递归，又不能用栈，那我们如何保证访
问顺序是中序遍历的左-根-右呢。原来我们需要构建一个螺纹二叉树，需要将所有为空的右子节
点指向中序遍历的下一个节点，这样中序遍历完左子结点后，就能顺利的回到其根节点继续遍
历了。具体算法如下：

1. 初始化指针 cur 指向 root
2. 当 cur 不为空时
　 - 如果 cur 没有左子结点
　    a) 打印出 cur 的值
　　  b) 将 cur 指针指向其右子节点
　 - 反之
　    将 pre 指针指向 cur 的左子树中的最右子节点　
　　　  * 若 pre 不存在右子节点
　　　      a) 将其右子节点指回 cur
　　　　    b) cur 指向其左子节点
　　　  * 反之
　　　　　 a) 将 pre 的右子节点置空
　　　　　 b) 打印 cur 的值
　　　　　 c) 将 cur 指针指向其右子节点
*/
//C++的写法
class Solution {
public:
    vector<int> inorderTraversal(TreeNode *root) {
        vector<int> res;
        if (!root) return res;
        TreeNode *cur, *pre;
        cur = root;
        while (cur) {
            if (!cur->left) {
                res.push_back(cur->val);
                cur = cur->right;
            } else {
                pre = cur->left;
                while (pre->right && pre->right != cur) pre = pre->right;
                if (!pre->right) {
                    pre->right = cur;
                    cur = cur->left;
                } else {
                    pre->right = NULL;
                    res.push_back(cur->val);
                    cur = cur->right;
                }
            }
        }
        return res;
    }
};

/*
其实 Morris 遍历不仅仅对中序遍历有用，对先序和后序同样有用，具体可参见网友 NOALGO 博客，和 Annie Kim's Blog 的博客。所以对二叉树的三种常见遍历顺序(先序，中序，后序)就有三种解法(递归，非递归，Morris 遍历)，总共有九段代码呀，熟练掌握这九种写法才算初步掌握了树的遍历
*/