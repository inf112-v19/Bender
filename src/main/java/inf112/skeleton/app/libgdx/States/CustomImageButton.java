package inf112.skeleton.app.libgdx.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CustomImageButton {
    private ImageButton button;
    private Texture texture;
    private Texture texturePressed;
    private Drawable image;
    private String textureName;
    private String texturePressedName;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private String textureLocation;
    private String texturePressedLocation;

    public CustomImageButton(String textureLocation, String texturePressedLocation, int xPos, int yPos, int width, int height) {
        this.textureName = textureLocation;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.textureLocation = textureLocation;
        this.texturePressedLocation = texturePressedLocation;

        texture = new Texture(Gdx.files.internal(this.textureLocation));
        texturePressed = new Texture(Gdx.files.internal(this.texturePressedLocation));
        button = new ImageButton(image);
        button.setSize(width, height);
        button.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(texture));
        button.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(texturePressed));
        button.setPosition(xPos, yPos);


    }

    public ImageButton getButton() {
        return button;
    }

    public Texture getTexture() {
        return texture;
    }
}
