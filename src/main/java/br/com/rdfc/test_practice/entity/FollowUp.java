package br.com.rdfc.test_practice.entity;

import br.com.rdfc.test_practice.types.Label;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "FOLLOW_UP")
public class FollowUp {
    private UUID id;
    private String externalId;
    private LocalDateTime date;
    private Label label;
    private Patient patient;
    private List<Sample> samples;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "EXTERNAL_ID", unique = true, nullable = false)
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Column(name = "DATE", nullable = false)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Column(name = "LABEL", nullable = false)
    @Enumerated(EnumType.STRING)
    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @OneToMany(mappedBy = "followUp")
    public List<Sample> getSamples() {
        return samples;
    }

    public void setSamples(List<Sample> samples) {
        this.samples = samples;
    }
}
