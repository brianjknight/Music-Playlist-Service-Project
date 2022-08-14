package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    /**
     * Converts a provided {@link Playlist} into a {@link PlaylistModel} representation.
     * @param playlist the playlist to convert
     * @return the converted playlist
     */
    public PlaylistModel toPlaylistModel(Playlist playlist) {
        List<String> tagsList = null;

        if (playlist.getTags() != null && playlist.getTags().size() != 0) {
            tagsList = new ArrayList<>(playlist.getTags());
        }

        return PlaylistModel.builder()
                .withId(playlist.getId())
                .withName(playlist.getName())
                .withCustomerId(playlist.getCustomerId())
                .withSongCount(playlist.getSongCount())
                .withTags(tagsList)
                .build();
    }
}
