/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import ejb.InterfaceLocal;
import ejb.RotaFacade;
import entity.Rota;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import jsf.util.ServiceLocator;

/**
 *
 * @author Eduardo
 */
@FacesConverter(forClass = Rota.class)
public class RotaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        try {
            InterfaceLocal<Rota> ejbRotaFacade = (InterfaceLocal<Rota>) ServiceLocator.lookupInterfaceLocal(RotaFacade.class, false);
            return ejbRotaFacade.find(getKey(value));
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

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Rota) {
            Rota o = (Rota) object;
            return getStringKey(o.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Rota.class.getName());
        }
    }
}
