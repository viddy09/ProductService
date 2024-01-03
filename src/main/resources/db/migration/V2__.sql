CREATE TABLE something
(
    id                 BIGINT NOT NULL,
    created_date       datetime NULL,
    last_modified_date datetime NULL,
    is_deleted         BIT(1) NOT NULL,
    foo                INT    NOT NULL,
    CONSTRAINT pk_something PRIMARY KEY (id)
);

DROP TABLE category_seq;

DROP TABLE jt_user_seq;

DROP TABLE msc_instructor_seq;

DROP TABLE msc_mentor_seq;

DROP TABLE msc_ta_seq;

DROP TABLE product_seq;

DROP TABLE sc_user_seq;

DROP TABLE tpc_user_seq;

ALTER TABLE sc_user
    MODIFY rating INT NOT NULL;

ALTER TABLE sc_user
    MODIFY user_type INT NULL;