USE backend_challenge;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS db_test (
    id UUID NOT NULL,
    name TEXT NOT NULL,
    price INTEGER NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO db_test VALUES ('bd7d0521-f995-4f3e-8a82-3c04712d2078', 'iphone', '500000');