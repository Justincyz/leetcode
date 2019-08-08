/*338. Counting Bits
Medium: 
Given a non negative integer number num. For every numbers i in the 
range 0 ≤ i ≤ num calculate the number of 1's in their binary 
representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]
Example 2:

Input: 5
Output: [0,1,1,2,1,2]
*/

/*解题思路
首先说一下解题思路。我们知道，二进制的数字一定是跟2有关系的。0, 2, 4, 8, 16....所有
这一类的数字用二进制表示都是只有一位为1。2 = 10, 4 = 100, 8 = 1000，16 = 10000，...
在所有这样数字的中间，我们可以发现这样一个规律。在2到4之间，有一个3，3 = 2+1. 相当于3
这个数是 01+ 10, 也就是1所包含的二进制1的数目加上2所包含的二进制1的数目。那我们扩展一下，
假设我们要计算16-32之间数字的二进制数，那么就是16代表的二进制数目加上(1到15)各自代表的二进制数目。
比如说17 = 16+1, 二进制数目就是 1+1 =2。 19 = 16+3,二进制数目就是 1+2 = 3。我们如此计算
每一位直到16+15 = 31。之所以可以这样是因为在之前的计算中我们已经计算过所有1-16的值，所以每一
次我们直接在数组中找到那一位代表的值就可以了。当运行到32时我们我们重新初始化一次count，
使之在新一轮的遍历[1-31]中帮助我们构成[33 - 64]的值。以此类推直到遍历完Num

*/

class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        if(num == 0) return res;
        int count = 1, prev =2;
        res[1] = 1;
        
        for(int i=2; i<=num; i++){
            if(i %prev == 0){
                res[i] = 1;
                count = 1;
                prev = i;
            } 
            else{
                res[i] = (res[count]+1);
                count++;
            }
        }
        return res;
    }
}
/*
这种方法是在网上看别人写的，也是一种奇淫巧技。
假设我们来看看一个随机处于0到num之间的数字。假设这个数是 0010101(乱打的)=21。
那么我们怎么在已经遍历过的数组中找到这个数的组合形式呢？我们将21拆分成，001010 和最后
一位的1。我们将这两个部分的中的1都找到就可以了，第一部分的是 res[i>>1]，第二部分的就是(i%2)
第一部分直接在数组中查找就好，第二部分无非就是0和1，也可以直接相加。
*/

class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        if(num == 0) return res;
        int count = 1, prev =2;
        res[1] = 1;

        for(int i=2; i<=num; i++){
            res[i]  = res[i>>1] + (i%2);
        }
        return res;
    }
}