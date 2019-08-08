/*957. Prison Cells After N Days
Medium: 
There are 8 prison cells in a row, and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the 
following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, 
then the cell becomes occupied. Otherwise, it becomes vacant.
(Note that because the prison is a row, the first and the last cells in the 
row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: cells[i] == 1 
f the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after 
N days (and N such changes described above.)

Example 1:
Input: cells = [0,1,0,1,1,0,0,1], N = 7
Output: [0,0,1,1,0,0,0,0]
Explanation: 
The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]

Example 2:
Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
Output: [0,0,1,1,1,1,1,0]

*/

/*解题思路
题目大意是，给定一个监狱的初始状态，用array来表示，每一天都会根据前一天关押犯人的位置不
而产生新的关押方式。如果一个监狱左右两边都没人，或者左右两边都没人的话，那么这个位置将会
被添加进一位新的犯人。否则的话就将当前牢房清空。题目问经过N天之后牢房的状态。
注意第二个例子，我们发现N很大。所以肯定不能遍历N次数组来做这道题目。我们注意到，除了初始化
的array之外，其余的所有情况下开头和结尾两个牢房都不能关押犯人。所以我们只有中间六个牢房
可以关押犯人，所以总共只有2^6个情况，也就是 64个不同的组合方式。所以这是一个循环。
当我们走过一轮循环之后，我们只需要在遇到曾经遇到的状态时检查这两个同样的状态之间总共
变换了多少次，然后用剩余的 N对这个数取余就可以了。

第一个办法是先找到这个循环的大小，然后用对N取余数。
第二个办法是一边遍历，一边检查是否出现了循环。
*/

class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        //第一次是不会参与循环的。因为初始状态两边可能会出现1，第一次之后两个边不可能出现1。
        int[] firstSimulation = new int[8];
        for (int i = 1; i < 7; i++) {
            firstSimulation[i] = (cells[i-1] == cells[i+1] ? 1 : 0);
        }
        return prisonAfterNDaysClean(firstSimulation, N - 1);
    }

    //clean之后的cells，经过N次的状态, 不需要考虑 N-1 问题。
    public int[] prisonAfterNDaysClean(int[] cells, int N) {
        int cycle = getCycle(cells);
        int realN = N % cycle;
        for(int j = 1; j <= realN; j++) {
            int[] nextSimulation = new int[8];
            for (int i = 1; i < 7; i++) {
                nextSimulation[i] = (cells[i-1] == cells[i + 1] ? 1 : 0);
            }
            cells = nextSimulation;
        }
        return cells;
    }
    
    //只调用一次，找到这个circle的大小
    public int getCycle(int[] cells) {
        int cycle = 0;
        int[] initial = cells;
        while (true) {
            cycle++;
            int[] nextSimulation = new int[8];
            for (int i = 1; i < 7; i++) {
                nextSimulation[i] = (cells[i-1] == cells[i + 1] ? 1 : 0);
            }
            //找到了轮回，此时经历了cycle次变化。
            if (Arrays.equals(initial, nextSimulation)) break;
            cells = nextSimulation;
        }
        //此时cells已经回到原始状态，可安全返回。
        return cycle;
    }
}



//这种方式简短，但是因为map的读取所以会比上面那个办法慢上不少
class Solution {
     public int[] prisonAfterNDays(int[] cells, int N) {
         HashMap<String, Integer> map = new HashMap<>();
         while(N>0){
             int[] temp = new int[8]; 
             map.put(Arrays.toString(cells), N--);

             for(int i=1; i<7; i++){
                 temp[i] = cells[i-1] == cells[i+1] ? 1:0;
             }
             if(map.containsKey(Arrays.toString(temp))){
                 N %= (map.get(Arrays.toString(temp)) -N);
             }
             cells = temp;
         }
         return cells;
     }
}