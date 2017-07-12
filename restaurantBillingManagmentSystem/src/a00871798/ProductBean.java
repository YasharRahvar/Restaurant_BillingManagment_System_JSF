/**
 * @author Yashar Rahvar
 * Date: 15/Jan/2016
 * Project: restaurantBillingManagment 
 * Class: ProductBean 
 */
package a00871798;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

@ViewScoped
@ManagedBean
public class ProductBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 820049723526504197L;
	private LazyDataModel<Product> products = null;
	private Product product;

	/**
	 * 
	 * @return returns LazyDataModel<Product>
	 */
	public LazyDataModel<Product> getAllProducts() {
		if (products == null) {
			products = new ProductLazyList();
		}
		return products;
	}

	/**
	 * 
	 * @return product
	 */
	public Product getProduct() {
		if (product == null) {
			product = new Product();
		}
		return product;
	}

	/**
	 * 
	 * @param product
	 *            setting product.
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		Product selectedProduct = ((Product) event.getObject());
		System.out.println(selectedProduct);
		LoadSelectedProducts.addToSelectedProducts(selectedProduct);
	}

	/**
	 * 
	 * @param event
	 */

	public void onRowSelectRemove(SelectEvent event) {
		Product selectedProduct = ((Product) event.getObject());
		System.out.println(selectedProduct);
		LoadSelectedProducts.removeFromSelectproducts(selectedProduct);
	}

	/**
	 * 
	 * @param p
	 */
	public void addToCard(Product p) {
		LoadSelectedProducts.addToSelectedProducts(p);

	}
}
