CREATE TABLE `people`
(
    `id`       INT UNSIGNED                                      NOT NULL AUTO_INCREMENT,
    `user_id`  INT UNSIGNED                                      NOT NULL,
    `name`     VARCHAR(100)                                      NOT NULL,
    `lastname` VARCHAR(100)                                      NOT NULL,
    `dni`      VARCHAR(9)                                        NOT NULL UNIQUE,
    `birthdate`DATE                                              NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);