DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS local_businesses;




CREATE TABLE local_businesses(
	business_pk int unsigned NOT NULL AUTO_INCREMENT,
	business_id varchar(40) NOT NULL,
	address varchar(100) NOT NULL,
	phone varchar (20) NOT NULL,
	category enum 
	('TEA', 'COFFEE', 'DELI', 'AMERICAN', 'PIZZA', 'PUB', 'BAKERY'),
	PRIMARY KEY (business_pk)	
);
CREATE TABLE images(
	image_pk int unsigned NOT NULL AUTO_INCREMENT,
	image_id varchar(40) NOT NULL,
	business_fk int unsigned NOT NULL,
	width int NOT NULL,
	height int NOT NULL,
	mime_type enum ('image/jpeg','image/png'),
	name varchar (256),
	data mediumblob,
	PRIMARY KEY (image_pk),
	FOREIGN KEY (business_fk) REFERENCES local_businesses(business_pk)
	
);

CREATE TABLE customers(
	customer_pk int unsigned NOT NULL AUTO_INCREMENT,
	customer_id varchar(40) NOT NULL,
	first_name varchar(45) NOT NULL,
	last_name varchar(45) NOT NULL,
	PRIMARY KEY (customer_pk),
	UNIQUE KEY (customer_id)
);









