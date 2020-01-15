/*182. Duplicate Emails
Easy
链接: https://leetcode.com/problems/duplicate-emails/
Write a SQL query to find all duplicate emails in a table named Person.

+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
For example, your query should return the following for the above table:

+---------+
| Email   |
+---------+
| a@b.com |
+---------+
Note: All emails are in lowercase.


*/

/*解题思路


*/


# Write your MySQL query statement below
#560ms
select distinct p1.email as Email
from Person p1, Person p2
where p1.Email = p2.Email and p1.Id != p2.Id

#这种写法更快, 400ms
select Email
from(
    select Email, count(id) as n
    from Person 
    group by email
    having n>1
)A