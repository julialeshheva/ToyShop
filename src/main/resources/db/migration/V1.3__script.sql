CREATE TABLE categories(
    id	                serial,
    title               VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE products(
    id	                serial,
    category_id         INT NOT NULL,
    title               VARCHAR(255) NOT NULL,
    full_description    VARCHAR(5000) NOT NULL,
    price               DECIMAL(8,2) NOT NULL,
    path_img            VARCHAR(250) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_CATEGORY_ID FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE users(
    id                  serial,
    username            VARCHAR(50) NOT NULL UNIQUE,
    password            VARCHAR(80) NOT NULL,
    first_name          VARCHAR(50) NOT NULL,
    last_name           VARCHAR(50) NOT NULL,
    email               VARCHAR(50) NOT NULL,
    delivery_address    VARCHAR(100) NOT NULL,
    phone_number        VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles(
   id                  serial,
   name                VARCHAR(50) DEFAULT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE users_roles(
   user_id               INT NOT NULL,
   role_id               INT NOT NULL,

   PRIMARY KEY (user_id, role_id),
   CONSTRAINT FK_USER_ID_01 FOREIGN KEY (user_id) REFERENCES users (id),
   CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id) REFERENCES roles (id)
);

/*CREATE TABLE roles (
   user_id   INT NOT NULL,
   role varchar(50) NOT NULL,

   PRIMARY KEY (user_id , role),
   CONSTRAINT fk_roles FOREIGN KEY (user_id) REFERENCES users (id)
);*/

CREATE TABLE statuses(
    id                  serial,
    title               VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE orders(
   id	                serial,
   user_id              INT NOT NULL,
   status_id            INT NOT NULL,
   delivery_date        TIMESTAMP NOT NULL,
   create_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   order_price          DECIMAL(8,2) NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES users (id),
   CONSTRAINT FK_STATUS_ID FOREIGN KEY (status_id) REFERENCES statuses (id)
);



CREATE TABLE orders_item(
   id	                 serial,
   product_id            INT NOT NULL,
   order_id              INT NOT NULL,
   quantity              INT NOT NULL,
   item_price            DECIMAL(8,2) NOT NULL,
   total_price           DECIMAL(8,2) NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT FK_ORDER_ID FOREIGN KEY (order_id) REFERENCES orders (id),
   CONSTRAINT FK_PRODUCT_ID_ORD_IT FOREIGN KEY (product_id) REFERENCES products (id)
);
