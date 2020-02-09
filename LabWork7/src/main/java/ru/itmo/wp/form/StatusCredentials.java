package ru.itmo.wp.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StatusCredentials {
    @NotNull
    @Pattern(regexp = "[0-9]+", message = "Numbers expected!")
    private String strId;

    @NotNull
    private long id;

    private boolean disabled;

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
