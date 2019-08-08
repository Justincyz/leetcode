/*251. Flatten 2D Vector
Medium: 
Design and implement an iterator to flatten a 2d vector. It should 
support the following operations: next and hasNext.

 
Example:
Vector2D iterator = new Vector2D([[1,2],[3],[4]]);

iterator.next(); // return 1
iterator.next(); // return 2
iterator.next(); // return 3
iterator.hasNext(); // return true
iterator.hasNext(); // return true
iterator.next(); // return 4
iterator.hasNext(); // return false
*/

/*解题思路
这道题目其实非常简单，就是让我们遍历一个二维数组。实现next()和 hasNext()的功能。
有一点要注意，就是可能中间会出现空数组，出现空数组就跳过。
第一种办法最简单，就是用一个queue先把所有的元素拿出来。然后每一次直接从queue中
取元素就好了。
第二种其实就是两个指针，一个指向当前的数组，另一个指向当前数组中的元素。
还是注意数组为空的情况

*/

//56ms beats 46%
class Vector2D {
    Queue<Integer> q;
    public Vector2D(int[][] v) {
        q = new LinkedList<>();
        for(int[] l: v){
            if(l.length>0){
                for(int l1 : l) q.add(l1);
            }
        }
    }
    
    public int next() {
        if(!q.isEmpty()) return q.poll();
        return -1;
    }
    
    public boolean hasNext() {
       return !q.isEmpty();
    }
}

//53ms beats 98%
class Vector2D {
    int[][] v;
    int col=0, row=0;
    public Vector2D(int[][] v) {
        this.v = v;
    }
    
    public int next() {
        if(hasNext()){
            int res = v[col][row++]; 
            return res;
        }else return -1;
    }
    
    public boolean hasNext() {
        while(col < v.length){
            if(row < (v[col].length)){
                return true;
            }
            col++;
            row = 0;
            
        }
        return false;
    }
}


/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(v);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */