package com.amazon.ata.music.playlist.service.dependency;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistSongsRequest;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class MyApp {
    public static void main(String[] args) {
        DynamoDBMapper dynamoDbMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        PlaylistDao playlistDao = new PlaylistDao(dynamoDbMapper);

        Playlist myPlaylistOne = playlistDao.getPlaylist("rEDoR");
        System.out.println(myPlaylistOne);

        Playlist myPlaylistTwo = playlistDao.getPlaylist("PS5Bm");
        System.out.println(myPlaylistTwo);

//        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
//                .withId("Brian1")
//                .withOrder("NOT A VALID ORDER")
//                .build();
    }
}
