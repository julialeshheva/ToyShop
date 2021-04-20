INSERT INTO categories (title) VALUES ('Куклы'), ('Машинки'), ('Настольные игры');
INSERT INTO products (category_id, title,full_description, price, path_img )
VALUES (1,'Кукла барби','Очень красивая кула барби', 200,'toy.jpg'),
       (1,'Кукла Даша','Лучшая кукла для маленьких детей', 300,'toy.jpg'),
       (1,'Кен','Лучший друг для кулы барби', 500,'toy.jpg'),
       (2,'Мазда','Детская машинка мазда для мальчиков', 1000,'toy.jpg'),
       (2,'Грузовик','Очень юольшой и удобный грузовик', 2000,'toy.jpg');
INSERT INTO users (username,password,first_name,last_name,email, delivery_address, phone_number) VALUES ('user1','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','Николай','Басков', 'baskov@mail.ru','улица Красоты 22, дом 5, квартира 20','+7 900 999 99 99');
INSERT INTO users (username,password,first_name,last_name,email, delivery_address, phone_number) VALUES ('user2','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','Филипп','Киркоров', 'kirkorov@mail.ru','улица Успеха 34, дом 2, квартира 2','+7 800 888 88 88');
INSERT INTO users (username,password,first_name,last_name,email, delivery_address, phone_number) VALUES ('user3','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','Ольга','Бузова', 'buzova@mail.ru','улица Цветов 5, дом 4, квартира 28', '+7 700 777 77 77');

INSERT INTO roles (name)
VALUES ('ADMIN'),
       ('USER');
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 2);
INSERT INTO statuses (title)
VALUES  ('CREATED'),
        ('APPROVED'),
        ('SENDED'),
        ('RECEIVED');
