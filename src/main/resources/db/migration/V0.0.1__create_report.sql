create table report
(
    id         int auto_increment,
    note       varchar(256)           null,
    latitude   DOUBLE                 not null,
    longitude  DOUBLE                 not null,
    created_at DATETIME default NOW() not null,
    constraint report_pk
        primary key (id)
);