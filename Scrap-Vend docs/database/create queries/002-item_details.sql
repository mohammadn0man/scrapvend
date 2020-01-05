create table item_details
(
	Item_id int auto_increment
		primary key,
	Item_name varchar(30) not null,
	Item_rate float not null,
	Item_measure varchar(20) not null,
	Item_image blob null
);

