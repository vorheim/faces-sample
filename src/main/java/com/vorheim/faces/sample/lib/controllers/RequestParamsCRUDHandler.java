package com.vorheim.faces.sample.lib.controllers;

import java.util.function.LongPredicate;
import javax.faces.context.FacesContext;

/**
 *
 * @author garodriguez
 */
public class RequestParamsCRUDHandler extends CRUDHandler {

    public RequestParamsCRUDHandler(Runnable createAction, LongPredicate readAction, LongPredicate editAction, LongPredicate deleteAction) {
        super(createAction, readAction, editAction, deleteAction);
    }

    @Override
    protected <T> T getParam(String key, Class<T> clazz) {
        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
        return convert(param, clazz);
    }

}
