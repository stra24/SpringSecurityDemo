CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    authority VARCHAR(20) NOT NULL
);

-- INSERT INTO users (username, password, authority) VALUES
-- ('taro', '{bcrypt}$2a$10$abcdefghijklmnopqrstuvxyz12345678', 'ADMIN'),
-- ('hanako', '{bcrypt}$2a$10$abcdefghijklmnopqrstuvxyz12345678', 'GENERAL');