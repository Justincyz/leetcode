/*60. Permutation Sequence
链接：https://leetcode.com/problems/permutation-sequence/
Medium: 
The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
*/

/*解题思路
参考链接: https://www.cnblogs.com/grandyang/p/4358678.html

这道题目是一道很有意思的题目。给出元素的个数，从1到n, 让我们找到第k个
排列组合的元素是什么。如同题目当中用n=3给出的例子一样。那么这道题目我们
肯定不能先生成所有的排列组合再取值，因为这样不仅浪费空间还浪费时间。
所以我们可以考虑用数学的办法来做这道题。
我们从小往大的方向看，
只有一位数的时候就是1   = 1个
两位数的时候是12,21     = 2个
三位数的时候是 123,132,213,231,312,321  = 6个
每次增加一位数，就是增加后的长度乘以上一位的长度
以此类推当n = 4的时候就有 4x6 = 24个数字, n = 5的时候就有24x5 = 120个元素

通过这个我们可以判断啥呢？我们实际上可以通过最高位来判断k会落在哪一个
区间当中，比如说当n = 3的时候，如果k= 1的话，那么说明没有任何permutation
需要做。如果k = 2的话，那么说明第一位不用变，只要把后两位进行交换就可以了。
如果k >2 且 k <=6的话，那么就说明第一位也需要被交换。

我们假设k = 5这样的一个例子。
首先我们判断最外面的一位也就是最高位是需要被交换的，如何判断？就是通过
计算5/3 > 1知道的。那么哪一位会到最前面来？我们通过取余来计算 5%3 = 2,
也就是第二位会被挪到最前面来。记住这里不是交换，而是挪过来(例子是123的话，第一位和第三位的交换指的是321，但是挪过来指的是312).
所以第一位就是3，此时剩下来的就是12两位。此时k = 2因为我们已经调整好第一位了，k取的是和3的余数。
接下来其实就是解当k=2, 目前n =2位。我们要挪到第一位就是1，因为2/2 = 1,
所以不用变。
接下来k = 1了,因为2%1 = 1。
也是因为1/1 = 1, 所以不用变。
那么最后的结果就是312了。
这道题主要考察的就是细心，因为有些地方存在错位的情况。
1. list[i]存的是目前为止的向上取值的范围。list[1] = 1, list[2] = 2, list[3] = 6.
但是每一次查找范围是对上一位的最大范围，也就是list[i-1]进行操作。这一点要注意。
2. k要先进行减一的操作，因为题目里告诉我们找到第k个时是从1开始计算的，
但是我们要从0开始计算。
3. 每一次我们取到最高位的值之后要从stringbuffer中移除，否则会出错。

*/


class Solution {
    public String getPermutation(int n, int k) {
        int[] list = new int[n+1];
        list[0] =1;
        StringBuffer sb = new StringBuffer();
        
        for(int i=1; i<=n; i++){
            sb.append(i);
            list[i] = i*list[i-1];
        }
        k--;
        int offset =0;
        StringBuffer res = new StringBuffer();
        for(int i=n; i>=1; --i){
            int pos = k/(list[i-1]);
            res.append(sb.charAt(pos));
            k%=(list[i-1]);
            sb.deleteCharAt(pos);
        }
        
        return res.toString();
    }
}