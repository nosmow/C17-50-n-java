CREATE TABLE `accounts`
(
    `id`       INT UNSIGNED                                      NOT NULL AUTO_INCREMENT,
    `user_id`  INT UNSIGNED                                      NOT NULL,
    `number`   VARCHAR(11)                                       NOT NULL UNIQUE,
    `balance` DECIMAL                                            NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);