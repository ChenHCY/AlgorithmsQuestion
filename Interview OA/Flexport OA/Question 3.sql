/* Flexport OA Question 3 : Cities With More Customers Than Average

country table:
id country_name
1  Austria
2  Germany
3  United Kingdom


city table:

id city_name country_id
1  Wien      1
2  Berlin    2
3  Hamburg   2
4  London    3


customer table:

id customer_name city_id
1  cust1         1
2  cust2         4
3  cust3         3
4  cust4         1
5  cust5         2
6  cust6         1
7  cust7         4
8  cust8         2

*/

/*Get all the elements from the country, city and customer table*/
WITH CTE AS(
SELECT DISTINCT c.country_name, c1.city_name, COUNT(city_id) AS cnt,
AVG(COUNT(city_id)) OVER() avg_cnt
FROM COUNTRY c
INNER JOIN City c1 ON c1.country_id = c.id
INNER JOIN Customer c2 ON c2.city_id = c1.id
GROUP BY c.country_name, c1.city_name
)    

/*Get and select the final result*/
SELECT country_name, city_name, cnt
FROM CTE WHERE cnt > avg_cnt ORDER BY country_name
