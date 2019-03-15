package com.shop.sport.command;

import com.shop.sport.dao.Dao;
import com.shop.sport.domain.Product;
import com.shop.sport.domain.ProductDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteCommand implements Command {
    private static final Command INSTANCE = new DeleteCommand();
    private final Dao<Product> dao = ProductDao.getInstance();

    private DeleteCommand() {
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        long id = Long.valueOf(req.getParameter("id"));
        dao.delete(id);

        List<Product> products = dao.findAll();
        req.setAttribute("products", products);
        return "main.jsp";
    }
}
