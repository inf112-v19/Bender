package inf112.skeleton.app.libgdx.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CustomImageButton {
    private ImageButton button;
    private Texture texture;
    private Drawable image;
    private int height, width;

    public CustomImageButton(String textureLocation, String texturePressedLocation, int xPos, int yPos, int width, int height) {
        this.height = height;
        this.width = width;
        texture = new Texture(Gdx.files.internal(textureLocation));
        Texture texturePressed = new Texture(Gdx.files.internal(texturePressedLocation));
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
