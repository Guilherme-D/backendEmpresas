set foreign_key_checks=0;

CREATE TABLE users (
    id INT(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE obras (
    id INT(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    prazo INT(6),
    valor_total INT(20),
    bonus_entrega INT(20),
    tempo_limite INT(20),
    user_id INT(20) UNSIGNED NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL DEFAULT NULL
);


CREATE TABLE bonus (
    id INT(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    obras_id INT(20) UNSIGNED NOT NULL,
    dias INT(6),
    valor INT(20),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL DEFAULT NULL,

    CONSTRAINT FK_bonus_obras_id FOREIGN KEY (obras_id) REFERENCES obras(id)
);

CREATE TABLE equipes (
    id INT(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    obras_id INT(20) UNSIGNED NOT NULL,
    quantidade_funcionarios INT(20),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL DEFAULT NULL,

    CONSTRAINT FK_equipes_obras_id FOREIGN KEY (obras_id) REFERENCES obras(id)
);

set foreign_key_checks=1;