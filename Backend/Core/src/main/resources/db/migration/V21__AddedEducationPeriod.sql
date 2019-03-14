create sequence education_period_sequence_gen start 1 increment 1;
create table un_education_period (id int8 not null, start_date timestamp, speciality_id int8, end_date timestamp, primary key (id));
alter table un_education_period add constraint FKg34j750bbuanm131cph3qtr14 foreign key (speciality_id) references un_speciality;
INSERT INTO un_education_period(id, start_date, speciality_id, end_date) VALUES (1, '2018-08-01 12:00:00', 1, '2018-11-28 12:00:00');

ALTER TABLE un_lesson add column education_period_id int8;
alter table un_lesson add constraint FKg34j750bbuanm1313143qtr6r foreign key (education_period_id) references un_education_period;
UPDATE un_lesson SET education_period_id = 1;

UPDATE un_speciality SET institute_id = 1;
UPDATE un_speciality SET code = '01.01.01';
UPDATE un_group SET speciality_id = 1;