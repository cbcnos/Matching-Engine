package com.morgan.engine;

import java.util.Scanner;

/**
 * Matching Engine criada como exercício para o processo seletivo do Banco Morgan Stanley.
 * 
 * Recebe entradas com os seguintes argumentos:
 * - Tipo (limit/market)
 * - Side (buy/sell)
 * - Price (quando order for limit)
 * - Qty
 */
public class Main {
    
    // função reponsável por logar uma trade efetuada
    private static void printTrade (Order order) {
        System.out.println("Trade, price: " + order.price + ", qty: " + order.qty);
    }

    public static void main(String[] args) {
        
        // cria orderlists para as ordens de compra e de venda
        OrderList buyOrders = new OrderList();
        OrderList sellOrders = new OrderList();
        
        // scanner responsável por ler as entradas do usuário
        Scanner scanner = new Scanner(System.in);
        
        // loop principal que processará as entradas
        while (true) {
            
            // passa uma linha de entrada para um novo scanner que irá extrair as informações
            Scanner orderScanner = new Scanner(scanner.nextLine());
            
            // lê o tipo da ordem
            String type = orderScanner.next();
            
            // verifica se a ordem é limit ou market
            // operações do tipo limit:
            if (type.equals("limit")) {
                
                // lê o side da ordem
                String side = orderScanner.next();
                
                // lê o preço e a quantidade da ordem e coloca num objeto ordem
                Order newOrder = new Order();
                newOrder.price = orderScanner.nextInt();
                newOrder.qty = orderScanner.nextInt();
                
                // verifica se é uma ordem de compra
                if (side.equals("buy")) {
                    
                    // verifica se é possível gerar uma trade imediatamente
                    Order cheapestOrder = sellOrders.getFirst();
                    while (!sellOrders.isEmpty() && newOrder.qty > 0 && cheapestOrder.price <= newOrder.price) {
                        
                        // garante que não será comprado mais do que o solicitado
                        if (cheapestOrder.qty >= newOrder.qty) {
                            cheapestOrder.qty = newOrder.qty;
                        }
                        
                        // efetua a compra
                        sellOrders.decreaseOrder(cheapestOrder);
                        newOrder.qty -= cheapestOrder.qty;
                        printTrade(cheapestOrder);
                    }
                    
                    // caso não tenha comprado imediatamente, adiciona na lista
                    if (newOrder.qty > 0) {
                        buyOrders.addOrder(newOrder);
                    }
                }
                // verifica se é uma ordem de venda
                else if (side.equals("sell")) {
                    
                    // verifica se é possível gerar uma trade imediatamente
                    Order highestOrder = buyOrders.getLast();
                    while (!buyOrders.isEmpty() && newOrder.qty > 0 && highestOrder.price >= newOrder.price) {
                        
                        // garante que não será comprado mais do que o solicitado
                        if (highestOrder.qty >= newOrder.qty) {
                            highestOrder.qty = newOrder.qty;
                        }
                        
                        // efetua a venda
                        buyOrders.decreaseOrder(highestOrder);
                        newOrder.qty -= highestOrder.qty;
                        printTrade(highestOrder);
                    }
                    
                    // caso não tenha comprado imediatamente, adiciona na lista
                    if (newOrder.qty > 0) {
                        sellOrders.addOrder(newOrder);
                    }
                }
            }
            // operações do tipo market:
            else if (type.equals("market")) {
                
                // lê o side e a quantidade da ordem
                String side = orderScanner.next();
                int qty = orderScanner.nextInt();
                
                // verifica se é uma ordem de compra
                if (side.equals("buy")) {
                    
                    // realiza um loop para comprar até alcançar a quantidade solicitada ou acabarem as ordens disponíveis
                    while (qty > 0 && !sellOrders.isEmpty()) {
                        
                        // obtem a ordem com melhor preço para compra
                        Order bestPriceOrder = sellOrders.getFirst();
                        
                        // garante que não será comprado mais do que o solicitado
                        if (bestPriceOrder.qty >= qty) {
                            bestPriceOrder.qty = qty;
                        }
                        
                        // efetua a compra
                        sellOrders.decreaseOrder(bestPriceOrder);
                        qty -= bestPriceOrder.qty;
                        printTrade(bestPriceOrder);
                    }
                }
                // verifica se é uma ordem de venda
                else if (side.equals("sell")) {
                    
                    // realiza um loop para vender até alcançar a quantidade solicitada ou acabarem as ordens disponíveis
                    while (qty > 0 && !buyOrders.isEmpty()) {
                        
                        // obtem a ordem com melhor preço para venda
                        Order bestPriceOrder = buyOrders.getLast();
                        
                        // garante que não será vendido mais do que o solicitado
                        if (bestPriceOrder.qty >= qty) {
                            bestPriceOrder.qty = qty;
                        }
                        
                        // efetua a compra
                        buyOrders.decreaseOrder(bestPriceOrder);
                        qty -= bestPriceOrder.qty;
                        printTrade(bestPriceOrder);
                    }
                }
            }
            // caso não seja inserido uma operação váliada, será logado as listas atuais de compra e venda para debug
            else {
                System.out.println("Buy orders:" + buyOrders);
                System.out.println("Sell orders:" + sellOrders);
            }
        }
    }
    
}
