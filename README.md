# Desafio Técnico Sicredi

Microsserviço responsável por gerenciar Pautas de Votação dos Associados da Cooperativa.

## Descrição

Para iniciar um processo de votação, inicialmente deve ser criada uma Pauta, em seguida deve ser criada uma
Sessão de Votação, para então permitir que os associados registrem seus Votos na sessão. Por fim, os Votos
da Sessão de Votação ou da Pauta (incluindo todas as sessões) podem ser contabilizados.

Esta aplicação permite o agendamento de Sessões de Votação, definindo uma data/hora de início e um período 
de validade(em minutos). É possível agendar N Sessões de Votação para a mesma Pauta.

A contabilização de Votos da Pauta pode ser acessada a partir do momento da criação da Pauta, sendo possível
habilitar ou desabilitar o detalhamento dos votos por Sessão de Votação.

A contabilização de Votos da Sessão de Votação pode ser acessada a partir do momento da criação da Sessão 
de Votação.

### Persistência de dados
Por padrão está habilitado o banco Postgres, porém é possível alterar
facilmente para o banco H2 (com persistência de dados em arquivo),
editando as propriedades spring.datasource do arquivo application.properties:
```
##H2
spring.h2.console.enabled=true
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:file:./data/test
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

##Postgres
#spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
#spring.datasource.username=postgres
#spring.datasource.password=password
```
Para execução dos testes de integração, é utilizado o banco H2 em memória.

### Validade padrão da Sessão de Votação
Para alterar a validade padrão da sessão da votação (quando o usuário não informa a validade na criação da sessão),
basta alterar a configuração no arquivo application.properties:
```
#s = segundos, m = minutos, h = horas, d = dias
config.validade-padrao-sessao-votacao=1m
```

### Validação do Associado
Para validação do CPF do associado pode ser escolhida a forma de validação entre validação interna ou consulta 
em API externa. Basta alterar a configuração no arquivo application.properties:
```
#### Validador de CPF ###
#true = utiliza api, false = utiliza validador interno
config.cpf-validacao-externa=true
```

## Pré-requisitos

- Desenvolvimento
  - JDK 20+ (JDK 21 recomendado) 
- Execução 
  - A) Java 20+ 
  - B) Docker + docker-compose

