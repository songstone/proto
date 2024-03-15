INSERT INTO employee(email, password, `name`, phone, is_deleted, created_at)
VALUES ('test1@naver.com', '1234', 'song1', '010-1231-2232', 'Y', CURRENT_TIMESTAMP),
       ('test2@naver.com', '5678', 'song2', '010-1331-2232', 'N', CURRENT_TIMESTAMP),
       ('test3@naver.com', '1313', 'song3', '010-1431-2232', 'N', CURRENT_TIMESTAMP),
       ('test4@naver.com', '2424', 'song4', '010-1231-2233', 'N', CURRENT_TIMESTAMP),
       ('test5@naver.com', '3535', 'song5', '010-1231-2234', 'N', CURRENT_TIMESTAMP),
       ('test6@naver.com', '4646', 'song6', '010-1231-2235', 'N', CURRENT_TIMESTAMP),
       ('test7@naver.com', '5757', 'song7', '010-1231-2236', 'N', CURRENT_TIMESTAMP),
       ('test8@naver.com', '6868', 'song8', '010-1231-2237', 'N', CURRENT_TIMESTAMP),
       ('test9@naver.com', '7979', 'song9', '010-1231-2238', 'N', CURRENT_TIMESTAMP),
       ('test10@naver.com', '8080', 'song10', '010-1231-2239', 'N', CURRENT_TIMESTAMP);

INSERT INTO department(`name`, is_deleted, created_at)
VALUES  ('개발A', 'N', CURRENT_TIMESTAMP),
        ('개발B', 'N', CURRENT_TIMESTAMP),
        ('개발C', 'N', CURRENT_TIMESTAMP),
        ('개발D', 'N', CURRENT_TIMESTAMP);

INSERT INTO department(`name`, parent_no, is_deleted, created_at)
VALUES  ('개발A-1', 1, 'N', CURRENT_TIMESTAMP),
        ('개발A-2', 1, 'N', CURRENT_TIMESTAMP),
        ('개발A-3', 1, 'N', CURRENT_TIMESTAMP),
        ('개발B-1', 2, 'N', CURRENT_TIMESTAMP),
        ('개발B-2', 2, 'N', CURRENT_TIMESTAMP);
