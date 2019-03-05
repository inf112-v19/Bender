[1mdiff --git a/src/main/java/inf112/skeleton/app/cards/ProgramDeck.java b/src/main/java/inf112/skeleton/app/cards/ProgramDeck.java[m
[1mindex 7330280..e3f5bc8 100644[m
[1m--- a/src/main/java/inf112/skeleton/app/cards/ProgramDeck.java[m
[1m+++ b/src/main/java/inf112/skeleton/app/cards/ProgramDeck.java[m
[36m@@ -27,8 +27,7 @@[m [mpublic class ProgramDeck extends Deck<IProgramCard> {[m
     }[m
 [m
     private static int priority() {[m
[31m-//            priorityValue +=10;[m
[31m-//            return priorityValue;[m
[31m-        return 0;[m
[32m+[m[32m            priorityValue +=10;[m
[32m+[m[32m            return priorityValue;[m
     }[m
 }[m
[1mdiff --git a/src/main/java/inf112/skeleton/app/utils/States/RoundState.java b/src/main/java/inf112/skeleton/app/utils/States/RoundState.java[m
[1mindex 1c16386..ecbf1b7 100644[m
[1m--- a/src/main/java/inf112/skeleton/app/utils/States/RoundState.java[m
[1m+++ b/src/main/java/inf112/skeleton/app/utils/States/RoundState.java[m
[36m@@ -6,7 +6,6 @@[m [mimport com.badlogic.gdx.graphics.g2d.SpriteBatch;[m
 import com.badlogic.gdx.scenes.scene2d.InputEvent;[m
 import com.badlogic.gdx.scenes.scene2d.InputListener;[m
 import com.badlogic.gdx.scenes.scene2d.Stage;[m
[31m-import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;[m
 import inf112.skeleton.app.RobotDemo;[m
 [m
 import java.util.ArrayDeque;[m
[36m@@ -22,8 +21,6 @@[m [mpublic class RoundState extends State {[m
     private CustomImageButton confirm;[m
     private CustomImageButton reset;[m
 [m
[31m-[m
[31m-    //TODO: make button a genereic method/class[m
     public RoundState(GameStateManager gsm) {[m
         super(gsm);[m
         order = new ArrayDeque();[m
[36m@@ -32,7 +29,7 @@[m [mpublic class RoundState extends State {[m
 [m
         initializeTextures();[m
         makeCardButtons();[m
[31m-        makeConfirmButtons();[m
[32m+[m[32m        makeConfirmationButtons();[m
     }[m
 [m
     public void initializeTextures() {[m
[36m@@ -50,7 +47,7 @@[m [mpublic class RoundState extends State {[m
         }[m
     }[m
 [m
[31m-    public void makeConfirmButtons() {[m
[32m+[m[32m    public void makeConfirmationButtons() {[m
         confirm = new CustomImageButton("Confirm.png", "Confirm.png", RobotDemo.WIDTH - 300, RobotDemo.CARD_WIDTH / 2, 100, 50);[m
         reset = new CustomImageButton("Reset.png", "Reset.png", RobotDemo.WIDTH - 300, 30, 100, 50);[m
         confirm.getButton().addListener(new InputListener() {[m
[36m@@ -123,6 +120,10 @@[m [mpublic class RoundState extends State {[m
 [m
     @Override[m
     public void dispose() {[m
[32m+[m[32m        confirm.getTexture().dispose();[m
[32m+[m[32m        reset.getTexture().dispose();[m
[32m+[m[32m        cardBackground.dispose();[m
[32m+[m[32m        tileTexture.dispose();[m
 [m
     }[m
 }[m
