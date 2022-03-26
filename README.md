# api-user
> Exemplo de uma API de Usuários utilizando Java com o Framework Spring-Boot, JUnit e Mockito para testes.

[![NPM Version][npm-image]][npm-url]
[![Build Status][travis-image]][travis-url]
[![Downloads Stats][npm-downloads]][npm-url]

## Exemplo de uso
 Nesta API é possível: 
 * Cadastrar um usuário com Nome, Email e Password;
 * Listar todos os usuários cadastrados;
 * Procurar um usuário por ID;
 * Atualizar um usuário por ID;
 * Excluir um usuário por ID;

## Algumas validações: 
* Não é permitido mais de um e-mail igual no cadastro. Caso já exista algum usuário com o e-mail cadastrado o sistema não permite a gravação. Serve no cadastro e na atualização do registro.

## Requisitos de Banco de Dados
Nenhum, utilizado banco H2 em memória.

## Acesso para testes no Heroku
https://app-user-spring.herokuapp.com/swagger-ui/index.html#/

## Teste via Swagger (Localhost):
![](https://user-images.githubusercontent.com/414878/157461855-e9ae9c1a-f3d1-47bf-9eb0-1b44b22f7ee4.png)

## Teste via Postman:

* POST (Salvando)
![](https://user-images.githubusercontent.com/414878/157462025-a46151d8-9b01-405e-b1f5-24a6fbd4242a.png)

* GETALL (Buscando todos)
![](https://user-images.githubusercontent.com/414878/157461921-49dce45c-d3c5-4d83-abfc-8eb059dbaefd.png)

* GETBYID (Buscando por ID)
![](https://user-images.githubusercontent.com/414878/157461977-2f6a2ead-08c8-4c34-be29-707257dd5e54.png)

* PUT (Atualizando por ID)
![](https://user-images.githubusercontent.com/414878/157462103-b4ec815e-3fd9-4c30-b94e-9d50a609872c.png)

* DELETE (Deletando por ID
![](https://user-images.githubusercontent.com/414878/157462046-c49de6d7-6928-49e4-a007-ee03990a4494.png)

## Meta

Edison de Azevedo Filho – [@azevedoedison](https://twitter.com/azevedoedison) – 
[https://www.linkedin.com/in/edison-de-azevedo/](https://www.linkedin.com/in/edison-de-azevedo/)
[https://github.com/azevedoedison/api-user](https://github.com/azevedoedison/)


[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics

