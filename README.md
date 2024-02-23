# CashFlow PRO

Um API de controle de despesas pessoais

## Endpoints

- Categorias

  - Listar todas
  - [Detalhar](#Detalhar-Categorias)
  - Cadastrar
  - Apagar
  - Editar

- Movimentacoes

---

### Listar todas

' GET ' /categoria

retorna um array com todas as categorias cadastradas.

**Exemplo de Resposta**

```json
[
  {
    "id": 1,
    "nome": "Alimentacao",
    "icone": "fast-food"
  }
]
```

**Codigos de Status**

| codigo | Descricao                    |
| ------ | ---------------------------- |
| 200    | Dados retornados com sucesso |

### Detalhar Categorias

`Get` /categoria/{id}

```json
{
  "id": 1,
  "nome": "Alimentacao",
  "icone": "fast-food"
}
```

**Codigo De Status**

| Codigo | Descricao                                  |
| ------ | ------------------------------------------ |
| 200    | Dados Retornados com sucesso               |
| 404    | Id da categoria nao encontrado (Not Found) |

### Cadastrar Categoria

`POST` /categoria

Insere uma nova categoria

**Corpo da Requisicao**

| Campo | Tipo   | Obrigatorio | descricao                                 |
| ----- | ------ | :---------: | ----------------------------------------- |
| Nome  | String |      S      | Um nome curto para a categoria            |
| Icone | String |      N      | O nome do icone conforme o material Icons |

**Categoria**
**Exemplo de Corpo da requisicao**

```json
{
  "nome": "Alimentacao",
  "icone": "fast-food"
}
```

**Exemplo de Resposta**

```json
[
  {
    "id": 1,
    "nome": "Alimentacao",
    "icone": "fast-food"
  }
]
```

**Codigo de Status**

| Codigo | Descricao                                           |
| ------ | --------------------------------------------------- |
| 201    | Categoria criada com sucesso                        |
| 400    | Erro de validacao - verifique o corpo da requisicao |

### Apagar Categorias

`Delete` /categoria/{id}

Apaga a categoria com o `id` informado

**Codigo de Status**

| Codigo | Descricao                                      |
| ------ | ---------------------------------------------- |
| 204    | No Content - Categoria foi apagada com sucesso |
| 404    | Id da categoria noa encontrado                 |

### Editar Categoria
