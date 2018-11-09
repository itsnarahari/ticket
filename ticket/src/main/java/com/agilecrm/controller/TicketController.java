
package com.agilecrm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agilecrm.model.Ticket;
import com.agilecrm.servicesimpl.ServiceImpl;

//o@WebServlet("/contact")
public class TicketController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Author By Narahari
	 */
	ServiceImpl services = new ServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action");

		if (action.equals("addTicket")) {
			addTicket(req, resp);
		}
		if (action.equals("updateTicket")) {
			try {
				updateTicket(req, resp);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (action.equals("updateResolution")) {
			try {
				updateResolution(req, resp);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (action.equals("getTicketById")) {
			try {
				getTicketById(req, resp);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void addTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Ticket ticket = new Ticket();
		PrintWriter out = resp.getWriter();
		out.print("In Post");

		ticket.setName(req.getParameter("name"));
		ticket.setEmail(req.getParameter("email"));
		ticket.setMobile(Long.parseLong(req.getParameter("mobile")));
		ticket.setProblem_type((req.getParameter("problem_type")));
		ticket.setProblem_desc((req.getParameter("problem_desc")));
		int status = 0;
		try {
			status = services.addTicket(ticket);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (status > 0)
			out.print("Ticket Generated");
		else
			out.print("not done");

	}

	protected void updateTicket(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		Ticket ticket = new Ticket();
		ServiceImpl services = new ServiceImpl();
		int status = 0;
		ticket.setTicketId(Integer.parseInt(req.getParameter("id")));
		ticket.setDept(req.getParameter("dept"));
		ticket.setStatus(req.getParameter("status"));
		PrintWriter out = resp.getWriter();
		services.updateTicket(ticket);
		if (status == 0)
			out.print("Ticket Updated");
		else
			out.print("Not Found");
	}

	protected void updateResolution(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ClassNotFoundException, SQLException {

		Ticket ticket = new Ticket();
		ServiceImpl services = new ServiceImpl();
		int status = 0;
		ticket.setTicketId(Integer.parseInt(req.getParameter("id")));
		ticket.setStatus(req.getParameter("status"));
		ticket.setResolution(req.getParameter("resolution"));

		PrintWriter out = resp.getWriter();
		services.updateResolution(ticket);
		if (status == 0)
			out.print("Ticket Updated");
		else
			out.print("Not Found");
	}

	protected void getTicketById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		String listContact = "/ticketStatus.jsp";
		Ticket ticket = new Ticket();
		ServiceImpl services = new ServiceImpl();
		ticket.setTicketId(Integer.parseInt(req.getParameter("id")));
		PrintWriter out = resp.getWriter();
		out.print(services.getTicketById(ticket));
		//RequestDispatcher view = req.getRequestDispatcher(listContact);
		//req.setAttribute("ticket", services.getTicketById(ticket));
		//view.forward(req, resp);

	}
}
