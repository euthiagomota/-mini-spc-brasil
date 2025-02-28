
***Diagrama Relacional***
---

```mermaid
erDiagram
    CLIENTES {
        Long id PK
        String cpf_cnpj "UNIQUE"
        String nome
        String contato
        String endereco
        Enum status "ADIMPLENTE | INADIMPLENTE"
    }
    
    DIVIDAS {
        Long id PK
        Long cliente_id FK
        Long empresa_id FK
        Decimal valor
        Date data_vencimento
        Enum status "PENDENTE | PAGA"
    }
    
    EMPRESAS {
        Long id PK
        String cnpj "UNIQUE"
        String razao_social
        String email
        String senha
    }
    
    CLIENTES ||--o{ DIVIDAS : "possui"
    EMPRESAS ||--o{ DIVIDAS : "registra"
