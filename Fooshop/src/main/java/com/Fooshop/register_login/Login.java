 package com.Fooshop.register_login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Result;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username= request.getParameter("uemail");
		String password= request.getParameter("upassword");
		HttpSession session= request.getSession();
		RequestDispatcher dispatcher= null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdatabase","root","1234");
			PreparedStatement prep= con.prepareStatement("select *from register where email=? and password=?");
			prep.setString(1, username);
			prep.setString(2, password);
			
			ResultSet rs=prep.executeQuery();
			 if(rs.next()) {
				 session.setAttribute("Name", rs.getString("name"));
				 dispatcher = request.getRequestDispatcher("index.html");
			 }
			 else {
				 request.setAttribute("status", "failed");
				 dispatcher = request.getRequestDispatcher("login.html");
			 }
			 dispatcher.forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		

}
