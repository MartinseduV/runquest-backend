package com.runquest.runquest_backend.service;
import com.runquest.runquest_backend.dto.RunRequest;
import com.runquest.runquest_backend.entity.Player;
import com.runquest.runquest_backend.entity.Run;
import com.runquest.runquest_backend.exceptions.PlayerNotFoundException;
import com.runquest.runquest_backend.repository.PlayerRepository;
import com.runquest.runquest_backend.repository.RunRepository;
import com.runquest.runquest_backend.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private RunRepository runRepository;

    @InjectMocks
    private PlayerService playerService;



    @Test
    void shouldCompleteAndGiveXp(){

        Player player = new Player();
        player.setId(1L);
        player.setLevel(1);
        player.setXp(0L);

        RunRequest request = new RunRequest();
        request.setDistanceMeters(5000L);
        request.setDurationSeconds(1800L);


        when(playerRepository.findById(1L))
                .thenReturn(Optional.of(player));


        when(playerRepository.save(any(Player.class)))
                .thenReturn(player);

        Player result = playerService.completeRun(1L, request);

        assertEquals(50L, result.getXp());
        assertEquals(1, result.getLevel());
        verify(runRepository).save(any(Run.class));
        verify(playerRepository).save(player);
    }

    @Test
    void shouldThrowExceptionWhenPlayerDoesNotExist(){
        RunRequest request = new RunRequest();
        request.setDistanceMeters(5000L);
        request.setDurationSeconds(1800L);

        when(playerRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                PlayerNotFoundException.class,
                () -> playerService.completeRun(999L, request)
        );

        verify(runRepository, never()).save(any(Run.class));
        verify(playerRepository, never()).save(any(Player.class));


    }


}
