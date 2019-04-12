package com.acme.videoserver.core.library;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ComparableVideoclip implements Videoclip {

    private final Videoclip clip;

    public ComparableVideoclip(Videoclip clip) {
        this.clip = clip;
    }

    @Override
    public String uuid() {
        return clip.uuid();
    }

    @Override
    public String title() {
        return clip.title();
    }

    @Override
    public Image thumbnail() {
        return clip.thumbnail();
    }

    @Override
    public Instant recordingDateTime() {
        return clip.recordingDateTime();
    }

    @Override
    public String description() {
        return clip.description();
    }

    @Override
    public List<String> participants() {
        return clip.participants();
    }

    @Override
    public List<String> tags() {
        return clip.tags();
    }

    @Override
    public int hashCode() {
        return Objects.hash(description(), participants(), recordingDateTime(), tags(), thumbnail(), title(), uuid());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Videoclip)) {
            return false;
        }

        Videoclip other = Videoclip.class.cast(obj);

        return Objects.equals(uuid(), other.uuid())
                && Objects.equals(title(), other.title())
                && Objects.equals(description(), other.description())
                && Objects.equals(recordingDateTime(), other.recordingDateTime())
                && new HashSet<>(participants()).equals(new HashSet<>(other.participants()))
                && new HashSet<>(tags()).equals(new HashSet<>(other.tags())) && thumbnailEquals(thumbnail(), other.thumbnail());
    }

    private boolean thumbnailEquals(Image image1, Image image2) {
        if ((image1 == null && image2 != null) || (image1 != null && image2 == null) || (image1 == null && image2 == null)) {
            return false;
        }

        return image1.equals(image2);
    }

}
