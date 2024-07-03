CREATE DATABASE training_db;

USE training_db;

-- 学生テーブルの作成
CREATE TABLE Students (
    student_id CHAR(4) NOT NULL,
    name NVARCHAR(31) NOT NULL,
    email VARCHAR(63) NOT NULL,
    password VARCHAR(63) NOT NULL,
    PRIMARY KEY (student_id)
);

-- 学生テーブルのレコード挿入
INSERT INTO Students
(student_id, name, email, password) VALUES
('1401', 'Java太郎', 'mail@example.com', 'JavaJava'),
('1402', '山口', 'mail@example.com', '20020403'),
('1403', '福武こころ', 'mail@example.com', 'baton');

-- コーステーブルの作成
CREATE TABLE Courses (
    course_id CHAR(5) NOT NULL,
    course_name NVARCHAR(31) NOT NULL,
    day_of_week CHAR(3),
    period CHAR(1),
    PRIMARY KEY (course_id)
);

-- コーステーブルのレコード挿入
INSERT INTO Courses (course_id, course_name, day_of_week, period) VALUES
('1H001', 'Pythonプログラミング', '1', '1'),
('1H002', 'Javaプログラミング', '2', '2'),
('1H003', 'C++プログラミング', '3', '3'),
('1H004', 'JavaScriptプログラミング', '4', '4'),
('1H005', 'Web制作実習', '5', '5'),
('1H006', 'Goプログラミング', '1', '2'),
('1H007', 'Swiftプログラミング', '2', '3'),
('1H008', '線形代数', '3', '4'),
('1H009', '微分積分', '4', '5'),
('1H010', 'R入門', '5', '1'),
('1H011', 'ベクトル解析', '1', '3'),
('1H012', '数理統計', '2', '4'),
('1H013', 'データベース実習', '3', '5'),
('1H014', '複素関数', '4', '1'),
('1H015', '信号処理', '5', '2'),
('1H016', '電気・電子回路', '1', '4'),
('1H017', '開発基礎実習', '2', '5'),
('1H018', 'シェルスクリプト', '3', '1'),
('1H019', 'Djangoフレームワーク', '4', '2'),
('1H020', 'センサ工学', '5', '3'),
('1H021', '詠歌・批評実習', '1', '5'),
('1H022', '和歌鑑賞', '2', '1'),
('1H023', '和歌の世界', '3', '2'),
('1H024', 'Spring Boot', '4', '3'),
('1H025', '材料力学', '5', '4'),
('1H026', '流体力学', '1', '1'),
('1H027', '弾性力学', '2', '2'),
('1H028', '塑性力学', '3', '3'),
('1H029', 'レオロジー', '4', '4'),
('1H030', 'スマートライフ技術実習', '5', '5'),
('1H031', '物理化学', '1', '2'),
('1H032', '分析化学', '2', '3'),
('1H033', '有機化学', '3', '4'),
('1H034', '基礎化学実験', '4', '5'),
('1H035', '無機化学', '5', '1'),
('1H036', '機械学習基礎', '1', '3'),
('1H037', 'ビッグデータ解析', '2', '4'),
('1H038', 'ウォーターフォール実習', '3', '5'),
('1H039', '高分子化学', '4', '1'),
('1H040', '生命化学', '5', '2'),
('1H041', 'Docker入門', '1', '4'),
('1H042', 'DevOps実習', '2', '5'),
('1H043', 'Kubernetes', '3', '1'),
('1H044', 'Academic Communication(英)', '4', '2'),
('1H045', 'Businness Communication(英)', '5', '3'),
('1H046', 'AWS基礎', '1', '5'),
('1H047', '基礎力学', '2', '1'),
('1H048', 'Microsoft Azure', '3', '2'),
('1H049', 'Linux', '4', '3'),
('1H050', '電磁気学', '5', '4');

