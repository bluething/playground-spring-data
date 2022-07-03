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