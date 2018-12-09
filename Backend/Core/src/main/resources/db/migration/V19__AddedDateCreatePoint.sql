alter table un_point add column date_of_create timestamp;
update public.un_point set date_of_create='2018-12-08 12:00:00';