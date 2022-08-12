package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.music.playlist.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.CreatePlaylistResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreatePlaylistActivityTest {
    private CreatePlaylistActivity createPlaylistActivity;
    private CreatePlaylistRequest createPlaylistRequest;

    @Mock
    private PlaylistDao playlistDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        createPlaylistActivity = new CreatePlaylistActivity(playlistDao);
    }

    @Test
    public void handleRequest_provideCreatePlaylistRequest_returnsCreatePlaylistResult(){
        //GIVEN
        String name = "expectedName";
        String customerId = "expectedCustomerId";
        List<String> tags = Arrays.asList("tag1, tag2, tag3");
        CreatePlaylistRequest playlistRequest = CreatePlaylistRequest.builder()
                .withName(name)
                .withCustomerId(customerId)
                .withTags(tags)
                .build();

        //WHEN
        CreatePlaylistResult createPlaylistResult = createPlaylistActivity.handleRequest(playlistRequest, null);

        //THEN
        assertEquals(playlistRequest.getName(), createPlaylistResult.getPlaylist().getName(), "Expected name attributes to be equal.");
        assertEquals(playlistRequest.getCustomerId(), createPlaylistResult.getPlaylist().getCustomerId(), "Expected customerId attributes to be equal.");
        assertEquals(playlistRequest.getTags(), createPlaylistResult.getPlaylist().getTags(), "Expected tags attributes to be equal.");
    }

    @Test
    public void handleRequest_invalidCharactersDoubleQuotesInName_throwsInvalidAttributeException() {
        //GIVEN
        String name = "invalidName\"";
        String customerId = "expectedCustomerId";
        List<String> tags = Arrays.asList("tag1, tag2, tag3");
        CreatePlaylistRequest playlistRequest = CreatePlaylistRequest.builder()
                .withName(name)
                .withCustomerId(customerId)
                .withTags(tags)
                .build();

        //WHEN THEN
        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(playlistRequest, null));

    }

    @Test
    public void handleRequest_invalidCharactersSingleQuotesInName_throwsInvalidAttributeException() {
        //GIVEN
        String name = "invalidName'''";
        String customerId = "expectedCustomerId";
        List<String> tags = Arrays.asList("tag1, tag2, tag3");
        CreatePlaylistRequest playlistRequest = CreatePlaylistRequest.builder()
                .withName(name)
                .withCustomerId(customerId)
                .withTags(tags)
                .build();

        //WHEN THEN
        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(playlistRequest, null));

    }

    @Test
    public void handleRequest_invalidCharactersBackSlashInName_throwsInvalidAttributeException() {
        //GIVEN
        String name = "invalidName\\";
        String customerId = "expectedCustomerId";
        List<String> tags = Arrays.asList("tag1, tag2, tag3");
        CreatePlaylistRequest playlistRequest = CreatePlaylistRequest.builder()
                .withName(name)
                .withCustomerId(customerId)
                .withTags(tags)
                .build();

        //WHEN THEN
        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(playlistRequest, null));

    }

    @Test
    public void handleRequest_invalidCharactersDoubleQuotesInCustomerId_throwsInvalidAttributeException() {
        //GIVEN
        String name = "validName";
        String customerId = "invalidCustomerId\"\"";
        List<String> tags = Arrays.asList("tag1, tag2, tag3");
        CreatePlaylistRequest playlistRequest = CreatePlaylistRequest.builder()
                .withName(name)
                .withCustomerId(customerId)
                .withTags(tags)
                .build();

        //WHEN THEN
        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(playlistRequest, null));

    }

    @Test
    public void handleRequest_invalidCharactersSingleQuotesInCustomerId_throwsInvalidAttributeException() {
        //GIVEN
        String name = "validName";
        String customerId = "invalidCustomerId\'\'\'";
        List<String> tags = Arrays.asList("tag1, tag2, tag3");
        CreatePlaylistRequest playlistRequest = CreatePlaylistRequest.builder()
                .withName(name)
                .withCustomerId(customerId)
                .withTags(tags)
                .build();

        //WHEN THEN
        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(playlistRequest, null));

    }

    @Test
    public void handleRequest_invalidCharactersBackSlashInCustomerId_throwsInvalidAttributeException() {
        //GIVEN
        String name = "validName";
        String customerId = "\\invalidCustomerId\\";
        List<String> tags = Arrays.asList("tag1, tag2, tag3");
        CreatePlaylistRequest playlistRequest = CreatePlaylistRequest.builder()
                .withName(name)
                .withCustomerId(customerId)
                .withTags(tags)
                .build();

        //WHEN THEN
        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(playlistRequest, null));

    }


}
