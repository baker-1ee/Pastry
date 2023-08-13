create table Users (
    user_seq bigint not null comment '사용자 순번' primary key,
    first_name varchar(50) not null comment '이름',
    last_name varchar(50) not null comment '성',
    password varchar(50) not null comment '비밀번호',
    created_by bigint not null comment '생성자',
    updated_by bigint not null comment '수정자',
    created_datetime datetime not null comment '생성일시',
    updated_datetime datetime not null comment '수정일시'
);
