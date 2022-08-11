package com.amazon.ata.music.playlist.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Represents a record in the album_tracks table.
 */
@DynamoDBTable(tableName = "album_tracks")
public class AlbumTrack {
    @DynamoDBHashKey(attributeName = "asin")
    private String asin;

    @DynamoDBRangeKey(attributeName = "track_number")
    private Integer trackNumber;

    @DynamoDBAttribute(attributeName = "album_name")
    private String albumName;

    @DynamoDBAttribute(attributeName = "song_title")
    private String songTitle;

}
