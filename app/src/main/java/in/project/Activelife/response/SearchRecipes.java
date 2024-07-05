package in.project.Activelife.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.project.Activelife.models_1.RootObjectModel;

public class SearchRecipes {
    private int from;
    private int to;
    private int count;
    @SerializedName("hits")
    @Expose()
    private RootObjectModel[] foodRecipes;

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCount() {
        return count;
    }

    public RootObjectModel[] getFoodRecipes() {
        return foodRecipes;
    }
    public String toString()
    {
        return "Recipes{" + "Recipes=\t" + foodRecipes + "from = \t" + from + "to=\t" + to + "totalCount = \t" + count + "}";
    }
}
