import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("txtUsuario"),
			   contrasneha = request.getParameter("txtContrasenha");
		if(usuario.equalsIgnoreCase("isotrol") && contrasneha.equalsIgnoreCase("123")) {
			//request.setAttribute("txtUsuario", usuario);
			//request.setAttribute("txtContrasenha", contrasneha);
			response.sendRedirect("principal.jsp");
		}else {
			response.sendRedirect("principal.jsp");
		}
		//request.getRequestDispatcher("/Hello");
	}
}
