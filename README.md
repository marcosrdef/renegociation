# renegociation
1 - caso não tenha o docker e o docker-compose instalado, será necessário instalar
2 - após a instalação executar os comandos docker-compose -f docker-compose.dynamoDBlocal.yml -p dynamoDB up para subir o container do dynamoDB
e depois executar o comando docker-compose -f docker-compose.kafka.yml -p kafka up para subir o container do kafka
3 - clonar o projeto de renegociação e subir a aplicação local (quando subir ele, irá criar algumas tabelas e popular no dynamoDB)
4 - clonar o projeto de efetivação de renegociação e subir a aplicação local
5 - clonar o projeto de listagem de ofertas e subir a aplicação local
6 - o projeto é baseado em arquitetura de eventos, então executar primeiramente os serviços do projeto de renegociação, cada endpoint do controller
irá enviar para um tópico específico no kafka que será escutado por um listener no projeto de efetivação de renegociação (no caso de simulação e efetivação)
e um listener no projeto de listagem de produtos que irá de fato execucação de cada item, para consultar os valores de retorno, pegar o id da transação no momento
do envio da mensagem no kafka e utilizar no controller de cada projeto

obs.: no body da request no projeto de renegociação é necessário colocar o cpf sem caracteres especiais, só com números e dentro da lista abaixo, que está
na base
- 60013927060
- 09166650038
- 89852935089
- 66114431006
- 60650165004
- 12768334073
