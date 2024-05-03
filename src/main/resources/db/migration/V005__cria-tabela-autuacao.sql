CREATE TABLE autuacao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    veiculo_id BIGINT NOT NULL,
    descricao varchar(255) NOT NULL,
    valor_multa DECIMAL(10,2) NOT NULL,
    data_ocorrencia DATETIME NOT NULL,
    PRIMARY KEY (id)
);

alter table autuacao add constraint fk_autuacao_veiculo
foreign key (veiculo_id) references veiculo(id);