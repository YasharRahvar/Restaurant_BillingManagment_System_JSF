/**
 * @author Yashar Rahvar
 * Date: 15/Jan/2016
 * Project: restaurantBillingManagment 
 * Class: Product
 */
package a00871798;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;

@SuppressWarnings("serial")
@SessionScoped
public class Product implements Serializable{

	private int ID;
	private String name;
	private String category;
	private double price;
	
	public Product(){
		
	}
	public Product(int iD, String name, String category, double price) {
		super();
		ID = iD;
		this.name = name;
		this.category = category;
		this.price = price;
	}
	/**
	 * 
	 * @return returns product id.
	 */
	public int getID() {
		return ID;
	}
/**
 * 
 * @param iD sets product id.
 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * 
	 * @return returns product name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name sets product name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return returns product category.
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * 
	 * @param category sets product category.
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * 
	 * @return returns product price.
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * 
	 * @param price sets product price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * toString method of product object.
	 */
	@Override
	public String toString() {
		return "Product [ID=" + ID + ", name=" + name + ", category=" + category + ", price=" + price + "]";
	}
	
}
