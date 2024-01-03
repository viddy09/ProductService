CREATE TABLE category
(
    id                 BIGINT NOT NULL,
    created_date       datetime NULL,
    last_modified_date datetime NULL,
    is_deleted         BIT(1) NOT NULL,
    name               VARCHAR(255) NULL,
    `description`      VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE jt_instructor
(
    user_id BIGINT NOT NULL,
    company VARCHAR(255) NULL,
    CONSTRAINT pk_jt_instructor PRIMARY KEY (user_id)
);

CREATE TABLE jt_mentor
(
    user_id BIGINT NOT NULL,
    rating  INT    NOT NULL,
    CONSTRAINT pk_jt_mentor PRIMARY KEY (user_id)
);

CREATE TABLE jt_ta
(
    user_id   BIGINT NOT NULL,
    grad_year VARCHAR(255) NULL,
    CONSTRAINT pk_jt_ta PRIMARY KEY (user_id)
);

CREATE TABLE jt_user
(
    id        BIGINT NOT NULL,
    user_name VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    CONSTRAINT pk_jt_user PRIMARY KEY (id)
);

CREATE TABLE msc_instructor
(
    id        BIGINT NOT NULL,
    user_name VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    company   VARCHAR(255) NULL,
    CONSTRAINT pk_msc_instructor PRIMARY KEY (id)
);

CREATE TABLE msc_mentor
(
    id        BIGINT NOT NULL,
    user_name VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    rating    INT    NOT NULL,
    CONSTRAINT pk_msc_mentor PRIMARY KEY (id)
);

CREATE TABLE msc_ta
(
    id        BIGINT NOT NULL,
    user_name VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    grad_year VARCHAR(255) NULL,
    CONSTRAINT pk_msc_ta PRIMARY KEY (id)
);

CREATE TABLE product
(
    id                 BIGINT NOT NULL,
    created_date       datetime NULL,
    last_modified_date datetime NULL,
    is_deleted         BIT(1) NOT NULL,
    name               VARCHAR(255) NULL,
    title              VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    `description`      VARCHAR(255) NULL,
    category_id        BIGINT NULL,
    imageurl           VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE sc_user
(
    id        BIGINT NOT NULL,
    user_type INT NULL,
    user_name VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    grad_year VARCHAR(255) NULL,
    rating    INT    NOT NULL,
    company   VARCHAR(255) NULL,
    CONSTRAINT pk_sc_user PRIMARY KEY (id)
);

CREATE TABLE tpc_instructor
(
    id        BIGINT NOT NULL,
    user_name VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    company   VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_instructor PRIMARY KEY (id)
);

CREATE TABLE tpc_mentor
(
    id        BIGINT NOT NULL,
    user_name VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    rating    INT    NOT NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (id)
);

CREATE TABLE tpc_ta
(
    id        BIGINT NOT NULL,
    user_name VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    grad_year VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_ta PRIMARY KEY (id)
);

CREATE TABLE tpc_user
(
    id        BIGINT NOT NULL,
    user_name VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_user PRIMARY KEY (id)
);

ALTER TABLE jt_instructor
    ADD CONSTRAINT FK_JT_INSTRUCTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK_JT_MENTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE jt_ta
    ADD CONSTRAINT FK_JT_TA_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);