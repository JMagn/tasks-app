package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = Collections.singletonList(
                new TrelloBoardDto("1", "Board_1", Collections.singletonList(
                        new TrelloListDto("1", "List_1", false)))
        );

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, result.size());
        assertEquals(trelloBoardDtoList, result);
        result.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("Board_1", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("List_1", trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
            });
        });
    }

    @Test
    public void shouldFetchEmptyTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        //Then
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void createTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Card_1",
                "Test_card",
                "top",
                "1");

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1",
                "Created_card_1",
                "http://test.com");

        when(trelloClient.createNewCard(ArgumentMatchers.any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals(createdTrelloCardDto, result);
    }
}