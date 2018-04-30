package org;

import java.io.IOException;
import java.math.BigDecimal;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Parking
 */
@WebServlet("/Parking")
public class Parking extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Parking() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetPost(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp = "PUT";
		response.getWriter().append(resp);
		response.flushBuffer();
	}

	private void doGetPost(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		String error = null;
        String result=null;
		Result actionRes=null;
		boolean actionOk = false;

		if (action != null && !action.isEmpty()) {
			actionOk = true;
			String userId;
			String parkingId;

            BigDecimal cost = null;

			switch (action) {
			case "register":
				 userId = request.getParameter("userId");
				 parkingId = request.getParameter("parkingId");
				actionRes = Registration.register(userId,parkingId);

				break;
			case "unregister":
				 userId = request.getParameter("userId");
				 parkingId = request.getParameter("parkingId");
				actionRes = Registration.unregister(userId,parkingId);
				break;
				
			case "currentPayment":
				 userId = request.getParameter("userId");
                try {
                    cost = Registration.getCurrentCost(userId);
                } catch (IOException e) {
                    e.printStackTrace();
                    actionRes = new Result(false,"wrong userId");
                }
                actionRes = new Result(true,null);
                try {
                    response.getWriter().append("current cost is: " + cost + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
				
			default:
				error = "unknown action: " + action;
				actionOk = false;
			}
		}

        result = "Success";
		if (!actionOk) {
			result = "parameter action is missing";
		} else if (!actionRes.success) {
			result = actionRes.errorMessage;
		}

		try {
			response.getWriter().append(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
