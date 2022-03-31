package routes;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Cake.DaoCake;
import models.Cake;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CakeRouter extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DaoCake daoCake= DaoCake.getInstance();
    private static final Logger LOGGER = Logger.getLogger(CakeRouter.class.getName());

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String action = req.getServletPath();
        try{
            switch (action){
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    insertCake(req, resp);
                    break;
                case "/delete":
                    deleteCake(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateCake(req, resp);
                    break;
                default:
                    listCake(req, resp);
                    break;

            }
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "SQL Error", e);
        }
    }

    private void updateCake(HttpServletRequest req, HttpServletResponse resp)
        throws SQLException,IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("cake_id"));
        String name = req.getParameter("cake_name");
        int recipe_id = Integer.parseInt(req.getParameter("recipe_id"));

        Cake cake = new Cake(id, name, recipe_id);
        daoCake.update(cake);
        resp.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
        throws SQLException, IOException, ServletException{
        String id = req.getParameter("cake_id");
        Optional<Cake> existingCake = daoCake.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("");
        existingCake.ifPresent(s->req.setAttribute("cake", s));
    }

    private void deleteCake(HttpServletRequest req,HttpServletResponse resp)
        throws  SQLException, IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("cake_id"));

        Cake cake  = new Cake(id);
        daoCake.delete(cake);
        resp.sendRedirect("list");
    }

    private  void insertCake(HttpServletRequest req, HttpServletResponse resp)
        throws SQLException, IOException, ServletException{
        String name = req.getParameter("cake_name");
        int recipe = Integer.parseInt(req.getParameter("recipe_id"));

        Cake newCake = new Cake(name, recipe);
        daoCake.save(newCake);
        resp.sendRedirect("list");
    }


    private  void showNewForm(HttpServletRequest req, HttpServletResponse resp)
        throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/CakeForm.jsp");
        dispatcher.forward(req, resp);
    }

    private void listCake(HttpServletRequest req, HttpServletResponse resp)
        throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/CakeForm.jsp");

        List<Cake> listCake = daoCake.findAll();
        req.setAttribute("listCake", listCake);
    }

    

}
