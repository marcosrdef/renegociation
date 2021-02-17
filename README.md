# renegociation
1 - caso não tenha o docker e o docker-compose instalado, será necessário instalar
2 - após a instalação executar os comandos docker-compose -f docker-compose.yml -p renegociation up para subir o container do dynamoDB,do zookeeper e do kafka
3 - clonar o projeto de renegociação e subir a aplicação local (quando subir ele, irá criar algumas tabelas e popular no dynamoDB)
4 - clonar o projeto de efetivação de renegociação e subir a aplicação local
5 - clonar o projeto de listagem de ofertas e subir a aplicação local
6 - o projeto é baseado em arquitetura de eventos, então executar primeiramente os serviços do projeto de renegociação, cada endpoint do controller
irá enviar para um tópico específico no kafka que será escutado por um listener no projeto de efetivação de renegociação (no caso de simulação e efetivação)
e um listener no projeto de listagem de produtos que irá de fato execucação de cada item, para consultar os valores de retorno, pegar o id da transação no momento
do envio da mensagem no kafka e utilizar no controller de cada projeto
7 - para fazer o fluxo completo, clonar os 3 projetos, renegociation, effectiveRenegociation e offers, cada um já está configurado
para executar em um porta diferente, subir as 3 aplicações de forma local, mas antes disso instalar e subir os containers no docker compose
para subir o dynamoDB, o zookeeper e o kafka
8 - na solução desenvolvida está sendo usado apenas um banco, por questões de limitação no momento de executar na máquina local, mas a ideia
é ter um banco separado em cada microserviço, terem microserviços com banco independentes, com consistência eventual e aproveitando os recursos
de eventos e histórico de eventos do kakfa que já tem uma consistência

obs.: no body da request no projeto de renegociação é necessário colocar o cpf sem caracteres especiais, só com números e dentro da lista abaixo, que está
na base
- 60013927060
- 09166650038
- 89852935089
- 66114431006
- 60650165004
- 12768334073

exemplo de chamada de solicitação de simulação:

Verbo POST
recurso: simulation request

endpoint: http://localhost:8092/simulation


body request:
{
    "document": "89852935089" (cpf do cliente)
}

exemplo de response:

{
    "groupSimulationId": "850c9566-8190-4152-a286-361e4a05e27f",
    "documentId": "89852935089",
    "date": "Wed Feb 17 12:43:55 BRT 2021",
    "message": "Processando solicitação"
}

obs.: o campo do body response groupSimulationId é utilizado para fazer a consulta da simulação no microserviço effectiveRenegociation,
no endpoint simulation consultation e também é utilizado para fazer a solicitação de uma renegociação no microserviço renegociation (o mesmo que executou
para solicitar a simulação)

exemplo de chamada de solicitação de renegociação:

Verbo POST
recurso: renegociation request
endpoint: http://localhost:8092/renegociation

body request:
{
    "document": "12768334073", (cpf do cliente)
    "simulationId": "31763879-97d7-4160-a172-667beaa376fa", (id do item escolhido na lista de simulações
															 ,retornado no microserviço effectiveRenegociation,
															 no endpoint simulation consultation, campo id)
    "groupSimulationId": "22b9bc27-e6ed-4466-848e-3e8a788b2ab2" (id do grupo de simulação, retornado no microserviço		
																 renegociation, no endpoint simulation request)
}

exemplo de response:

{
    "transactionId": "39894e9e-5482-4cab-8e14-1fc8ec812d72",
    "date": "Wed Feb 17 12:50:16 BRT 2021",
    "message": "Processando solicitação",
    "documentId": "12768334073",
    "simulationId": "31763879-97d7-4160-a172-667beaa376fa",
    "groupSimulationId": "22b9bc27-e6ed-4466-848e-3e8a788b2ab2"
}

campo transactionId pode ser utilizado no microserviço effectiveRenegociation, no endpoint renegociation consultation,
para fazer a consulta que foi solicitada 

exemplo de chamada de solicitação de listagem de ofertas:

Verbo POST
recurso: products request
endpoint: http://localhost:8092/offers
body da request:
{
    "document": "12768334073" (cpf do cliente)
}

exemplo de response:

{
    "groupOffersId": "c50a6a0f-51bf-4d97-a789-2ea07383f666",
    "date": "Wed Feb 17 13:40:12 BRT 2021",
    "message": "Processando solicitação",
    "documentId": "12768334073",
    "customerType": "3"
}

o campo groupOffersId pode ser utilizado para consulta das ofertas solicitadas para o cliente no microserviço offers
no endpoint offers consultation para consultar as ofertas geradas para o cliente
