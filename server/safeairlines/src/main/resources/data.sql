create table if not exists persistent_logins (
  username varchar(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

-- INSERT INTO role VALUES (1, 'ADMIN');
-- INSERT INTO role VALUES (2, 'BUYER');

-- INSERT INTO user VALUES (0,1,'admin@gmail.com','$2a$10$5yC4meSZGDFC/bEWquhkAe.3B6B0kk5GJcu4dTN3O52G0jiBdauVa','admin');
-- INSERT INTO user_role VALUES (1,0);

-- for tests
