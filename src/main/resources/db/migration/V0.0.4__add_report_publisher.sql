alter table wego.report
    add publisher_id VARCHAR(64) not null;

alter table wego.report
    add constraint report_user_null_fk
        foreign key (publisher_id) references wego.user (id);