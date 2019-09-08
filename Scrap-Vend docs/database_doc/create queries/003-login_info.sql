create table login_info
(
	Username varchar(50) not null,
	Role int default 2 not null,
	password char(32) not null,
	email varchar(100) not null,
	contact_no varchar(12) not null,
	constraint login_info_Username_uindex
		unique (Username),
	constraint login_info_contact_no_uindex
		unique (contact_no),
	constraint login_info_email_uindex
		unique (email)
)
comment 'role 0 - user 1 - pickuPerson 2 - admin';

alter table login_info
	add primary key (Username);

