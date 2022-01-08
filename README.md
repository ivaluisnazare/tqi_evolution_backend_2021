# tqi_evolution_backend_2021
tqi evolution backend desafio.

### Descrição 
O projeto consiste em uma API, cuja entrada é dados de cliente, que solicita um determinado empréstimo, e saida é informações desse mesmo cliente e dos seus empréstimos solicitados. Sendo assim, opitei por utilizar a ferramenta spring security, para autenticação do usuário e administrador; o banco de dados, em mémória, h2; assim como outras dependências, ver pom.xml. É válido salientar, que utilizei, por opção, juros compostos na definição do montante a ser pago pelo cliente, em função do número, pré-definido, de parcelas e valor do emprestimo solicitado. 



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

'''

{
"email": "helo@gmail.com",
"password": "helo0133",
"roles": ["USERS"],
"name": "Heloisa Nazaré",
"cpf": "11822101557",
"rg": "56444333",
"address":"Rua das congonhas"
}

'''
 
* Variáves de emtrada referentes aos detalhes do empréstimo, LoanDetails:
  * email;
  * código, único;
  * renda;
  * valor do empréstimo;
  * taxa;
  * número de parcelas;
  * número de meses, dado para pagar a primeira parcela a partir do dia da requisição e suposta aprovação do empréstiomo.

São dois os grupos do conjunto de saída, quais são: um grupo com id e código do empréstimo, único para cada empréstimo, e outro com detalhes do empéstiomo, com código específico.

* Variáveis de saída referentes aos detalhes do empréstimo:
  * email;
  * código do empréstiomo;
  * renda;
  * valor do empréstimo;
  * o montante a ser pago pelo valor do empréstimo, incluido juros compostos;
  * valor da parcela;
  * número de parcelas;
  * data de vencimento da primeira parcela.

