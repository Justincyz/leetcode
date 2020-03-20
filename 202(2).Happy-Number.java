/*202. Happy Number
链接：https://leetcode.com/problems/happy-number/
Easy: 
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/

/*解题思路
这道题定义了一种快乐数，就是说对于某一个正整数，如果对其各个位上的数字分别平方，
然后再加起来得到一个新的数字，再进行同样的操作，如果最终结果变成了1，则说明是快乐
数，如果一直循环但不是1的话，就不是快乐数，那么现在任意给我们一个正整数，让我们判
断这个数是不是快乐数，题目中给的例子19是快乐数，那么我们来看一个不是快乐数的情况，
比如数字11有如下的计算过程：

1^2 + 1^2 = 2
2^2 = 4
4^2 = 16
1^2 + 6^2 = 37
3^2 + 7^2 = 58
5^2 + 8^2 = 89
8^2 + 9^2 = 145
1^2 + 4^2 + 5^2 = 42
4^2 + 2^2 = 20
2^2 + 0^2 = 4

我们发现在算到最后时数字4又出现了，那么之后的数字又都会重复之前的顺序，这个循环中
不包含1，那么数字11不是一个快乐数，发现了规律后就要考虑怎么用代码来实现，我们可以
用 HashSet 来记录所有出现过的数字，然后每出现一个新数字，在 HashSet 中查找看是否存
在，若不存在则加入表中，若存在则跳出循环，并且判断此数是否为1，若为1返回true，不为1返
回false
*/


class Solution {
    public boolean isHappy(int n) {
        Set<Integer> repeat = new HashSet<>();
        while(!repeat.contains(n)){
            int sumOfChar =0;
            repeat.add(n);
            while(n > 0){
                sumOfChar += Math.pow(n%10, 2);
                n /=10;
            }
            if(sumOfChar == 1) return true;
            n = sumOfChar;
        }
        return false;
    }
}
/*
这道题还有一种快慢指针的解法，跟之前那道 Linked List Cycle 检测环的方法类似，不同的是这道题环一定存在，不过有的环不符合题意，只有
最后 slow 停在了1的位置，才表明是一个快乐数。而且这里每次慢指针走一步，
快指针走两步，不是简单的指向next，而是要调用子函数计算各位上数字的平方和，
当快慢指针相等时，跳出循环，并且判断慢指针是否为1即可，
*/

class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = n; 
        while(true){
            slow = findNext(slow);
            fast = findNext(fast);
            fast = findNext(fast);
            if(slow == fast) break;
        }
        return slow == 1;
    }
    
    public int findNext(int n){
        int sumOfChar =0;
        while(n > 0){
            sumOfChar += Math.pow(n%10, 2);
            n /=10;
        }
        return sumOfChar;
    }
}