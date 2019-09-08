create table company_sale_details
(
	Company_id int not null,
	Sales_id int auto_increment,
	Sales_amount float not null,
	Date datetime not null,
	Sale_status varchar(20) not null,
	constraint company_sale_details_Sales_id_uindex
		unique (Sales_id),
	constraint company_sale_details___fk0
		foreign key (Company_id) references company_details (Company_id)
);

create index company_sale_details_fk0
	on company_sale_details (Company_id);

alter table company_sale_details
	add primary key (Sales_id);

