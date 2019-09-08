create table order_cancel
(
	Booking_id int not null,
	Cancellation_id int auto_increment,
	Feedback varchar(255) null,
	constraint order_cancel_Cancellation_id_uindex
		unique (Cancellation_id),
	constraint order_cancel___fk0
		foreign key (Booking_id) references booking_details (Booking_id)
);

alter table order_cancel
	add primary key (Cancellation_id);

