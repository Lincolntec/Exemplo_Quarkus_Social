-- CREATE TABLE QUARKUS-SOCIAL
create table USERS(
	int bigserial not null primary key,
	name varchar(100) not null,
	age integer not null
)