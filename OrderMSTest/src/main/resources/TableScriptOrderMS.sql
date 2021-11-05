drop database if exists orderdb;
create database orderdb;
use orderdb;

#Create table order_table
drop table if exists order_table;
create table order_table(
order_id varchar(30) primary key,
buyer_id varchar(30),
amount float,
date Date,
address varchar(50),
status varchar(20)
);
#Insert into order table
select * from order_table;


#Create table products_ordered
drop table if exists products_ordered;
create table products_ordered(
buyer_id varchar(30),
prod_id varchar(30),
seller_id varchar(30),
quantity integer,
primary key (prod_id,buyer_id)
);
#Insert into products ordered
select * from products_ordered;