package com.amazon.ata.music.playlist.service.dynamodb;

import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.exceptions.AlbumTrackNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AlbumTrackDaoTest {
    @InjectMocks
    AlbumTrackDao albumTrackDao;

    @Mock
    DynamoDBMapper dynamoDBMapper;

    AlbumTrack albumTrack;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        albumTrack = new AlbumTrack();
        albumTrack.setAsin("B019HKJTCI");
        albumTrack.setTrackNumber(6);
        albumTrack.setAlbumName("Dark Side of the Moon");
        albumTrack.setSongTitle("Money");
    }

    @Test
    public void getAlbumTrack_givenExistingAlbumTrack_returnAlbumTrack() {
        //GIVEN
        String asin = "B019HKJTCI";
        Integer trackNumber = 6;
        when(dynamoDBMapper.load(AlbumTrack.class, asin, trackNumber)).thenReturn(albumTrack);

        //WHEN
        AlbumTrack retrievedAlbumTrack = albumTrackDao.getAlbumTrack(asin, trackNumber);

        //THEN
        assertTrue(albumTrack.equals(retrievedAlbumTrack), "Expected the requested album track to be equal.");
    }

    @Test
    public void getAlbumTrack_givenNonExistingAlbumTrack_throwsAlbumTrackNotFoundException() {
        //GIVEN
        String asin = "falseASIN";
        Integer trackNumber = 6;
        when(dynamoDBMapper.load(AlbumTrack.class, asin, trackNumber)).thenReturn(null);

        //WHEN & THEN
        assertThrows(AlbumTrackNotFoundException.class, () -> albumTrackDao.getAlbumTrack(asin, trackNumber));
    }


}
