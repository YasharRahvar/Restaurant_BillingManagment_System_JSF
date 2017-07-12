/**
 * @author Yashar Rahvar
 * Date: 15/Jan/2016
 * Project: restaurantBillingManagment 
 * Class: Database
 * This class is responsible of connecting to database.
 */

package a00871798.database;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import a00871798.Product;

@SuppressWarnings("serial")
@ApplicationScoped
@ManagedBean
public class Database implements Serializable {
	private static List<Product> products = new ArrayList<Product>();
	private Product selectedProduct;
	private String username;
	private String password;
	private String url;
	private String driver;
	private Connection conn;

	// Get Connection
	public Connection getConnection() throws SQLException {
		url = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("url");
		driver = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("driver");
		username = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("user");
		password = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("pass");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("FAILED TO CONNECT TO DATABASE");
		}
		return conn;
	}

	public List<Product> getProducts() throws SQLException, NamingException, ClassNotFoundException, ParseException {
		try {
			products.clear();
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM A00871798_Products");
			while (result.next()) {
				ArrayList<String> oneRow = new ArrayList<String>();
				// Creating employee objects
				Product product = new Product();
				for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
					oneRow.add(result.getString(i));
				}
				for (int i = 0; i < oneRow.size();) {
					product.setID((Integer.parseInt(oneRow.get(i++))));
					product.setName(oneRow.get(i++));
					product.setCategory(oneRow.get(i++));
					product.setPrice(Double.parseDouble(oneRow.get(i++)));
				}
				products.add(product);
			}
			return products;
		} finally {
			close();
		}
	}

	public String add() throws SQLException, NamingException {
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM A00871798_Products WHERE ID = '" + selectedProduct.getID() + "'");
			if (rs.next()) {
				FacesContext.getCurrentInstance().addMessage("addProductForm:priceInputText",
						new FacesMessage("ID Already Exists for Another Product!"));
				System.out.println("exist");
			} else {
				int result = stmt.executeUpdate("INSERT INTO  A00871798_Products" + "(ID,Name,Category,Price)"
						+ "VALUES('" + selectedProduct.getID() + "', '" + selectedProduct.getName() + "', '"
						+ selectedProduct.getCategory() + "', '" + selectedProduct.getPrice() + "')");
				if (result > 0) {
					FacesContext.getCurrentInstance().addMessage("addProductForm:priceInputText",
							new FacesMessage("product Added Successfully."));
				}
			}
			return null;
		} finally {
			close();
			clear();
		}
	}

	public String remove() throws SQLException, NamingException, ClassNotFoundException, ParseException {
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM A00871798_Products WHERE ID = '" + selectedProduct.getID() + "'");
			if (rs.next()) {
				int result = stmt
						.executeUpdate("DELETE FROM A00871798_Products WHERE ID = '" + selectedProduct.getID() + "'");
				if (result > 0) {
					FacesContext.getCurrentInstance().addMessage("deleteProductForm:deleteInputText",
							new FacesMessage("Product is Removed Successfully --> ID:  " + selectedProduct.getID()));
				} else {
					FacesContext.getCurrentInstance().addMessage("deleteProductForm:deleteInputText",
							new FacesMessage("Failed to Remove Product"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage("deleteProductForm:deleteInputText",
						new FacesMessage("Failed to Remove Product --> " + selectedProduct.getID()));
			}
			return null;
		} finally {
			close();
			clear();
		}
	}

	public String find() throws SQLException, NamingException, IOException {
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();

			ResultSet result = stmt
					.executeQuery("SELECT name FROM A00871798_Products WHERE ID = '" + selectedProduct.getID() + "'");
			ResultSetMetaData rsmdForSearch = result.getMetaData();
			try {
				StringBuilder productName = new StringBuilder();
				while (result.next()) {
					for (int i = 1; i <= rsmdForSearch.getColumnCount(); i++) {
						productName.append((result.getString(i)) + " ");
					}
				}
				if (productName != null && !productName.toString().equals("")) {
					FacesContext.getCurrentInstance().addMessage("findProductForm:findInputText", new FacesMessage(
							"Product Found. --> " + productName + " With ID#  " + selectedProduct.getID()));
				} else {
					FacesContext.getCurrentInstance().addMessage("findProductForm:findInputText",
							new FacesMessage("No Match Found!"));
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return null;
		} finally {
			close();
			clear();
		}
	}

	public Product getSelectedProduct() {
		if (selectedProduct == null) {
			selectedProduct = new Product();
		}
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public void clear() {
		selectedProduct.setID(0);
		selectedProduct.setName(null);
		selectedProduct.setCategory(null);
		selectedProduct.setPrice(0);
	}

	public void close() throws SQLException {
		if (conn == null) {
			return;
		}
		conn.close();
		conn = null;
	}
}
