CREATE TABLE "user"
(
    id   INT,
    name VARCHAR(20) NOT NULL
);

-- 创建序列
create sequence user_id_seq increment by 1 minvalue 1 no maxvalue start with 1;

