
package com.agilecrm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agilecrm.model.Ticket;
import com.agilecrm.servicesimpl.ServiceImpl;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		PrintWriter out = resp.getWriter();

		out.print("In Post");

		String action = req.getParameter("action");

		if (action.equals("addTicket")) {
			try {
				addTicket(req, resp);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		if (action.equals("getAllTickets")) {
			try {
				getAllTickets(req, resp);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (action.equals("getJson")) {
			try {
				getJson(req, resp);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void addTicket(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, MessagingException {

		Ticket ticket = new Ticket();
		PrintWriter out = resp.getWriter();

		ticket.setName(req.getParameter("name"));
		ticket.setEmail(req.getParameter("email"));
		ticket.setMobile(Long.parseLong(req.getParameter("mobile")));
		ticket.setProblem_type((req.getParameter("problem_type")));
		ticket.setProblem_desc((req.getParameter("problem_desc")));
		int status = 0;
		boolean stat = false;
		try {
			status = services.addTicket(ticket);
			System.out.println(ticket.getTicketId());
			out.print(ticket.getTicketId());
			stat = services.sendTicketIdByEmail(ticket);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (status > 0) {
			out.print("Ticket Generated id=");
			out.print(ticket.getTicketId());
		} else if (stat == true) {
			out.print("Mail Sent");

		} else
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
		Ticket ticket = new Ticket();
		ticket.setTicketId(Integer.parseInt(req.getParameter("id")));
		PrintWriter out = resp.getWriter();
		services.getTicketById(ticket);
		out.print(ticket.getName() + " " + ticket.getEmail() + " " + ticket.getMobile() + " " + ticket.getProblem_type()
				+ " " + ticket.getProblem_desc() + " " + ticket.getDept() + " " + ticket.getStatus() + " "
				+ ticket.getResolution());

	}

	protected void getAllTickets(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		Ticket ticket = new Ticket();
		PrintWriter out = resp.getWriter();
		services.listTickets();
		out.print(ticket.getName() + " " + ticket.getEmail() + " " + ticket.getMobile() + " " + ticket.getProblem_type()
				+ " " + ticket.getProblem_desc() + " " + ticket.getDept() + " " + ticket.getStatus() + " "
				+ ticket.getResolution());
	}

	protected void deleteTicket(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		Ticket ticket = new Ticket();
		PrintWriter out = resp.getWriter();
		ticket.setTicketId(Integer.parseInt(req.getParameter("id")));
		int status = services.deleteTicket(ticket);
		if (status > 0) {
			out.print("Record Deleted");
		} else {
			out.print("Record Not Found");
		}

	}

	protected void getJson(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		ObjectMapper mapper = new ObjectMapper();
		Ticket ticket= new Ticket();
		PrintWriter out=resp.getWriter(); 
		try {
			
			//ticket=ticket.setTicketId(Integer.parseInt(req.getParameter("id")));
			out.print(services.getJson(ticket));			// Convert object to JSON string and save into a file directly
			mapper.writeValueAsString(ticket);

			// Convert object to JSON string
			String jsonInString1 = mapper.writeValueAsString(ticket.getName());
			System.out.println(jsonInString1);

			// Convert object to JSON string and pretty print
			jsonInString1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ticket);
			System.out.println(jsonInString1);
			out.println(jsonInString1);

		} catch (JsonGenerationException e) {
			e.printStackTrace();

		}

	}
}
