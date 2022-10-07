

create table  users
(
    id               int auto_increment
        primary key,
    activation_date  datetime(6)  null,
    activation_token varchar(256) null,
    age              int          null,
    created          datetime(6)  null,
    email            varchar(255) null,
    image            varchar(255) null,
    name             varchar(255) null,
    non_locked       bit          null,
    password         varchar(255) null,
    role             varchar(255) null,
    surname          varchar(255) null,
    updated          datetime(6)  null
);

create table interests
(
    id      int auto_increment
        primary key,
    created datetime(6)  null,
    name    varchar(255) null
);



create table  articles
(
    id          int auto_increment
        primary key,
    content     varchar(255) null,
    created     datetime(6)  null,
    title       varchar(255) null,
    updated     datetime(6)  null,
    author_id   int          not null,
    interest_id int          not null,
    constraint FK7nx3l1opmxamusbl5t2b85ro0
        foreign key (interest_id) references interests (id),
    constraint FKe02fs2ut6qqoabfhj325wcjul
        foreign key (author_id) references users (id)
);

create table comments
(
    id         int auto_increment
        primary key,
    content    varchar(255) null,
    created    datetime(6)  null,
    created2    datetime(6)  null,
    updated    datetime(6)  null,
    article_id int          not null,
    author_id  int          not null,
    constraint FKk4ib6syde10dalk7r7xdl0m5p
        foreign key (article_id) references articles (id),
    constraint FKn2na60ukhs76ibtpt9burkm27
        foreign key (author_id) references users (id)
);


create table user_interests
(
    user_id     int not null,
    interest_id int not null,
    constraint FK8h2mk04o9ctuoqvntl29chtcm
        foreign key (interest_id) references interests (id),
    constraint FKdv9fflrh61wyuujfwx2yn1tb4
        foreign key (user_id) references users (id)
);

