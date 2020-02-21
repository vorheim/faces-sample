/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vorheim.faces.sample.lib.controllers;

import java.util.function.LongPredicate;
import javax.faces.context.FacesContext;

/**
 *
 * @author garodriguez
 */
public class SessionMapCRUDHandler extends CRUDHandler {

    private static final String KEY_PREFIX = "crud_handler_";

    public SessionMapCRUDHandler(Runnable createAction, LongPredicate readAction, LongPredicate editAction, LongPredicate deleteAction) {
        super(createAction, readAction, editAction, deleteAction);
    }

    @Override
    protected <T> T getParam(String key, Class<T> clazz) {
        Object value = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(KEY_PREFIX + key);
        return convert(value, clazz);
    }

    @Override
    public String setParams(String action, Long id) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(KEY_PREFIX + ACTION_KEY, action);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(KEY_PREFIX + ID_KEY, id);
        return super.setParams(action, id);
    }

}
