package routes;

import controller.Cake.DaoCake;
import controller.Recipe.DaoRecipe;
import models.Cake;
import models.Recipe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecipeRouter extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DaoRecipe daoRecipe = DaoRecipe.getInstance();
    private static final Logger LOGGER = Logger.getLogger(RecipeRouter.class.getName());

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                    insertRecipe(req, resp);
                    break;
                case "/delete":
                    deleteRecipe(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateRecipe(req, resp);
                    break;
                default:
                    listRecipe(req, resp);
                    break;

            }
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "SQL Error", e);
        }
    }

    private void updateRecipe(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException,IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("recipe_id"));
        String description = req.getParameter("recipe_description");
        String ingredients = req.getParameter("ingredients");


        Recipe recipe = new Recipe(id, description, ingredients);
        daoRecipe.update(recipe);
        resp.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String id = req.getParameter("recipe_id");
        Optional<Recipe> existingRecipe = daoRecipe.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("");
        existingRecipe.ifPresent(s->req.setAttribute("recipe", s));
    }

    private void deleteRecipe(HttpServletRequest req,HttpServletResponse resp)
            throws  SQLException, IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("recipe_id"));

        Recipe recipe  = new Recipe(id);
        daoRecipe.delete(recipe);
        resp.sendRedirect("list");
    }

    private  void insertRecipe(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException{
        int recipe = Integer.parseInt(req.getParameter("recipe_id"));
        String ingredients = req.getParameter("ingredients");
        String description = req.getParameter("recipe_description");


        Recipe newRecipe = new Recipe(recipe, ingredients, description);
        daoRecipe.save(newRecipe );
        resp.sendRedirect("list");
    }

    private  void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/RecipeForm.jsp");
        dispatcher.forward(req, resp);
    }

    private void listRecipe(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/RecipeForm.jsp");

        List<Recipe> listRecipe = daoRecipe.findAll();
        req.setAttribute("listRecipe", listRecipe);
    }

}
