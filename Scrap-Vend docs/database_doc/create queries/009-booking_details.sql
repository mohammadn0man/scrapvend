create table booking_details
(
	User_id int not null,
	Booking_date_time datetime not null,
	Scheduled_pickup_date_time datetime not null,
	Pickup_date_time datetime null,
	Booking_id int auto_increment,
	Pickup_person_id int not null,
	Pickup_status varchar(20) not null,
	Pickup_person_rating int(5) null,
	constraint booking_details_Booking_id_uindex
		unique (Booking_id),
	constraint booking_details___fk0
		foreign key (User_id) references user_details (User_id),
	constraint booking_details___fk1
		foreign key (Pickup_person_id) references pickup_person_details (Pickup_person_id)
);

create index booking_details_fk0
	on booking_details (User_id);

create index booking_details_fk1
	on booking_details (Pickup_person_id);

alter table booking_details
	add primary key (Booking_id);

