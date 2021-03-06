package deco2800.ragnarok.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import deco2800.ragnarok.GameScreen;
import deco2800.ragnarok.ThomasGame;
import deco2800.ragnarok.managers.GameManager;
import deco2800.ragnarok.managers.TextureManager;

public class MainMenuScreen implements Screen {
    final ThomasGame game;
    private Stage stage;
    private Skin skin;

    /**
     * The constructor of the MainMenuScreen
     * @param game the Iguana Chase Game to run
     */
    public MainMenuScreen(final ThomasGame game) {
        this.game = game;

        stage = new Stage(new ExtendViewport(1280, 720), game.batch);
        skin = GameManager.get().getSkin();

        Image background = new Image(GameManager.get().getManager(TextureManager.class).getTexture("background"));
        background.setFillParent(true);
        stage.addActor(background);

        Label logo = new Label("Ragnarok Racer", skin);
        logo.setFontScale(5.0f);
        logo.setPosition(1280/2 - 275, 720/2 + 100);
        stage.addActor(logo);

        Button newGameBtn = new TextButton("SINGLE PLAYER", skin, "main_menu");
        newGameBtn.setPosition(10, 100);
        stage.addActor(newGameBtn);

        Button connectToServerButton = new TextButton("CONNECT TO SERVER", skin, "main_menu");
        connectToServerButton.setPosition(10, 50);
        stage.addActor(connectToServerButton);

//        Button startServerButton = new TextButton("START SERVER", skin, "main_menu");
////        startServerButton.setPosition(10, 0);
////        stage.addActor(startServerButton);

        connectToServerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(new ThomasGame(), false));
            }
        });

        newGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(new ThomasGame(), true));
            }
        });
    }
    
   /**
     * Begins things that need to begin when shown
     */
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Pauses the screen
     */
    public void pause() {
        //do nothing
    }

    /**
     * Resumes the screen
     */
    public void resume() {
        //do nothing
    }

    /**
     * Hides the screen
     */
    public void hide() {
        //do nothing
    }

    /**
     * Resizes the main menu stage to a new width and height
     * @param width the new width for the menu stage
     * @param height the new width for the menu stage
     */
    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Renders the menu
     * @param delta
     */
    public void render (float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(delta);
        stage.draw();
    }

    /**
     * Disposes of the stage that the menu is on
     */
    public void dispose() {
        stage.dispose();
    }
}