CREATE SCHEMA IF NOT EXISTS EZ_FASTFOOD;


CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.CUSTOMER(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.CATEGORY (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.PRODUCT (
    id BIGSERIAL PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    description TEXT,
    price DECIMAL,
    FOREIGN KEY (category_id) REFERENCES EZ_FASTFOOD.CATEGORY(id)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.ORDER (
    id BIGSERIAL PRIMARY KEY,
    customer_id INT NULL,
    order_time TIMESTAMP WITH TIME ZONE,
    total_price DECIMAL(10, 2),
    order_status VARCHAR(50) NULL,
    customer_name VARCHAR(255),
    completed_time TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (customer_id) REFERENCES EZ_FASTFOOD.CUSTOMER(id)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.ORDER_ITEMS (
    id BIGSERIAL PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL,
    FOREIGN KEY (order_id) REFERENCES EZ_FASTFOOD.ORDER(id),
    FOREIGN KEY (product_id) REFERENCES EZ_FASTFOOD.PRODUCT(id)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.PAYMENT (
    id BIGSERIAL PRIMARY KEY,
    order_id INT,
    customer_id INT NULL,
    payment_date TIMESTAMP WITH TIME ZONE NULL,
    payment_price DECIMAL,
    payment_status VARCHAR(50),
    FOREIGN KEY (order_id) REFERENCES EZ_FASTFOOD.ORDER(id),
    FOREIGN KEY (customer_id) REFERENCES EZ_FASTFOOD.CUSTOMER(id)
);

INSERT INTO EZ_FASTFOOD.CUSTOMER (name, cpf, email)
VALUES 
('Thaynara da Silva', '359.380.170-17', 'thaynara@gmail.com'),
('Flavio da Silva', '530.335.610-80', 'flavio@gmail.com');

INSERT INTO EZ_FASTFOOD.CATEGORY (name)
VALUES 
('LANCHE'),
('BEBIDA'),
('ACOMPANHAMENTO'),
('SOBREMESA');


INSERT INTO EZ_FASTFOOD.PRODUCT (name,description,price, category_id)
VALUES 
('X-BURGUER', 'CARNE BOVINA, QUEIJO PRATA, ALFACE, TOMATE, CEBOLA E MOLHO DA CASA','20.50',1),
('X-FRANGO', 'FILE DE FRANGO EMPANADO, MUÇARELA, ALFACE, TOMATE,BACON,CEBOLA E MOLHO DA CASA','19.50',1),
('COCA-COLA', 'REFRIGERENTE DE 300 ML','5.50',2),
('TUTU', 'REFRIGERENTE DE GARRAFA DAS ANTIGAS','4.5',2),
('BATATA FRITA', 'FRITAS ONDULADAS','11.90',3),
('PETIT GATEAU', 'TIRA GOSTO','7.00',4);



