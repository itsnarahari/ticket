package com.agilecrm.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.agilecrm.dao.Dao;
import com.agilecrm.model.Ticket;
import com.agilecrm.util.MySqlConnection;

public class DaoImpl implements Dao {

	static Connection con;
	static int status;

	public int addTicket(Ticket ticket) throws SQLException, ClassNotFoundException {
		con = MySqlConnection.getConnection();
		try {

			PreparedStatement ps = con.prepareStatement(
					"insert into ticket(name,email,mobile,problem_type,problem_desc,status) values(?,?,?,?,?,'NEW')",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, ticket.getName());
			ps.setString(2, ticket.getEmail());
			ps.setLong(3, ticket.getMobile());
			ps.setString(4, ticket.getProblem_type());
			ps.setString(5, ticket.getProblem_desc());
			status = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				ticket.setTicketId(rs.getInt(1));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return status;
	}

	public int updateTicket(Ticket ticket) throws ClassNotFoundException, SQLException {

		try {
			String sql = "update ticket set dept=?,status=? where ticketId=?";
			con = MySqlConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ticket.getDept());
			ps.setString(2, ticket.getStatus());
			ps.setInt(3, ticket.getTicketId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;

	}

	public int updateResolution(Ticket ticket) throws ClassNotFoundException, SQLException {

		try {
			String sql = "update ticket set status=?, resolution=? where ticketId=?";
			con = MySqlConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ticket.getStatus());
			ps.setString(2, ticket.getResolution());
			ps.setInt(3, ticket.getTicketId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> listTickets() throws SQLException, ClassNotFoundException {

		List<Ticket> list = new ArrayList<>();
		con = MySqlConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from ticket");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Ticket ticket = new Ticket();
			ticket.setTicketId(rs.getInt("ticketId"));
			ticket.setName(rs.getString("name"));
			ticket.setEmail(rs.getString("email"));
			ticket.setMobile(rs.getLong("mobile"));
			ticket.setProblem_type(rs.getString("problem_type"));
			ticket.setProblem_desc(rs.getString("problem_desc"));
			ticket.setDept(rs.getString("dept"));
			ticket.setStatus(rs.getString("status"));
			ticket.setResolution(rs.getString("resolution"));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketById(Ticket ticket) throws SQLException, ClassNotFoundException {

		List<Ticket> list = new ArrayList<>();
		con = MySqlConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from ticket where ticketId=?");
		ps.setInt(1, ticket.getTicketId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ticket.setTicketId(rs.getInt("ticketId"));
			ticket.setName(rs.getString("name"));
			ticket.setEmail(rs.getString("email"));
			ticket.setMobile(rs.getLong("mobile"));
			ticket.setProblem_type(rs.getString("problem_type"));
			ticket.setProblem_desc(rs.getString("problem_desc"));
			ticket.setDept(rs.getString("dept"));
			ticket.setStatus(rs.getString("status"));
			ticket.setResolution(rs.getString("resolution"));

		}
		return list;
	}

	@Override
	public int deleteTicket(Ticket ticket) {
		try {
			String sql = "delete from ticket where ticketId=?";
			con = MySqlConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ticket.getTicketId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;

	}

	@Override
	public boolean sendTickeyByEmail(Ticket ticket) throws MessagingException {

		String to = ticket.getEmail();
		String subject = "Ticket Generated";
		String msg = "Your complaint registered successfully your complaint id:" + ticket.getTicketId();
		final String from = "naraharinaik7@gmail.com";
		final String password = "Narahari%43";
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		// session.setDebug(true);
		Transport transport = session.getTransport();
		InternetAddress addressFrom = new InternetAddress(from);

		MimeMessage message = new MimeMessage(session);
		message.setSender(addressFrom);
		message.setSubject(subject);
		message.setContent(msg, "text/plain");
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		transport.connect();
		Transport.send(message);
		transport.close();
		return false;
	}

	@Override
	public Ticket getJson(Ticket ticket) throws ClassNotFoundException, SQLException {
		
		con = MySqlConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from ticket where ticketId=?");
		ps.setInt(1, ticket.getTicketId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ticket.setTicketId(rs.getInt("ticketId"));
			ticket.setName(rs.getString("name"));
			ticket.setEmail(rs.getString("email"));
			ticket.setMobile(rs.getLong("mobile"));
			ticket.setProblem_type(rs.getString("problem_type"));
			ticket.setProblem_desc(rs.getString("problem_desc"));
			ticket.setDept(rs.getString("dept"));
			ticket.setStatus(rs.getString("status"));
			ticket.setResolution(rs.getString("resolution"));

		}

		return ticket;
	}
	
}
