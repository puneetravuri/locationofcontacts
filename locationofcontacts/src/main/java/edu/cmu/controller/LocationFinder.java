package edu.cmu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cmu.controller.action.Action;
import edu.cmu.controller.action.AuthenticateAction;
import edu.cmu.controller.action.DisplayAction;
import edu.cmu.controller.action.LoginAction;

/**
 * Servlet implementation class LocationFinder
 */
public class LocationFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		Action.add(new AuthenticateAction());
		Action.add(new LoginAction());
		Action.add(new DisplayAction());
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String path = request.getServletPath();
		String action = path.substring(path.lastIndexOf('/') + 1);

		String nextPage = Action.perform(action, request);

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
		} else if (nextPage.endsWith(".jsp")) {
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		} else {
			response.sendRedirect(nextPage);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
