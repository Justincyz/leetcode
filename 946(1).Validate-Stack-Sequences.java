/*946. Validate Stack Sequences
链接：https://leetcode.com/problems/validate-stack-sequences/
Medium: 
Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.

 

Example 1:

Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
Output: true
Explanation: We might do the following sequence:
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
Example 2:

Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
Output: false
Explanation: 1 cannot be popped before 2.
*/

/*解题思路
这道题目给我们一个pushed数组，一个popped数组，让我们模拟Stack
的方式，看是否所有的popped都可以正确的完成。也就是在栈顶都有相对应
的push好的元素可以pop出来。
我们其实就可以利用一个stack来做这道题目，利用两个指针，一个i指向
pushed,一个j指向popped。如过栈顶元素和popeed[j]一样，我们就Pop
出来，如果不一样活着栈为空，那么我们就继续Push pushed[i]进入栈中。
*/
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j=0;
        
        for(int i: pushed){
            stack.push(i);
            //其实这个当中不用加上判断条件 j< popped.length， 因为两个数组的长度一样，不存在j到了结尾但是i还没有循环完的问题
            while(!stack.isEmpty() && popped[j] == stack.peek()){
                stack.pop();
                j++;
            }
        }
        
        return stack.isEmpty();
    }
}

//第一次自己写的，有点复杂但是逻辑一样

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i=0, j=0;
        while(j<popped.length){
            if(stack.isEmpty()){
                stack.push(pushed[i++]);
            }else{
                if(stack.peek() != popped[j]){
                    if(i<pushed.length) stack.push(pushed[i++]);
                    else break;
                }else{
                    while(!stack.isEmpty() && j<popped.length && stack.peek() == popped[j] ){
                        stack.pop();
                        j++;
                    }
                }
            }

        }
        
        return stack.isEmpty();
    }
}

