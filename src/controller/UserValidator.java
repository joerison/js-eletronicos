package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.User;

@SuppressWarnings("serial")
public class UserValidator extends HttpServlet {
	private static final List<User> users = getUsers();

	private static List<User> getUsers() {

		List<User> users = new ArrayList<User>();

		User userOne = new User("joe", "joe");
		User userTwo = new User("aaa", "aaa");

		users.add(userOne);
		users.add(userTwo);

		System.out.println("obtendo lista de usuarios");

		return users;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("validando login... doGet");
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("validando login... doPost");
		RequestDispatcher rd;

		String name = req.getParameter("name");
		String password = req.getParameter("password");

		System.out.println("nome: " + name);
		System.out.println("senha: " + password);
		User user = validateLogin(name, password);

		if (user == null) {
			rd = req.getRequestDispatcher("/loginError.jsp");
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			rd = req.getRequestDispatcher("/corporativo/loginSuccess.jsp");
		}

		rd.forward(req, res);
	}

	private User validateLogin(String name, String password) {
		// All parameters must be valid
		System.out.println("validando login...validateLogin");
		if (name == null || password == null) {
			return null;
		}

		// Get a user by key
		User userLocalizado = null;
		for (User user : users) {
			if (user.getName().equals(name)) {
				userLocalizado = user;
			}
		}

		if (userLocalizado == null) {
			return null;
		}

		if (!userLocalizado.getPassword().equals(password.trim())) {
			return null;
		}

		return userLocalizado;
	}
}