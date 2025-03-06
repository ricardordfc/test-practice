package br.com.rdfc.test_practice.entity;

import br.com.rdfc.test_practice.types.Type;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "SAMPLE")
public class Sample {
    private UUID id;
    private String externalId;
    private LocalDateTime date;
    private FollowUp followUp;
    private Type type;
    private String storage;

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

    @ManyToOne
    @JoinColumn(name = "FOLLOW_UP_ID", nullable = false)
    public FollowUp getFollowUp() {
        return followUp;
    }

    public void setFollowUp(FollowUp followUp) {
        this.followUp = followUp;
    }

    @Column(name = "TYPE", nullable = false)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Column(name = "STORAGE", nullable = false)
    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

}
