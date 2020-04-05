CREATE TABLE account
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    balance DOUBLE NOT NULL
);

CREATE TABLE account_history
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    amount           DOUBLE      NOT NULL,
    transaction_type VARCHAR(64) NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    account_id       INTEGER     NOT NULL,
    CONSTRAINT FK_accounts_history
        FOREIGN KEY (account_id) REFERENCES account (id)
);

