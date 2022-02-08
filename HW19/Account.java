package HW19;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Account {

    private static final Random rnd = new Random();

    String userName;
    ArrayList<Video> likedVideos;

    public static void main(String[] args) {
        Account acc = new Account("Account", generateVideos(10));
        acc.addVideo(new Video("1", "sargsrg", 0));
        acc.printVideos();
        acc.removeVideoByIndex(1);
        acc.printVideos();
        acc.removeDuplicates();
        acc.printVideos();

    }

    private static Video[] generateVideos(int length) {
        Video[] videos = new Video[length];
        int stringLength = 5;
        int percentOfDuplicates = 20;
        for (int i = 0; i < length; i++){
            if(videos[i] != null){
                continue;
            }
            String name = generateRandomString(stringLength);
            String author = generateRandomString(stringLength);
            int likes = rnd.nextInt(100000);
            videos[i] = new Video(name, author, likes);
            if(rnd.nextInt(100) < percentOfDuplicates){
                videos[rnd.nextInt(length)] = videos[i].copy();
            }
        }
        return videos;
    }

    private static String generateRandomString(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String res = "";
        for(int i = 0; i < length; i++){
            res += alphabet.charAt(rnd.nextInt(alphabet.length()));
        }
        return res;
    }

    public Account(String userName, Video[] likedVideos) {
        this.userName = userName;
        this.likedVideos = new ArrayList<>();
        for (Video video : likedVideos) {
            this.addVideo(video);
        }
    }

    public Account(String userName) {
        this.userName = userName;
        this.likedVideos = new ArrayList<>();
    }

    public void removeVideoByIndex(int index) {
        this.likedVideos.remove(index);
    }

    public void removeVideo(Video video) {
        this.likedVideos.remove(video);
    }

    public void removeDuplicates() {
        for (int i = 0; i < likedVideos.size(); i++) {
            Video currentVideo = likedVideos.get(i);
            for (int j = i + 1; j < likedVideos.size(); j++) {
                Video duplicate = likedVideos.get(j);
                // Проверка по хэшу
                if (currentVideo.hashCode() == duplicate.hashCode()) {
                    // Полная проверка
                    if (currentVideo.equals(duplicate)) {
                        System.out.println("\n######");
                        System.out.println("Удаление видео " + duplicate);
                        System.out.println("######");
                        removeVideo(duplicate);
                        j--;
                        //При удалении элемента мы можем пропустить следующий, поэтому используем декремент
                    }
                }
            }
        }
    }

    public void addVideo(Video video) {
        this.likedVideos.add(video);
    }

    public void printVideos() {
        Iterator<Video> iterator = likedVideos.iterator();
        int iter = 1;
        System.out.println("Понравившиеся видео:");
        while (iterator.hasNext()) {
            System.out.println(iter++ + " - " + iterator.next().toString());
        }
    }
}
