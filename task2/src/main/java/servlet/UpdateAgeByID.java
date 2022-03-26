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

    @WebServlet("/updateAge")
    public class UpdateAgeByID extends HttpServlet {

        private static final String UPDATE_AGE = "UPDATE USERS SET AGE = ? WHERE iduser = ?";

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int IDUser = Integer.parseInt(req.getParameter("IDUser"));
            int age = Integer.parseInt(req.getParameter("age"));

            try (Connection connection = DBConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AGE)) {

                preparedStatement.setInt(1, age);
                preparedStatement.setInt(2, IDUser);
                int result = preparedStatement.executeUpdate();

                if (result > 0) {
                    resp.getWriter().println(result + " row updated");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

