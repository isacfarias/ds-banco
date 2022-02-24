# Projeto ds-banco
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/isacfarias/ds-banco/blob/main/LICENSE)

O projeto ds-banco vem com a ideia de consolidar conhecimentos adquiridos em minhas experiências de trabalho, bem como implementar novas features do spring,
portanto pode ter coisas que não faça sentido, mas foi uma forma de explorar a ideia, e aplicar o maxímo de recurso possivel.

# Diagrama da disposição dos Microsserviços
![Modelo](https://github.com/isacfarias/ds-banco/blob/main/assets/ds-banco-diagrama.png)

## Tecnologias

esse projeto usa:
- Java 11
- Spring Boot 2.5.9
- Spring Web
- Spring JPÀ
- Spring cloud 2020.0.3
- Spring cloud gateway
- Feing
- Eureka Server/Cliente
- RabbitMQ
- Lombook
- MapStrucs
- Swagger
- h2

# Sobre o Projeto

Este projeto se trata de um desafio tecnico, que possui alguns requisitos a ser cumprido:

Para o cadastro de pessoa foi implementado o projeto ds-banco, com os seguintes requisitos:
- pessoa
    - Tipo: Pessoa Física(PF) ou Juridica(PJ)
    - Numero de documento: (Tamanho 11 caso PF e tamanho 14 caso PJ)
    - Score da pessoa: (Valor randômico de 0 a 9)

Para a abertura de conta corrente foi implementado o projeto conta-corrente
- conta-corrente
    - Numero: (6 digitos gerados únicos e gerados automaticamente)
    - Agência: (Parametrizavél via configuração)
    - Tipo: Corrente (C) ou Empresarial (E) -> Selecinado automaticamente conforme a pessoa recebida
    - Vinculo com a pessoa a qual a conta se refere
    
Para o vinculo de produtos da conta corrente foi implementado o projeto contacorrente-produtos com os seguinte requisitos
- produtos-conta-corrente    
    - Se score for 0 ou 1, o limite de cheque especial estará desabilitado e não irá gerar cartão de crédito.
    - Se score for entre 2 e 5 o limite de cheque especial será de R$ 1000,00 e o limite do cartão de crédito criado será de R$ 200,00
    - Se score for entre 6 e 8 o limite de cheque especial será de R$ 2000,00 e o limite do cartão de crédito criado será de R$ 2000,00
    - Se score for 9 o limite de cheque especial será de R$ 5000,00 e o limite do cartão de crédito criado será de R$ 15000,00
    
    
Para deixarmos as coisas mais emocionantes, foi implementado um serviço só para produtos
- produto
    - As faixas de limite poder ser parametrizaveis
   
Temos os microsservicos 
- gateway que serve para identifcar nossas rotas
- eureka-serve que faz o registro do nossos microsserviços de forma que não precisamos identificar em qual porta eles estão sendo executado;


## Quer testar os aplicativos

Nesse caso você precisa fazer o clone do mono repo acima, e importar eles na IDE de sua preferência, ou você pode compilar os .jar com maven e gerar as imagens docker, que usaremos no nosso arquivo docker-compose mais tarde, complicado, segue o fio:

Caso tenha o maven instalado na sua maquina:
No seu terminal execute os comandos abaixo:

 - *mvn clean package* - nesse caso ele irá limpar o projeto e gerar seu .jar, porem ele irar executar os testes para garantir a compilação
 
 ou

 - *mvn clean package -DskipTests* - nesse caso ele irá limpar o projeto e gerar seu .jar, porem ele vai pular os testes;

 Como estamos usando o plugin com.spotify, ao realizar o empacotamento com o maven, já teremos as imagens docker prontas ao final do build.
 ![docker-images](https://github.com/isacfarias/ds-banco/blob/main/assets/docker-images.png)

 Com o processo de build finalizado é hora de subir os microsserviçoes, temos o nosso docker-compose na raiz do repo todo configurado, sendo necessário executar o comando:
 - docker-compose up -d

 ![docker-compose](https://github.com/isacfarias/ds-banco/blob/main/assets/docker-compose.png)

Com isso teremos os serviços rabbitMQ, gateway, eureka-server, pessoa, conta-corrente, conta-corrente-produtos, produtos online.

Feito isso já é possivel ver os serviços online: http://localhost:8761/
![eureka-server](https://github.com/isacfarias/ds-banco/blob/main/assets/eureka-server.png)


Caso queira conhecer os endpoint disponivel e o modelo de dados: http://localhost:8765/swagger-ui/

Tambem é possivel importa o arquivo client para o Postman ou Insomnia: [client_rest_api.json](https://github.com/isacfarias/ds-banco/blob/main/cliente_rest_api.json)

![doc](https://github.com/isacfarias/ds-banco/blob/main/assets/swagger.png)
