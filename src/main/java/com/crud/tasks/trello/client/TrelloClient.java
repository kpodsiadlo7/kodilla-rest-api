package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloAppToken;

    @Value("${trello.api.username}")
    private String trelloUsername;

    public List<TrelloBoardDto> getTrelloBoards() {
        return buildURI();
    }

    private List<TrelloBoardDto>buildURI(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" +trelloUsername +"/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloAppToken)
                .build().encode().toUri();
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();
    }

}
