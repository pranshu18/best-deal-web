package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scraping.Search;
import utils.Item;
import utils.Product;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query=request.getParameter("query");
		String orderByType=request.getParameter("sortby");
		String oldSortBy=request.getParameter("oldsortby");
		
		if(oldSortBy==null) {
			Product product=Search.getResults(query, orderByType);

			request.getSession().setAttribute("itemResults", product.getItems());

		}else{
			
			ArrayList<Item> items=(ArrayList<Item>) request.getSession().getAttribute("itemResults");
			
			Product product=new Product();
			product.setItems(items);
			product.setProductName(query.toUpperCase());
			
			Search.orderResults(orderByType, product);
			
			request.getSession().setAttribute("itemResults", product.getItems());
		}
		
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/Results.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
