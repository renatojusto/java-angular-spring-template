package com.templatestack.service.dto.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

public class ProjetistaResponse extends RepresentationModel<ProjetistaResponse> implements Serializable {

    private Long id;
    private String name;
    private Date dateCreation;
    private Date dateLastUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateLastUpdate() {
        return dateLastUpdate;
    }

    public void setDateLastUpdate(Date dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProjetistaResponse that = (ProjetistaResponse) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(dateCreation, that.dateCreation) &&
            Objects.equals(dateLastUpdate, that.dateLastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, dateCreation, dateLastUpdate);
    }
}
