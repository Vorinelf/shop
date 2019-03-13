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
    private static final String TO_EDIT = "to_edit";
    private static final String EDIT = "edit";


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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("products", products);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case CREATE:
                String type = req.getParameter("type");
                String name = req.getParameter("name");
                double price = Double.parseDouble(req.getParameter("price"));
                long nextId = idGenerator.incrementAndGet();

                Product newProduct = new Product(nextId, name, type, price);
                products.add(newProduct);
                break;
            case DELETE: {
                long id = Long.valueOf(req.getParameter("id"));
                products.removeIf(product -> product.getId() == id);
                break;
            }
            case TO_EDIT: {
                long id = Long.valueOf(req.getParameter("id"));
                products.stream()
                        .filter(product -> product.getId() == id)
                        .findFirst()
                        .ifPresent(product -> req.setAttribute("product", product));
                break;
            }
            case EDIT: {
                long id = Long.valueOf(req.getParameter("id"));
                type = req.getParameter("type");
                name = req.getParameter("name");
                price = Double.parseDouble(req.getParameter("price"));
                products.stream()
                        .filter(product -> product.getId()==id)
                        .findFirst()
                        .ifPresent(product -> {
                            product.setName(name);
                            product.setType(type);
                            product.setPrice(price);
                        });
                break;
            }
        }


        req.setAttribute("products", products);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}



