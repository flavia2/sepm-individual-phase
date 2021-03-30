CREATE TABLE IF NOT EXISTS sport
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500) NULL
);

CREATE TABLE IF NOT EXISTS Horse
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)           NOT NULL,
    description VARCHAR(500)           NULL,
    birthday    DATE                   NOT NULL,
    gender      ENUM('female', 'male') NOT NULL,
    sport       BIGINT                 NULL,
    mother   BIGINT                 NULl,
    father   BIGINT                 NULL,
    CONSTRAINT fk_sportId FOREIGN KEY (sport) REFERENCES sport (id) ON DELETE SET NULL,
    CONSTRAINT fk_parentId1 FOREIGN KEY (mother) REFERENCES horse (id) ON DELETE SET NULL,
    CONSTRAINT fk_parentId2 FOREIGN KEY (father) REFERENCES horse (id) ON DELETE SET NULl
);