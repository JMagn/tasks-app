package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToBoards() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("Board 1", "Test board", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(1, result.size());
        assertThat(result).isEqualTo(Collections.singletonList(new TrelloBoard("Board 1", "Test board", new ArrayList<>())));
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard("Board 1", "Test board", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(1, result.size());
        assertThat(result).isEqualTo(Collections.singletonList(new TrelloBoardDto("Board 1", "Test board", new ArrayList<>())));
    }

    @Test
    public void mapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("List 1", "Test list", true);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto);
        //When
        List<TrelloList> result = trelloMapper.mapToList(trelloListDtoList);
        //Then
        assertEquals(1, result.size());
        assertThat(result).isEqualTo(Collections.singletonList(new TrelloList("List 1", "Test list", true)));
    }

    @Test
    public void mapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("List 1", "Test list", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        //When
        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(1, result.size());
        assertThat(result).isEqualTo(Collections.singletonList(new TrelloListDto("List 1", "Test list", true)));
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card", "Card desc", "1", "2");
        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertThat(result).isEqualTo(new TrelloCardDto("Card", "Card desc", "1", "2"));
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("CardDto", "CardDto desc", "1", "2");
        //When
        TrelloCard result = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertThat(result).isEqualTo(new TrelloCard("CardDto", "CardDto desc", "1", "2"));
    }

    @Test
    public void mapNullBoards() {
        //Given
        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(null);
        //Then
        assertThat(result).isEqualTo(null);
    }

    @Test
    public void mapNullBoardsDto() {
        //Given
        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(null);
        //Then
        assertThat(result).isEqualTo(null);
    }

    @Test
    public void mapNullList() {
        //Given
        //When
        List<TrelloList> result = trelloMapper.mapToList(null);
        //Then
        assertThat(result).isEqualTo(null);
    }

    @Test
    public void mapNullListDto() {
        //Given
        //When
        List<TrelloListDto> result = trelloMapper.mapToListDto(null);
        //Then
        assertThat(result).isEqualTo(null);
    }

    @Test
    public void mapNullCardDto() {
        //Given
        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(null);
        //Then
        assertThat(result).isEqualTo(null);
    }

    @Test
    public void mapNullCard() {
        //Given
        //When
        TrelloCard result = trelloMapper.mapToCard(null);
        //Then
        assertThat(result).isEqualTo(null);
    }
}
