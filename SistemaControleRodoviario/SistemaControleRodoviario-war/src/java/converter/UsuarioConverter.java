/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import ejb.UsuarioFacade;
import entity.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import jsf.util.ServiceLocator;

/**
 *
 * @author Eduardo
 */
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        try {
            UsuarioFacade ejbUsuarioFacade = (UsuarioFacade) ServiceLocator.lookupNoInterface(UsuarioFacade.class);
            return ejbUsuarioFacade.find(getKey(value));
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }

    }

    java.lang.Long getKey(String value) {
        java.lang.Long key;
        key = Long.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Long value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Usuario) {
            Usuario o = (Usuario) object;
            return getStringKey(o.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuario.class.getName());
        }
    }
}
