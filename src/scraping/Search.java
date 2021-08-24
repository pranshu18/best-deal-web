package scraping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;

import utils.Item;
import utils.Product;
import websites.Amazon;
import websites.Flipkart;
import websites.Snapdeal;


public class Search {
	
	public final static String PRICE="Price";
	public final static String DELIVERY_DATE="Delivery Date";
	public final static String NUMBER_OF_REVIEWS="Number of Reviews";
	public final static String RATING="Rating";
	public final static String DISCOUNT_PERCENTAGE="Discount Percentage";

	public static Product getResults(String query, String orderByType) {		
		Product product=new Product();
		product.setProductName(query.toUpperCase());

		try {
			WebClient client= setupClient();
			
			fillDetails(client, query, product);
			
			orderResults(orderByType,product);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return product;
	}

	private static WebClient setupClient() {
		WebClient client = new WebClient(BrowserVersion.FIREFOX);  

		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);
				
		return client;
	}

	private static void fillDetails(WebClient client, String query, Product product) throws FailingHttpStatusCodeException, MalformedURLException, IOException, URISyntaxException {
		Amazon amazon=new Amazon();
		Flipkart flipkart=new Flipkart();
		Snapdeal snapdeal=new Snapdeal();
		
		amazon.fillDetails(client, query, product);
		flipkart.fillDetails(client, query, product);
		snapdeal.fillDetails(client, query, product);
	}

	public static void orderResults(String orderByType, Product product) {
		
		ArrayList<Item> items=product.getItems();
		
		switch(orderByType) {
		case PRICE:
			Collections.sort(items, new Item().new SortByPrice());
			break;
		case DELIVERY_DATE:
			Collections.sort(items, new Item().new SortByDeliveryDate());		
			break;
		case NUMBER_OF_REVIEWS:
			Collections.sort(items, new Item().new SortByNumberOfReviews());		
			break;
		case RATING:
			Collections.sort(items, new Item().new SortByRating());		
			break;
		case DISCOUNT_PERCENTAGE:
			Collections.sort(items, new Item().new SortByDiscountPercentage());	
			break;
		}		
	}

}
