package com.agr.user.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "session_active", nullable = false)
    private Boolean sessionActive;

    @Column(name = "id_person", nullable = false)
    private Long idPerson;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "status_user", nullable = false)
    private Boolean statusUser;

    @Column(name = "intentos_fallidos", nullable = false)
    private Integer intentosFallidos = 0;

    public User(Long id, String username, String password, String email, Boolean sessionActive, Long idPerson, Boolean status, Boolean statusUser, Integer intentosFallidos) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.sessionActive = sessionActive;
        this.idPerson = idPerson;
        this.status = status;
        this.statusUser = statusUser;
        this.intentosFallidos = intentosFallidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSessionActive() {
        return sessionActive;
    }

    public void setSessionActive(Boolean sessionActive) {
        this.sessionActive = sessionActive;
    }

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(Boolean statusUser) {
        this.statusUser = statusUser;
    }

    public Integer getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(Integer intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }
}
