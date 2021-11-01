drop database if exists userdb;
create database userdb;
use userdb;

#Create table buyer
create table buyer (
buyer_id varchar(30) not null,
name varchar(30),
email varchar(30),
phone_number varchar(10),
password varchar(60),
is_privileged varchar(5),
reward_points varchar(20),
is_active varchar(5),
primary key (buyer_id)
);
#Insert into table buyer
select * from buyer;

#Create table seller
create table seller (
seller_id varchar(30) not null,
name varchar(30),
email varchar(30),
phone_number varchar(10),
password varchar(60),
is_active varchar(5),
primary key (seller_id)
);
#Insert into table seller
select * from seller;

#Create table wishlist
create table wishlist (
buyer_id varchar(30),
prod_id varchar(30),
primary key (prod_id,buyer_id)
);
#Insert into table wishlist
select * from wishlist;

#Create table cart
create table cart (
buyer_id varchar(30),
prod_id varchar(30),
quantity int,
primary key (prod_id,buyer_id)
);
#Insert into table cart
select * from cart;