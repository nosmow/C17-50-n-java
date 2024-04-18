CREATE TABLE `transactions`
(
    `id`                  INT UNSIGNED                          NOT NULL AUTO_INCREMENT,
    `sender_account_id`   INT UNSIGNED                          NOT NULL,
    `receiver_account_id` INT UNSIGNED                          NOT NULL,
    `transaction_date`    DATE                                  NOT NULL,
    `amount`              DECIMAL                               NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`sender_account_id`) REFERENCES `accounts`(`id`),
    FOREIGN KEY (`receiver_account_id`) REFERENCES `accounts`(`id`)
);