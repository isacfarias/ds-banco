# Projeto ds-banco
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/isacfarias/ds-banco/blob/main/LICENSE)

O projeto ds-banco vem com a ideia de consolidar conhecimentos adquidos em minhas experiencias de trabalho, bem como implementar novas features do spring,
portanto pode ter coisa que não faça sentido, mas foi uma forma de explorar a ideia, e aplicar o maximo de recurso possivel.

# Diagrama da disposição dos microserviços
![Modelo](https://github.com/isacfarias/ds-banco/blob/feature/rabbitmq_functions/assets/ds-banco-diagrama.png)

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

Este projeto se trata de um desafio tecnico, que possui alguns requisitos a ser comprido:

Para o cadastro de pessoa foi implementado o projeto ds-banco, com os seguintes requisitos:
- pessoa
    - Tipo: Pessoa Física(PF) ou Juridica(PJ)
    - Numero de documento: (Tamanho 11 caso PF e tamanho 14 caso PJ)
    - Score da pessoa: (Valor randômico de 0 a 9)

Para a abertura de conta corrente foi implementado o projeto ds-conta-corrente
- conta-corrente
    - Numero: (6 digitos gerados únicos e gerados automaticamente)
    - Agência: (Parametrizavél via configuração)
    - Tipo: Corrente (C) ou Empresarial (E) -> Selecinado automaticamente conforme a pessoa recebida
    - Vinculo com a pessoa a qual a conta se refere
    
Para o vinculo de produtos da conta corrente foi implementado o projto ds-contacorrente-produtos com os seguinte requisitos
- produtos-conta-corrente    
    - Se score for 0 ou 1, o limite de cheque especial estará desabilitado e não irá gerar cartão de crédito.
    - Se score for entre 2 e 5 o limite de cheque especial será de R$ 1000,00 e o limite do cartão de crédito criado será de R$ 200,00
    - Se score for entre 6 e 8 o limite de cheque especial será de R$ 2000,00 e o limite do cartão de crédito criado será de R$ 2000,00
    - Se score for 9 o limite de cheque especial será de R$ 5000,00 e o limite do cartão de crédito criado será de R$ 15000,00
    
    
Para deixamos as coisas mais emocionantes, foi implementado um serviço só para produtos
- produto
    - As faixas de limite poder ser parametrizaveis
   
Temos os microsservicos 
- gateway que serve para identifcar nossas rotas
- eureka-serve que faz o registro do nossos micro serviços de forma que não precisamos identificar em qual porta eles estão sendo executado;


## Quer testar os aplicativos

Nesse caso você precisa fazer o clone do mono repo acima, e importar eles na IDE de preferencia, ou pode compilar os .jar com maven e gerar as imagens docker, que usaremos no nosso arquivo docker-compose, complicado, segue o fio:

Caso tenha o maven intalado na sua maquina:
você pode no seu terminal executar os comandos abaixo:

 - *mvn clean package* - nesse caso ele irá limpar o projeto e gerar seu .jar, porem ele irar executar os testes para garantir a compilação
 - *mvn clean package -DskipTests* - nesse caso ele irá limpar o projeto e gerar seu .jar, porem ele vai pular os testes;

 como estamos usando o plugin com.spotify, ao realizar o empacotamento com o maven já teremos nossas imagens docker prontas para usogit 
