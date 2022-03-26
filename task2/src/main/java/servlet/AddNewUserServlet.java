package servlet;

import service.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/addUser")
public class AddNewUserServlet extends HttpServlet {

    private static final String INSERT_USER = "INSERT INTO users (name, age) values (?,  ?)";
    private java.sql.Connection connection;
    private final PreparedStatement preparedStatement;

    public AddNewUserServlet(Connection connection, PreparedStatement preparedStatement) {
        this.connection = connection;
        this.preparedStatement = preparedStatement;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);

                int result_set = preparedStatement.executeUpdate();

                if (result_set > 0) {
                    resp.getWriter().println(result_set + " row added");
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
