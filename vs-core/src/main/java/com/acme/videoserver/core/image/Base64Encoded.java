package com.acme.videoserver.core.image;

import java.util.Base64;

import com.acme.videoserver.core.library.Image;

public class Base64Encoded {

    private final Image image;

    public Base64Encoded(Image image) {
        this.image = image;
    }

    public String asString() {

        byte[] data = image.data();

        String encodedData = data.length == 0 ? "" : Base64.getMimeEncoder().encodeToString(image.data());

        return "data:" + image.mimeType() + ";base64," + encodedData;
    }

}