-- 履修登録テーブルの作成
CREATE TABLE Enrollments (
    student_id CHAR(4),
    course_id CHAR(5),
    enrollment_date DATETIME,
    cancel_flag BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

-- 履修登録テーブルのレコード挿入
INSERT INTO Enrollments (student_id, course_id, enrollment_date) VALUES
('1401', '1H001', '2024-04-01 09:00:00'),
('1401', '1H002', '2024-04-01 10:00:00'),
('1401', '1H003', '2024-04-01 11:00:00'),
('1401', '1H004', '2024-04-01 12:00:00'),
('1401', '1H005', '2024-04-01 13:00:00'),
('1401', '1H006', '2024-04-01 14:00:00'),
('1401', '1H007', '2024-04-01 15:00:00'),
('1401', '1H008', '2024-04-01 16:00:00'),
('1402', '1H009', '2024-04-01 09:00:00'),
('1402', '1H010', '2024-04-01 10:00:00'),
('1402', '1H011', '2024-04-01 11:00:00'),
('1402', '1H012', '2024-04-01 12:00:00'),
('1402', '1H013', '2024-04-01 13:00:00'),
('1402', '1H014', '2024-04-01 14:00:00'),
('1402', '1H015', '2024-04-01 15:00:00'),
('1402', '1H016', '2024-04-01 16:00:00'),
('1403', '1H017', '2024-04-01 09:00:00'),
('1403', '1H018', '2024-04-01 10:00:00'),
('1403', '1H019', '2024-04-01 11:00:00'),
('1403', '1H020', '2024-04-01 12:00:00'),
('1403', '1H021', '2024-04-01 13:00:00'),
('1403', '1H022', '2024-04-01 14:00:00'),
('1403', '1H023', '2024-04-01 15:00:00'),
('1403', '1H024', '2024-04-01 16:00:00');

-- 教員テーブルの作成
CREATE TABLE Instructors (
    instructor_id CHAR(5) NOT NULL,
    name NVARCHAR(31) NOT NULL,
    email VARCHAR(63) NOT NULL,
    password VARCHAR(63),
    PRIMARY KEY (instructor_id)
);

-- ダミーレコードの作成
INSERT INTO Instructors (instructor_id, name, email, password) VALUES
('T001A', '神戸健', 'mail@example.com', 'password123'),
('T002A', '長田結衣', 'mail@example.com', 'password123'),
('T003A', '加古川潤', 'mail@example.com', 'password123'),
('T004A', '姫路さとみ', 'mail@example.com', 'password123'),
('T005A', '小野孝之', 'mail@example.com', 'password123'),
('T006A', '垂水はるか', 'mail@example.com', 'password123'),
('T007A', '三宮和也', 'mail@example.com', 'password123'),
('T008A', '湊川真希', 'mail@example.com', 'password123'),
('T009A', '鶴甲翔', 'mail@example.com', 'password123'),
('T010A', '灘まさみ', 'mail@example.com', 'password123'),
('T011A', '舞子智', 'mail@example.com', 'password123'),
('T012A', '須磨エリカ', 'mail@example.com', 'password123'),
('T013A', '多聞桃李', 'mail@example.com', 'password123'),
('T014A', '西宮あおい', 'mail@example.com', 'password123'),
('T015A', '尼崎旬', 'mail@example.com', 'password123'),
('T016A', '川西恵梨香', 'mail@example.com', 'password123'),
('T017A', '御影春馬', 'mail@example.com', 'password123'),
('T018A', '明石景子', 'mail@example.com', 'password123'),
('T019A', '相生斗真', 'mail@example.com', 'password123'),
('T020A', '赤穂さとみ', 'mail@example.com', 'password123'),
('T021B', 'Chris Evans', 'mail@example.com', 'password123'),
('T022B', 'Scarlett Johansson', 'mail@example.com', 'password123'),
('T023B', 'Robert Downey Jr.', 'mail@example.com', 'password123'),
('T024B', 'Jennifer Lawrence', 'mail@example.com', 'password123'),
('T025B', 'Tom Hiddleston', 'mail@example.com', 'password123'),
('T026B', '成瀬愛瑠', 'mail@example.com', 'password123'),
('T027B', '生見亜未', 'mail@example.com', 'password123'),
('T028B', '吉田仁美', 'mail@example.com', 'password123'),
('T029B', '笠田卓哉', 'mail@example.com', 'password123'),
('T030B', '教員未定', 'mail@example.com', 'password123');

-- 担当テーブルの作成
CREATE TABLE Instructions (
    course_id CHAR(5) NOT NULL,
    instructor_id CHAR(5) NOT NULL,
    PRIMARY KEY (course_id, instructor_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id),
    FOREIGN KEY (instructor_id) REFERENCES Instructors(instructor_id)
);

-- 担当テーブルのダミーレコード生成
INSERT INTO Instructions (course_id, instructor_id) VALUES
('1H001', 'T001A'),
('1H002', 'T002A'),
('1H003', 'T003A'),
('1H004', 'T004A'),
('1H013', 'T013A'),
('1H014', 'T014A'),
('1H015', 'T015A'),
('1H016', 'T016A'),
('1H017', 'T017A'),
('1H018', 'T018A'),
('1H019', 'T019A'),
('1H020', 'T020A'),
('1H021', 'T021B'),
('1H022', 'T022B'),
('1H023', 'T023B'),
('1H024', 'T024B'),
('1H025', 'T025B'),
('1H026', 'T026B'),
('1H027', 'T027B'),
('1H028', 'T028B'),
('1H029', 'T029B'),
('1H030', 'T030B'),
('1H031', 'T001A'),
('1H032', 'T002A'),
('1H033', 'T003A'),
('1H034', 'T004A'),
('1H035', 'T005A'),
('1H036', 'T006A'),
('1H037', 'T007A'),
('1H038', 'T008A'),
('1H039', 'T009A'),
('1H040', 'T010A'),
('1H041', 'T011A'),
('1H042', 'T012A'),
('1H043', 'T013A'),
('1H044', 'T014A'),
('1H045', 'T015A'),

('1H046', 'T016A'),
('1H047', 'T017A'),
('1H048', 'T018A'),
('1H049', 'T019A'),
('1H050', 'T020A');

-- 2人の教員による担当
('1H006', 'T006A'),
('1H006', 'T021B'),
('1H007', 'T007A'),
('1H007', 'T022B'),
('1H008', 'T008A'),
('1H008', 'T023B'),

-- 3人の教員による担当
('1H009', 'T009A'),
('1H009', 'T024B'),
('1H009', 'T025B'),
('1H010', 'T010A'),
('1H010', 'T026B'),
('1H010', 'T027B'),

-- 5人の教員による担当
('1H005', 'T005A'),
('1H005', 'T001A'),
('1H005', 'T002A'),
('1H005', 'T003A'),
('1H005', 'T004A'),

('1H011', 'T011A'),
('1H011', 'T028B'),
('1H011', 'T029B'),
('1H011', 'T030B'),
('1H011', 'T001A'),

('1H012', 'T012A'),
('1H012', 'T002A'),
('1H012', 'T003A'),
('1H012', 'T004A'),
('1H012', 'T005A'),
