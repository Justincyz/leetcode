/*311. Sparse Matrix Multiplication
链接：
Medium: 
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

Input:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:

     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/

/*解题思路
这道题让我们实现稀疏矩阵相乘，稀疏矩阵的特点是矩阵中绝大多数的元素为0，而相乘的结果是还应该是稀疏矩阵，即还是大多数元素为0，那么我们使用传统的矩阵相乘的算法肯定会处理大量的0乘0的无用功，所以我们需要适当的优化算法。首先复习一下矩阵的相乘，对于结果中每一位的数字(i,j)来说，都要在原矩阵中使用A的i行点乘以B的j列。所以一个正常的矩阵相乘需要三次方的时间复杂度。我们首先
遍历矩阵A，将A的每一行中非0的元素用map记录下来，这样在做点成的时候就不需要遍历那些原本为0的位置了。
然后我们创建一个二维数组储存我们的结果。这个二维数组的高是A的高度，宽是B的宽度。
然后我们按照普通的方法，先从Map获取A的每一行的元素，与B的每一列相乘。这里让人比较头疼的就是确定
结果数组和A和B中遍历到元素的坐标了。
我们最外层是按照A的行来遍历的。所以结果中我们也是先填充行。对于A的每一行中的有效位置i来说，我们要
取的是B[i][j]的值。而j 是遍历B[0].length中的点得来的而不是B.length，为什么呢？
我们还是来用上面的例子讲解。
当我们获取A的第一行时，这个list只储存了一个有效位置就是1, 
本来对于结果中的(0,0)这个位置，我们应该是 1x7+0x0+0x0 (A的第一行和B的第一列)，但是
按照我们便利的顺序来看，当我们遍历完A里面的(0,0)这个位置之后，就不会再遍历这个位置了。
所以我们只能先考虑B中所有需要和A中(0,0)所代表的第一行相乘的列，而B中的列也只有每列第一个
需要和(0,0)相乘，所以当我们遍历B的时候，是按照行来遍历的，然后将结果累加到结果中相应的位置上。


*/

class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for(int i=0; i<A.length; i++){
            map.put(i, new ArrayList());
            for(int j=0; j<A[0].length; j++){
                if(A[i][j] == 0) continue;
                map.get(i).add(j);
            }
        }
        
        int[][] res = new int[A.length][B[0].length];
        
        for(int r = 0; r < A.length; r++){
            List<Integer> row = map.get(r);
                for(int i: row){
                    for(int j=0; j<B[0].length; j++){
                        if(B[i][j] == 0) continue;
                        /*
						这里最不好确定的就是B的位置。对于结果来说，
						我们是在行遍历，所以可以确定是res[r][j],也就是A
						的行r和B的列j。
						我们也可以很快的确定这是A的第r行的第i个数字。
						但是这里我们是按照B的行来遍历的，所以取的是
						B[i][j]的值。因为A的列位置i对应的是B的行位置i.
                        */
                        res[r][j] += (B[i][j] * A[r][i]);
                }
            }
        }
        return res;
    }
}