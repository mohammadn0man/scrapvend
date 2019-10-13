create table admin_details
(
	name varchar(20) not null,
	admin_id int auto_increment,
	Username varchar(50) not null,
	constraint admin_admin_id_uindex
		unique (admin_id),
	constraint admin___fk0
		foreign key (Username) references login_info (Username)
);

create index admin___fk1
	on admin_details (Username);

alter table admin_details
	add primary key (admin_id);

