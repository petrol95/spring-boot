# CREATE DATABASE IF NOT EXISTS geek_db;
# use geek_db;
#
# CREATE SCHEMA geek_db;

DROP TABLE IF EXISTS role;

CREATE TABLE role (
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(50) DEFAULT NULL,
    PRIMARY KEY(id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = latin1;

INSERT INTO role (name)
VALUES ('ROLE_EMPLOYEE'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id int(11) NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    password char(80) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    PRIMARY KEY(id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = latin1;

INSERT INTO user (username, password, first_name, last_name, email)
VALUES ('alex', '$2a$10$KGJX4fC8NS.JsDbcZjg2Be5PLS.Y3IA9qbvKKj3S2Rqjb4a9bjYZO', 'Alex', 'GeekBrains', 'alex@gb.com'),
       ('bob', '$2a$10$KGJX4fC8NS.JsDbcZjg2Be5PLS.Y3IA9qbvKKj3S2Rqjb4a9bjYZO', 'Bob', 'GeekBrains', 'bob@gb.com');

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
 user_id int(11) NOT NULL,
 role_id int(11) NOT NULL,
 PRIMARY KEY(user_id, role_id),
 KEY FK_ROLE_idx (role_id),
 CONSTRAINT FK_USER_05 FOREIGN KEY (user_id)
     REFERENCES user (id)
     ON DELETE NO ACTION ON UPDATE NO ACTION,
 CONSTRAINT FK_ROLE FOREIGN KEY (role_id)
     REFERENCES role (id)
     ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARSET = latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 2),
    (2, 3);

DROP TABLE IF EXISTS students;
CREATE TABLE students (
 id INT(11) NOT NULL AUTO_INCREMENT,
 name VARCHAR(100) DEFAULT NULL,
 last_name   VARCHAR(50) NOT NULL,
                          email       VARCHAR(50) NOT NULL,
                          phone       VARCHAR(50) NOT NULL,
                          insert_date DATE        NOT NULL,
                          principal   INT(11)     NOT NULL,
                          PRIMARY KEY (id),
                          CONSTRAINT FK_USER FOREIGN KEY (principal)
                              REFERENCES user (id)
                              ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE =InnoDB CHARSET = latin1;


DROP TABLE IF EXISTS courses;
CREATE TABLE courses (
                         id          INT(11) NOT NULL AUTO_INCREMENT,
                         title       VARCHAR(100)     DEFAULT NULL,
                         description VARCHAR(255)     DEFAULT NULL,
                         PRIMARY KEY (id)
) ENGINE =InnoDB CHARSET = latin1;

DROP TABLE IF EXISTS students_courses;
CREATE TABLE students_courses (
                                  student_id INT(11) NOT NULL,
                                  course_id  INT(11) NOT NULL,

                                  PRIMARY KEY (student_id, course_id),

                                  CONSTRAINT FK_STUDENT FOREIGN KEY (student_id)
                                      REFERENCES students (id)
                                      ON DELETE NO ACTION ON UPDATE NO ACTION,

                                  CONSTRAINT FK_COURSE FOREIGN KEY (course_id)
                                      REFERENCES courses (id)
                                      ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE =InnoDB CHARSET = latin1;
