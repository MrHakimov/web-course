package ru.itmo.wp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table
public class Tag {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Pattern(regexp = "[0-9]+", message = "Expected numbers")
    private String name;

    /** @noinspection unused*/
    public Tag() {
    }

    public Tag(@NotNull String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
