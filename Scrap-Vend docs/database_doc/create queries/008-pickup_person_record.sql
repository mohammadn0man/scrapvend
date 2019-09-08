create table pickup_person_record
(
	Pickup_person_id int not null,
	Date timestamp not null,
	`10:00AM-12:00PM` tinyint default 1 null,
	`12:00PM-02:00PM` tinyint default 1 null,
	`02:00PM-04:00PM` tinyint default 1 null,
	`04:00PM-06:00PM` tinyint default 1 null,
	constraint pickup_person_record___fk0
		foreign key (Pickup_person_id) references pickup_person_details (Pickup_person_id)
);

create index pickup_person_record_fk0
	on pickup_person_record (Pickup_person_id);

