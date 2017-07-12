/**
 * @author Yashar Rahvar
 * Date: 15/Jan/2016
 * Project: restaurantBillingManagment 
 * Class: Order
 */
package a00871798;

import java.io.Serializable;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import a00871798.database.Database;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class Order implements Serializable {

	Database database = new Database();
	private List<Product> selectedProducts;

	/**
	 * 
	 * @param p sets the product
	 */
	public void addToBasket(Product p) {
		selectedProducts.add(p);
	}

}
