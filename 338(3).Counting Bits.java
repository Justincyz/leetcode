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

/*解题思路 dp
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

/*
时隔数月重写这道题目又琢磨出来另一种新的思路。
还是和每一个数字所处在的位置有关系。对于每一个数字来说，总共会处在三种不同的位置当中。
1. 这个数字正好的2的x次方。比如说(2, 4, 8, 16 ... ) 这样的数子只有一个1。
2. 这个数字是当前二进制长度可以表达的最大数。比如说当数字为2的时候，我们总共需要两个bit来表达二进制。对于数字14来说，总共需要四个bit(1110)来表达。那么我所谓的二进制长度可以表达的最大值，如果是两个bit的话，最大值就是(11 - > 3)。如果是四个bit的话，最大值就是(1111 - > 15)。 那么第二种情况就是这样，在这种情况下，所有的元素都是1.
3. 第三种情况就是在上面这两种情况之外的情况。在这种情况下，1的个数是当前二进制长度可以表达的最大数减去这个最大数减去当前数的结果上面1的个数。听起来有一点绕，我举一个例子就很容易理解了。
比如说9这个数，他的1的个数实际上就是15里面1的个数减去6里面1的个数。15 = 1111， 6 = 0110，可以发现9=1001。结果是2。

我们针对上面三种情况写下来三种不同的if statements. 在这里的一个小关键就是我们每一次增加一个Bit的时候，都用一个整数count记录下来。这样可以帮助我们快速的统计个数。
*/

class Solution {
    public int[] countBits(int num) {
        int[] dp = new int[num+1];
        int count = 1;
        for(int i=1; i<=num; i++){
            if(i == Math.pow(2,count)){
                dp[i] = 1;
                count++;
            }else if(i == Math.pow(2, count)-1){
                dp[i] = count;
            }else{
                dp[i] = count-dp[(int)Math.pow(2, count)-1-i];    
            }  
        }
        return dp;
    }
}