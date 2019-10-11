/*703. Kth Largest Element in a Stream
链接：https://leetcode.com/problems/kth-largest-element-in-a-stream/
Easy: 
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
*/

/*解题思路
这道题目让我们找到input stream 中的第K大的值。就是每一次会插入
一个新的值，然后返回第K大的值。最简单也是最快的做法就是维持一个
大小为K的priorityqueue。 注意输入的nums有可能是空的。

*/

class KthLargest {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        for(int i: nums){
            pq.add(i);
            if(pq.size() > k) pq.poll();
        }
    }
    
    public int add(int val) {
        pq.add(val);
        if(pq.size() > k) pq.poll();
 
        return pq.peek();
    }
}

/*
这是我第一次写的，速度稍微慢了一些，因为每一次因为元素的增加
使得N值会变大，虽然还是O(logn)。
我用二分法在list中找到要被插入的位置。
这个collection.binarySearch()是个好东西，
如果元素出现在数组中，那么返回的就是这个元素的位置。如果元素
没有出现在数组中，那么会返回一个负数，这个负数取反代表的是新的元素
应该在数组中的第几位的。
比如说[1,3,5]，我要插入2，那么新的元素应该是插入再数组的第二个，
也就是1的后面，那么此时call binarySearch()就会返回-2, 所以我们这里
取反然后减一就是新插入的位置了。
*/
class KthLargest {
    List<Integer> list = new ArrayList<>();
    int k;
    public KthLargest(int k, int[] nums) {
        for(int i: nums){
            list.add(i);
        }
        Collections.sort(list);
        this.k = k;
    }
    
    public int add(int val) {
        int idx = Collections.binarySearch(list, val);
        if(idx >= 0) list.add(idx, val);
        else list.add(-1*idx-1, val);
        
        return list.get(list.size()-k);
    }
}
