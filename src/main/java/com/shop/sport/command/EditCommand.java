package com.shop.sport.command;

import com.shop.sport.dao.Dao;
import com.shop.sport.domain.Product;
import com.shop.sport.domain.ProductDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditCommand implements Command {
    private static final Command INSTANCE = new EditCommand();
    private final Dao<Product> dao = ProductDao.getInstance();

    private EditCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        long id = Long.valueOf(req.getParameter("id"));
        String type = req.getParameter("type");
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        Product updatedProduct = Product.builder().id(id).name(name).type(type).price(price).build();
        dao.update(updatedProduct);

        List<Product> products = dao.findAll();
        req.setAttribute("products", products);
        return "main.jsp";
    }
}
