package jsf;

import ejb.UsuarioFacade;
import entity.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import jsf.util.JsfUtil;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario current;
    @EJB
    private UsuarioFacade ejbFacade;

    public UsuarioController() {
    }

    public Usuario getSelected() {
        if (current == null) {
            current = new Usuario();

        }
        return current;
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public String prepareCreate() {
        current = new Usuario();

        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
            return "Index";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {

        performDestroy();

        current = null;
        return "Index";
    }

    public String login() {
        try {
            List<Usuario> usuarioLogin = getFacade().verificaLogin(current);
            if (usuarioLogin.isEmpty()) {
                throw new Exception();
            } else {
                current = usuarioLogin.get(0);
                return "MenuPrincipal";
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String exit() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }
}
