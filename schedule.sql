-- 데이터베이스 선택
USE Schedule;

-- 테이블 생성
CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          name VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          updated_at DATETIME NOT NULL
);
