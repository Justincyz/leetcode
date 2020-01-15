/*196. Delete Duplicate Emails
Easy
链接: https://leetcode.com/problems/delete-duplicate-emails/
Write a SQL query to delete all duplicate email entries in a table named Person, keeping only unique emails based on its smallest Id.

+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
Id is the primary key column for this table.
For example, after running your query, the above Person table should have the following rows:

+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
Note:

Your output is the whole Person table after executing your sql. Use delete statement.

*/

/*解题思路
这道题有个玄学的地方，我一开始是这样写的
DELETE FROM Person
WHERE Id IN
    (SELECT P1.Id FROM Person AS P1, Person AS P2 
	     WHERE P1.Id > P2.Id AND P1.Email = P2.Email);

结果不行，看了答案发现是这个问题，MySQL Don't allow referring delete target table in sub query, a workaround is use ( select * from Person ) to get a new table.
所以要先选择出来才可以
*/
# Write your MySQL query statement below

delete from Person where Id in (
select p1.Id from (select * from Person) p1, (select * from Person) p2
where p1.Email = p2.Email and p1.Id > p2.Id )

    