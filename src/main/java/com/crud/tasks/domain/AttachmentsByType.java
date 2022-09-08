package com.crud.tasks.domain;

import com.crud.tasks.domain.trello.Trello;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AttachmentsByType {

    @JsonProperty("trello")
    private Trello trello;
}
