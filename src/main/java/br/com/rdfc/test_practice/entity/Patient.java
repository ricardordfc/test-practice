package br.com.rdfc.test_practice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "PATIENT")
public class Patient implements Serializable {

    private UUID id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    public Patient() {
    }

    public Patient(UUID id, String name, String cpf, LocalDate birthDate, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false, length = 512)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CPF", nullable = false, length = 11)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @JsonProperty("birth_date")
    @Column(name = "BIRTH_DATE", nullable = false)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("creation_date")
    @Column(name = "CREATION_DATE", nullable = false)
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("update_date")
    @Column(name = "UPDATE_DATE", nullable = false)
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(name, patient.name) && Objects.equals(cpf, patient.cpf) && Objects.equals(birthDate, patient.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, birthDate);
    }

    @Override
    public String toString() {
        String sb;
        sb = "Patient{" + "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", birthDate=" + birthDate +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
        return sb;
    }
}
