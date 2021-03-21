CREATE TABLE IF NOT EXISTS sport
(
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Horse
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)            NOT NULL,
    description VARCHAR(500)            NULL,
    gender      ENUM('female', 'male') NOT NULL,
    birthday    DATE                    NOT NULL,
    sport       BIGINT                  NULL,
    CONSTRAINT fk_sportId FOREIGN KEY (sport) REFERENCES sport(id) ON DELETE SET NULL
);