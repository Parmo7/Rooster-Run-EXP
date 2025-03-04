package uk.ac.aston.teamproj.singleplayer;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import uk.ac.aston.teamproj.game.MainGame;
import uk.ac.aston.teamproj.singleplayer.SinglePlayerScreen;
import uk.ac.aston.teamproj.game.sprites.Bomb;
import uk.ac.aston.teamproj.game.sprites.Brick;
import uk.ac.aston.teamproj.game.sprites.Coin;
import uk.ac.aston.teamproj.game.sprites.EndPlane;
import uk.ac.aston.teamproj.game.sprites.InteractiveTileObject;
import uk.ac.aston.teamproj.game.sprites.Lightning;
import uk.ac.aston.teamproj.game.sprites.Mud;


public class SingleWorldContactListener implements ContactListener {

	private SinglePlayerScreen SinglePlayerScreen;
	
	public SingleWorldContactListener(SinglePlayerScreen SinglePlayerScreen) {
		this.SinglePlayerScreen = SinglePlayerScreen;
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();

		int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
		
		// check if rooster is colliding with the ground
		if (fixA.getUserData() == "legs" || fixB.getUserData() == "legs") {
			Fixture beak = (fixA.getUserData() == "legs")? fixA : fixB;
			Fixture object = (beak == fixA)? fixB : fixA; //the other object
			
			//check if other object is an interactive one
			if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
				SinglePlayerScreen.resetJumpCount1();
			}
		}
		
		switch (cDef) {
			case (MainGame.ROOSTER_BIT2 | MainGame.BOMB_BIT):			
				Fixture bombFixture = (fixA.getFilterData().categoryBits == MainGame.BOMB_BIT) ? fixA : fixB;
				Bomb bomb = ((Bomb) bombFixture.getUserData());
				
				bomb.onHit();
				SinglePlayerScreen.makeBombExplode(bomb);
				SinglePlayerScreen.player.decreaseLives();
				break;

			case (MainGame.ROOSTER_BIT2 | MainGame.BRICK_BIT):
				Fixture brickFixture = (fixA.getFilterData().categoryBits == MainGame.BRICK_BIT) ? fixA : fixB;
				((Brick) brickFixture.getUserData()).onHit();
				break;

			case (MainGame.ROOSTER_BIT2 | MainGame.COIN_BIT):
				
				SinglePlayerScreen.player.updateCoins(1);

				Fixture coinFixture = (fixA.getFilterData().categoryBits == MainGame.COIN_BIT) ? fixA : fixB;
				((Coin) coinFixture.getUserData()).onHit();
				
				break;

			case (MainGame.ROOSTER_BIT2 | MainGame.LIGHTNING_BIT):			
				Fixture lightningFixture = (fixA.getFilterData().categoryBits == MainGame.LIGHTNING_BIT) ? fixA : fixB;
				((Lightning) lightningFixture.getUserData()).onHit();
				break;
				
			case (MainGame.ROOSTER_BIT2 | MainGame.PLANE_BIT):
				SinglePlayerScreen.player.onFinish();
				
				Fixture PlaneFixture = (fixA.getFilterData().categoryBits == MainGame.PLANE_BIT)? fixA : fixB;
				((EndPlane) PlaneFixture.getUserData()).onHit();
				break;
			
			case (MainGame.ROOSTER_BIT2 | MainGame.MUD_BIT):
				Fixture mudFixture = (fixA.getFilterData().categoryBits == MainGame.MUD_BIT) ? fixA : fixB;
				((Mud) mudFixture.getUserData()).onHit();
				break;

			default:
				break;
		}
			
	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
