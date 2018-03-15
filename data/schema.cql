create table btp_category (
id varchar, 
name text, 
description text,
primary key (id, name)
)

create table btp_product (
id varchar, 
name text, 
description text,
category_id varchar,
color text,
make text,
size varchar,
price double,
primary key (id, category_id)
)

create table btp_store (
id varchar, 
name text, 
description text,
address text,
city text,
country text,
zipcode varchar,
longitute double,
latitude double,
primary key (id, zipcode)
)

create table btp_store_location (
id varchar, 
name text, 
description text,
address text,
city text,
country text,
zipcode varchar,
longitute double,
latitude double,
primary key (zipcode, city)
)

create table btp_inventory_supply (
id varchar, 
name text, 
description text,
store_id varchar,
product_id varchar,
quantity double,
primary key (id, name)
)