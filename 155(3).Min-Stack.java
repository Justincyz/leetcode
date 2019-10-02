/*155. Min Stack

链接：https://leetcode.com/problems/min-stack/
Easy: 
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
 

Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

/*解题思路
这道题目有两个注意的地方，第一个是当stack为空时，要重新将min设置为 Integer.Max_Value。
第二个就是如果要被pop()出去的值是当前的min value, 那么我们要对min重新赋值
*/


class MinStack {
    Stack<Integer> s;
    int min = Integer.MAX_VALUE;
    /** initialize your data structure here. */
    public MinStack() {
        s = new Stack();
    }
    
    public void push(int x) {
        if(x < min) min = x;
        s.push(x);
        s.push(min);
    }
    
    public void pop() {
        int t1 = s.pop();
        int t2 = s.pop();
        if( t1 == t2 && !s.isEmpty()) min = s.peek(); 
        if(s.isEmpty()) min =Integer.MAX_VALUE;
    }
    
    public int top() {
        int t = s.pop();
        int t1 = s.peek();
        s.push(t);
        return t1;
    }
    
    public int getMin() {
        int t = s.peek();
        return t;        
    }
}




//同样的配方，使用了两个stack

class MinStack {
    
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    /** initialize your data structure here. */
    public MinStack() {
        
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            int min = minStack.peek();
            if (x <= min) {
            	minStack.push(x);
            }
        }
    }
    
    public void pop() {
        int x = stack.pop();
        if (x == minStack.peek()) {
        	minStack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
