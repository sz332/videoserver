package com.acme.videoserver.core.image;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cactoos.scalar.SolidScalar;
import org.cactoos.scalar.UncheckedScalar;

import com.acme.videoserver.core.library.Image;

public class Base64EncodedImage implements Image {

    private static final Pattern PATTERN = Pattern.compile("data:image\\/([a-zA-Z]*);base64,([^\"]*)");

    private final UncheckedScalar<Base64Encoded> output;

    public Base64EncodedImage(String text) {

        this.output = new UncheckedScalar<>(new SolidScalar<>(() -> {

            Matcher matcher = PATTERN.matcher(text);

            String mimeType;
            byte[] data;

            if (matcher.matches()) {
                mimeType = "image/" + matcher.group(1);
                data = Base64.getMimeDecoder().decode(matcher.group(2));
            } else {
                mimeType = "";
                data = Base64.getMimeDecoder().decode(text);
            }

            return new Base64Encoded(mimeType, data);
        }));

    }

    @Override
    public String mimeType() {
        return output.value().mimeType();
    }

    @Override
    public byte[] data() {
        return output.value().data();
    }

    private class Base64Encoded {

        private final byte[] data;
        private final String mimeType;

        public Base64Encoded(String mimeType, byte[] data) {
            this.mimeType = mimeType;
            this.data = data;
        }

        public String mimeType() {
            return mimeType;
        }

        public byte[] data() {
            return data;
        }

    }

}
