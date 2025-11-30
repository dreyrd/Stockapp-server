create table stocks (
    stock_id integer not null auto_increment,
    enable bit,
    stock_name varchar(255),
    stock_photo varbinary(255),
    stock_symbol varchar(255) UNIQUE,
    primary key (stock_id)
);

create table users (
    user_id integer not null auto_increment,
    email varchar(255),
    enable bit,
    name varchar(255),
    password varchar(255),
    user_photo varbinary(255),
    primary key (user_id)
);