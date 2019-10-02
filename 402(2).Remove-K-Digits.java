/*402. Remove K Digits
Medium: 

Given a non-negative integer num represented as a string, remove k digits 
from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 
1219 which is the smallest.

Example 2:
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the 
output must not contain leading zeroes.

Exampel 3: 
num = "1234567890"
k  = 9
-> 0
*/

/*解题思路
这道题目让我们在一个数字中拿掉某几个，使得剩余的数字值最小。同时注意边界条件，比如说
不能有Leading zero, 如果k > 数字长度的话怎么处理。
我们可以用stack或者dequeue来做，这里我用的是dequeue, 因为这样可以比较容易的
访问dequeue的头部，以便把leading zero去掉。
这题跟 LeetCode 上之前那道 Create Maximum Number 有些类似，可以借鉴其中的思路，
如果n是 num 的长度，我们要去除k个，那么需要剩下 n-k 个，怎么判断哪些数字应该去掉呢？
首先来考虑，若数字是递增的话，比如 1234，那么肯定是要从最后面移除最大的数字。若是乱序
的时候，比如 1324，若只移除一个数字，移除谁呢？我们一眼可以看出是移除3，变成 124 是最小。
但是怎么设计算法呢，实际上这里利用到了单调栈的思想，这里我们维护一个递增栈，只要发现当前
的数字小于栈顶元素的话，就将栈顶元素移除，比如点那个遍历到2的时候，栈里面有1和3，此时2小
于栈顶元素3，那么将3移除即可。为何一定要移除栈顶元素呢，后面说不定有更大的数字呢？这是因为
此时栈顶元素在高位上，就算后面的数字再大，也是在低位上，我们只有将高位上的数字尽可能的变小，
才能使整个剩下的数字尽可能的小。我们开始遍历给定数字 num 的每一位，对于当前遍历到的数字c，
进行如下 while 循环，如果 dq 不为空，且k大于0，且 dq 的顶部大于c，那么应该将 res 的顶
部移去，且k自减1。当跳出 while 循环后，我们将c加入 res 中，最后将 res 的大小重设为 n-k。
根据题目中的描述，可能会出现 "0200" 这样不符合要求的情况，所以我们用一个 while 循环来去
掉前面的所有0，然后返回时判断是否为空，为空则返回 “0”：

*/

class Solution {
    public String removeKdigits(String num, int k) {
        if(k>=num.length()) return "0";
        Deque<Integer> dq = new LinkedList();
        for(int i=0; i<num.length(); i++){
            int c = num.charAt(i)-'0';
            while(!dq.isEmpty() && k>0  && dq.peekLast() > c){
                k--;
                dq.removeLast();
            }
            dq.addLast(c);
        }
        
        StringBuffer sb = new StringBuffer();
        
        //remove the leading zero
        while(!dq.isEmpty() && dq.peekFirst() ==0){
            dq.pollFirst();
        }
        
        //add the element from dequeue to final result
        //we need to remove k element, then we just add dq.size() -k element to our result
        while(!dq.isEmpty() && dq.size()>k){
            sb.append(dq.pollFirst());
        } 
        
        //if the dequeue becomes empty, then return 0, else return string
        return sb.length()!=0? sb.toString(): "0";
    }
}




/* 9/14/2019  第二次重写*/
class Solution {
    public String removeKdigits(String num, int k) {
        if(num.length() <=k) return "0";
        Stack<Integer> s = new Stack<>();
        
        for(int i=0; i <num.length(); i++){
            char c = num.charAt(i);
            while(k > 0 && !s.isEmpty() && num.charAt(s.peek()) > c){
                s.pop();
                k--;
            }
            s.push(i);
        }
        
 
      
        StringBuffer sb = new StringBuffer();
        while(!s.isEmpty()){
            if(k-- >0) s.pop();
            else sb.append(num.charAt(s.pop()));    
        } 
        sb.reverse();
        while(sb.length()>0){
            if(sb.charAt(0) != '0') break;
            else sb.deleteCharAt(0);
        }
        
        return sb.length() == 0? "0" :sb.toString();
    }
}



