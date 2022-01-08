# tqi_evolution_backend_2021
tqi evolution backend desafio. 

#### Descrição 
O projeto consiste em uma REST API , ativa localmente, cuja entrada é dados de cliente, que solicita um determinado empréstimo, e saida é informações desse mesmo cliente e dos seus empréstimos solicitados. Sendo assim, opitei por utilizar a ferramenta spring security, para autenticação do usuário e administrador; o banco de dados, em mémória, h2; assim como outras dependências, ver pom.xml. É válido salientar, que utilizei, por opção, juros compostos na definição do montante a ser pago pelo cliente, em função do número, pré-definido, de parcelas e valor do emprestimo solicitado. 

* Dependências:
  * Spring Security;
  * h2;
  * Lombok.

No projeto optei por adotar duas tabelas: User, com as entradas do usário cliente, e LoanDetails, com detalhes do pedido de empréstimo do cliented, não relacionadas, fiz isso devido a fulcionalidade de autenticação e restrição de acesso a URL do Spring Security.

* Variáveis de entrada referentes ao usuário, cliente, tabela User:
  * email;
  * senha;
  * nome;
  * cpf válido, hibernate @CPF;
  * rg;
  * encereço completo.

Como seugue, entrada JSON, no Postman, Authorization type No Auth,  com livre permissão de acesso, POST USER,

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
 
* Variáves de entrada referentes aos detalhes do empréstimo, LoanDetails:
  * email;
  * código, único;
  * renda;
  * valor do empréstimo;
  * taxa;
  * número de parcelas;
  * número de meses, dado para pagar a primeira parcela a partir do dia da requisição e suposta aprovação do empréstiomo.

Como é mostrado abaixo, código de entrada, com restrição de acesso principal ao administrador, Authorization type Basic Auth,

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

São dois os grupos do conjunto de saída, quais são: um grupo com id e código do empréstimo, único para cada empréstimo, e outro com detalhes do empéstiomo, com código específico.

* Variáveis de saída referentes à lista de empréstimos solicitados pelo cliente, com restrição de acesso ao usuário logado:
 * código do empréstimo;
 * valor do empréstimo;
 * número de empréstimo.

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

* Variáveis de saída referentes aos detalhes do empréstimo, também com restrição de acesso ao usuário logado:
  * email;
  * código do empréstiomo;
  * renda;
  * valor do empréstimo;
  * o montante a ser pago pelo valor do empréstimo, incluido juros compostos;
  * valor da parcela;
  * número de parcelas;
  * data de vencimento da primeira parcela.

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
]
```

[Ver documentação Postman] (https://documenter.getpostman.com/view/18038992/UVXerHSH).


