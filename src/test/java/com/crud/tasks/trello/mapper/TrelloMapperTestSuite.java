package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.trello.trellodto.TrelloBoardDto;
import com.crud.tasks.domain.trello.trellodto.TrelloCardDto;
import com.crud.tasks.domain.trello.trellodto.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void trelloMapperMapToDtoTest() {
        // given
        List<TrelloList> trelloLists = Collections.singletonList(new TrelloList("1", "name", true));
        List<TrelloBoard> trelloBoardList = Collections.singletonList(new TrelloBoard("1", "board", trelloLists));
        TrelloCard trelloCard = new TrelloCard("cardName1", "cardDescription1", "1", "1234");
        // when
        List<TrelloBoardDto> testedBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        List<TrelloListDto> testedTrelloListDto = trelloMapper.mapToListDto(trelloLists);
        TrelloCardDto testedTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // then
        assertEquals(testedBoardDtoList.get(0).getId(), trelloBoardList.get(0).getId());
        assertEquals(testedBoardDtoList.get(0).getName(), trelloBoardList.get(0).getName());
        assertEquals(testedTrelloListDto.get(0).getId(), trelloLists.get(0).getId());
        assertEquals(testedTrelloListDto.get(0).getName(), trelloLists.get(0).getName());
        assertEquals(testedTrelloListDto.get(0).isClosed(), trelloLists.get(0).isClosed());
        assertEquals(testedTrelloCardDto.getName(), trelloCard.getName());
        assertEquals(testedTrelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(testedTrelloCardDto.getPos(), trelloCard.getPos());
        assertEquals(testedTrelloCardDto.getListId(), trelloCard.getListId());
    }

    @Test
    public void trelloMapperMapToNotDtoTest() {
        // given
        List<TrelloListDto> trelloLists =
                Collections.singletonList(new TrelloListDto("1", "name1", true));
        List<TrelloBoardDto> trelloBoardList =
                Collections.singletonList(new TrelloBoardDto("nameBoard1", "1", trelloLists));
        TrelloCardDto trelloCard =
                new TrelloCardDto("cardName1", "cardDescription1", "1", "1234");

        // when
        List<TrelloBoard> testedBoardList = trelloMapper.mapToBoards(trelloBoardList);
        List<TrelloList> testedList = trelloMapper.mapToList(trelloLists);
        TrelloCard testedCard = trelloMapper.mapToCard(trelloCard);

        // then
        testedBoardList.forEach(trelloBoard -> {
            assertEquals(trelloBoard.getId(), trelloBoardList.get(0).getId());
            assertEquals(trelloBoard.getName(), trelloBoardList.get(0).getName());
        });
        testedList.forEach(trelloList -> {
            assertEquals(trelloList.getId(), trelloLists.get(0).getId());
            assertEquals(trelloList.getName(), trelloLists.get(0).getName());
            assertEquals(trelloList.isClosed(), trelloLists.get(0).isClosed());
        });
    }
}
