package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ModelConverterTest {
    private Playlist myPlaylist;
    private ModelConverter modelConverter;
    Set<String> pinkFloydTags;


    @BeforeEach
    public void setup() {
        modelConverter = new ModelConverter();
        pinkFloydTags = new HashSet<>();
    }

    @Test
    public void toPlaylistModel_givenPlaylist_returnsPlaylistModelMatchingAttributes() {
        //GIVEN
        myPlaylist = new Playlist();
        myPlaylist.setId("PL1");
        myPlaylist.setName("Pink Floyd");
        myPlaylist.setCustomerId("Brian123");
        myPlaylist.setSongCount(3);

        pinkFloydTags.add("Dark Side");
        pinkFloydTags.add("The Wall");
        myPlaylist.setTags(pinkFloydTags);

        //WHEN
        PlaylistModel pinkFloydPlaylistModel = modelConverter.toPlaylistModel(myPlaylist);

        //THEN
        assertEquals(myPlaylist.getId(), pinkFloydPlaylistModel.getId(), "Expected id attribute to match.");
        assertEquals(myPlaylist.getName(), pinkFloydPlaylistModel.getName(), "Expected name attribute to match.");
        assertEquals(myPlaylist.getCustomerId(), pinkFloydPlaylistModel.getCustomerId(), "Expected customerId attribute to match.");
        assertEquals(myPlaylist.getSongCount(), pinkFloydPlaylistModel.getSongCount(), "Expected songCount attribute to match.");
    }

    @Test
    public void toPlaylistModel_givenPlaylistWithNoTags_returnsPlaylistModelWithNullList() {
        //GIVEN
        myPlaylist = new Playlist();
        myPlaylist.setId("PL1");
        myPlaylist.setName("Pink Floyd");
        myPlaylist.setCustomerId("Brian123");
        myPlaylist.setSongCount(3);
        myPlaylist.setTags(pinkFloydTags);

        //WHEN
        PlaylistModel pinkFloydPlaylistModel = modelConverter.toPlaylistModel(myPlaylist);

        //THEN
        assertNull(pinkFloydPlaylistModel.getTags(), "Expected the attribute List<String> tags to be null");
    }



}
