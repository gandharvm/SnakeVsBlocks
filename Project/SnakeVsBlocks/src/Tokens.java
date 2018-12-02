import javafx.scene.image.ImageView;

/**
 * This is an abstract class and cannot be instantiated. It contains a method to get the Image View of the token.
 */
public abstract class Tokens {
    /**
     * This stores the image view of the image of token
     */
    protected ImageView imageView;

    /**
     * To get the Image View of the token.
     * @return Image View of the token
     */
    public ImageView getView() {
        return imageView;
    }

    /**
     * Default constructor to allocate space only.
     */
    public Tokens() {
    }
}
