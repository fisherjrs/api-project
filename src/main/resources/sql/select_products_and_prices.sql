select * from myretail.products;

select * from myretail.prices;

delete from myretail.products
where id in (811,-811, 411, 101);


delete from myretail.prices
where id in (811,-811, 411, 101);

delete from myretail.products
		where id = 411;

select p.*, pr.price from myretail.products p, myretail.prices pr
where p.id = pr.id
and p.id = 99;

SELECT p.*, pr.price from 
myretail.products p LEFT OUTER JOIN myretail.prices pr
ON p.id = pr.id
where p.id = 99;

SELECT p.*, pr.price 
		from myretail.products p LEFT OUTER JOIN myretail.prices pr
		ON p.id = pr.id
		where p.id = 99
		order by p.id