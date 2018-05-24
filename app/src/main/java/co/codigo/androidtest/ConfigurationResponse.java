package co.codigo.androidtest;

import java.util.List;

class ConfigurationResponse {
    private ImageConfiguration images;
    private List<String> change_keys;

    public ImageConfiguration getImages() {
        return images;
    }

    public void setImages(ImageConfiguration images) {
        this.images = images;
    }

    public List<String> getChange_keys() {
        return change_keys;
    }

    public void setChange_keys(List<String> change_keys) {
        this.change_keys = change_keys;
    }
}
