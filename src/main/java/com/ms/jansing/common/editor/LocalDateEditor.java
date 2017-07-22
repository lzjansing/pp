package com.ms.jansing.common.editor;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by jansing on 17-7-22.
 */
public class LocalDateEditor extends PropertyEditorSupport {
    public LocalDateEditor() {
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isNotBlank(text)) {
            this.setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

    }

    public String getAsText() {
        LocalDate obj = (LocalDate) this.getValue();
        return obj == null ? "" : DateTimeFormatter.ofPattern("yyyy-MM-dd").format(obj);
    }
}
