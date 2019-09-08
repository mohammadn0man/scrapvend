create table pickup_person_details
(
	Name varchar(50) not null,
	Aadhar_no varchar(12) not null,
	Pickup_person_id int auto_increment,
	Salary float not null,
	Rating int(5) default 0 null,
	Pickup_person_status varchar(25) default 'working' null,
	Username varchar(50) not null,
	constraint pickup_person_details_Aadhar_no_uindex
		unique (Aadhar_no),
	constraint pickup_person_details_Pickup_person_id_uindex
		unique (Pickup_person_id),
	constraint pickup_person_details_Username_uindex
		unique (Username),
	constraint pickup_person_details___fk0
		foreign key (Username) references login_info (Username)
);

alter table pickup_person_details
	add primary key (Pickup_person_id);

