-- 사용자(회원) 정보를 저장할 테이블
CREATE TABLE user_info(
	id VARCHAR2(100) CONSTRAINT user_info_id_pk PRIMARY KEY,
	pwd VARCHAR2(100) CONSTRAINT user_info_pwd_nn Not null,
	email VARCHAR2(100),
	profile VARCHAR2(100), --프로필 이미지 경로를 저장할 칼럼
	regdate DATE -- 가입일
);