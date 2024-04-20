CREATE TABLE `categories_transactions`
(
    `id`                INT UNSIGNED                                      NOT NULL AUTO_INCREMENT,
    `transaction_id`    INT UNSIGNED                                      NOT NULL,
    `category_id`       INT UNSIGNED                                      NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`transaction_id`) REFERENCES `transactions`(`id`),
    FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`)
);