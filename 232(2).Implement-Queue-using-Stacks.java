/*232. Implement Queue using Stacks
链接：https://leetcode.com/problems/implement-queue-using-stacks/
Easy: 
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Example:

MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);  
queue.peek();  // returns 1
queue.pop();   // returns 1
queue.empty(); // returns false
Notes:

You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/

/*解题思路
一开始想到的最简单的是O(N)插入和O(1) ->pop() &peek()。但是实际上
我们不是对每一次新插入的元素都需要完成整个排序。
在这里s1是放输入的元素，s2是放输出的元素。如果s2
不为空，那么我们直接pop() s2中的元素就可以了。只有
当S2为空的时候，我们再把S1中的元素放入S2中。
这样Amortized complexity都是O(1)

*/
//Amortized complexity都是O(1)
class MyQueue {

    /** Initialize your data structure here. */
    Stack<Integer> s1, s2;
    public MyQueue() {
        s1 = new Stack(); s2 = new Stack();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(empty()) return -1;
        int res = peek();
        s2.pop();
        return res;
    }
    
    /** Get the front element. */
    public int peek() {
        if(empty()) return -1;
        if(s2.isEmpty()){
            while(!s1.isEmpty()) s2.push(s1.pop());
        }
        return s2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s2.isEmpty() && s1.isEmpty();
    }
}



//这是一开始写的，每一次Push都是O(N)，pop()是O(1).
class MyQueue {

    /** Initialize your data structure here. */
    Stack<Integer> s1, s2;
    public MyQueue() {
        s1 = new Stack(); s2 = new Stack();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	//push的时候先把S2倒到S1中，然后把最新的放入S2,最后又倒回来，是O(N)
        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
        s2.push(x);
        while(!s1.isEmpty()){
            s2.push(s1.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(empty()) return -1;
        else return s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if(empty()) return -1;
        else return s2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */