insert into roles (id,name) VALUES (1,'ADMIN')
insert into roles (id,name) VALUES (2,'OPERATOR')
insert into roles (id,name) VALUES (3,'DOOR')

insert into users VALUES (1, true ,'developer','$2a$05$uu0x3hIf6LiWgWTZx080Pup.1S.nQx5Zv4jM/bPJqgTsHvakDpg7e','79130744802','User1')
insert into users values (2, true ,'operator','$2a$05$uu0x3hIf6LiWgWTZx080Pup.1S.nQx5Zv4jM/bPJqgTsHvakDpg7e','79963326612','User2')

insert into users_roles values (1,1)
insert into users_roles values (1,2)

insert  into guests values(1,true,'Степан','79130744802')
insert  into guests values(2,true,'Степан2','79130744803')
insert  into guests values(3,true,'Иван','79963326612')

insert  into doors values(1,'any adress',125.44,false ,'mac adress','79236181237')

insert into guests_accessed_doors values (1,1)