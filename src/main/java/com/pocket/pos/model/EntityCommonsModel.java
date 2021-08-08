package com.pocket.pos.model;

import java.time.LocalDateTime;

public abstract class EntityCommonsModel {

    private Long version;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private boolean deleted;

    public EntityCommonsModel() {

    }

    public EntityCommonsModel(Long version, boolean deleted) {
        this.version = version;
        this.deleted = deleted;
    }

    public EntityCommonsModel(Long version, LocalDateTime createDateTime, LocalDateTime updateDateTime, boolean deleted) {
        this.version = version;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
        this.deleted = deleted;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
