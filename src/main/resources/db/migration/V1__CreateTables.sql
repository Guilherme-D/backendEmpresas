
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE users_seq;

CREATE TABLE users (
    id INT CHECK (id > 0) DEFAULT NEXTVAL ('users_seq') PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL,

    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP(0) NULL DEFAULT NULL
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE obras_seq;

CREATE TABLE obras (
    id INT CHECK (id > 0) DEFAULT NEXTVAL ('obras_seq') PRIMARY KEY,
    prazo INT,
    valor_total INT,
    bonus_entrega INT,
    tempo_limite INT,
    user_id INT CHECK (user_id > 0) NOT NULL,

    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP(0) NULL DEFAULT NULL
);


-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE bonus_seq;

CREATE TABLE bonus (
    id INT CHECK (id > 0) DEFAULT NEXTVAL ('bonus_seq') PRIMARY KEY,
    obras_id INT CHECK (obras_id > 0) NOT NULL,
    dias INT,
    valor INT,

    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

    CONSTRAINT FK_bonus_obras_id FOREIGN KEY (obras_id) REFERENCES obras(id)
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE equipes_seq;

CREATE TABLE equipes (
    id INT CHECK (id > 0) DEFAULT NEXTVAL ('equipes_seq') PRIMARY KEY,
    obras_id INT CHECK (obras_id > 0) NOT NULL,
    quantidade_funcionarios INT,

    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

    CONSTRAINT FK_equipes_obras_id FOREIGN KEY (obras_id) REFERENCES obras(id)
);

