create table items (
    item_id bigserial primary key,
    item_name varchar(255) not null,
    user_id bigint not null references users(user_id),
    item_price varchar(50) not null,
    UNIQUE (item_name,user_id)
);
