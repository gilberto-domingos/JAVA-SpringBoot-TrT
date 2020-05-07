create table service_order(
    id bigint not null auto_increment,
    client_id bigint not null,
    description text not null,
    price decimal(10,2) not null,
    status varchar(20) not null,
    date_opened datetime not null,
    date_closed datetime,    
                               primary key(id) 
);

alter table service_order add constraint fk_service_order_client
foreign key (client_id) references client (id);

