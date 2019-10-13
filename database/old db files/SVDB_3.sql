create table company_details
(
    Company_id   int auto_increment,
    Company_name varchar(50) not null,
    Address      varchar(50) not null,
    Contact_no   varchar(50) not null,
    constraint company_details_Company_id_uindex
        unique (Company_id)
);

alter table company_details
    add primary key (Company_id);

create table company_sale_details
(
    Company_id   int         not null,
    Sales_id     int auto_increment,
    Sales_amount float       not null,
    Date         datetime    not null,
    Sale_status  varchar(20) not null,
    constraint company_sale_details_Sales_id_uindex
        unique (Sales_id),
    constraint company_sale_details___fk0
        foreign key (Company_id) references company_details (Company_id)
);

create index company_sale_details_fk0
    on company_sale_details (Company_id);

alter table company_sale_details
    add primary key (Sales_id);

create table contact_us
(
    Author     varchar(30)  not null,
    Email      varchar(30)  not null,
    Contact_no int(15)      not null,
    Subject    varchar(50)  not null,
    Message    varchar(255) not null,
    Contact_id int auto_increment,
    constraint contact_id_UNIQUE
        unique (Contact_id)
);

alter table contact_us
    add primary key (Contact_id);

create table item_details
(
    Item_id      int auto_increment
        primary key,
    Item_name    varchar(30) not null,
    Item_rate    float       not null,
    Item_measure varchar(20) not null,
    Item_image   blob        null
);

create table login_info
(
    Username varchar(50)   not null,
    Role     int default 0 not null,
    password char(32)      not null,
    constraint login_info_Username_uindex
        unique (Username)
)
    comment 'role 0 - user 1 - pickuPerson 2 - admin';

alter table login_info
    add primary key (Username);

create table admin
(
    name     varchar(20) not null,
    admin_id int         not null,
    Username varchar(50) not null,
    constraint admin_admin_id_uindex
        unique (admin_id),
    constraint admin___fk0
        foreign key (Username) references login_info (Username)
);

create index admin___fk1
    on admin (Username);

alter table admin
    add primary key (admin_id);

create table pickup_person_details
(
    Name                 varchar(50)                   not null,
    Contact_no           int(15)                       not null,
    Aadhar_no            int(12)                       not null,
    Pickup_person_id     int auto_increment,
    Salary               float                         not null,
    Rating               int(5)      default 0         null,
    Pickup_person_status varchar(25) default 'working' null,
    Username             varchar(50)                   not null,
    constraint pickup_person_details_Pickup_person_id_uindex
        unique (Pickup_person_id),
    constraint pickup_person_details_Username_uindex
        unique (Username),
    constraint pickup_person_details___fk0
        foreign key (Username) references login_info (Username)
);

alter table pickup_person_details
    add primary key (Pickup_person_id);

create table pickup_person_record
(
    Pickup_person_id  int               not null,
    Date              timestamp         not null,
    `10:00AM-12:00PM` tinyint default 1 null,
    `12:00PM-02:00PM` tinyint default 1 null,
    `02:00PM-04:00PM` tinyint default 1 null,
    `04:00PM-06:00PM` tinyint default 1 null,
    constraint pickup_person_record___fk0
        foreign key (Pickup_person_id) references pickup_person_details (Pickup_person_id)
);

create index pickup_person_record_fk0
    on pickup_person_record (Pickup_person_id);

create table user_details
(
    Name       varchar(50)                 not null,
    Address    varchar(100) default 'NULL' null,
    Contact_no varchar(15)                 not null,
    email_id   varchar(50)                 not null,
    User_id    int auto_increment,
    Username   varchar(50)                 not null,
    constraint user_details_User_id_uindex
        unique (User_id),
    constraint user_details_Username_uindex
        unique (Username),
    constraint user_details___fk0
        foreign key (Username) references login_info (Username)
);

alter table user_details
    add primary key (User_id);

create table booking_details
(
    User_id                    int         not null,
    Booking_date_time          datetime    not null,
    Scheduled_pickup_date_time datetime    not null,
    Pickup_date_time           datetime    null,
    Booking_id                 int auto_increment,
    Pickup_person_id           int         not null,
    Pickup_status              varchar(20) not null,
    Pickup_person_rating       int(5)      null,
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

create table booked_item_list
(
    Item_id          int       not null,
    Booking_id       int       not null,
    Item_qty         float     not null,
    Item_amount      float     not null,
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

create table order_cancel
(
    Booking_id      int          not null,
    Cancellation_id int auto_increment,
    Feedback        varchar(255) null,
    constraint order_cancel_Cancellation_id_uindex
        unique (Cancellation_id),
    constraint order_cancel___fk0
        foreign key (Booking_id) references booking_details (Booking_id)
);

alter table order_cancel
    add primary key (Cancellation_id);

create table payment_details
(
    Payment_id     int                           not null
        primary key,
    Payment_amount float                         not null,
    Booking_id     int                           not null,
    Payment_mode   varchar(20) default 'online'  null,
    Payment_status varchar(20) default 'pending' null,
    constraint payment_details___fk0
        foreign key (Booking_id) references booking_details (Booking_id)
);

create index payment_details_fk0
    on payment_details (Booking_id);


