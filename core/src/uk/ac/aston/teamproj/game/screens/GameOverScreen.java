package uk.ac.aston.teamproj.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import uk.ac.aston.teamproj.game.MainGame;
import uk.ac.aston.teamproj.game.scenes.Hud;
import uk.ac.aston.teamproj.game.tools.SoundManager;

/**
 * 
 * Created by Parmo on 8/11/2020
 *
 */

public class GameOverScreen implements Screen {

	private Viewport viewport;
	private Stage stage;
	
	@SuppressWarnings("unused")
	private MainGame game;
	
	//font
	private BitmapFont font;
	
	public GameOverScreen(MainGame game) {
		
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("gameover.mp3"));
    	SoundManager.playSound(sound);
    	
		this.game = game;
		viewport = new FitViewport(MainGame.V_WIDTH/6, MainGame.V_HEIGHT/6, new OrthographicCamera());
		stage = new Stage(viewport, ((MainGame) game).batch);
		
		//font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/RetroGaming.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 20;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
		//e.g. abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?: 
		// These characters should not repeat! 

		font = generator.generateFont(parameter);
		font.setColor(Color.WHITE);
		generator.dispose();
		
		Label.LabelStyle labelFont = new Label.LabelStyle(font, Color.WHITE);
		
		Table table = new Table();
		table.center();
		table.setFillParent(true);
		
		
		Label playAgainLabel = new Label ("Click Screen to Play Again", labelFont);
		Label showScore = new Label (showCoins(), labelFont);
		
		table.row();
		table.add(playAgainLabel).expandX().padTop(80f);
		table.row();
		table.add(showScore).expandX();
		stage.addActor(table);
		
		
		Texture background = new Texture("buttons/untitled.png");
		table.background(new TextureRegionDrawable(new TextureRegion(background)));
		PlayScreen.resetSession();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public String showCoins() {
		return "Coins Collected: " + PlayScreen.player.getCoins();
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.justTouched()) {
			game.setScreen(new MultiplayerMenuScreen(( MainGame )game));
			dispose();
		}
		
		Gdx.gl.glClearColor(0,  0,  0 , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
			
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
