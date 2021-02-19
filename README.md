# ficticius-clean

#### Rodar a aplicação com MYSql usando docker

* Executar `docker-compose up` na raiz do projeto e um container com a aplicação e outro o MYSql já configurado será levantando; 
* A aplicação está disponível em `http://localhost:8080`

#### Rodar a aplicação usando banco em memória (H2)
* Necessário ter uma versão do java 11+;
* Você rodar a aplicação com a sua IDE de preferência;
* Executar `java -jar ficticius-clean.jar` na reaiz do projeto;
* A aplicação está disponível em `http://localhost:8080`

#### Testes de unidade e integração
* Os testes de unidade estão em src/test;
* Os testes de integração estão em src/integration e são rodados com o banco H2;

#### API
* A documentação da API estão disponível em `http://localhost:8080/swagger-ui.html`
