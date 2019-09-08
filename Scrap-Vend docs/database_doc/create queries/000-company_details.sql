create table company_details
(
	Company_id int auto_increment,
	Company_name varchar(50) not null,
	Address varchar(50) not null,
	Contact_no varchar(50) not null,
	constraint company_details_Company_id_uindex
		unique (Company_id)
);

alter table company_details
	add primary key (Company_id);

