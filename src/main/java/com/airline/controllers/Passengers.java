package com.airline.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Passenger;
import com.airline.service.PassengerService;

/**
 * Servlet implementation class Flights
 */
@WebServlet("/passengers")
public class Passengers extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@EJB
	PassengerService ps;
	
    public Passengers() { super(); }

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		List<Passenger> pList = (List<Passenger>) ps.getPassengers();
		request.setAttribute("passengers_list", pList);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/passengers_list.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
