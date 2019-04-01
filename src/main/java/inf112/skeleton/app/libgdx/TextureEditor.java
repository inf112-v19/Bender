package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureEditor {

    public Texture rotate90(Texture texture) {
        TextureData textureData = texture.getTextureData();
        if (!textureData.isPrepared())
            textureData.prepare();
        Pixmap pixmap = textureData.consumePixmap();
        final int height = pixmap.getHeight();
        final int width = pixmap.getWidth();
        Pixmap rotated = new Pixmap(height, width, pixmap.getFormat());

        for (int x = 0; x < height; x++)
            for (int y = 0; y < width; y++)
                rotated.drawPixel(x, y, pixmap.getPixel(y, x));

        pixmap.dispose();
        return new Texture(rotated);
    }

    public Texture rotate180(Texture texture) {
        TextureData textureData = texture.getTextureData();
        if (!textureData.isPrepared())
            textureData.prepare();
        Pixmap pixmap = textureData.consumePixmap();
        final int height = pixmap.getHeight();
        final int width = pixmap.getWidth();
        Pixmap rotated = new Pixmap(width, height, pixmap.getFormat());

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                rotated.drawPixel(x, y, pixmap.getPixel(width - x, height - y));

        pixmap.dispose();
        return new Texture(rotated);
    }

    public Texture changeColour(Texture texture, float r, float g, float b, float a) {
        TextureData textureData = texture.getTextureData();
        Pixmap pixmap = textureData.consumePixmap();
        pixmap.setColor(r, g, b, a);
        return new Texture(pixmap);
    }

    public Texture rotate270(Texture texture) {
        return this.rotate90(rotate180(texture));
    }


    public Texture mergeTextures(Texture frontTexture, Texture backTexture, int xPos, int yPos, int newFrontTextureHeight, int newFrontTextureWidth) {
        frontTexture = this.resizeTexture(newFrontTextureWidth, newFrontTextureHeight, frontTexture);
        TextureData frontTextureData = frontTexture.getTextureData();
        TextureData backTextureData = backTexture.getTextureData();
        Pixmap frontPixmap = frontTextureData.consumePixmap();

        backTextureData.prepare();
        Pixmap backPixmap = backTextureData.consumePixmap();

        final int textureHeight = backPixmap.getHeight();
        final int textureWidth = backPixmap.getWidth();

        for (int x = 0; x < textureWidth; x++)
            for (int y = 0; y < textureHeight; y++) {
                int frontVal = frontPixmap.getPixel(x, y);
                backPixmap.drawPixel((x + xPos), (y), frontVal);
            }
        return new Texture(backPixmap);

    }

    public Texture resizeTexture(int newWidth, int newHeight, Texture texture) {
        TextureData textureData = texture.getTextureData();
        if (!textureData.isPrepared())
            textureData.prepare();
        Pixmap pixmapOld = textureData.consumePixmap();
        Pixmap pixmapNew = new Pixmap(newWidth, newHeight, pixmapOld.getFormat());
        pixmapNew.drawPixmap(pixmapOld,
                0, 0, pixmapOld.getWidth(), pixmapOld.getHeight(),
                0, 0, pixmapNew.getWidth(), pixmapNew.getHeight()
        );
        return new Texture(pixmapNew);
    }
}
