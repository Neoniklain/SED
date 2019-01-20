ALTER TABLE un_specialty add column institute_id bigint;
alter table un_specialty add constraint FKg34j750bbuanm141cph3qtr6r foreign key (institute_id) references un_institute;