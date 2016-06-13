DROP DATABASE IF EXISTS quiz_db;
CREATE DATABASE quiz_db;

USE quiz_db;

CREATE TABLE IF NOT EXISTS account (
  id              INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username        CHAR(64)     NOT NULL UNIQUE,
  hashed_password CHAR(64)     NOT NULL UNIQUE,
  email_address   VARCHAR(254) NOT NULL,
  nickname        NCHAR(32),
  first_name      NCHAR(32),
  last_name       NCHAR(32),
  birthdate       DATE,
  gender          CHAR(8),
  avatar_filename VARCHAR(128)
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS admin (
  user_id INT,
  FOREIGN KEY (user_id) REFERENCES account (id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS quiz (
  id                      INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id                 INT,
  create_time             DATETIME,
  description             TEXT,
  is_multiple_page        BOOL         DEFAULT FALSE,
  is_immediate_correction BOOL         DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS question (
  id                  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quiz_id             INT,
  is_picture_response BOOLEAN      DEFAULT FALSE,
  question_text       TEXT,
  question_index      INT,
  FOREIGN KEY (quiz_id) REFERENCES quiz (id)
    ON DELETE CASCADE
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS answer (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  question_id INT,
  answer_text TEXT,
  is_correct  BOOL         DEFAULT FALSE,
  FOREIGN KEY (question_id) REFERENCES question (id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS score (
  id           INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quiz_id      INT,
  account_id   INT,
  time_taken   TIME,
  time_started DATETIME,
  FOREIGN KEY (quiz_id) REFERENCES quiz (id)
    ON DELETE CASCADE,
  FOREIGN KEY (account_id) REFERENCES account (id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS friend (
  account_id_1 INT,
  account_id_2 INT,
  FOREIGN KEY (account_id_1) REFERENCES account (id)
    ON DELETE CASCADE,
  FOREIGN KEY (account_id_2) REFERENCES account (id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS achievement_type (
  id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name TEXT
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS achievement (
  account_id  INT,
  type_id     INT,
  unlock_time DATETIME,
  FOREIGN KEY (account_id) REFERENCES account (id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS friend_request (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  sender_id   INT,
  receiver_id INT,
  send_time   DATETIME,
  FOREIGN KEY (sender_id) REFERENCES account (id)
    ON DELETE CASCADE,
  FOREIGN KEY (receiver_id) REFERENCES account (id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS challenge_request (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  sender_id   INT,
  receiver_id INT,
  send_time   DATETIME,
  quiz_id     INT,
  FOREIGN KEY (sender_id) REFERENCES account (id)
    ON DELETE CASCADE,
  FOREIGN KEY (receiver_id) REFERENCES account (id)
    ON DELETE CASCADE,
  FOREIGN KEY (quiz_id) REFERENCES quiz (id)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS message (
  id           INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  sender_id    INT,
  receiver_id  INT,
  send_time    DATETIME,
  message_text TEXT,
  FOREIGN KEY (sender_id) REFERENCES account (id)
    ON DELETE CASCADE,
  FOREIGN KEY (receiver_id) REFERENCES account (id)
    ON DELETE CASCADE
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS announcements (
  id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_id        INT,
  announcement_text TEXT,
  post_time         DATETIME
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;






