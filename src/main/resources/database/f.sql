select *
from subscription
         inner join league l on l.id = subscription.league_id
         inner join translation t on l.id = t.league_id
         inner join commentator c on t.commentator_id = c.id
where subscription.user_id = 1;


insert into league (name, country)
values ('НБА', 'США');

insert into league (name, country)
values ('Евролига', 'Европа');

insert into league (name, country)
values ('Лига ВТБ', 'Россия');

insert into team (league_id, name)
values ('1', 'Чикаго Буллз'),
       ('1', 'Детройт Пистонс'),
       ('1', 'Орландо Меджик'),
       ('1', 'Минесота Тимбурвулвс'),
       ('2', 'Реал'),
       ('2', 'Монако'),
       ('2', 'Альба'),
       ('2', 'Милан'),
       ('3', 'Автодор'),
       ('3', 'Уникс'),
       ('3', 'Зенит'),
       ('3', 'МБА');


alter table users
    add column role_id INT;

insert into dict_role (name)
values ('Администратор'),
       ('Пользователь'),
       ('Комментатор');