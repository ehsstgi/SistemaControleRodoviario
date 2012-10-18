package jsf;

import ejb.InterfaceLocal;
import ejb.PassagemLocal;
import entity.Passagem;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;

@Named("passagemController")
@SessionScoped
public class PassagemController implements Serializable {

    private Passagem current;
    private DataModel items = null;
    @EJB(mappedName = "ejb/PassagemFacade")
    private PassagemLocal ejbFacade;
    @EJB(mappedName = "ejb/AssentoFacade", beanName = "AssentoFacade", beanInterface = InterfaceLocal.class)
    private InterfaceLocal ejbAssentoFacade;
    @EJB(mappedName = "ejb/RotaFacade", beanName = "RotaFacade", beanInterface = InterfaceLocal.class)
    private InterfaceLocal ejbRotaFacade;
    @EJB(mappedName = "ejb/LinhaFacade", beanName = "LinhaFacade", beanInterface = InterfaceLocal.class)
    private InterfaceLocal ejbLinhaFacade;
    @EJB(mappedName = "ejb/OnibusFacade", beanName = "OnibusFacade", beanInterface = InterfaceLocal.class)
    private InterfaceLocal ejbOnibusFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @Inject
    private UsuarioController usuarioController;

    public PassagemController() {
    }

    public Passagem getSelected() {
        if (current == null) {
            current = new Passagem();
            current.setUsuario(usuarioController.getSelected());
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
                    return getFacade().countPorUsuario(getSelected());
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRangePorUsuario(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, getSelected()));
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
            getSelected().setUsuario(usuarioController.getSelected());
            getSelected().setNomeUsuario(getSelected().getUsuario().getNome());
            getSelected().setRgUsuario(getSelected().getUsuario().getRg());
            getSelected().setHorarioChegada(current.getRota().getHoraDestino());
            getSelected().setHorarioSaida(current.getRota().getHoraOrigem());
            getSelected().setValor(current.getRota().getValor());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PassagemCreated"));
            return "View";
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

    public String pagamento() {
        if (isPassagemValida()) {
            return "Pagamento";
        } else {
            return null;
        }
    }

    public boolean isPassagemValida() {
        try {
            List<Passagem> verifica = getFacade().verificaPassagem(current);
            if (verifica.isEmpty()) {
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return false;
        }
    }
}