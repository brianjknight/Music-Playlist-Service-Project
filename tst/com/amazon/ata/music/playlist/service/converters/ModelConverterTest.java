package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.models.SongModel;
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

    @Test
    public void toSongModel_givenAlbumTrack_returnsSongModelWithSameAttributes() {
        //GIVEN
        AlbumTrack albumTrack = new AlbumTrack();
        albumTrack.setAsin("B019HKJTCI");
        albumTrack.setTrackNumber(6);
        albumTrack.setAlbumName("Dark Side of the Moon");
        albumTrack.setSongTitle("Money");

        //WHEN
        SongModel songModel = modelConverter.toSongModel(albumTrack);

        //THEN
        assertTrue(albumTrack.getAsin().equals(songModel.getAsin()), "Expected asins to be the same.");
        assertTrue(albumTrack.getAlbumName().equals(songModel.getAlbum()), "Expected Album Names to be the same.");
        assertTrue(albumTrack.getSongTitle().equals(songModel.getTitle()), "Expected song titles to be the same.");
        assertTrue(albumTrack.getTrackNumber().equals(songModel.getTrackNumber()), "Expected track numbers to be the same.");

    }

}
