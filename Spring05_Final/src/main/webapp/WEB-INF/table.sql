-- 댓글을 저장할 테이블
CREATE TABLE board_cafe_comment(
	num NUMBER PRIMARY KEY, -- 글번호 (댓글 자체의 프라이머리 키 댓글, 대댓글 다 다르다.)
	writer VARCHAR2(100), -- 작성자
	content VARCHAR2(500), -- 내용
	target_id VARCHAR2(100), -- 댓글 대상자의 아이디 (누구에게 댓글을 쓸건지 대상자)
	ref_group NUMBER, -- 원글(카페글)의 글번호
	comment_group NUMBER, -- 댓글의 그룹번호 (한댓글의 대댓글 포함 그룹번호 ex)5)
	deleted CHAR(3) DEFAULT 'no', -- 삭제된 댓글인지 여부 'yes' or 'no'
	regdate DATE -- 댓글 작성일
);
-- 댓글의 글번호를 얻어낼 시퀀스
CREATE SQUENCE board_cafe_comment_seq;