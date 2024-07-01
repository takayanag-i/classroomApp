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
('1401', 'Java太郎', 'java@example.com', 'JavaJava'),
('1402', '山口', 'meimei@example.com', '20020403'),
('1403', '福武こころ', 'heart@example.com', 'baton');

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
('1H001', 'Python入門', '1', '1'),
('1H002', 'Javaプログラミング', '2', '2'),
('1H003', 'C++プログラミング', '3', '3'),
('1H004', 'JavaScript基礎', '4', '4'),
('1H005', 'Rubyプログラミング', '5', '5'),
('1H006', 'Goプログラミング', '1', '2'),
('1H007', 'Swift開発', '2', '3'),
('1H008', 'Kotlin入門', '3', '4'),
('1H009', 'PHPプログラミング', '4', '5'),
('1H010', 'R言語基礎', '5', '1'),
('1H011', 'SQLデータベース', '1', '3'),
('1H012', 'HTML/CSS', '2', '4'),
('1H013', 'TypeScript', '3', '5'),
('1H014', 'Rustプログラミング', '4', '1'),
('1H015', 'Scala基礎', '5', '2'),
('1H016', 'Perlプログラミング', '1', '4'),
('1H017', 'MATLAB入門', '2', '5'),
('1H018', 'Shellスクリプト', '3', '1'),
('1H019', 'Djangoフレームワーク', '4', '2'),
('1H020', 'Flask開発', '5', '3'),
('1H021', 'React.js', '1', '5'),
('1H022', 'Angular.js', '2', '1'),
('1H023', 'Vue.js', '3', '2'),
('1H024', 'Spring Boot', '4', '3'),
('1H025', 'Laravel', '5', '4'),
('1H026', 'Node.js', '1', '1'),
('1H027', 'Express.js', '2', '2'),
('1H028', 'ASP.NET', '3', '3'),
('1H029', 'Unityゲーム開発', '4', '4'),
('1H030', 'Unreal Engine', '5', '5'),
('1H031', 'TensorFlow', '1', '2'),
('1H032', 'PyTorch', '2', '3'),
('1H033', 'OpenCV', '3', '4'),
('1H034', 'NLP with Python', '4', '5'),
('1H035', '機械学習基礎', '5', '1'),
('1H036', '深層学習応用', '1', '3'),
('1H037', 'ビッグデータ解析', '2', '4'),
('1H038', 'データビジュアライゼーション', '3', '5'),
('1H039', 'Hadoop', '4', '1'),
('1H040', 'Spark', '5', '2'),
('1H041', 'DevOps実践', '1', '4'),
('1H042', 'コンテナ技術', '2', '5'),
('1H043', 'Kubernetes', '3', '1'),
('1H044', 'CI/CDパイプライン', '4', '2'),
('1H045', 'クラウドインフラ', '5', '3'),
('1H046', 'AWS基礎', '1', '5'),
('1H047', 'Google Cloud Platform', '2', '1'),
('1H048', 'Microsoft Azure', '3', '2'),
('1H049', 'Linuxシステム管理', '4', '3'),
('1H050', 'Windows Server', '5', '4');

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
('T001A', '佐藤健', 'satou.takeru@example.com', 'password123'),
('T002A', '新垣結衣', 'aragaki.yui@example.com', 'password123'),
('T003A', '松本潤', 'matsumoto.jun@example.com', 'password123'),
('T004A', '石原さとみ', 'ishihara.satomi@example.com', 'password123'),
('T005A', '山田孝之', 'yamada.takayuki@example.com', 'password123'),
('T006A', '綾瀬はるか', 'ayase.haruka@example.com', 'password123'),
('T007A', '二宮和也', 'ninomiya.kazunari@example.com', 'password123'),
('T008A', '堀北真希', 'horikita.maki@example.com', 'password123'),
('T009A', '櫻井翔', 'sakurai.sho@example.com', 'password123'),
('T010A', '長澤まさみ', 'nagasawa.masami@example.com', 'password123'),
('T011A', '大野智', 'ohno.satoshi@example.com', 'password123'),
('T012A', '沢尻エリカ', 'sawajiri.erika@example.com', 'password123'),
('T013A', '松坂桃李', 'matsuzaka.tori@example.com', 'password123'),
('T014A', '宮崎あおい', 'miyazaki.aoi@example.com', 'password123'),
('T015A', '小栗旬', 'oguri.shun@example.com', 'password123'),
('T016A', '戸田恵梨香', 'toda.erika@example.com', 'password123'),
('T017A', '三浦春馬', 'miura.haru@example.com', 'password123'),
('T018A', '北川景子', 'kitagawa.keiko@example.com', 'password123'),
('T019A', '生田斗真', 'ikuta.toma@example.com', 'password123'),
('T020A', '石原さとみ', 'ishihara.satomi2@example.com', 'password123'),
('T021B', 'Chris Evans', 'chris.evans@example.com', 'password123'),
('T022B', 'Scarlett Johansson', 'scarlett.johansson@example.com', 'password123'),
('T023B', 'Robert Downey Jr.', 'robert.downey@example.com', 'password123'),
('T024B', 'Jennifer Lawrence', 'jennifer.lawrence@example.com', 'password123'),
('T025B', 'Tom Hiddleston', 'tom.hiddleston@example.com', 'password123'),
('T026B', '生見愛瑠', 'melulu@example.com', 'password123'),
('T027B', '成瀬亜未', 'ami@example.com', 'password123'),
('T028B', '吉田仁美', 'hitomi.yoshida@example.com', 'password123'),
('T029B', '笠田卓哉', 't-kasada@example.com', 'password123'),
('T030B', '高柳遼', 'noreply@example.com', 'password123');

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
-- 1人の教員による担当
('1H001', 'T001A'),
('1H002', 'T002A'),
('1H003', 'T003A'),
('1H004', 'T004A'),
('1H005', 'T005A'),

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

-- 他のコース
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
