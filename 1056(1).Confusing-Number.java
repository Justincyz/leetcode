/*1056. Confusing Number
链接：https://leetcode.com/problems/confusing-number/
Easy: 
Given a number N, return true if and only if it is a confusing number, which satisfies the following condition:

We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.

Example 1:
Input: 6
Output: true
Explanation: 
We get 9 after rotating 6, 9 is a valid number and 9!=6.

Example 2:
Input: 89
Output: true
Explanation: 
We get 68 after rotating 89, 86 is a valid number and 86!=89.

Example 3:
Input: 11
Output: false
Explanation: 
We get 11 after rotating 11, 11 is a valid number but the value remains the same, thus 11 is not a confusing number.

Example 4:
Input: 25
Output: false
Explanation: 
We get an invalid number after rotating 25.
*/

/*解题思路
很简单的一道题目，第一次写有些钉子碰到了，往下看

*/


class Solution {
    public boolean confusingNumber(int N) {
        int temp = N;
        if (temp == 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        while (temp != 0) {
            int digit = temp % 10;
            if (digit == 1) {
                sb.append(1);
            } else if (digit == 6) {
                sb.append(9);
            } else if (digit == 9) {
                sb.append(6);
            } else if (digit == 0) {
                sb.append(0);
            } else if (digit == 8) {
                sb.append(8);
            } else {
                return false;
            }
            temp /= 10;
        }
        return Integer.parseInt(sb.toString()) != N;
        
    }
}



/*
这是我一开始自己写的, 最后比较数值的时候犯了一个错误。
我一开始是用
Integer.valueOf(str2) != Integer.valueOf(str)
来比较两个数值，如果两个数值一样则返回false。
结果不管怎么样两边都不会相等，即使数字不一样也是返回false;
后来突然想起来，这样子比较其实是在比较两个object, 所以不管怎么样
都是不等的。
我们必须要先从Object转化成基本类型，再比较才可以
*/

class Solution {
    public boolean confusingNumber(int N) {
        if(N == 6 || N==9) return true;
        String str = String.valueOf(N);
        char[] list = str.toCharArray();
        char[] list2 = new char[list.length];
        int s = 0, e = list.length-1;
        
        while(s <= e){
            if(list[s] != '8' && list[s] !='0'&& list[s] !='1' && list[s] !='6' && list[s] !='9'){
                return false;
            }
            if(list[e] != '8' && list[e] !='0'&& list[e] !='1' && list[e] !='6' && list[e] !='9'){
                return false;
            }
            list2[s] = list[e];
            list2[e] = list[s];
            if(list2[s] == '6'){
                list2[s] = '9';
            }
            else if(list2[s] == '9'){
                list2[s] = '6';
            }
            if(list2[e] == '6'){
                list2[e] = '9';
            }
            else if(list2[e] == '9'){
                list2[e] = '6';
            }
           
            s++;
            e--;
        }
        
        String str2 = new String(list2);
        int i = Integer.valueOf(str2);
        int j = Integer.valueOf(str);
      //  System.out.println(Integer.valueOf(str2)+" "+ Integer.valueOf(str));
        return i != j;
    }
}