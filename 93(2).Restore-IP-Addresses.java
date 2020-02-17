/*93. Restore IP Addresses
Medium
链接: https://leetcode.com/problems/restore-ip-addresses/
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]


*/

/*解题思路
P地址由32位二进制数组成，为便于使用，常以XXX.XXX.XXX.XXX形式表现，每组XXX代表小于或
等于255的10进制数。所以说IP地址总共有四段，每一段可能有一位，两位或者三位，范围是[0, 
255]，题目明确指出输入字符串只含有数字，所以当某段是三位时，我们要判断其是否越界（>25
5)，还有一点很重要的是，当只有一位时，0可以成某一段，如果有两位或三位时，像 00， 
01， 001， 011， 000等都是不合法的，所以我们还是需要有一个判定函数来判断某个字符串
是否合法。这道题其实也可以看做是字符串的分段问题，在输入字符串中加入三个点，将字符串
分为四段，每一段必须合法，求所有可能的情况。根据目前刷了这么多题，得出了两个经验，一
是只要遇到字符串的子序列或配准问题首先考虑动态规划DP，二是只要遇到需要求出所有可能情
况首先考虑用递归。这道题并非是求字符串的子序列或配准问题，更符合第二种情况，所以我们
要用递归来解。我们用k来表示当前还需要分的段数，如果k = 0，则表示三个点已经加入完成，
四段已经形成，若这时字符串刚好为空，则将当前分好的结果保存。若k != 0, 则对于每一段，
我们分别用一位，两位，三位来尝试，分别判断其合不合法，如果合法，则调用递归继续分剩下
的字符串，最终和求出所有合法组合，

*/

class Solution {
    List<String> list;
    public List<String> restoreIpAddresses(String s) {
        list = new ArrayList<>();
        if(s.length() < 4 || s.length() > 12) return list;
        helper(s, 0, 0, new StringBuffer());
        return list;
    }
    
    public void helper(String s, int index, int dot, StringBuffer sb){
        if(index >= s.length()) return;
        if(dot == 3){
            if(Integer.valueOf(s.substring(index, s.length())) <= 255 && (s.length()-index)<=3){
                if(s.charAt(index) == '0' && (s.length()-index)>1) ;
                else list.add(sb.append(s.substring(index, s.length())).toString());
            }
            return;
        }
        
        int len = sb.length();

        
        for(int i= index+1;  i<= (s.charAt(index) == '0'? index+1: index+3) && i<s.length(); i++){
            if(Integer.valueOf(s.substring(index, i)) <= 255){
                helper(s, i, dot+1, sb.append((s.substring(index, i)+".")));
                sb.setLength(len);
            }
        }
        return;
    }
}
//这个应该是最短的做法，并且最快
public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        helper(s, 0, "", res);
        return res;
    }
    public void helper(String s, int n, String out, List<String> res) {
        if (n == 4) {
            if (s.isEmpty()) res.add(out);
            return;
        }
        for (int k = 1; k < 4; ++k) {
            if (s.length() < k) break;
            int val = Integer.parseInt(s.substring(0, k));
            //k!= String.valueOf(val).length()的意思是看有没有出现0打头并且长度大于一的数字
            if (val > 255 || k != String.valueOf(val).length()) continue;
            //n==3代表了三个点全部用完了，后面跟一个""
            helper(s.substring(k), n + 1, out + s.substring(0, k) + (n == 3 ? "" : "."), res);
        }
    }
}



//化简算法。

class Solution {
    List<String> list;
    public List<String> restoreIpAddresses(String s) {
        list = new ArrayList<>();
        if(s.length() < 4 || s.length() > 12) return list;
        
        helper(s, 0, 0, new StringBuffer());
        return list;
    }
    
    public void helper(String s, int index, int dot, StringBuffer sb){
        if(index >= s.length()) return;
        if(dot == 3){
            if(Integer.valueOf(s.substring(index, s.length())) <= 255 && (s.length()-index)<=3){
                if(s.charAt(index) == '0' && (s.length()-index)>1) ;
                else list.add(sb.append(s.substring(index, s.length())).toString());
            }
            return;
        }
        
        int len = sb.length();
        if(s.charAt(index) == '0'){
            helper(s, index+1, dot+1, sb.append((s.substring(index, index+1)+".")));
            sb.setLength(len);
            return;
        }
        
        if(index < s.length()-3){
            if(Integer.valueOf(s.substring(index, index+3)) <= 255){
                helper(s, index+3, dot+1, sb.append((s.substring(index, index+3)+".")));
            }
        }
        sb.setLength(len);
        if(index < s.length()-2){
            helper(s, index+2, dot+1, sb.append((s.substring(index, index+2)+".")));
        }
        sb.setLength(len);
        if(index < s.length()-1){
            helper(s, index+1, dot+1, sb.append((s.substring(index, index+1)+".")));
        }
        sb.setLength(len);
        
    }
}