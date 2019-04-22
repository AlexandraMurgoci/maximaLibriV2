package com.maximaLibri.maximaLibriV2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "bx_users")
public class BxUser {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Column
    private String location;

    @Column
    private Integer age;

    public BxUser() {
    }

    public BxUser(User user) {
        this.id = user.getId();
        this.age = user.getAge();
        this.location = user.getLocation();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BxUser bxUser = (BxUser) o;
        return id.equals(bxUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BxUser{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", age=" + age +
                '}';
    }
}
