/*183. Customers Who Never Order
Easy
链接: https://leetcode.com/problems/customers-who-never-order/
Suppose that a website contains two tables, the Customers table and the Orders table. Write a SQL query to find all customers who never order anything.

Table: Customers.

+----+-------+
| Id | Name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
Table: Orders.

+----+------------+
| Id | CustomerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
Using the above tables as example, return the following:

+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+

*/

/*解题思路
第二个写法是常规写法，第一个写法利用了left join的性质(性质: https://blog.csdn.net/zhengsy_/article/details/90733864)
获取customerid没有出现在customers这一边的id
*/


# Write your MySQL query statement below
select c.name as Customers
from customers c left join (select customerId from Orders) as cid
on c.id = cid.customerid
where cid.customerid is null


select c.name as Customers
from customers c 
where c.id not in (select customerId from Orders)
