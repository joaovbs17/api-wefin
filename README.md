## Descrição
**WEFIN API** é um sistema de conversão de produtos e moedas desenvolvido com conceito de **API First**. O projeto implementa um sistema de comércio onde é possível gerenciar reinos, moedas, produtos e realizar conversões entre diferentes moedas e cálculos de produtos.

### Tecnologias Utilizadas
- Java 21 
- Spring Boot 3.4.5
- PostgreSQL
- Maven

### Pré-requisitos
- JDK 21
- Maven 3.8+
- PostgreSQL 14+

### Funcionalidades Principais

- **🏰 Gestão de Reinos**: CRUD completo para reinos
- **💰 Gestão de Moedas**: CRUD completo para moedas
- **📦 Gestão de Produtos**: CRUD completo para produtos
- **💱 Conversão de Moedas**: Sistema de conversão entre moedas
- **🔄 Conversão de Produtos**: Cálculo de produtos em diferentes moedas
- **📊 Taxa de Câmbio**: Gestão de taxas de câmbio entre moedas
- **💳 Transações**: Sistema de transações

### Script SQL

```SQL
CREATE TABLE public.tb_reino (
    id bigserial NOT NULL,
    nome varchar(255) NOT NULL,
    CONSTRAINT tb_reino_pkey PRIMARY KEY (id),
    CONSTRAINT reino_unique_01 UNIQUE (nome)
);
CREATE TABLE public.tb_moeda (
    id bigserial NOT NULL,
    nome varchar(255) NOT NULL,
    sigla varchar(255) NOT NULL,
    reino_origem_id int8 NOT NULL,
    CONSTRAINT tb_moeda_pkey PRIMARY KEY (id),
    CONSTRAINT fk_reino_origem FOREIGN KEY (reino_origem_id) REFERENCES public.tb_reino(id),
    CONSTRAINT moeda_unique_01 UNIQUE (nome),
    CONSTRAINT moeda_unique_02 UNIQUE (sigla)
);
CREATE TABLE public.tb_taxa_cambio (
    id bigserial NOT NULL,
    data_hora timestamp(6) NOT NULL,
    taxa numeric(38, 2) NOT NULL,
    moeda_destino_id int8 NOT NULL,
    moeda_origem_id int8 NOT NULL,
    CONSTRAINT tb_taxa_cambio_pkey PRIMARY KEY (id),
    CONSTRAINT fk_moeda_origem FOREIGN KEY (moeda_origem_id) REFERENCES public.tb_moeda(id),
    CONSTRAINT fk_moeda_destino FOREIGN KEY (moeda_destino_id) REFERENCES public.tb_moeda(id)
);
CREATE TABLE public.tb_produto (
    id bigserial NOT NULL,
    nome varchar(255) NOT NULL,
    moeda_id int8 NULL,
    CONSTRAINT tb_produto_pkey PRIMARY KEY (id),
    CONSTRAINT fk_moeda FOREIGN KEY (moeda_id) REFERENCES public.tb_moeda(id)
);

CREATE TABLE public.tb_transacao (
    id bigserial NOT NULL,
    data_hora timestamp(6) NOT NULL,
    valor_convertido numeric(38, 2) NOT NULL,
    valor_origem numeric(38, 2) NOT NULL,
    produto_id int8 NULL,
    reino_origem_id int8 NOT NULL,
    taxa_cambio_id int8 NOT NULL,
    CONSTRAINT tb_transacao_pkey PRIMARY KEY (id),
    CONSTRAINT fk_taxa_cambio FOREIGN KEY (taxa_cambio_id) REFERENCES public.tb_taxa_cambio(id),
    CONSTRAINT fk_reino_origem FOREIGN KEY (reino_origem_id) REFERENCES public.tb_reino(id),
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES public.tb_produto(id)
);
```
