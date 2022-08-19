package com.amazon.ata.music.playlist.service.dependency;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazon.ata.music.playlist.service.converters.AlbumTrackLinkedListConverter;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistSongsRequest;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.ArrayList;
import java.util.List;

public class MyApp {
    public static void main(String[] args) {
        DynamoDBMapper dynamoDbMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        PlaylistDao playlistDao = new PlaylistDao(dynamoDbMapper);

        Playlist myPlaylistOne = playlistDao.getPlaylist("rEDoR");
        System.out.println(myPlaylistOne);

        List<String> myStrings = new ArrayList<>();
        myStrings.add("One");
        myStrings.add("Two");
        myStrings.add("Three");

//        List<String> otherlist = new ArrayList<>();

//        AlbumTrackLinkedListConverter converter = new AlbumTrackLinkedListConverter();
//        String convertedList = converter.convert(myStrings);
//        System.out.println(convertedList);

//        Playlist myPlaylistTwo = playlistDao.getPlaylist("PS5Bm");
//        System.out.println(myPlaylistTwo);

//        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
//                .withId("Brian1")
//                .withOrder("NOT A VALID ORDER")
//                .build();
    }
}
