CREATE TABLE "test_data"
(
    id   INT,
    key VARCHAR(128) NOT NULL,
    updated_count INT NOT NULL
);

-- 创建序列
create sequence test_data_id_seq increment by 1 minvalue 1 no maxvalue start with 1;

