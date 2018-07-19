create table invoice (
    invoice_id bigserial primary key,
    user_id bigint not null ,
    total_amount varchar(50) not null
);
