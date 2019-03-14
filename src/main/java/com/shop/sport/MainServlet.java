package com.shop.sport;

import com.shop.sport.dao.Dao;
import com.shop.sport.domain.Product;
import com.shop.sport.domain.ProductDao;

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
    private static final String CREATE = "create";
    private static final String DELETE = "delete";
    private static final String TO_EDIT = "to_edit";
    private static final String EDIT = "edit";
    private Dao<Product> dao = ProductDao.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = dao.findAll();
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
                Product newProduct = Product.builder().name(name).type(type).price(price).build();
                dao.save(newProduct);
                break;
            case DELETE: {
                long id = Long.valueOf(req.getParameter("id"));
                dao.delete(id);
                break;
            }
            case TO_EDIT: {
                long id = Long.valueOf(req.getParameter("id"));
                dao.get(id).ifPresent(product -> req.setAttribute("product", product));

                break;
            }
            case EDIT: {
                long id = Long.valueOf(req.getParameter("id"));
                type = req.getParameter("type");
                name = req.getParameter("name");
                price = Double.parseDouble(req.getParameter("price"));
                Product updatedProduct = Product.builder().id(id).name(name).type(type).price(price).build();
                dao.update(updatedProduct);
                break;
            }
        }
        List<Product> products = dao.findAll();

        req.setAttribute("products", products);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}