### **Dependências de Compilação e Runtime**
- SpringBoot [[Doc](https://spring.io/projects/spring-boot)]
  - Framework JAVA.
- SpringDoc OpenAPI / Swagger [[Doc](https://springdoc.org/)]
  - Interface do usuário para renderizar visualmente a documentação de uma API definida com a especificação OpenAPI.
- Spring Boot Actuator / Health [[Doc](https://docs.spring.io/spring-boot/docs/2.5.6/reference/html/actuator.html)]
  - Monitoramento da integridade do serviço.
- Spring Boot Starter Validation [[Doc](https://www.baeldung.com/spring-boot-bean-validation)]
  - Validador de propriedades do objeto (campo, getter) e os parâmetros do método para seus beans (REST, CDI, JPA).
- Spring Boot Starter Cache [[Doc](https://www.baeldung.com/spring-cache-tutorial)]
  - Cache de dados do aplicativo Utilizando CDI.
- Spring Boot Data JPA [[Doc](https://spring.io/projects/spring-data-jpa)]
  - Acesso a dados.
- Spring Boot Dev Tools [[Doc](https://www.baeldung.com/spring-boot-devtools)]
  - Facilitador de configuração e execução da aplicação. Ex: Live Reload.
- Flyway [[Doc](https://documentation.red-gate.com/fd#)]
  - Gerenciador de migrations do banco de dados.
- JDBC Driver - Postgres
  - Conexão ao banco de dados PostgresSQL via JDBC.
- Lombok [[Doc](https://projectlombok.org/features/all)]
  - Utilização de Annotations pra geração de código verboso/repetitivo (getter, setter, construtores, etc).

### **Dependências de Testes**

- Spring Boot Starter Test
  - Ferramentas para criação de testes de integração e de unidade.
- REST-assured [[Doc](https://rest-assured.io/)]
  - Biblioteca que permite testar serviços REST em Java.
- JaCoCo [[Doc](https://www.eclemma.org/index.html)]
  - Geração de relatório de cobertura de testes.
- JDBC Driver - H2 
  - Conexão ao banco de dados em memória H2 via JDBC.

## Estrutura do projeto

```
/src/com/example/desafiotecnicosicredi  # Projeto
  /client                        # Client REST (integração com outros APPs)
  /configuration                 # Configuração do microsserviço
  /constants                     # Constantes da aplicação
  /dto                           # Objetos de transferência de dados entre camadas
  /endpoint                      # Recursos/Endpoints do MS
  /entity                        # Entidades que compõem o domínio
  /enums                         # Enumeradores do MS
  /exception                     # Exceções personalizadas
    /handler                     # Manipulador de exceções
  /mapper                        # Mapeadores de DTOs para Entidades e vice-versa
  /repository                    # Objetos de acesso a dados utilizando Repository Pattern
  /service                       # Serviços da Aplicação
  /utils                         # Classes utilitárias de auxílio ao desenvolvimento
  /validation                    # Classes de validações
```

## Execução do projeto

### A) Execução local, via maven (necessário JAVA instalado)

Navegue até o diretório raiz do projeto e siga as instruções abaixo.

Caso seu sistema não esteja com JDK 21 instalado como variável de ambiente, serão necessários alguns comandos para garantir que seu terminal
reconheça o uso do java.

Se esse for seu caso, execute os seguintes comandos:

```bash
export JAVA_HOME="<seu_caminho_customizado_jdk21>"
export PATH=$JAVA_HOME/bin:$PATH
```
<br>

#### Execução da aplicação
```bash
./mvnw spring-boot:run
```

Após a satisfação de dependências e subida da interface, a aplicação estará no ar!

Para verificar, acesse:

http://localhost:8090/swagger-ui/index.html

<br>

#### Execução de testes
Para executar testes, usar o comando:
```bash
./mvnw test
```

#### Cobertura de testes

Para gerar o Relatório de cobertura de código, rodar o comando `verify`

Para executar os testes, usar o comando:

```bash
./mvnw clean verify
```

Após conclusão, abrir no navegador o arquivo

```
<diretorio_raiz_projeto>/target/site/jacoco/index.html
```

### B) Execução via Docker Compose (necessário Docker e docker-compose instalados)

Para compilar o projeto, gerar o pacote executável (jar)
e executar a aplicação:
```bash
docker-compose up --build
```
Para somente executar a aplicação
```bash
docker-compose up
```

<br>

## Documentação da API

Para visualizar a documentação dos endpoints do microsserviço acesse o link:

http://localhost:8090/swagger-ui/index.html

Para obter o arquivo OpenAPI 3.0:

http://localhost:8090/v3/api-docs

<br>

### **Validações**

Este projeto utiliza o `Hibernate Validator` para realizar as validações dos campos dos objetos.

Para mais detalhes de como realizar as validações, [consultar este tutorial](https://docs.jboss.org/hibernate/validator/8.
0/reference/en-US/html_single/#validator-gettingstarted-createmodel).

#### **_Exemplo_**

Exemplo de classe com validações:

- Primeiro adicione annotations nos campos que desejas validar

  ```java
  public class VotoRequestDTO {
    @NotNull
    private OpcaoVoto opcao;

    @NotNull
    @CPFValid
    private String cpfAssociado;

    @NotNull
    private Long idSessao;
  }
  ```

#### **_Annotations e Validators customizados_**

Para atender algumas necessidades do projeto, foram criadas `Annotations` e `Validators` customizados, tais como:

- CPFValid
  - Validar se um número informado é um CPF válido

Confira detalhes no pacote `validation`