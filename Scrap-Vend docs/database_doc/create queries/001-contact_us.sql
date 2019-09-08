create table contact_us
(
	Author varchar(30) not null,
	Email varchar(30) not null,
	Contact_no int(15) not null,
	Subject varchar(50) not null,
	Message varchar(255) not null,
	Contact_id int auto_increment,
	constraint contact_id_UNIQUE
		unique (Contact_id)
);

alter table contact_us
	add primary key (Contact_id);

