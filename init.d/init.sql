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
    period INT,
    PRIMARY KEY (course_id)
);

-- コーステーブルのレコード挿入
INSERT INTO Courses (course_id, course_name, day_of_week, period) VALUES
('1H001', 'Python入門', 'Mon', 1),
('1H002', 'Javaプログラミング', 'Tue', 2),
('1H003', 'C++プログラミング', 'Wed', 3),
('1H004', 'JavaScript基礎', 'Thu', 4),
('1H005', 'Rubyプログラミング', 'Fri', 5),
('1H006', 'Goプログラミング', 'Mon', 2),
('1H007', 'Swift開発', 'Tue', 3),
('1H008', 'Kotlin入門', 'Wed', 4),
('1H009', 'PHPプログラミング', 'Thu', 5),
('1H010', 'R言語基礎', 'Fri', 1),
('1H011', 'SQLデータベース', 'Mon', 3),
('1H012', 'HTML/CSS', 'Tue', 4),
('1H013', 'TypeScript', 'Wed', 5),
('1H014', 'Rustプログラミング', 'Thu', 1),
('1H015', 'Scala基礎', 'Fri', 2),
('1H016', 'Perlプログラミング', 'Mon', 4),
('1H017', 'MATLAB入門', 'Tue', 5),
('1H018', 'Shellスクリプト', 'Wed', 1),
('1H019', 'Djangoフレームワーク', 'Thu', 2),
('1H020', 'Flask開発', 'Fri', 3),
('1H021', 'React.js', 'Mon', 5),
('1H022', 'Angular.js', 'Tue', 1),
('1H023', 'Vue.js', 'Wed', 2),
('1H024', 'Spring Boot', 'Thu', 3),
('1H025', 'Laravel', 'Fri', 4),
('1H026', 'Node.js', 'Mon', 1),
('1H027', 'Express.js', 'Tue', 2),
('1H028', 'ASP.NET', 'Wed', 3),
('1H029', 'Unityゲーム開発', 'Thu', 4),
('1H030', 'Unreal Engine', 'Fri', 5),
('1H031', 'TensorFlow', 'Mon', 2),
('1H032', 'PyTorch', 'Tue', 3),
('1H033', 'OpenCV', 'Wed', 4),
('1H034', 'NLP with Python', 'Thu', 5),
('1H035', '機械学習基礎', 'Fri', 1),
('1H036', '深層学習応用', 'Mon', 3),
('1H037', 'ビッグデータ解析', 'Tue', 4),
('1H038', 'データビジュアライゼーション', 'Wed', 5),
('1H039', 'Hadoop', 'Thu', 1),
('1H040', 'Spark', 'Fri', 2),
('1H041', 'DevOps実践', 'Mon', 4),
('1H042', 'コンテナ技術', 'Tue', 5),
('1H043', 'Kubernetes', 'Wed', 1),
('1H044', 'CI/CDパイプライン', 'Thu', 2),
('1H045', 'クラウドインフラ', 'Fri', 3),
('1H046', 'AWS基礎', 'Mon', 5),
('1H047', 'Google Cloud Platform', 'Tue', 1),
('1H048', 'Microsoft Azure', 'Wed', 2),
('1H049', 'Linuxシステム管理', 'Thu', 3),
('1H050', 'Windows Server', 'Fri', 4);

-- 履修登録テーブルの作成
CREATE TABLE Enrollments (
    student_id CHAR(4),
    course_id CHAR(5),
    enrollment_date DATETIME,
    delete_flag BOOLEAN DEFAULT FALSE,
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
