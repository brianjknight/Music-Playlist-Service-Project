package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.PlaylistNotFoundException;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.GetPlaylistResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetPlaylistActivityTest {
    @Mock
    private PlaylistDao playlistDao;

    private GetPlaylistActivity getPlaylistActivity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getPlaylistActivity = new GetPlaylistActivity(playlistDao);
    }

    @Test
    public void handleRequest_savedPlaylistFound_returnsPlaylistModelInResult() {
        // GIVEN
        String expectedId = "expectedId";
        String expectedName = "expectedName";
        String expectedCustomerId = "expectedCustomerId";
        int expectedSongCount = 0;
        List<String> expectedTags = Lists.newArrayList("tag");

        Playlist playlist = new Playlist();
        playlist.setId(expectedId);
        playlist.setName(expectedName);
        playlist.setCustomerId(expectedCustomerId);
        playlist.setSongCount(expectedSongCount);
        playlist.setTags(Sets.newHashSet(expectedTags));

        when(playlistDao.getPlaylist(expectedId)).thenReturn(playlist);

        GetPlaylistRequest request = GetPlaylistRequest.builder()
            .withId(expectedId)
            .build();

        // WHEN
        GetPlaylistResult result = getPlaylistActivity.handleRequest(request, null);

        System.out.println("Playlist = " + result.getPlaylist());

        // THEN
        assertEquals(expectedId, result.getPlaylist().getId());
        assertEquals(expectedName, result.getPlaylist().getName());
        assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());
        assertEquals(expectedSongCount, result.getPlaylist().getSongCount());
        assertEquals(expectedTags, result.getPlaylist().getTags());
    }

    @Test
    public void handleRequest_savedPlaylistFoundNoTags_returnsPlaylistModelInResult() {
        // GIVEN
        String expectedId = "expectedId";
        String expectedName = "expectedName";
        String expectedCustomerId = "expectedCustomerId";
        int expectedSongCount = 0;
        List<String> expectedTags = new ArrayList<>();

        Playlist playlist = new Playlist();
        playlist.setId(expectedId);
        playlist.setName(expectedName);
        playlist.setCustomerId(expectedCustomerId);
        playlist.setSongCount(expectedSongCount);
        playlist.setTags(Sets.newHashSet(expectedTags));

        when(playlistDao.getPlaylist(expectedId)).thenReturn(playlist);

        GetPlaylistRequest request = GetPlaylistRequest.builder()
            .withId(expectedId)
            .build();

        // WHEN
        GetPlaylistResult result = getPlaylistActivity.handleRequest(request, null);

        // THEN
        assertEquals(expectedId, result.getPlaylist().getId());
        assertEquals(expectedName, result.getPlaylist().getName());
        assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());
        assertEquals(expectedSongCount, result.getPlaylist().getSongCount());
        assertNull(result.getPlaylist().getTags());
    }

    @Test
    public void handleRequest_noPlaylistFound_throwsPlaylistNotFoundException() {
        // GIVEN
        String expectedId = "expectedId";
        String expectedName = "expectedName";
        String expectedCustomerId = "expectedCustomerId";
        int expectedSongCount = 0;
        List<String> expectedTags = Lists.newArrayList("tag");

        Playlist playlist = new Playlist();
        playlist.setId(expectedId);
        playlist.setName(expectedName);
        playlist.setCustomerId(expectedCustomerId);
        playlist.setSongCount(expectedSongCount);
        playlist.setTags(Sets.newHashSet(expectedTags));

        when(playlistDao.getPlaylist(expectedId)).thenThrow(PlaylistNotFoundException.class);

        GetPlaylistRequest request = GetPlaylistRequest.builder()
                .withId(expectedId)
                .build();

        // WHEN THEN
        //GetPlaylistResult result = getPlaylistActivity.handleRequest(request, null);
        assertThrows(PlaylistNotFoundException.class,
                () -> getPlaylistActivity.handleRequest(request, null));
    }


}
