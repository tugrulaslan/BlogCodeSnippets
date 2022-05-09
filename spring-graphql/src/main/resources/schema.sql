CREATE TABLE historical_price
(
  id IDENTITY NOT NULL PRIMARY KEY,
  symbol varchar NOT NULL,
  time numeric NOT NULL,
  price numeric NOT NULL,
  volume numeric NOT NULL
);
