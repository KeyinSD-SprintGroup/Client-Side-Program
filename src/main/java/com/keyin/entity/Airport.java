package com.keyin.entity;

public class Airport {
    private long id;
    private String name;
    private String code;
    private Long cityId;

    public Long getCityId() {
        return cityId;
    }

    public void setCity(Long id) {
        this.cityId = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
