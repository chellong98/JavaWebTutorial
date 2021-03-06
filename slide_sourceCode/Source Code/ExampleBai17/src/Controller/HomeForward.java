package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Emp;
import DAO.HomeDAO;
import DB.DBConnection;
import java.sql.*;
import java.util.*;

@WebServlet("/HomeForward")
public class HomeForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public HomeForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String pageidstr = request.getParameter("pageid");
		
		//ep kieu string sang int de tinh start
		int pageid = Integer.parseInt(pageidstr);
		int count = 4;
		
		//neu pageid = 1 => khong phan trang
		//neu pageid != 1 => phan trang cho no
		
		if (pageid == 1)
		{
			
		}
		else 
		{
			pageid = pageid -1;
			pageid = pageid * count +1;
		}
		
		
		Connection conn = DBConnection.CreateConnection();
		
		List<Emp> list = HomeDAO.DisplayEmp(pageid, count, conn);
		
		
		int sumrow = HomeDAO.Countrow(conn);
		
		int maxpageid = (sumrow/count)+1;
		
		request.setAttribute("maxpageid",maxpageid);
		
		request.setAttribute("listemp",list);
		request.setAttribute("numberpage",Integer.parseInt(pageidstr));
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/View/Home.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
