package inf112.skeleton.app.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.cards.MoveCard;
import inf112.skeleton.app.core.cards.RotateCard;

public class CardTextureGenerator {
    private TextureEditor textureEditor;
    private Texture straightArrow;
    private Texture rotateLeft;
    private Texture basicCard;

    public CardTextureGenerator() {
        textureEditor = new TextureEditor();
        straightArrow = new Texture(Gdx.files.internal("cards/arrow_forward.png"));
        rotateLeft = new Texture(Gdx.files.internal("cards/arrow_rotate_90_left.png"));
        basicCard = new Texture(Gdx.files.internal("cards/card.png"));
    }

    public Texture generateTexture(IProgramCard card) {
        if (card instanceof MoveCard)
            return generateMoveCard((MoveCard) card);
        return generateRotateCard((RotateCard) card);

    }

    private Texture generateRotateCard(RotateCard card) {
        switch (card.getCurrentDirection()) {
            case RIGHT:
                return makeRightTurn();
            case LEFT:
                return makeLeftTurn();
            case UTURN:
                return makeUTurn();
        }
        return null;
    }

    private Texture makeUTurn() {
        Texture rotated = textureEditor.rotate90(rotateLeft);
        return textureEditor.mergeTextures(rotated, basicCard, 20, 100, 140, 140);
    }

    private Texture makeLeftTurn() {
        return textureEditor.mergeTextures(rotateLeft, basicCard, 20, 100, 140, 140);
    }

    private Texture makeRightTurn() {
        Texture flipped = textureEditor.flip(rotateLeft, false, true);
        return textureEditor.mergeTextures(flipped, basicCard, 20, 100, 140, 140);
    }

    private Texture generateMoveCard(MoveCard card) {
        if (!card.movesBackwards()) {
            return generateForwardCard();
        }
        Texture flipped = textureEditor.rotate90(textureEditor.rotate90(straightArrow));
        return textureEditor.mergeTextures(flipped, basicCard, 20, 100, 140, 140);

    }

    private Texture generateForwardCard() {
        return textureEditor.mergeTextures(straightArrow, basicCard, 20, 100, 140, 140);
    }
}
