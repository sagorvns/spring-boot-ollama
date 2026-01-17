package Design_Patterns;
//The Adapter Pattern is a structural design pattern that allows incompatible interfaces to work together.
// It acts as a bridge between the existing class and the client by wrapping the old interface with a new one.

interface MediaPlayer { void play(String fileType, String fileName); }

class MP3Player implements MediaPlayer {
    public void play(String fileType, String fileName) {
        System.out.println("Playing MP3: " + fileName);
    }
}

class MP4Player {
    void playMP4(String fileName) {
        System.out.println("Playing MP4: " + fileName);
    }
}

// Adapter Class
class MediaAdapter implements MediaPlayer {
    private MP4Player mp4Player = new MP4Player();

    public void play(String fileType, String fileName) {
        if (fileType.equalsIgnoreCase("MP4")) {
            mp4Player.playMP4(fileName);
        }
    }
    public static void main(String[] args) {
        MediaPlayer player = new MediaAdapter();
        player.play("MP4", "video.mp4"); // Playing MP4: video.mp4
    }
}

