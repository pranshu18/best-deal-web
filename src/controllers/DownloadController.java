package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utils.Item;

/**
 * Servlet implementation class ResultController
 */
@WebServlet("/DownloadController")
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Item> items=(ArrayList<Item>) request.getSession().getAttribute("itemResults");
		String query=request.getParameter("query");
		String orderByType=request.getParameter("sortby");
		
		String currentTime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
        String fileName=query+" results_"+currentTime+".xlsx";

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-disposition", "attachment; filename=\""+fileName+"\"");

		ServletOutputStream sos=response.getOutputStream();
		
        writeExcelToOutputStream(items,orderByType,sos);
        
        sos.flush();
        sos.close();
        
        System.out.println("\nDownload done");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private void writeExcelToOutputStream(ArrayList<Item> items, String orderByType, OutputStream os) {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("By "+orderByType);

    	Row row= sheet.createRow(0);
    	row.createCell(0).setCellValue("Name");
    	row.createCell(1).setCellValue("Seller");
    	row.createCell(2).setCellValue("Out of Stock");
    	row.createCell(3).setCellValue("Price");
    	row.createCell(4).setCellValue("Number of Reviews");
    	row.createCell(5).setCellValue("Rating");
    	row.createCell(6).setCellValue("Discount Percentage");
    	row.createCell(7).setCellValue("Link");

    	int i=1;
        for(Item item:items) {
        	row= sheet.createRow(i);
        	
        	row.createCell(0).setCellValue(item.getItemName());
        	row.createCell(1).setCellValue(item.getSeller());
        	
    		if (!item.isOutOfStock()) 
    			row.createCell(2).setCellValue("NO");
    		else
    			row.createCell(2).setCellValue("YES");

        	row.createCell(3).setCellValue(item.getPrice());
        	row.createCell(4).setCellValue(item.getNumberOfReviews());
        	row.createCell(5).setCellValue(item.getRating());
        	row.createCell(6).setCellValue(item.getDiscountPercentage());
        	row.createCell(7).setCellValue(item.getLink().toString());
        	i++;
        }
		
		try {
			workbook.write(os);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
