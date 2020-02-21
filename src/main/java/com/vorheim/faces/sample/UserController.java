package com.vorheim.faces.sample;

import com.vorheim.faces.sample.lib.controllers.CRUDHandler;
import com.vorheim.faces.sample.lib.controllers.RequestParamsCRUDHandler;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author Darken
 */
@ManagedBean
@ViewScoped
public class UserController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private User current;
    private int selectedItemIndex;

    private final CRUDHandler crudHandler;

    public UserController() {
        crudHandler = new RequestParamsCRUDHandler(this::initForCreate, this::initForRead, this::initForUpdate, this::initForDelete);
    }

    @PostConstruct
    public void init() {
        current = new User();
        crudHandler.handleAction();
    }

    public String goTo(String action, Long id) {
        return "form.xhtml?faces-redirect=true&" + crudHandler.setParams(action, id);
    }

    private void initForCreate() {
        LOG.info("initForCreate");
    }

    private boolean initForRead(Long id) {
        LOG.info("initForRead {}", id);
        return true;
    }

    private boolean initForUpdate(Long id) {
        LOG.info("initForUpdate {}", id);
        return true;
    }

    private boolean initForDelete(Long id) {
        LOG.info("initForDelete {}", id);
        return true;
    }

    public void save() {
        LOG.info(current.getName());
    }

    public User getSelected() {
        if (current == null) {
            current = new User();
            selectedItemIndex = -1;
        }
        return current;
    }
}
