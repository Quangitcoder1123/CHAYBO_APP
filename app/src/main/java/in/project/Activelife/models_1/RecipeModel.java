package in.project.Activelife.models_1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.project.Activelife.models_1.image_model.RootimageModel;

public class RecipeModel {
    private String label;
    private String url;
    private String image;
    private String source;
    private float yield;
    private float calories;
    private float totalWeight;
    @SerializedName("images")
    @Expose()
    private RootimageModel rootimageModel;

    public RecipeModel() {
        new RecipeModel(label,url,image,source,yield,calories,totalWeight,rootimageModel);
    }

    public RecipeModel(String label, String image, String source, String url, float yield, float calories, float totalWeight, RootimageModel rootimageModel) {
        this.label = label;
        this.image = image;
        this.url = url;
        this.source = source;
        this.yield = yield;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.rootimageModel = rootimageModel;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public float getYield() {
        return yield;
    }

    public void setYield(float yield) {
        this.yield = yield;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public RootimageModel getRootimageModel() {
        return rootimageModel;
    }

    public void setRootimageModel(RootimageModel rootimageModel) {
        this.rootimageModel = rootimageModel;
    }
}
