-- =====================================================
-- База данных "Мебель" (вариант 24, магазин мебели)
-- Импорт: phpMyAdmin -> вкладка "Импорт" (или "SQL")
-- Совместимо с MySQL 5.7+/8.x и MariaDB 10.x (XAMPP, Open Server)
-- =====================================================

SET NAMES utf8mb4;

CREATE DATABASE IF NOT EXISTS mebel
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

USE mebel;

-- Удаляем таблицы, если скрипт запускается повторно
-- (сначала zakazy, потому что она ссылается на остальные)
DROP TABLE IF EXISTS zakazy;
DROP TABLE IF EXISTS mebel;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS sotr;

-- -----------------------------------------------------
-- Таблица "Сотрудники"
-- -----------------------------------------------------
CREATE TABLE sotr (
  id_sotr   INT NOT NULL AUTO_INCREMENT,
  fam       VARCHAR(50) NOT NULL,
  name      VARCHAR(50) NOT NULL,
  otchestvo VARCHAR(50),
  doljnost  VARCHAR(50) NOT NULL,
  telefon   VARCHAR(20),
  PRIMARY KEY (id_sotr)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Таблица "Клиенты"
-- -----------------------------------------------------
CREATE TABLE clients (
  id_client INT NOT NULL AUTO_INCREMENT,
  fam       VARCHAR(50) NOT NULL,
  name      VARCHAR(50) NOT NULL,
  otchestvo VARCHAR(50),
  telefon   VARCHAR(20),
  adres     VARCHAR(150),
  PRIMARY KEY (id_client)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Таблица "Мебель"
-- -----------------------------------------------------
CREATE TABLE mebel (
  id_mebel     INT NOT NULL AUTO_INCREMENT,
  naimenovanie VARCHAR(100) NOT NULL,
  opisanie     VARCHAR(255),
  material     VARCHAR(100),
  kolvo_sklad  INT NOT NULL DEFAULT 0,
  cena         DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id_mebel)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -----------------------------------------------------
-- Таблица "Заказы" (каждый заказ - одна позиция мебели)
-- summa = цена мебели * kolvo
-- -----------------------------------------------------
CREATE TABLE zakazy (
  id_zakaza   INT NOT NULL AUTO_INCREMENT,
  id_sotr     INT NOT NULL,
  id_client   INT NOT NULL,
  id_mebel    INT NOT NULL,
  kolvo       INT NOT NULL,
  data_zakaza DATE NOT NULL,
  summa       DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id_zakaza),
  KEY idx_zakazy_sotr (id_sotr),
  KEY idx_zakazy_client (id_client),
  KEY idx_zakazy_mebel (id_mebel),
  CONSTRAINT fk_zakazy_sotr FOREIGN KEY (id_sotr)
    REFERENCES sotr (id_sotr) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_zakazy_client FOREIGN KEY (id_client)
    REFERENCES clients (id_client) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_zakazy_mebel FOREIGN KEY (id_mebel)
    REFERENCES mebel (id_mebel) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =====================================================
-- Заполнение таблиц данными
-- =====================================================

INSERT INTO sotr (id_sotr, fam, name, otchestvo, doljnost, telefon) VALUES
(1,  'Орлов',     'Виктор',    'Николаевич',  'Директор',              '+7 (912) 345-10-01'),
(2,  'Смирнова',  'Елена',     'Андреевна',   'Старший менеджер',      '+7 (912) 345-10-02'),
(3,  'Козлов',    'Дмитрий',   'Сергеевич',   'Менеджер',              '+7 (912) 345-10-03'),
(4,  'Попова',    'Анна',      'Викторовна',  'Продавец-консультант',  '+7 (912) 345-10-04');

INSERT INTO clients (id_client, fam, name, otchestvo, telefon, adres) VALUES
(1,  'Иванов',    'Пётр',      'Сергеевич',   '+7 (903) 111-22-33', 'г. Москва, ул. Ленина, д. 12, кв. 5'),
(2,  'Петрова',   'Мария',     'Олеговна',    '+7 (905) 222-33-44', 'г. Москва, ул. Садовая, д. 3, кв. 41'),
(3,  'Сидоров',   'Николай',   'Иванович',    '+7 (916) 333-44-55', 'г. Москва, пр-т Мира, д. 45, кв. 17'),
(4,  'Кузнецова', 'Светлана',  'Петровна',    '+7 (925) 444-55-66', 'г. Москва, ул. Гагарина, д. 8, кв. 2');

INSERT INTO mebel (id_mebel, naimenovanie, opisanie, material, kolvo_sklad, cena) VALUES
(1,  'Диван угловой',       'Угловой диван с механизмом «еврокнижка» и ящиком для белья', 'Велюр, ЛДСП',          5,  45990.00),
(2,  'Кровать двуспальная', 'Кровать 160x200 см с подъёмным механизмом',                  'Массив сосны',         4,  32500.00),
(3,  'Шкаф-купе',           'Трёхдверный шкаф-купе с зеркалом, ширина 180 см',            'ЛДСП, зеркало',        3,  38700.00),
(4,  'Письменный стол',     'Стол 120x60 см с двумя выдвижными ящиками',                  'ЛДСП',                 12, 8990.00);

-- Сумма заказа = цена мебели * количество
INSERT INTO zakazy (id_zakaza, id_sotr, id_client, id_mebel, kolvo, data_zakaza, summa) VALUES
(1, 3, 1, 1, 1, '2026-10-01', 45990.00),
(2, 4, 2, 4, 2, '2026-10-02', 17980.00),
(3, 3, 4, 3, 1, '2026-10-05', 38700.00),
(4, 4, 3, 2, 1, '2026-10-17', 32500.00);
