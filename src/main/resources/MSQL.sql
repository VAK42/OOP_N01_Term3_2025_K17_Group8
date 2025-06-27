USE defaultdb;

DROP TABLE IF EXISTS `export`, `import`, `product`, `report`, `category`, `token`, `user`;

CREATE TABLE `user` (
    userId CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE token (
    userId CHAR(36) NOT NULL,
    token VARCHAR(255) NOT NULL UNIQUE,
    date TIMESTAMP NOT NULL,
    PRIMARY KEY (userId, token),
    FOREIGN KEY (userId) REFERENCES `user`(userId) ON DELETE CASCADE
);

CREATE TABLE category (
    categoryId CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE product (
    pdId CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    pdName VARCHAR(100) UNIQUE NOT NULL,
    pdPrice DECIMAL(12,2) NOT NULL,
    categoryId CHAR(36),
    pdInfo TEXT NOT NULL,
    pdQuantity INT NOT NULL DEFAULT 0,
    FOREIGN KEY (categoryId) REFERENCES category(categoryId) ON DELETE SET NULL
);

CREATE TABLE `import` (
    ipId CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    pdId CHAR(36) NOT NULL,
    pdPrice DECIMAL(12,2) NOT NULL,
    pdQuantity INT NOT NULL,
    userId CHAR(36),
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pdId) REFERENCES product(pdId) ON DELETE RESTRICT,
    FOREIGN KEY (userId) REFERENCES `user`(userId) ON DELETE SET NULL
);

CREATE TABLE `export` (
    epId CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    pdId CHAR(36) NOT NULL,
    pdPrice DECIMAL(12,2) NOT NULL,
    pdQuantity INT NOT NULL,
    pdTotalPrice DECIMAL(12,2) GENERATED ALWAYS AS (pdPrice * pdQuantity) STORED,
    userId CHAR(36),
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pdId) REFERENCES product(pdId) ON DELETE RESTRICT,
    FOREIGN KEY (userId) REFERENCES `user`(userId) ON DELETE SET NULL
);

CREATE TABLE report (
    reportId CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    userId CHAR(36) NOT NULL,
    rpName VARCHAR(100) NOT NULL,
    rpInfo TEXT NOT NULL,
    FOREIGN KEY (userId) REFERENCES `user`(userId) ON DELETE CASCADE
);
