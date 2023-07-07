--liquibase formatted sql

-- changeset dkovachev:1
create index students_name_index on students (name);

-- changeset dkovachev:2
create index faculties_name_and_color_index on faculties (name, color);
