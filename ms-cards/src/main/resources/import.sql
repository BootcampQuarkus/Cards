-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
insert into cardtype (id, name, createdat) values(nextval('hibernate_sequence'), 'Credit','2023.01.01');
insert into cardtype (id, name, createdat) values(nextval('hibernate_sequence'), 'Debit','2023.01.01');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');