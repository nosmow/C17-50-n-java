CREATE TABLE `users`
(
    `id`       INT UNSIGNED                                      NOT NULL AUTO_INCREMENT,
    `role`     ENUM ('ROLE_ADMIN', 'ROLE_USER', 'ROLE_BUSINESS') NOT NULL,
    `email`    VARCHAR(255)                                      NOT NULL UNIQUE,
    `phone`    INT                                               NULL,
    `country`  VARCHAR(100)                                      NOT NULL,
    `password` VARCHAR(255)                                      NOT NULL,
    `active`   TINYINT(1)                                        NOT NULL,
    PRIMARY KEY (`id`)
);