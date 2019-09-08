create table booked_item_list
(
	Item_id int not null,
	Booking_id int not null,
	Item_qty float not null,
	Item_amount float not null,
	Pickup_timestamp timestamp not null,
	constraint booked_item_list___fk0
		foreign key (Item_id) references item_details (Item_id),
	constraint booked_item_list___fk1
		foreign key (Booking_id) references booking_details (Booking_id)
);

create index booked_item_list_fk0
	on booked_item_list (Item_id);

create index booked_item_list_fk1
	on booked_item_list (Booking_id);

