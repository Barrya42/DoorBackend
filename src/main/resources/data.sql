insert into roles (id,name) VALUES (1,'ADMIN')
insert into roles (id,name) VALUES (2,'OPERATOR')
insert into roles (id,name) VALUES (3,'DOOR')

insert into users VALUES (1,'developer','pass','+79130744802','user1')
insert into users values (2,'operator','pass','+79963326612','user2')

insert into users_roles values (1,1)
insert into users_roles values (1,2)

insert  into guests values(1,true,'Степан','+79130744802')