DROP DATABASE IF EXISTS quiz_db;
CREATE DATABASE quiz_db;

USE quiz_db;

# Table for user accounts
CREATE TABLE IF NOT EXISTS account (
  id              INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username        VARCHAR(64) NOT NULL UNIQUE,
  hashed_password CHAR(64)    NOT NULL,
  email_address   VARCHAR(254),
  first_name      NVARCHAR(32),
  last_name       NVARCHAR(32)
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

# Table for admin accounts
CREATE TABLE IF NOT EXISTS admin (
  account_id INT,
  FOREIGN KEY (account_id) REFERENCES account (id)
    ON DELETE CASCADE
);

# Table for quizzes
CREATE TABLE IF NOT EXISTS quiz (
  id                      INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_id              INT,
  date_created            DATETIME     DEFAULT now(),
  name                    NVARCHAR(256),
  description             TEXT,
  has_random_order        BOOL         DEFAULT FALSE,
  is_multiple_page        BOOL         DEFAULT FALSE,
  is_immediate_correction BOOL         DEFAULT FALSE
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

# Table for questions types (is supposed to have mostly fixed size of rows during lifetime)
CREATE TABLE IF NOT EXISTS question_type (
  id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  type NVARCHAR(64)
);

# Table for questions linked to quiz
CREATE TABLE IF NOT EXISTS question (
  id             INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quiz_id        INT,
  type_id        INT,
  question_text  TEXT,
  question_index INT,
  FOREIGN KEY (quiz_id) REFERENCES quiz (id)
    ON DELETE CASCADE,
  FOREIGN KEY (type_id) REFERENCES question_type (id)
    ON DELETE CASCADE
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

# Table for answers linked to question
CREATE TABLE IF NOT EXISTS answer (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  question_id INT,
  answer      TEXT,
  is_correct  BOOL         DEFAULT TRUE,
  FOREIGN KEY (question_id) REFERENCES question (id)
    ON DELETE CASCADE
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

# Table representing users' scoreboard
CREATE TABLE IF NOT EXISTS quiz_result (
  id                 INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quiz_id            INT,
  account_id         INT,
  score              INT,
  time_taken         TIME,
  result_submit_date DATETIME,
  FOREIGN KEY (quiz_id) REFERENCES quiz (id)
    ON DELETE CASCADE,
  FOREIGN KEY (account_id) REFERENCES account (id)
    ON DELETE CASCADE
);

# Table representing friendship data
CREATE TABLE IF NOT EXISTS friend (
  account_id_1 INT,
  account_id_2 INT,
  FOREIGN KEY (account_id_1) REFERENCES account (id)
    ON DELETE CASCADE,
  FOREIGN KEY (account_id_2) REFERENCES account (id)
    ON DELETE CASCADE
);

# Table for achievement types (is supposed to have mostly fixed size of rows during lifetime)
CREATE TABLE IF NOT EXISTS achievement_type (
  id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name TEXT
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

# Table for users' achievements
CREATE TABLE IF NOT EXISTS achievement (
  account_id  INT,
  type_id     INT,
  unlock_date DATETIME,
  FOREIGN KEY (account_id) REFERENCES account (id)
    ON DELETE CASCADE
);

# Table holding friend requests
CREATE TABLE IF NOT EXISTS friend_request (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  sender_id   INT,
  receiver_id INT,
  send_date   DATETIME,
  FOREIGN KEY (sender_id) REFERENCES account (id)
    ON DELETE CASCADE,
  FOREIGN KEY (receiver_id) REFERENCES account (id)
    ON DELETE CASCADE
);

# Table holding challenge requests
CREATE TABLE IF NOT EXISTS challenge_request (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  sender_id   INT,
  receiver_id INT,
  send_date   DATETIME,
  quiz_id     INT,
  FOREIGN KEY (sender_id) REFERENCES account (id)
    ON DELETE CASCADE,
  FOREIGN KEY (receiver_id) REFERENCES account (id)
    ON DELETE CASCADE,
  FOREIGN KEY (quiz_id) REFERENCES quiz (id)
    ON DELETE CASCADE
);

# Table holding messages
CREATE TABLE IF NOT EXISTS message (
  id           INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  sender_id    INT,
  receiver_id  INT,
  send_date    DATETIME,
  message_text TEXT,
  is_read      BOOLEAN      DEFAULT FALSE,
  FOREIGN KEY (sender_id) REFERENCES account (id)
    ON DELETE CASCADE,
  FOREIGN KEY (receiver_id) REFERENCES account (id)
    ON DELETE CASCADE
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

# Table holding admin's announcements
CREATE TABLE IF NOT EXISTS announcement (
  id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_id        INT,
  announcement_text TEXT,
  post_date         DATETIME
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

# Inserting expected values for question types
INSERT INTO question_type (type) VALUES ("Question-Response");
INSERT INTO question_type (type) VALUES ("Fill in the Blank");
INSERT INTO question_type (type) VALUES ("Multiple Choice");
INSERT INTO question_type (type) VALUES ("Picture-Response");

# Inserting expected values for achievement types
INSERT INTO achievement_type (name) VALUES ("Amateur Author");
INSERT INTO achievement_type (name) VALUES ("Prolific Author");
INSERT INTO achievement_type (name) VALUES ("Prodigious Author");
INSERT INTO achievement_type (name) VALUES ("Quiz Machine");
INSERT INTO achievement_type (name) VALUES ("I am the Greatest");
INSERT INTO achievement_type (name) VALUES ("Practice Makes Perfect");

INSERT INTO account (username, hashed_password, email_address, first_name, last_name) Values ('nini', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'winini
eri@yahoo.com', 'nino', 'mumladze');
INSERT INTO admin (account_id) values (LAST_INSERT_ID());