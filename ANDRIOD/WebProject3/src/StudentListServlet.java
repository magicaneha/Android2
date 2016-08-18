

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class StudentListServlet
 */
@WebServlet("/StudentListServlet")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jList="";
		try
		{
			ArrayList<Student> slist = new ArrayList<Student>();
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conn = DriverManager.getConnection("jdbc:odbc:mydsn");
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("select * from student");
			
			while(rs.next())
			{
				int sid = rs.getInt(1);
				String sname = rs.getString(2);
				float fees = rs.getFloat(3);
				Student s = new Student(sid, sname, fees);
				slist.add(s);
			}
			
			Gson gson = new Gson();
			jList = gson.toJson(slist);
			System.out.println(jList);
			response.getWriter().write(jList);
			
		}
		catch (Exception e) {
			System.out.println(e.toString());
			response.getWriter().write(e.toString());
		}
		
	}
}
