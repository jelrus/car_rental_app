drop table if exists order_actions;
drop table if exists manager_actions;
drop table if exists order_car_passport;
drop table if exists user_orders;

drop table if exists actions;
drop table if exists orders;
drop table if exists passports;
drop table if exists cars;
drop table if exists users;

create table users(
                      id bigint auto_increment primary key,
                      created datetime(6) not null,
                      updated datetime(6) not null,
                      username nvarchar(255) unique not null,
                      password nvarchar(255) not null,
                      first_name nvarchar(255),
                      last_name nvarchar(255),
                      profile_pic nvarchar(255),
                      balance decimal(12,2),
                      description text,
                      enabled boolean,
                      role_type enum('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER') not null
                   );

create table cars(
                     id bigint auto_increment primary key,
                     created datetime(6) not null,
                     updated datetime(6) not null,
                     title nvarchar(255),
                     product_pic nvarchar(255),
                     brand enum('BMW', 'MERCEDES', 'HONDA', 'TOYOTA'),
                     quality enum('MPV', 'LUXURY', 'SPORTS', 'SUV'),
                     info text,
                     rental_price decimal(12,2),
                     enabled boolean
);

create table passports(
                          id bigint auto_increment primary key,
                          created datetime(6) not null,
                          updated datetime(6) not null,
                          first_name nvarchar(255),
                          last_name nvarchar(255),
                          birth_date datetime(6),
                          country nvarchar(255),
                          zip_code nvarchar(255),
                          region nvarchar(255),
                          city nvarchar(255),
                          street nvarchar(255),
                          building nvarchar(255),
                          phone_number nvarchar(255),
                          email nvarchar(255)
);

create table orders(
                       id bigint auto_increment primary key,
                       created datetime(6) not null,
                       updated datetime(6) not null,
                       with_driver boolean,
                       start datetime(6),
                       end datetime(6),
                       order_status enum('PROCESSING', 'APPROVED', 'REJECTED', 'ACTIVE', 'CAR_INSPECTION',
                                         'DAMAGE_REFUND', 'CLOSED'),
                       enabled boolean
);

create table actions(
                        id bigint auto_increment primary key,
                        created datetime(6) not null,
                        updated datetime(6) not null,
                        identifier nvarchar(255),
                        message text,
                        enabled boolean
);

create table user_orders(
                            id bigint auto_increment primary key,
                            created datetime(6) not null,
                            updated datetime(6) not null,
                            user_id bigint not null,
                            order_id bigint not null,
                            UNIQUE KEY `uos` (`user_id`, `order_id`),
                            foreign key (user_id) references users(id) ON DELETE CASCADE,
                            foreign key (order_id) references orders(id) ON DELETE CASCADE
);

create table order_car_passport(
                          id bigint auto_increment primary key,
                          created datetime(6) not null,
                          updated datetime(6) not null,
                          order_id bigint not null unique,
                          car_id bigint not null,
                          passport_id bigint not null,
                          UNIQUE KEY `ocp` (`order_id`, `car_id`, `passport_id`),
                          foreign key (order_id) references orders(id) ON DELETE CASCADE,
                          foreign key (car_id) references cars(id) ON DELETE CASCADE,
                          foreign key (passport_id) references passports(id) ON DELETE CASCADE
);

create table order_actions(
                              id bigint auto_increment primary key not null,
                              created datetime(6) not null,
                              updated datetime(6) not null,
                              order_id bigint not null,
                              action_id bigint not null,
                              UNIQUE KEY `oas` (`order_id`, `action_id`),
                              foreign key (order_id) references orders(id) ON DELETE CASCADE,
                              foreign key (action_id) references actions(id) ON DELETE CASCADE
);

create table manager_actions(
                                id bigint auto_increment primary key not null,
                                created datetime(6) not null,
                                updated datetime(6) not null,
                                user_id bigint not null,
                                action_id bigint not null,
                                UNIQUE KEY `mas` (`user_id`, `action_id`),
                                foreign key (user_id) references users(id) ON DELETE CASCADE,
                                foreign key (action_id) references actions(id) ON DELETE CASCADE
);