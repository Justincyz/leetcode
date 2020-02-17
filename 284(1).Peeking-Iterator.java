/*284. Peeking Iterator
链接：https://leetcode.com/problems/peeking-iterator/
Medium: 
Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Example:

Assume that the iterator is initialized to the beginning of the list: [1,2,3].

Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2. 
You call next() the final time and it returns 3, the last element. 
Calling hasNext() after that should return false.
Follow up: How would you extend your design to be generic and work with all types, not just integer?
*/

/*解题思路
这道题让我们实现一个顶端迭代器，在普通的迭代器类Iterator的基础上增加了peek的功能，
就是返回查看下一个值的功能，但是不移动指针，next()函数才会移动指针，那我们可以定义
一个变量专门来保存下一个值，再用一个bool型变量标记是否保存了下一个值，再调用原来的
一些成员函数，就可以实现这个顶端迭代器了

*/
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> i;
    int val = 0; //下一位值，只有当pk = true的时候可以读到
    boolean pk = false; //检查是否存上了下一位值
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    i = iterator;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if(pk){
            return val;
        }else{
        	/*如果我们刚call过next，那么此时的peek()也需要更新一下，
			更新到下一位
			*/
            val = i.next();
            pk = true;
            return val;
        }
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		//如果pk==true,说明下一位已经被储存了，那么直接读取就可以
        if(pk){
            pk = false;
            return val;
        }
        //如果在这之前并没有call peek()，说明下一位并没有被读取，那么直接读取下一位
        if(i.hasNext()){
            return i.next();
        }else 
            return -1;
	}

	@Override
	public boolean hasNext() {
		/*如果pk= true,那肯定有下一位,(避免我们使用peek()已经读完了所
		有的元素。此时指针.hasNext()实际上为空)*/
        if(pk){
            return true;
        }
        //否则的话调用原生函数查看是否有下一位
	    return i.hasNext();
	}
}

//网上的人给出另一个解法

class PeekingIterator implements Iterator<Integer> {
    Integer cache = null;
    Iterator<Integer> it;
    
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.it = iterator;
	    cache = it.next();
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return cache;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    int ret = cache;
	    if(it.hasNext()){
	        cache = it.next();
	    }
	    else{
	        cache = null;
	    }
	    return ret;
	}

	@Override
	public boolean hasNext() {
	    return (cache != null);
	}
}