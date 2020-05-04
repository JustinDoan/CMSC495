PRAGMA foreign_keys = ON;

CREATE TABLE users (
  id NUMBER PRIMARY KEY AUTOINCREMENT,
  last_update DATETIME NOT NULL,
  user_name TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL
);

CREATE TABLE accounts (
  id NUMBER PRIMARY KEY AUTOINCREMENT,
  account_num NUMBER UNIQUE,
  routing_num NUMBER,
  balance REAL
);

CREATE TABLE users_to_accounts (
  user_id NUMBER NOT NULL,
  account_id NUMBER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (account_id) REFERENCES accounts (id)
);

CREATE TABLE deposits (
  id NUMBER PRIMARY KEY AUTOINCREMENT,
  source_account_id NUMBER NOT NULL,
  dest_account_id NUMBER NOT NULL,
  amount REAL,
  FOREIGN KEY (source_account_id) REFERENCES accounts (id),
  FOREIGN KEY (dest_account_id) REFERENCES accounts (id)
);

CREATE TABLE withdrawals (
  id NUMBER PRIMARY KEY AUTOINCREMENT,
  source_account_id NUMBER NOT NULL,
  dest_account_id NUMBER NOT NULL,
  amount REAL,
  FOREIGN KEY (source_account_id) REFERENCES accounts (id),
  FOREIGN KEY (dest_account_id) REFERENCES accounts (id)
);

CREATE TABLE cards (
  id NUMBER PRIMARY KEY AUTOINCREMENT,
  account_id NUMBER NOT NULL,
  card_num NUMBER UNIQUE,
  balance REAL,
  FOREIGN KEY (account_id) REFERENCES accounts (id)
);

CREATE TABLE receipts (
  id NUMBER PRIMARY KEY AUTOINCREMENT,
  user_id NUMBER NOT NULL,
  card_id NUMBER,
  sub_total REAL,
  sales_tax REAL,
  discount REAL,
  total REAL,
  cash_paid REAL,
  change_due REAL,
  FOREIGN KEY (card_id) REFERENCES cards (id)
);
