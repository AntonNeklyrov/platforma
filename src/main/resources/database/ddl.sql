create table translation
(
    id             BIGSERIAL   not null,
    user_id        INT         not null,
    league_id      INT         not null,
    commentator_id integer     not null,
    guest_team     varchar(50) not null,
    home_team      varchar(50) not null,
    start_date     date        not null,
    start_time     time        not null,
    constraint PK_TRANSLATION primary key (id),
    foreign key (user_id) references users (id),
    foreign key (commentator_id) references commentator (id),
    foreign key (league_id) references league (id)
);

create table subscription
(
    id        BIGSERIAL not null,
    user_id   INT4      not null,
    league_id INT       not null,
    cost      FLOAT     not null,
    period    INT4      not null,
    date      date      not null,
    constraint PK_SUBSCRIPTION primary key (id),
    foreign key (user_id) references users (id),
    foreign key (league_id) references league (id)
);

create table league
(
    id      INT          not null,
    name    varchar(100) not null,
    country varchar(50)  null,
    constraint PK_LEAGUE primary key (id)
);


create table commentator
(
    id         INT4         not null,
    first_name varchar(100) not null,
    last_name  varchar(100) not null,
    email      varchar(100) null,
    constraint PK_COMMENTATOR primary key (id)
);

create table users
(
    id          INT4         not null,
    first_name  varchar(100) not null,
    last_name   varchar(100) not null,
    card_number varchar(100) not null,
    email       varchar(100) null,
    constraint PK_USER primary key (id)
);





