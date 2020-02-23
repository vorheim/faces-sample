package com.vorheim.faces.sample.lib.controllers;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;

/**
 *
 * @author Darken
 */
public class ValidationDelegate {

    private static final String KEY_TITLE = "val.err.title";
    private static final String KEY_REQUIRED_MSG = "val.err.required";
    private static final String KEY_DATE_RANGE_MSG = "val.err.date.range";

    private final MessagesDelegate messagesDelegate;
    private final ResourceBundle bundle;

    private final String title;

    public ValidationDelegate(MessagesDelegate messagesDelegate, ResourceBundle bundle) {
        this.messagesDelegate = messagesDelegate;
        this.bundle = bundle;
        title = bundle.getString(KEY_TITLE);
    }

    private void warn(String msg) {
        messagesDelegate.warn(title, msg);
    }

    private void warn(String template, Object... arguments) {
        warn(MessageFormat.format(template, arguments));
    }

    public boolean validateRequired(Object value, String fieldName) {
        return validateRequired(new FieldToValidate<>(value, fieldName));
    }

    private boolean validateRequired(FieldToValidate field) {
        if (field.isEmpty()) {
            warn(bundle.getString(KEY_REQUIRED_MSG), field.name);
            return false;
        }
        return true;
    }

    public boolean validateDateRange(Date date0, String name0, Date date1, String name1) {
        return validateDateRange(new FieldToValidate<>(date0, name0), new FieldToValidate<>(date1, name1));
    }

    private boolean validateDateRange(FieldToValidate<Date> d0, FieldToValidate<Date> d1) {
        boolean result = validateRequired(d0);
        result &= validateRequired(d1);
        if (result && d0.value.compareTo(d1.value) > 0) {
            warn(MessageFormat.format(bundle.getString(KEY_DATE_RANGE_MSG), d0.name, d1.name));
        }
        return result;
    }

    public static class FieldToValidate<T> {

        public final T value;
        public final String name;

        public FieldToValidate(T value, String name) {
            this.value = value;
            this.name = name;
        }

        public boolean isEmpty() {
            if (value == null) {
                return true;
            }
            if (value instanceof String && ((String) value).trim().isEmpty()) {
                return true;
            }
            return value instanceof Collection && ((Collection) value).isEmpty();
        }
    }
}
