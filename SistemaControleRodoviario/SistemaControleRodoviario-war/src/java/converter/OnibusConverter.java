/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import ejb.InterfaceLocal;
import ejb.OnibusFacade;
import entity.Onibus;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import jsf.util.ServiceLocator;

/**
 *
 * @author Eduardo
 */
@FacesConverter(forClass = Onibus.class)
public class OnibusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        try {
            InterfaceLocal<Onibus> ejbOnibusFacade = (InterfaceLocal<Onibus>) ServiceLocator.lookupInterfaceLocal(OnibusFacade.class, false);
            return ejbOnibusFacade.find(getKey(value));
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
        if (object instanceof Onibus) {
            Onibus o = (Onibus) object;
            return getStringKey(o.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Onibus.class.getName());
        }
    }
}