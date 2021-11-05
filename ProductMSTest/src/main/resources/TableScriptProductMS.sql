drop database if exists prod_db;
create database prod_db;
use prod_db;

#Create table product
drop table if exists product;
create table product (
prod_id varchar(30) not null,
product_name varchar(100),
price float,
stock int,
description varchar(500),
image varchar(50),
seller_id varchar(30),
category varchar(30),
sub_category varchar(30),
product_rating float,
primary key (prod_id)
);
#Insert into product table
select * from product;

#Create table subscribed product
drop table if exists subscribed_product;
create table subscribed_product (
buyer_id varchar(30),
prod_id varchar(30),
quantity int,
primary key (buyer_id,prod_id)
);
#Insert into subscribed product table
select * from subscribed_product;


