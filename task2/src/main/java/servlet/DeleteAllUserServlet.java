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


@WebServlet("/deleteAll")
public class DeleteAllUserServlet extends HttpServlet {

    private static final String DELETE_ALL_USER = "DELETE FROM users ";
    private java.sql.Connection connection;
    private final PreparedStatement preparedStatement;

    public DeleteAllUserServlet(Connection connection, PreparedStatement preparedStatement) {
        this.connection = connection;
        this.preparedStatement = preparedStatement;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_USER)) {

            int resultSet = preparedStatement.executeUpdate();

            if (resultSet > 0) {
                resp.getWriter().println(resultSet + " row deleted");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
