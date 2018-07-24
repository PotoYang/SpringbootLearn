CREATE TABLE user_info (
  user_id   INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(255) NOT NULL,
  password  VARCHAR(255) NOT NULL,
  phone     VARCHAR(255) NOT NULL
)
  ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE excel_info (
  program_set_id   INT,
  program_set_name VARCHAR(255),
  program_id       INT,
  program_name     VARCHAR(255),
  period_number    INT,
  path             VARCHAR(255)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;