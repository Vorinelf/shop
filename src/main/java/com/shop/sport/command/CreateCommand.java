package com.shop.sport.command;

import com.shop.sport.dao.Dao;
import com.shop.sport.domain.Product;
import com.shop.sport.domain.ProductDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreateCommand implements Command {
    private static final Command INSTANCE = new CreateCommand();
    private final Dao<Product> dao = ProductDao.getInstance();

    private CreateCommand(){}

    public static Command getInstance() {return INSTANCE;}


    @Override
    public String execute(HttpServletRequest req) {
        String type = req.getParameter("type");
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        Product newProduct = Product.builder().name(name).type(type).price(price).build();
        dao.save(newProduct);

        List<Product> products = dao.findAll();
        req.setAttribute("products", products);
        return "main.jsp";
    }
}
