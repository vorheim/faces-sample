package com.vorheim.faces.sample.lib.controllers;

import java.util.function.LongPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author garodriguez
 */
public abstract class CRUDHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CRUDHandler.class);

    protected static final String ACTION_KEY = "a";
    protected static final String ID_KEY = "id";
    
    private final Runnable createAction;
    private final LongPredicate readAction;
    private final LongPredicate editAction;
    private final LongPredicate deleteAction;

    public CRUDHandler(Runnable createAction, LongPredicate readAction, LongPredicate editAction, LongPredicate deleteAction) {
        this.createAction = createAction;
        this.readAction = readAction;
        this.editAction = editAction;
        this.deleteAction = deleteAction;
    }

    protected abstract <T> T getParam(String key, Class<T> clazz);

    public String setParams(String action, Long id) {
        return String.format("%s=%s&%s=%s", ACTION_KEY, action, ID_KEY, id);
    }

    protected <T> T convert(Object value, Class<T> clazz) {
        if (Long.class.equals(clazz)) {
            Long l = null;
            try {
                l = new Long(String.valueOf(value));
            } catch (NumberFormatException e) {
            }
            return (T) l;
        } else {
            return (T) value;
        }
    }

    public void handleAction() {
        String action = getParam(ACTION_KEY, String.class);
        Long id = getParam(ID_KEY, Long.class);

        LOG.info("Action {} with id {}", action, id);
        if (action == null) {
            return;
        }
        switch (action) {
            case "c":
                createAction.run();
                break;
            case "r":
                readAction.test(id);
                break;
            case "u":
                editAction.test(id);
                break;
            case "d":
                deleteAction.test(id);
                break;
            default:
                break;
        }
    }

}
