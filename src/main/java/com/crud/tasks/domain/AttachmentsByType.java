package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AttachmentsByType {

    @JsonProperty("trello")
    private Trello trello;
}
