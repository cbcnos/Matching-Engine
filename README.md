# Matching Engine
Matching Engine criada como exercício para o processo seletivo do Banco Morgan Stanley.

Recebe entradas com os seguintes argumentos:
 * Tipo (limit/market)
 * Side (buy/sell)
 * Price (quando order for limit)
 * Qty

A classe Main é responsável por processar as entradas e saídas, além de efetuar as transações quando possível.

A classe OrderList é responsável por armazenar as ordens do tipo limit e garantir os Bonus points das seguite formas:
* Implementa um TreeMap para armazenar as ordens de compra ou venda com acesso em tempo (log n), sendo n o número de ordens
* A chave para cada posição no TreeMap é o preço da ordem e o valor é uma LinkedList, que armazena as ordens de mesmo valor na ordem que foram inseridas

## Observações
* Limit orders com preços que gerariam trades serão efetuados imediatamente, pois considerou-se que esse seria o comportamento esperado de uma engine real

* É possível verificar o estado atual das listas inserindo uma entrada não reconhecida, por exemplo:

![image](https://user-images.githubusercontent.com/4401086/140084003-8c0f177b-f15d-417e-9154-3e35e718b58c.png)

* O projeto foi desenvolvido, compilado e testado utilizando a Apache Netbeans IDE 12.5

## Exemplo de entrada e saída

![image](https://user-images.githubusercontent.com/4401086/140084891-4709e006-e98f-4477-bc68-4bd574e97300.png)
