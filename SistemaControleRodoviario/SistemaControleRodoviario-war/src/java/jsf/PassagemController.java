package jsf;

import ejb.InterfaceLocal;
import ejb.PassagemLocal;
import entity.Passagem;
import entity.Rota;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.naming.InitialContext;

@Named("passagemController")
@SessionScoped
public class PassagemController implements Serializable {

    private Passagem current;
    private DataModel items = null;
    @EJB (mappedName="ejb/PassagemFacade")
    private PassagemLocal ejbFacade;
    @EJB (mappedName="ejb/AssentoFacade", beanName="AssentoFacade",beanInterface=InterfaceLocal.class)
    private InterfaceLocal ejbAssentoFacade;
    @EJB (mappedName="ejb/RotaFacade", beanName="RotaFacade",beanInterface=InterfaceLocal.class)
    private InterfaceLocal ejbRotaFacade;
    @EJB (mappedName="ejb/LinhaFacade", beanName="LinhaFacade",beanInterface=InterfaceLocal.class)
    private InterfaceLocal ejbLinhaFacade;
    @EJB (mappedName="ejb/OnibusFacade", beanName="OnibusFacade",beanInterface=InterfaceLocal.class)
    private InterfaceLocal ejbOnibusFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PassagemController() {
    }

    public Passagem getSelected() {
        if (current == null) {
            current = new Passagem();  
            
            selectedItemIndex = -1;
        }
        return current;
    }

    private PassagemLocal getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Passagem) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Passagem();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getSelected().setUsuario(getUsuarioController().getSelected());
            getSelected().setNomeUsuario(getSelected().getUsuario().getNome());
            getSelected().setRgUsuario(getSelected().getUsuario().getRg());
//            getSelected().setHorarioChegada(current.getRota().getHoraDestino());
//            getSelected().setHorarioSaida(current.getRota().getHoraOrigem());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PassagemCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Passagem) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PassagemUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Passagem) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PassagemDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }
    public SelectItem[] getItemsAvailableSelectOneAssento() {
        return JsfUtil.getSelectItems(ejbAssentoFacade.findAll(), true);
    }
    public SelectItem[] getItemsAvailableSelectOneRota() {
        return JsfUtil.getSelectItems(ejbRotaFacade.findAll(), true);
    }
    public SelectItem[] getItemsAvailableSelectOneLinha() {
        return JsfUtil.getSelectItems(ejbLinhaFacade.findAll(), true);
    }
    public SelectItem[] getItemsAvailableSelectOneOnibus() {
        return JsfUtil.getSelectItems(ejbOnibusFacade.findAll(), true);
    }

    @FacesConverter(forClass = Passagem.class)
    public static class PassagemControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PassagemController controller = (PassagemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "passagemController");
            return controller.ejbFacade.find(getKey(value));
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
            if (object instanceof Passagem) {
                Passagem o = (Passagem) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Passagem.class.getName());
            }
        }
    }
    @FacesConverter(forClass = Rota.class)
    public static class RotaControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PassagemController controller = (PassagemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "passagemController");
                        try{
               controller.ejbRotaFacade = (InterfaceLocal) new InitialContext().lookup("java:app/SistemaControleRodoviario-ejb/ejb/RotaFacade"); 
            }catch(Exception e){
               e.getStackTrace();
            }
 
            return controller.ejbRotaFacade.find(getKey(value));
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
    public String passagemPorUsuario() {
        try {
            List<Passagem> listaPassagem = getFacade().passagemPorUsuario(current);
            if (listaPassagem.isEmpty()) {
               throw new Exception();
            }else{
                
                return "MenuPrincipal";
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    private UsuarioController getUsuarioController(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
    }
}