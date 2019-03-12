package com.shop.sport;

import com.shop.sport.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product nike = new Product(1,"Nike","sneakers",95);
        Product adidas = new Product(2,"Adidas","sneakers",100);
        Product puma = new Product(3,"Puma","sneakers",85);
        List<Product> products = new ArrayList<>();
        products.add(nike);
        products.add(adidas);
        products.add(puma);
        req.setAttribute("products", products);
        req.getRequestDispatcher("main.jsp").forward(req,resp);
    }
}
