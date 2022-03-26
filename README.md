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


## Cobertura dos Testes:
![](https://user-images.githubusercontent.com/414878/160253821-30588414-96f7-4e35-ab54-aaa14afda4b2.png)

## Acesso para testes no Heroku
https://app-user-spring.herokuapp.com/swagger-ui/index.html#/

## Teste via Swagger (Localhost):
![](https://user-images.githubusercontent.com/414878/160253391-992078b2-bf6a-4724-b975-936366f033c0.png)

## Teste via Swagger:

* POST (Salvando)
![](https://user-images.githubusercontent.com/414878/160253441-acf0450b-51a6-494a-afc9-eb0f33ba3565.png)

* GETALL (Buscando todos)
![](https://user-images.githubusercontent.com/414878/160253404-770e7fa2-d9f0-4d13-afc6-be5c280fed5b.png)

* GETBYID (Buscando por ID)
![](https://user-images.githubusercontent.com/414878/160253738-10e25acc-a7f3-45c7-a0a2-b3dfe7315615.png)

* PUT (Atualizando por ID)
![](https://user-images.githubusercontent.com/414878/160253453-45ef8bf9-c24b-4f1b-a1db-7b61301eb467.png)

* DELETE (Deletando por ID
![](https://user-images.githubusercontent.com/414878/160253396-886be8c6-2dfc-4893-a0fb-3042facfb1e3.png)

## Meta

Edison de Azevedo Filho – [@azevedoedison](https://twitter.com/azevedoedison) – 
[https://www.linkedin.com/in/edison-de-azevedo/](https://www.linkedin.com/in/edison-de-azevedo/)
[https://github.com/azevedoedison/api-user](https://github.com/azevedoedison/)


[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics

