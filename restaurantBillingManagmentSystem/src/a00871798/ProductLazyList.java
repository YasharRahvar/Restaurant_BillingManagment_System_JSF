/**
 * @author Yashar Rahvar
 * Date: 15/Jan/2016
 * Project: restaurantBillingManagment 
 * Class: ProductLazyList 
 */
package a00871798;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import a00871798.database.Database;

public class ProductLazyList extends LazyDataModel<Product> {

	private static final long serialVersionUID = 1892583683157036005L;
	private Database database  = new Database();
	private List<Product> products;

	public ProductLazyList(){
		try {
			products = database.getProducts();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		//last page 
		try {
			if ( first + pageSize > database.getProducts().size() )
			{
				products = new ArrayList<Product>(database.getProducts().subList(first, database.getProducts().size()));
			}//if you click on next button and you're not on the last page
			else if( first!=0 && pageSize < database.getProducts().size() )
			{
				products = new ArrayList<Product>(database.getProducts().subList(first, first+pageSize));	
			}
			 
			else{ //first page
				products = new ArrayList<Product>(database.getProducts().subList(first, pageSize));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (sortField != null) {
			if (sortField != null && sortField.equals("ID") && sortOrder.name().equals("ASCENDING")) {
				Collections.sort(products, ComparatorsByIDNameAsc.NAME);
			}
			if (sortField != null && sortField.equals("ID") && sortOrder.name().equals("DESCENDING"))
				Collections.sort(products, ComparatorsByIDDes.NAME);
		}
		// set the total of players
		if(getRowCount() <= 0){
			try {
				setRowCount(database.getProducts().size());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		setPageSize(pageSize);
		return products;
	}

	@Override
	public Object getRowKey(Product product) {
		return product.getID();
	}
	
	@Override
	public Product getRowData(String productID) {
		Integer id = Integer.valueOf(productID);
		for ( Product product: products )
		{
			if ( id.equals(product.getID() )){
				return product;
			}
		}
		return null;
	}
	
	public static class ComparatorsByIDNameAsc {
		public static final Comparator<Product> NAME = new Comparator<Product>() {
			public int compare(Product o1, Product o2) {
				return Integer.toString(o1.getID())
						.compareTo(Integer.toString(o2.getID()));
			}
		};

	}

	public static class ComparatorsByIDDes {
		public static final Comparator<Product> NAME = new Comparator<Product>() {
			public int compare(Product o1, Product o2) {
				return Integer.toString(o2.getID())
						.compareTo(Integer.toString(o1.getID()));
			}
		};

	}
}
