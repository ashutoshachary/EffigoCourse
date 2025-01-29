insert into user_details(id, user_name, birth_date) values (10001, 'Ashu', current_date());

insert into user_details(id, user_name, birth_date) values (10002, 'Raghu', current_date());

insert into user_details(id, user_name, birth_date) values (10003, 'Ram', current_date());

insert into post(id,description,user_id) values(20001,'I want to learn AWS',10001);
insert into post(id,description,user_id) values(20002,'I want to learn Dance',10001);

insert into post(id,description,user_id) values(20003,'I want to learn Java',10002);
insert into post(id,description,user_id) values(20004,'I want to learn Mearn',10002);

insert into post(id,description,user_id) values(20005,'I want to learn DevOps',10003);


insert into todo(id, username, description, target_date, done) values (10001, 'ashutosh','learn aws with me', current_date(), false);


