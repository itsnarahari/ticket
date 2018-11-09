package com.agilecrm.main;

public class Example {
		/*public boolean update(Ticket ticket) {
			if(ticket.getProblemReport() == null) {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				String quary = "update tickets set dept_id = ? ,ticket_status ='INPROGRESS' where id = ?";
				try {
					connection = new DAOUtility().getConnection();
					preparedStatement = connection.prepareStatement(quary);
					preparedStatement.setInt(1, ticket.getDeptID());
					preparedStatement.setInt(2, ticket.getId());
					int i = preparedStatement.executeUpdate();
					System.out.println(i);
					return i>0;
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}else {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				String quary = "update tickets set resolution = ? ,ticket_status ='CLOSED' where id = ?";
				try {
					connection = new DAOUtility().getConnection();
					preparedStatement = connection.prepareStatement(quary);
					preparedStatement.setString(1, ticket.getProblemReport());
					preparedStatement.setInt(2, ticket.getId());
					int i = preparedStatement.executeUpdate();
					return i>0;
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			return false;*/
		}
		

