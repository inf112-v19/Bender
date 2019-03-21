package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

public class TextureEditor {

    public Texture rotate90(String path) {
        Pixmap pixmap = new Pixmap(Gdx.files.internal(path));
        final int height = pixmap.getHeight();
        final int width = pixmap.getWidth();
        Pixmap rotated = new Pixmap(height, width, pixmap.getFormat());

        for (int x = 0; x < height; x++)
            for (int y = 0; y < width; y++)
                rotated.drawPixel(x, y, pixmap.getPixel(y, x));

        pixmap.dispose();
        return new Texture(rotated);
    }

    public Texture changeColour(String path, float r, float g, float b, float a) {
        Pixmap pixmap = new Pixmap(Gdx.files.internal(path));
        pixmap.setColor(r, g, b, a);
        return new Texture(pixmap);
    }

    //Iterates through each pixel in a given texture, darkening it by given percentage
    public Texture darkenTexture(String path, float percentage) {
        Pixmap pixmap = new Pixmap(Gdx.files.internal(path));
        Color color = new Color();
        final int height = pixmap.getHeight();
        final int width = pixmap.getWidth();
        Pixmap newPixamp = new Pixmap(height, width, Pixmap.Format.RGBA8888);
        float truePercentage = 1 - percentage;
        Color newColor = new Color();
//        int R = 0, G = 0, B = 0, A = 0;
//        int newR = 0, newG = 0, newB = 0;
        newPixamp.setBlending(Pixmap.Blending.None);
        for (int x = 0; x < height; x++)
            for (int y = 0; y < width; y++) {
                int val = pixmap.getPixel(x, y);
                Color.rgba8888ToColor(color, val);

                int R = Math.round(color.r * 255f);
                int newR = Math.round(truePercentage * R);
                int G = Math.round(color.g * 255f);
                int newG = Math.round(truePercentage * G);
                int B = Math.round(color.b * 255f);
                int newB = Math.round(truePercentage * B);
                int A = Math.round(color.a * 255f);

//                newPixamp.setColor(newR, newG, newB, A-50);
                newColor.set(newR, newG, newB, A);
                //ARGB -- > RGBA
                newPixamp.drawPixel(x,y, newColor.toIntBits());

            }
//        System.out.println("R: " + R + " newR: " + newR);
//        System.out.println("G: " + G + " newG: " + newG);
//        System.out.println("B: " + B + " newB: " + newB);

        return new Texture(newPixamp);

    }

    //Currently assumes that the textures have same dimensions
    public Texture mergeTextures(Texture frontTexture, Texture backTexture) {
        TextureData frontTextureData = frontTexture.getTextureData();
        TextureData backTextureData = backTexture.getTextureData();

        Pixmap frontPixmap = frontTextureData.consumePixmap();
        Pixmap backPixmap = backTextureData.consumePixmap();

        final int textureHeight = frontPixmap.getHeight();
        final int textureWidth = frontPixmap.getWidth();

        for (int x = 0; x < textureWidth; x++)
            for (int y = 0; y < textureHeight; y++) {
                int frontVal = frontPixmap.getPixel(x, y);
                backPixmap.drawPixel(x, y, frontVal);
            }
        return new Texture(backPixmap);

    }

    public Texture changeToColourRed(String path) {
        return changeColour(path, 90f / 255f, 14f / 255f, 14f / 255f, 255f / 255f);
    }

    public Texture colourBlue(String path) {
        return changeColour(path, 25f / 255f, 25f / 255f, 112f / 255f, 255f / 255f);
    }

    public Texture resizeTexture(int newWidth, int newHeight, String path) {
        Pixmap pixmapOld = new Pixmap(Gdx.files.internal(path));
        Pixmap pixmapNew = new Pixmap(newWidth, newHeight, pixmapOld.getFormat());
        pixmapNew.drawPixmap(pixmapOld,
                0, 0, pixmapOld.getWidth(), pixmapOld.getHeight(),
                0, 0, pixmapNew.getWidth(), pixmapNew.getHeight()
        );
        return new Texture(pixmapNew);
    }
}
