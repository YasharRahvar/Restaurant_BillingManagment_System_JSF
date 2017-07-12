/**
 * @author Yashar Rahvar
 * Date: 15/Jan/2016
 * Project: restaurantBillingManagment 
 * Class: UserManager
 */
package a00871798.authentication;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class UserManager implements Serializable  {

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String logout() throws IOException{
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    ec.redirect(ec.getRequestContextPath());
		return null;
	}
}
