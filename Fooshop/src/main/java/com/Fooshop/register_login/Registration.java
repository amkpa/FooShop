 package com.Fooshop.register_login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.Driver;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/register")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name= request.getParameter("uname");
		String phone= request.getParameter("uphone");
		String email = request.getParameter("uemail");
		String password = request.getParameter("upassword");
		Connection con= null;
		RequestDispatcher dispatcher = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdatabase","root","1234");
			PreparedStatement prep= con.prepareStatement("insert into register(name,phone_number,email,password) values(?,?,?,?)");
			
			prep.setString(1, name);
			prep.setString(2, phone);
			prep.setString(3, email);
			prep.setString(4, password);
			int row =prep.executeUpdate();
			dispatcher = request.getRequestDispatcher("login.html");
			
			if(row>0) {
				request.setAttribute("status", "sucess");
			}
			else {
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
//PrintWriter sout = response.getWriter();
//sout.println("Connected");