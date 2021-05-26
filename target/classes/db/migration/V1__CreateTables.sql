
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE users (
    id INT CHECK (id > 0) IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_admin BIT NOT NULL,

    created_at DATETIME2(0) DEFAULT GETDATE(),
    deleted_at DATETIME2(0) NULL DEFAULT NULL
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE obras (
    id INT CHECK (id > 0) IDENTITY PRIMARY KEY,
    prazo INT,
    valor_total INT,
    bonus_entrega INT,
    tempo_limite INT,
    user_id INT CHECK (user_id > 0) NOT NULL,

    created_at DATETIME2(0) DEFAULT GETDATE(),
    deleted_at DATETIME2(0) NULL DEFAULT NULL
);


-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE bonus (
    id INT CHECK (id > 0) IDENTITY PRIMARY KEY,
    obras_id INT CHECK (obras_id > 0) NOT NULL,
    dias INT,
    valor INT,

    created_at DATETIME2(0) DEFAULT GETDATE(),
    deleted_at DATETIME2(0) NULL DEFAULT NULL,

    CONSTRAINT FK_bonus_obras_id FOREIGN KEY (obras_id) REFERENCES obras(id)
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE equipes (
    id INT CHECK (id > 0) IDENTITY PRIMARY KEY,
    obras_id INT CHECK (obras_id > 0) NOT NULL,
    quantidade_funcionarios INT,

    created_at DATETIME2(0) DEFAULT GETDATE(),
    deleted_at DATETIME2(0) NULL DEFAULT NULL,

    CONSTRAINT FK_equipes_obras_id FOREIGN KEY (obras_id) REFERENCES obras(id)
);


