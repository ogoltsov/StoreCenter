package com.epam.ok.storeCenter.model;

public class ResourceSpeciality extends BaseEntity {

    private Integer resourceId;
    private Integer specialityId;

    public ResourceSpeciality() {
    }

    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "ResourceSpeciality{" +
                "resourceId=" + resourceId +
                ", specialityId=" + specialityId +
                '}';
    }
}
