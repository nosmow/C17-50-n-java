CREATE TABLE `categories`
(
    `id`      INT UNSIGNED                                      NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(100)                                      NOT NULL,
    `user_id` INT UNSIGNED                                      NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);