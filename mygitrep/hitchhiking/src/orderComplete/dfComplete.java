package orderComplete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.mdb;

/**
 * Servlet implementation class dfComplete
 */
@WebServlet("/dfComplete")
public class dfComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dfComplete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tblID=request.getParameter("tblID");
		int foodID=Integer.parseInt(tblID);
		String result="success";
		Connection conn=mdb.getconntion();//���ӵ����ݿ� 
		try {
			String sql="update tblfood set tasked='4' where foodID='"+foodID+"'";
			PreparedStatement ps=conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();         
		}catch(Exception e) {
			result="fail";
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");			
		PrintWriter pw=response.getWriter();
		pw.print(result);
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
