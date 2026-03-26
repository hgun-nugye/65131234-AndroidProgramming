package ntu.bt8.recyclerview;

public class Landscape {
    private String imageName; // Tên file ảnh trong thư mục mipmap
    private String caption;   // Chú thích cho ảnh

    public Landscape(String imageName, String caption) {
        this.imageName = imageName;
        this.caption = caption;
    }

    public String getImageName() { return imageName; }
    public String getCaption() { return caption; }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
