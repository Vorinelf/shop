package com.shop.sport;

import com.shop.sport.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@WebServlet("/")

public class MainServlet extends HttpServlet {
    private final List<Product> products = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator;
    private static final String CREATE = "create";
    private static final String DELETE = "delete";


    public MainServlet() {
        Product nike = new Product(1, "Nike", "sneakers", 95);
        Product adidas = new Product(2, "Adidas", "sneakers", 100);
        Product puma = new Product(3, "Puma", "sneakers", 85);
        products.add(nike);
        products.add(adidas);
        products.add(puma);
        idGenerator = new AtomicLong(products.size());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        if (CREATE.equals(command)) {
            String type = req.getParameter("type");
            String name = req.getParameter("name");
            Double price = Double.valueOf(req.getParameter("price"));
            long nextId = idGenerator.incrementAndGet();

            Product product = new Product(nextId, name, type, price);
            products.add(product);
        }
        if(DELETE.equals(command)) {
            long id= Long.valueOf(req.getParameter("id"));
            products.removeIf(product -> product.getId()==id);
        }




        req.setAttribute("products", products);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("products", products);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
