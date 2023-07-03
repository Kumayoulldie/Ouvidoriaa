## Projeto de ouvidoria hospitalar para o A3
Projeto de semestre da Faculdade UNIFG. 
O projeto consiste em um sistema de ouvidoria hospitalar, onde o paciente pode fazer uma reclamação, sugestão ou elogio sobre o atendimento recebido no hospital. 

O ouvidor pode responder a reclamação, sugestão ou elogio do paciente.

Foi implementado um sistema de login, onde o paciente ou ouvidor pode se cadastrar e fazer login no sistema. Cada manifestação pode ter uma ou mais respostas do hospital, tanto do paciente, quanto do ouvidor.

O ouvidor pode fechar reclamações e ver reclamações de qualquer usuário, o usuário só pode ver suas próprias reclamações.

Sistema de hash de senhas não foi implementado.

Algumas funções como configurações de conta e tipo de usuário paciente e familiar também não foram implementados.

## É necessário rodar o script de criação do banco de dados para o funcionamento do sistema.
Usuário MYSQL criado usando os seguintes comandos:
```sql
CREATE USER 'admin'@'%' IDENTIFIED 
WITH mysql_native_password BY 'Ouvidoria3' ;
GRANT ALL PRIVILEGES ON . TO 'admin'@'%' ;
FLUSH PRIVILEGES;
```
## Não há tratamento de erros, pois não foi passado em aula
## Não há validação de dados, pois não foi passado em aula
Algumas tabelas podem não serem criadas sem dar erro algum por terem títulos grandes demais, por exemplo.