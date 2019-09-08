create table user_details
(
	Name varchar(50) not null,
	Address varchar(100) default 'NULL' null,
	User_id int auto_increment,
	Username varchar(50) not null,
	constraint user_details_User_id_uindex
		unique (User_id),
	constraint user_details_Username_uindex
		unique (Username),
	constraint user_details___fk0
		foreign key (Username) references login_info (Username)
);

alter table user_details
	add primary key (User_id);

