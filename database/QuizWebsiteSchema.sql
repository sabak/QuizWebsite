DROP DATABASE IF EXISTS quiz_db;
CREATE DATABASE quiz_db;

USE quiz_db;

# Table for user accounts
CREATE TABLE IF NOT EXISTS account (
  id              INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username        VARCHAR(64)  NOT NULL UNIQUE,
  hashed_password CHAR(64)     NOT NULL,
  email_address   VARCHAR(254) NOT NULL,
  first_name      NVARCHAR(32),
  last_name       NVARCHAR(32),
  birthdate       DATE
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

# Table for admin accounts
CREATE TABLE IF NOT EXISTS admin (
  user_id INT,
  FOREIGN KEY (user_id) REFERENCES account (id)
    ON DELETE CASCADE
);

# Table for quizzes
CREATE TABLE IF NOT EXISTS quiz (
  id                      INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id                 INT,
  create_date             DATETIME,
  description             TEXT,
  is_multiple_page        BOOL         DEFAULT FALSE,
  is_immediate_correction BOOL         DEFAULT FALSE
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;
;

# Table for questions linked to quizz
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

# Table for answers linked to question
CREATE TABLE IF NOT EXISTS answer (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  question_id INT,
  answer_text TEXT,
  is_correct  BOOL         DEFAULT TRUE,
  FOREIGN KEY (question_id) REFERENCES question (id)
    ON DELETE CASCADE
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;
;

# Table representing users' scoreboard
CREATE TABLE IF NOT EXISTS score (
  id             INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quiz_id        INT,
  account_id     INT,
  time_taken     TIME,
  quiz_take_date DATETIME,
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

# Table for achievement types (will fixed size)
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
  FOREIGN KEY (sender_id) REFERENCES account (id)
    ON DELETE CASCADE,
  FOREIGN KEY (receiver_id) REFERENCES account (id)
    ON DELETE CASCADE
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

# Table holding admin's announcements
CREATE TABLE IF NOT EXISTS announcements (
  id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_id        INT,
  announcement_text TEXT,
  post_date         DATETIME
)
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

INSERT INTO achievement_type (name) VALUES ("Amateur Author");
INSERT INTO achievement_type (name) VALUES ("Prolific Author");
INSERT INTO achievement_type (name) VALUES ("Prodigious Author");
INSERT INTO achievement_type (name) VALUES ("Quiz Machine");
INSERT INTO achievement_type (name) VALUES ("I am the Greatest");
INSERT INTO achievement_type (name) VALUES ("Practice Makes Perfect");
