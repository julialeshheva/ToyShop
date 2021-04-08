INSERT INTO categories (title) VALUES ('Куклы'), ('Машинки'), ('Настольные игры');
INSERT INTO products (category_id, title,full_description, price, path_img )
VALUES (1,'Кукла барби','Очень красивая кула барби', 200,'toy.jpg'),
       (1,'Кукла Даша','Лучшая кукла для маленьких детей', 300,'toy.jpg'),
       (1,'Кен','Лучший друг для кулы барби', 500,'toy.jpg'),
       (2,'Мазда','Детская машинка мазда для мальчиков', 1000,'toy.jpg'),
       (2,'Грузовик','Очень юольшой и удобный грузовик', 2000,'toy.jpg');
INSERT INTO users (username,password,first_name,last_name,email) VALUES ('user1','123','Николай','Басков', 'baskov@mail.ru');
INSERT INTO users (username,password,first_name,last_name,email) VALUES ('user2','123','Филипп','Киркоров', 'kirkorov@mail.ru');
INSERT INTO users (username,password,first_name,last_name,email) VALUES ('user3','123','Ольга','Бузова', 'buzova@mail.ru');

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (1, 2),
       (3, 2);
