create sequence speciality_sequence_gen start 1 increment 1;
create table un_speciality (id int8 not null, name varchar(255), department_id int8, institute_id int8, code varchar(255), primary key (id));

alter table un_speciality add constraint UK_3kvpwni13rvsqjy01630qn2ij unique (name);
alter table un_speciality add constraint FKg34j750bbuanm141cph3qtr6r foreign key (institute_id) references un_institute;
alter table un_speciality add constraint FKg34j750bbuanm131cph3qtr6r foreign key (department_id) references un_department;

alter table un_group DROP CONSTRAINT FKdg6ghbt5hjgch3617gaqa4u5a;
ALTER TABLE un_group add column speciality_id bigint;
alter table un_group add constraint FKg34j750bbuanm1313ph3qtr6r foreign key (speciality_id) references un_speciality;

INSERT INTO un_speciality(id, name, department_id) VALUES (1, 'ФИИТ', 1);
UPDATE un_group SET speciality_id = 1;
ALTER TABLE un_group DROP COLUMN department_id;