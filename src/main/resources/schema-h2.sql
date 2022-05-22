CREATE TABLE IF NOT EXISTS user
(
    id         bigint       NOT NULL AUTO_INCREMENT,
    email      varchar(50)  NOT NULL UNIQUE,
    username   varchar(20)  NOT NULL UNIQUE,
    password   varchar(255) NOT NULL,
    bio        varchar(255)          DEFAULT NULL,
    image      varchar(255)          DEFAULT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at TIMESTAMP             DEFAULT NULL,
    deleted_at TIMESTAMP             DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS follow
(
    id          bigint NOT NULL AUTO_INCREMENT,
    follower_id bigint NOT NULL,
    followee_id bigint NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (follower_id, followee_id)
);

CREATE TABLE IF NOT EXISTS article
(
    id          bigint       NOT NULL AUTO_INCREMENT,
    user_id     bigint       NOT NULL,
    slug        varchar(50)  NOT NULL,
    title       varchar(50)  NOT NULL,
    description varchar(255) NOT NULL,
    body        text         NOT NULL,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at  TIMESTAMP             DEFAULT NULL,
    deleted_at  TIMESTAMP             DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_id, slug)
);

CREATE TABLE IF NOT EXISTS comment
(
    id         bigint    NOT NULL AUTO_INCREMENT,
    user_id    bigint    NOT NULL,
    article_id bigint    NOT NULL,
    body       text      NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at TIMESTAMP          DEFAULT NULL,
    deleted_at TIMESTAMP          DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS favorite
(
    id         bigint NOT NULL AUTO_INCREMENT,
    user_id    bigint NOT NULL,
    article_id bigint NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_id, article_id)
);

CREATE TABLE IF NOT EXISTS tag
(
    id   bigint      NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS article_tag
(
    article_id bigint NOT NULL,
    tag_id     bigint NOT NULL,
    PRIMARY KEY (article_id, tag_id)
);
