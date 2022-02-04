# Credit analysis - Back-end Java solution

## Problema:

Uma empresa de empréstimo precisa criar um sistema de análise de crédito para fornecer aos seus clientes as seguintes 
funcionalidades:

##### i. Cadastro

O cliente pode cadastrar: nome, e-mail, CPF, RG, endereço completo, renda e senha.

##### ii. Login

A autenticação será realizada por e-mail e senha.

##### iii. Solicitação de empréstimo

Para solicitar um empréstimo, precisamos do valor do empréstimo, data da primeira parcela e quantidade de parcelas.
O máximo de parcelas será 60 e a data da primeira parcela deve ser no máximo 3 meses após o dia atual.

##### iv. Acompanhamento das solicitações de empréstimo
O cliente pode visualizar a lista de empréstimos solicitados por ele mesmo e também os detalhes de um dos seus empréstimos.
Na listagem, devemos retornar no mínimo o código do empréstimo, o valor e a quantidade de parcelas.
No detalhe do empréstimo, devemos retornar: código do empréstimo, valor, quantidade de parcelas, data da primeira parcela, e-mail do cliente e renda do cliente.


## Stack e módulos:

- Java 11
- PostgresSQL
- Spring Boot
- Spring Security
- Lombok

## Solução:

Para solucionar o problema, escolhi implementar uma API REST na linguagem Java utilizando o framework Spring Boot e um banco de dados real PostgresSQL.
Como exigia autenticação(login), aprendi um pouco sobre e utilizei o Spring Security.

Criei todo o corpo da minha API REST, com as entidades Cliente e Empréstimo.

- O usuário efetua o cadastro. Aqui utilizei o Postman para fazer as requisições no formato JSON.
- O usuário autenticado pode fazer um pedido de empréstimo.
- Na hora que o usuário faz o pedido, a ideia foi capturar o usuário "logado" e já registrar na tabela de empréstimo, no momento do pedido, para que ele não precise introduzir 
no empréstimo alguma informação já cadastrada na tabela Cliente. Isso, tendo em vista conectar as duas tabelas. 
- Já que estava utilizando o Security, coloquei também um encriptador para proteger as senhas dos usuários.

- Pretendo ainda colocar confirmação de e-mail, para que `enable = true` na Entidade Cliente.
- E também aprender um pouco sobre JWT e fazer a autenticação por Token.
- Explorar o UserRole, e implementar o restante do CRUD com permissões para USER e ADMIN.


### Pontos do projeto

- API REST com os métodos HTTP, resolvendo regras de negócio nas classes de Serviço em que o usuário não tem acesso a informações desnecessárias. O que deixa a aplicação mais segura.

- Autenticação e links protegidos pelo Security e senhas encriptadas.

- Database real instalado localmente.

### Fazendo:

No momento em que escrevo esse README e faço o primeiro commit, falta ainda conseguir fazer o último item (iv) do Problema.

Isto é, fazer com que o Cliente tenha acesso a somente os seus empréstimos.