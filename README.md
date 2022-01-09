# Desafio tqi_evolution_backend_2021

### 1 . Descrição 
O projeto consiste em um modelo REST API , ativo localmente, cuja entrada são dados de cliente, que solicita um determinado empréstimo, e saída é informações desse mesmo cliente e dos seus empréstimos solicitados. Sendo assim, optei por utilizar a ferramenta spring security, para autenticação do usuário cliente e administrador; o banco de dados, em memória, h2; assim como outras dependências, ver *pom.xml*. É válido salientar que utilizei o Postman para inserção e saídas na análise das variáveis e, por opção, juros compostos na definição do montante a ser pago pelo cliente, em função do número, predefinido, de parcelas, taxa de juros, no momento da solicitação, e valor do pedido de empréstimo.

### 2 . Desenvolvimento e Resultados

No projeto optei por adotar duas tabelas: User, com as entradas do usuário cliente, e LoanDetails, com detalhes do pedido de empréstimo do cliente, não relacionadas. Fiz isso devido a funcionalidade de autenticação e restrição de acesso o URL do Spring Security.

Foi feito acesso irrestrito a URL de cadastro de cliente, que, por sua vez, cadastra, também, os administradores, povoando, dessa forma, a tabela de usuários, com email único, referência ao usuário cadastrado e administrador. 
A tabela User tem como colunas as variáveis de entrada que caracterizam o usuário cliente e administrador, que são:
  * email;
  * senha;
  * roles
  * nome;
  * cpf válido, hibernate @CPF;
  * rg;
  * encereço completo,
onde "roles" são as permissões, em lista, de usuário e/ou administrador.

Como seugue, entrada JSON, no Postman, com Authorization type No Auth, livre permissão de acesso, POST USER.

```
{
"email": "helo@gmail.com",
"password": "helo0133",
"roles": ["USERS"],
"name": "Heloisa Nazaré",
"cpf": "insert valid cpf",
"rg": "56444333",
"address":"Rua das congonhas"
}
```

O modelo impõe ser o acesso a URL para inserção de detalhamento do empréstimo solicitado, pelo usuário cliente, restrito ao usuário administrador, responsável por popular a tabela LoanDetails com as entradas:
  * email;
  * código, único;
  * renda;
  * valor do empréstimo;
  * taxa;
  * número de parcelas;
  * número de meses, dado para pagar a primeira parcela, a partir do dia da requisição e suposta aprovação do empréstimo.

Como é mostrado abaixo, código de entrada, com Authorization type Basic Auth.
```  
{
"email": "helo@gmail.com",
"code":"111003",
"wage":2251.51,
"loanAmount":13000,
"feesCharged":0.016,
"numberOfInstallments":30,
"monthsToPay":2
}  
```
```
{
"email": "helo@gmail.com",
"code":"111103",
"wage":2251.51,
"loanAmount":18000,
"feesCharged":0.056,
"numberOfInstallments":50,
"monthsToPay":2
}
```

São dois os URLs no conjunto de saídas, com restrição de acesso ao usuário cliente logado, quais são: lista de empréstimo do usuário, e outra com detalhes do empéstimo e seu código específico.

O URL, lista de empéstimos, segue com as especificações de cada empréstimo:

 * código do empréstimo;
 * valor do empréstimo;
 * número de parcelas do emprestimo.

Como pode ser observado abaixo, as saídas para as entradas na tabela LoanDetails, propostas anteriormente,  mostram ter o cliente, logado com *helo@gmail.com*, duas solicitações de empréstimos, cada um com seu respectivo código, valor e números de parcelas.

```[
    [
        "111003",
        13000.0,
        30
    ],
    [
        "111103",
        18000.0,
        50
    ]
]
```
Para o caso do URL com o detalhamento de uma solicitação de empréstimo específico, as variáveis de saída são:
  * email;
  * código do empréstiomo;
  * renda;
  * valor do empréstimo;
  * o montante a ser pago pelo valor do empréstimo, incluido juros compostos;
  * valor da parcela;
  * número de parcelas;
  * data de vencimento da primeira parcela.

Quando com o id correspondente ao código único da solicitação, no exemplo ```  "111003" ```, fornece a resposta:

```
[
    [
        "helo@gmail.com",
        "111003",
        2251.51,
        13000.0,
        20929.294580728438,
        697.6431526909479,
        30,
        "2022-03-08"
    ]
].
```

Não suficiente as saídas, é possível, com o código, atualizar e deletar os pedidos de empréstimo, [Ver documentação Postman] (https://documenter.getpostman.com/view/18038992/UVXerHSH).

### 3. Conclusão 

O modelo mostra-se relevante em desempenhar a função de, com os dados de um cliente, cadastrar suas solicitações de empréstimo, detalhando-as em quantidade e forma, além de as atualizar e deletar. É relevante também que o modelo carece de mais segurança, sendo suficiente como registro de apresentação à proposta de produção, onde as restrições e permissões de usuários, cliente e administrador, satisfazerem as dependências dessa proposta. É valido salientar, por fim, que o modelo serve de protótipo a implementações relativas a armazenamento e caracterização dos dados, testes e segurança.

:hammer: Ivã Nazaré


