package com.morgan.engine;

import java.util.LinkedList;
import java.util.TreeMap;

/**
 * OrderList implementa um TreeMap para armazenar as ordens de compra ou venda
 * disponíveis para acesso em tempo (log n), sendo n o número de ordens
 * 
 * A chave para cada posição no TreeMap é o preço da ordem e o valor é uma LinkedList,
 * que armazena as ordens de mesmo valor na ordem que foram inseridas
 * 
 */
public class OrderList {
    
    private TreeMap<Integer, LinkedList<Integer>> orders;
    
    public OrderList() {
        orders = new TreeMap<>();
    }
    
    // insere uma ordem na lista
    public void addOrder (Order order) {
        if (orders.containsKey(order.price)) {
            orders.get(order.price).add(order.qty);
        }
        else {
            LinkedList<Integer> newList = new LinkedList<>();
            newList.add(order.qty);
            orders.put(order.price, newList);
        }
    }
    
    // informa se a lista está vazia
    public boolean isEmpty() {
        return orders.isEmpty();
    }
    
    // retorna a primeira ordem (de menor valor)
    public Order getFirst() {
        if (!orders.isEmpty()) {
            Order firstOrder = new Order();
            firstOrder.price = orders.firstKey();
            firstOrder.qty = orders.get(firstOrder.price).getFirst();
            return firstOrder;
        }
        return null;
    }
    
    // retorna a última ordem (de maior valor)
    public Order getLast() {
        if (!orders.isEmpty()) {
            Order lastOrder = new Order();
            lastOrder.price = orders.lastKey();
            lastOrder.qty = orders.get(lastOrder.price).getFirst();
            return lastOrder;
        }
        return null;
    }
    
    // reduz a quantidade disponível de uma ordem, efetivando a compra ou venda
    public void decreaseOrder(Order order) {
        LinkedList<Integer> list = orders.get(order.price);
        if (list.getFirst() == order.qty) {
            if (list.size() == 1) {
                orders.remove(order.price);
            }
            else {
                list.removeFirst();
            }
        } else {
            int newQty = list.getFirst() - order.qty;
            list.set(0, newQty);
        }
    }

    @Override
    public String toString() {
        return orders.toString();
    }
    
    
}
