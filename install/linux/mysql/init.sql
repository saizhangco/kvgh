DROP DATABASE IF EXISTS `kvgh` default character set utf8 collate utf8_general_ci;

CREATE DATABASE `kvgh`;

USE `kvgh`;

DROP TABLE IF EXISTS t_acceptance;

DROP TABLE IF EXISTS t_excel;

DROP TABLE IF EXISTS t_hospital_info;

DROP TABLE IF EXISTS t_medicine;

DROP TABLE IF EXISTS t_number_storage;

DROP TABLE IF EXISTS t_order;

DROP TABLE IF EXISTS t_user;

DROP TABLE IF EXISTS t_vendor;

CREATE TABLE t_acceptance (
	id bigint NOT NULL AUTO_INCREMENT,
	accepter varchar(255),
	amount float,
	batch_number varchar(255),
	date varchar(255),
	description varchar(255),
	gift_quantity integer,
	hospital_code varchar(255),
	invoice_no varchar(255),
	item_number varchar(255),
	material_discount float,
	medicinal_discount float,
	name varchar(255),
	number varchar(255),
	order_id bigint,
	period_of_validity varchar(255),
	source varchar(255),
	times integer,
	unit varchar(255),
	unit_price varchar(255),
	vendor_code varchar(255),
	PRIMARY KEY (id)
) ENGINE = InnoDB default charset=utf8;

CREATE TABLE t_excel (
	id bigint NOT NULL AUTO_INCREMENT,
	date varchar(255),
	excel_name varchar(255),
	order_id varchar(255),
	order_size integer,
	process_id varchar(255),
	status integer,
	vendor varchar(255),
	vendor_email varchar(255),
	PRIMARY KEY (id)
) ENGINE = InnoDB default charset=utf8;

CREATE TABLE t_hospital_info (
	id bigint NOT NULL AUTO_INCREMENT,
	name varchar(255),
	no varchar(255),
	PRIMARY KEY (id)
) ENGINE = InnoDB default charset=utf8;

CREATE TABLE t_medicine (
	id bigint NOT NULL AUTO_INCREMENT,
	comment1 varchar(255),
	comment2 varchar(255),
	health_insurance_code varchar(255),
	hospital_bar_code varchar(255),
	hospital_no varchar(255),
	item_number varchar(255),
	name varchar(255),
	new_vendor varchar(255),
	new_vendor_bar_code varchar(255),
	new_vendor_email varchar(255),
	new_vendor_fax varchar(255),
	new_vendor_phone varchar(255),
	number integer,
	old_vendor varchar(255),
	old_vendor_phone varchar(255),
	specifications varchar(255),
	unit varchar(255),
	unit_price DOUBLE PRECISION,
	PRIMARY KEY (id)
) ENGINE = InnoDB default charset=utf8;

CREATE TABLE t_number_storage (
	id bigint NOT NULL AUTO_INCREMENT,
	count integer NOT NULL,
	date varchar(255) NOT NULL,
	PRIMARY KEY (id)
) ENGINE = InnoDB default charset=utf8;

CREATE TABLE t_order (
	id bigint NOT NULL AUTO_INCREMENT,
	amount DOUBLE PRECISION,
	applicant varchar(255),
	applicant_department varchar(255),
	date varchar(255),
	delivery_address varchar(255),
	hint varchar(255),
	hospital_code varchar(255),
	name varchar(255),
	no varchar(255),
	number integer,
	orderer varchar(255),
	origin_no varchar(255),
	purchase_no varchar(255),
	status integer,
	type integer,
	unit varchar(255),
	unit_price DOUBLE PRECISION,
	vendor varchar(255),
	PRIMARY KEY (id)
) ENGINE = InnoDB default charset=utf8;

CREATE TABLE t_user (
	id bigint NOT NULL AUTO_INCREMENT,
	department varchar(255),
	email varchar(255),
	fax varchar(255),
	password varchar(255),
	phone varchar(255),
	username varchar(255),
	PRIMARY KEY (id)
) ENGINE = InnoDB default charset=utf8;

CREATE TABLE t_vendor (
	id bigint NOT NULL AUTO_INCREMENT,
	code varchar(255),
	email varchar(255),
	fax varchar(255),
	is_new bit,
	name varchar(255),
	phone varchar(255),
	PRIMARY KEY (id)
) ENGINE = InnoDB default charset=utf8;

ALTER TABLE t_number_storage
	ADD UNIQUE (date);