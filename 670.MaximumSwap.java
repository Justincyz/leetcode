/*670. Maximum Swap
Medium: 
Given a non-negative integer, you could swap two digits at
 most once to get the maximum valued number. Return the maximum 
 valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
*/

/*解题思路
题目给出了一个数字，让我们交换这个数字里面的两个数，使得整个值最大。
这道题目解法比较巧妙，用了bucket sort的思路。 我们需要尽可能找到乱序的
一对数字，前面的小，后面的大，来进行交换。什么叫乱序呢？ 比如说 9845。
那前面的9和8已经排列好了，而且是最大的两位。所以他们不参与交换。而应该交换
后面的4和5。我们如何利用bucket sort来解决这道题目呢？
首先我们建立十个桶。每个桶的位置相当于一个数字，那每个桶里面储存的就是
这个数字最后出现的位置。我们从头往后遍历。每一次都更新一下对应的桶。
然后我们从头到尾遍历这个数字，对于每一个数字我们从尾到头寻找是否有比这个
大，且位置在这个数字之后的这么一个数。 如果有，那么就交换这两个，交换后
的值就是交换一次的最大值了。可以直接返回。如果全部组合遍历完没有的话，说明
给定的num已经是最优解了，那就直接返回那个num

*/

class Solution {
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        
        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i;
        }
        
        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }
        
        return num;
    }
}
