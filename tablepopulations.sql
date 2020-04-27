DELETE FROM users;
DELETE FROM accounts;
DELETE FROM users_to_accounts;

INSERT INTO
  users (last_update, user_name, password)
    VALUES (strftime('%s','now'), "zach", "abc123");

INSERT INTO
  accounts (account_num, routing_num, balance)
    VALUES(123456789,123456789,10000.0);

/* This insert statement should be handled programatically when a new account is created. Need to track user sessions first.
*/
INSERT INTO
  users_to_accounts (user_id, account_id)
    VALUES(1,1);

/* Need to query existing accounts and cards so that drop down menus in "new receipt" are up-to-date. If do this when launch program can put in a single function. If do this when launch new account controller, etc., get the most recent db info.
*/

/* When inserting a user card or account, must check in GUI logic whether it exists already, and then warn the user
*/

SELECT account_num, routing_num, balance, user_id
  FROM accounts LEFT JOIN users_to_accounts
    ON accounts._id = users_to_accounts.account_id
      WHERE user_id = 1;

SELECT account_num, routing_num, balance, user_id
  FROM accounts LEFT JOIN users_to_accounts
    ON accounts._id = users_to_accounts.account_id
      WHERE user_id = 1;
