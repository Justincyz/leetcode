/*225. Implement Stack using Queues
Easy

Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
*/

/*解题思路
直接用deque就好了，双向链表。
deque一些常用的方法
Insert  addFirst(e)     offerFirst(e)   addLast(e)      offerLast(e)
Remove  removeFirst()   pollFirst()     removeLast()    pollLast()
Examine getFirst()      peekFirst()     getLast()       peekLast()

*/

class MyStack {
    Deque<Integer> q;
    /** Initialize your data structure here. */
    public MyStack() {
        q = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        q.addLast(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q.removeLast();
    }
    
    /** Get the top element. */
    public int top() {
        return q.getLast();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */