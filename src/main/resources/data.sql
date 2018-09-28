insert into roles (id,name) VALUES (1,'ADMIN')
insert into roles (id,name) VALUES (2,'OPERATOR')
insert into roles (id,name) VALUES (3,'DOOR')

insert into users VALUES (1, true ,'developer','$2a$05$uu0x3hIf6LiWgWTZx080Pup.1S.nQx5Zv4jM/bPJqgTsHvakDpg7e','79130744802','User1')
insert into users values (2, true ,'operator','$2a$05$uu0x3hIf6LiWgWTZx080Pup.1S.nQx5Zv4jM/bPJqgTsHvakDpg7e','79963326612','User2')

insert into users_roles(user_entity_id) values (1)
insert into users_roles(user_entity_id) values (2)

insert  into guests(enabled,name,phone) values(true,'Степан','79130744802')
insert  into guests(enabled,name,phone) values(true,'Степан2','79130744803')