package routes.Cake;

import controller.Cake.DaoCake;

import java.util.logging.Logger;

public class CakeRouter extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private DaoCake daoCake= DaoCake.getInstance();
    private static final Logger LOGGER = Logger.getLogger(CakeRouter.class.getName());
}
