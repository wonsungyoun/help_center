DROP TABLE IF EXISTS COUNSELOR;

CREATE TABLE counselor (
    id varchar(10),
    password varchar(10),
    user_name varchar(5),
    primary key (id)

);


DROP TABLE IF EXISTS COUNSEL;

CREATE TABLE COUNSEL (
    NO BIGINT AUTO_INCREMENT,
    create_id varchar(10) not null,
    counselor_id varchar(10),
    questions_title varchar(100) not null,
    questions_detail text not null,
    answer text,
    reg_date timestamp not null,
    answer_date timestamp,
    primary key (NO)
);


DROP TABLE IF EXISTS COUNSEL_WATING;