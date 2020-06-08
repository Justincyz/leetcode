/*1429. First Unique Number
Medium
链接: 
You have a queue of integers, you need to retrieve the first unique integer in the queue.

Implement the FirstUnique class:

FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
void add(int value) insert value to the queue.


Example 1:
Input: 
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
Output: 
[null,2,null,2,null,3,null,-1]

Explanation: 
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // return 2
firstUnique.add(5);            // the queue is now [2,3,5,5]
firstUnique.showFirstUnique(); // return 2
firstUnique.add(2);            // the queue is now [2,3,5,5,2]
firstUnique.showFirstUnique(); // return 3
firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // return -1

Example 2:
Input: 
["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
[[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
Output: 
[null,-1,null,null,null,null,null,17]

Explanation: 
FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
firstUnique.showFirstUnique(); // return -1
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
firstUnique.showFirstUnique(); // return 17

Example 3:
Input: 
["FirstUnique","showFirstUnique","add","showFirstUnique"]
[[[809]],[],[809],[]]
Output: 
[null,809,null,-1]

Explanation: 
FirstUnique firstUnique = new FirstUnique([809]);
firstUnique.showFirstUnique(); // return 809
firstUnique.add(809);          // the queue is now [809,809]
firstUnique.showFirstUnique(); // return -1

 
Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^8
1 <= value <= 10^8
At most 50000 calls will be made to showFirstUnique and add.
*/


/*解题思路
这道题目还是挺有意思的，是一道新的题目。题目的意思是说，给我们一个初始的数组。当程序调用showFirstUnique()的时候，要返回第一个按照顺序获得的，且unique的数字。unique的意思是说这个数字到目前为止没有出现过超过一次。我们会持续的调用add()方法添加数字，注意我们初始化的时候就会往里面添加进一个数组。往后添加的方式和第一次的添加方式是一样的。注意，这里一开始我没有搞明白结果写错了。这里我们给所有元素排序的依据是按照他们被添加进来的顺序，每一次showFirstUnique()也是按照这个顺序找数字的，一开始我以为是找最小的数字，后来才发现不是。

其实这个题目跟LRU特别像，但是没有那么难。我们也是建立一个Node节点，做成double linkedlist的形态。然后使用一个hashmap在O(1)时间内可以定位每个元素在什么位置。每一次添加元素我们都添加在链表的尾部。我们每一次获取元素都获取链表头部的元素。如果某一个元素出现了两次，那么我们就从链表和hashmap当中删除这个元素。同时移到一个叫做used的hashset()当中。这样我们就可以快速的知道是元素出现过。

这样增加，删除，getFirst()的时间都是O(1)
时间复杂度取决于元素的数量。

*/

class FirstUnique {
    Map<Integer, Node> map;
    Node head, tail;
    Set<Integer> used = new HashSet();
    public FirstUnique(int[] nums) {        
        map = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        for(int num: nums) add(num);
    }
    
    public int showFirstUnique() {
        if(head.next == tail) return -1;
        else return head.next.val;
    }
    
    public void add(int value) {
        if(used.contains(value)) return;
        if(map.containsKey(value)){
            removeNodeFromMapAndList(value);
        }else{
            addElement(value);
        }
    }
    
    public void removeNodeFromMapAndList(int value){
        Node node = map.get(value);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        map.remove(value);
        used.add(value);
    }
    
    public void addElement(int value){
        Node newNode = new Node(value);
        Node previous = tail.prev;
        newNode.next = tail;
        tail.prev = newNode;
        newNode.prev = previous;
        previous.next = newNode;
        map.put(value, newNode);
    }
    
    class Node{
        Node next=null, prev=null;
        int val;
        public Node(int val){
            this.val = val;
        }
    }
   
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */

