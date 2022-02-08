package HW19;

import java.util.Objects;
import java.util.Random;

public class Video {
    String name;
    String author;
    int likeCount;

    public static void main(String[] args) {
        Video a = new Video("a", "b",1);
        Video b = new Video("a", "b",1);
    }

    public Video(String name, String author, int likeCount) {
        this.name = name;
        this.author = author;
        this.likeCount = likeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return likeCount == video.likeCount && Objects.equals(name, video.name) && Objects.equals(author, video.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, likeCount);
    }

    public Video copy(){
        return new Video(this.name, this.author, this.likeCount);
    }

    @Override
    public String toString() {
        return String.format(
                "%s: %s. Likes: %d",
                name,
                author,
                likeCount
        );
    }
}
