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
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/selectByID")
public class SelectByIDServlet extends HttpServlet {

    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE iduser = ?";
    private java.sql.Connection connection;
    private final PreparedStatement preparedStatement;

    public SelectByIDServlet(Connection connection, PreparedStatement preparedStatement) {
        this.connection = connection;
        this.preparedStatement = preparedStatement;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int IDUser = Integer.parseInt(req.getParameter("IDUser"));

        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setInt(1, IDUser);
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {

                while (resultSet.next()) {
                    int idUser = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    int age = resultSet.getInt(3);

                    resp.getWriter().println("ID: " + idUser);
                    resp.getWriter().println("Name: " + name);
                    resp.getWriter().println("Age: " + age);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
