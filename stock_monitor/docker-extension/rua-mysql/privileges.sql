use mysql;

select host, user from user;

create user stock identified by '123456';

grant all on stock.* to stock@'%' identified by '123456' with grant option;

flush privileges;

-- privileges.sql