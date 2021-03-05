# Teste Símios Mercado Livre

API Rest para identificar DNA de Símios e humanos.

## API endpoints

### Local

```shell
POST http://localhost:8080/simian [analisa uma sequência de DNA]
GET http://localhost:8080/stats [statisticas dos DNAs consultados]
```

### AWS

```shell
POST http://simios-api.sa-east-1.elasticbeanstalk.com/simian [analisa uma sequência de DNA]
GET http://simios-api.sa-east-1.elasticbeanstalk.com/stats [statisticas dos DNAs consultados]
```

## Pré-requisitos

Para compilar e executar a aplicação localmente você vai precisar:

- [JDK 1.8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
- [MySQL 8.0](https://dev.mysql.com/downloads/mysql/)
- [Spring Tools](https://spring.io/tools) (recomendado)
- [Lombok](https://projectlombok.org/download)

## Configuração

Faça o clone do projeto:

```shell
git clone https://github.com/wallacerlima/teste-simios.git
```

Alterar o arquivo application.properties do com as informações de conexão do banco de dados.

```shell
spring.datasource.url=jdbc:mysql://{SEU_HOST}:{SUA_PORTA}/simians?createDatabaseIfNotExist=true
spring.datasource.username={SEU_USUARIO}
spring.datasource.password={SUA_SENHA}
```

Execute o projeto
```shell
mvn spring-boot:run
```
