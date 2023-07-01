alter table students
    add constraint age_check check ( age > 15 );

alter table students
    add constraint name_unique unique (name);

alter table students
    alter column name set not null;

alter table faculties
    add constraint name_color_unique unique (name, color);

alter table students
    alter column age set default 20;


