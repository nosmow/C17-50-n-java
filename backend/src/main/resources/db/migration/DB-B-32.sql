CREATE TABLE `companies`
(
    `id`       INT UNSIGNED                                      NOT NULL AUTO_INCREMENT,
    `user_id`  INT UNSIGNED                                      NOT NULL,
    `company`  VARCHAR(100)                                      NOT NULL,
    `cuit`     VARCHAR(11)                                       NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);