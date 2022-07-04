```sql
CREATE TABLE IF NOT EXISTS post (
    id INTEGER PRIMARY KEY,
    title VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS post_comment (
    id INTEGER PRIMARY KEY,
    review VARCHAR(200),
    post_id INTEGER,
    foreign key (post_id) references post(id)
);

INSERT INTO post(id, title) VALUES (1, '1st post');
INSERT INTO post_comment(id, review, post_id) VALUES (1, 'It''s good', 1);
INSERT INTO post_comment(id, review, post_id) VALUES (2, 'Not for me', 1);
```