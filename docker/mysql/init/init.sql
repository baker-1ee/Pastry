create table Users (
    user_seq bigint not null comment '사용자 순번' primary key,
    name varchar(50) comment '사용자 이름'
);