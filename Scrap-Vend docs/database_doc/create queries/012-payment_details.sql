create table payment_details
(
	Payment_id int not null,
	Payment_amount float not null,
	Booking_id int not null,
	Payment_mode varchar(20) default 'online' null,
	Payment_status varchar(20) default 'pending' null,
	primary key (Payment_id),
	constraint payment_details___fk0
		foreign key (Booking_id) references booking_details (Booking_id)
);

create index payment_details_fk0
	on payment_details (Booking_id);

