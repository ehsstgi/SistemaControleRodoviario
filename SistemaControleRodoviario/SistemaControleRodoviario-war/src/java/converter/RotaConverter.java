/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import ejb.InterfaceLocal;
import entity.Rota;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

/**
 *
 * @author Eduardo
 */
@FacesConverter(forClass = Rota.class)
public class RotaConverter implements Converter {

    private InterfaceLocal<Rota> ejbRotaFacade;

    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        try {
            ejbRotaFacade = (InterfaceLocal<Rota>) new InitialContext().lookup("java:app/SistemaControleRodoviario-ejb/RotaFacade!ejb.InterfaceLocal");

        } catch (Exception e) {
            e.getStackTrace();
        }
        return ejbRotaFacade.find(getKey(value));
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
        if (object instanceof Rota) {
            Rota o = (Rota) object;
            return getStringKey(o.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Rota.class.getName());
        }
    }
}
