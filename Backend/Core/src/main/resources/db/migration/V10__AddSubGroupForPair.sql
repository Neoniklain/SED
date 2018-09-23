alter table un_pair add column subgroup int;
alter table un_pair add column flow boolean;

UPDATE  un_pair set subgroup = 0 where subgroup is null;
UPDATE  un_pair set flow = 'FALSE' where flow is null;