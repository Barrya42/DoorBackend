insert into roles (name) VALUES ('ADMIN')
insert into roles (name) VALUES ('OPERATOR')
insert into roles (name) VALUES ('DOOR')



insert into users(enabled,name,password,phone,username) VALUES (true ,'developer','$2a$05$uu0x3hIf6LiWgWTZx080Pup.1S.nQx5Zv4jM/bPJqgTsHvakDpg7e','79130744802','User1')
insert into users(enabled,name,password,phone,username) values (true ,'operator','$2a$05$uu0x3hIf6LiWgWTZx080Pup.1S.nQx5Zv4jM/bPJqgTsHvakDpg7e','79963326612','User2')

insert into users_roles values (1,1)
insert into users_roles values (1,2)

insert  into guests(enabled,name,phone) values(true,'Степан','79130744802')
insert  into guests(enabled,name,phone) values(true,'Степан2','79130744803')
insert  into guests(enabled,name,phone) values(true,'Иван','79963326612')

insert  into doors(adress,balance,is_open,mac,phone) values('any adress',125.44,false ,'mac adress','79236181237')

insert into guests_accessed_doors values (1,1)