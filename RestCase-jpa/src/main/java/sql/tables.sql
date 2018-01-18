--CREATE SCHEMA rolo;
drop index IF EXISTS urro_unq;
drop index IF EXISTS roles_unq;
drop index IF EXISTS users_unq;
drop TABLE IF EXISTS rolo.urro;
drop TABLE IF EXISTS rolo.roles;
drop TABLE IF EXISTS rolo.users;

CREATE TABLE rolo.users (
  id SERIAL PRIMARY KEY,
  name varchar(30) NOT NULL,
  pass varchar(100) NOT NULL,
  mail varchar(50)
);
CREATE TABLE rolo.roles (
  id SERIAL PRIMARY KEY,
  code varchar(30) NOT NULL
 );
 CREATE TABLE rolo.urro (
  user_id INTEGER NOT NULL references rolo.users(id),
  role_id INTEGER NOT NULL references rolo.roles(id)
 );

 CREATE UNIQUE INDEX urro_unq ON rolo.urro (user_id,role_id);
 CREATE UNIQUE INDEX roles_unq ON rolo.roles (code);
 CREATE UNIQUE INDEX users_unq ON rolo.users (name);
---------
insert into rolo.roles (code) values ('admin');
insert into rolo.roles (code) values ('any');
--insert into rolo.users (name,pass) values ('admin','admin');
--insert into rolo.urro ( role_id,user_id) values ( (select id from rolo.roles where code='admin'),(select id from rolo.users where name='admin'));

INSERT INTO "rolo"."users" (name,pass,mail) VALUES ('alex','9uCh4qxBlFqap/+KiqoM68EqO8yYGpKa1c+BCgkOEa4=',null);
INSERT INTO "rolo"."users" (name,pass,mail) VALUES ('al2','m4cVEjJ8Cc6R3WSbP5amO3QI7yZ8jMVxARTmKXMMth8=',null);
INSERT INTO "rolo"."users" (name,pass,mail) VALUES ('www','9uCh4qxBlFqap/+KiqoM68EqO8yYGpKa1c+BCgkOEa4=',null);
INSERT INTO "rolo"."users" (name,pass,mail) VALUES ('admin','jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=',null);

INSERT INTO "rolo"."urro" (user_id,role_id) VALUES (2,3);
INSERT INTO "rolo"."urro" (user_id,role_id) VALUES (5,4);
INSERT INTO "rolo"."urro" (user_id,role_id) VALUES (6,4);