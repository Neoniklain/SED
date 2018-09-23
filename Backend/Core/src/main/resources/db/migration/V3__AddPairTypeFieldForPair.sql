create sequence pair_type_sequence_gen start 1 increment 1;
create table un_pair_type (id int8 not null, type varchar(255), primary key (id));

alter table un_pair_type add constraint UK_3kvpwngdwuioqjy01630qn2ij unique (type);

alter table un_pair add column pair_type_id int8;

alter table un_pair add constraint FK8vpwngdlqbp9a6c00rexdd77o foreign key (pair_type_id) references un_pair_type;

INSERT INTO un_pair_type (id, type) VALUES ((select nextval('pair_type_sequence_gen')), 'Лекция');
INSERT INTO un_pair_type (id, type) VALUES ((select nextval('pair_type_sequence_gen')), 'Практика');