/**
 * @author Yashar Rahvar
 * Date: 15/Jan/2016
 * Project: restaurantBillingManagment 
 * Class: LoadSelectedProducts
 * This class is responsible of loading products.
 */
package a00871798;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class LoadSelectedProducts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4487935594869393825L;
	private static List<Product> selectedProducts = new ArrayList<Product>();
	private double bill;
	

	public static void addToSelectedProducts(Product selectedProduct){
		selectedProducts.add(selectedProduct);
	}
	 
	public static void removeFromSelectproducts(Product selectedProduct){
			selectedProducts.remove(selectedProduct);
	}
	public  List<Product> getSelectedProducts() {
        return selectedProducts;
    }
	
	public double getBill(){
	    bill = 0;
		for(int i=0;i<selectedProducts.size();i++){
			bill +=selectedProducts.get(i).getPrice();
		}
		return bill;
	}
}
