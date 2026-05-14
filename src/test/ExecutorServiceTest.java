package test;

import client.Client;
import manager.CartManager;
import manager.OrderManager;
import manager.ProductManager;
import money.Money;
import order.Order;
import repository.Cart;
import repository.OrderRepository;
import repository.ProductRepository;
import threadsExecutor.Executor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {
    public static void main(String[] args) {
        threadsTest(3, 100);  //34080
//        threadsTest(10, 100); //10053
    }

    private static void threadsTest(int threads, int taskCount) {
        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);
        productManager.createSmartphone("001", "samsung", Money.of("4600"), 10000);
        Executor testExecutor = new Executor(threads);
        OrderManager orderManager = new OrderManager(new OrderRepository(), new CartManager(new Cart(), repository), testExecutor);
        Client client = new Client("Piotr", "Nowak", "012310101");
        List<Future<Order>> futures = new ArrayList<>();
        long start = System.nanoTime();
        for (int i = 0; i < taskCount; i++) {
            Cart carts = new Cart();
            CartManager cartManagerTester = new CartManager(carts, repository);
            cartManagerTester.addProductToCart("001", 1);
            futures.add(orderManager.order(carts, client, ZonedDateTime.now()));
        }
        for (Future<Order> f : futures) {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        testExecutor.shutdown();
        waitForExecutorExecution(testExecutor);
        long end = System.nanoTime();
        System.out.println("Threads " + threads + ": " + (end - start) / 1_000_000);
    }

    private static void waitForExecutorExecution(Executor testExecutor) {
        try {
            testExecutor.getExecutorService().awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}



