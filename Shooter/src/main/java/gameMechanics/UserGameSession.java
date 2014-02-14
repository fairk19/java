package gameMechanics;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandr
 * Date: 06.12.13
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
public class UserGameSession {

    private static int maxRange;
    private static int speedSprite;
    private static int lifeSprite;
    private Integer userId;
    private int x;
    private int y;
    private Boolean isDead = false;


    public UserGameSession(){}
    public UserGameSession (Integer userId){
        this.userId = userId;
        Random random = new Random();
        Integer posX = random.nextInt(GameMap.getWidth() - 1);
        Integer posY = random.nextInt(GameMap.getHeight() - 1);
        this.x = posX;
        this.y = posY;
    }

    public void UserDead(){
        isDead = true;
        this.lifeSprite = 0;
    }

    public int getLifeSprite(){
        return lifeSprite;
    }
    public int getMaxRange(){
        return maxRange;
    }
    public int getSpeedSprite(){
        return speedSprite;
    }
    public int getX() { return x;}
    public int getY() { return y;}

    public void setSpeedSprite(int speedSprite) { this.speedSprite = speedSprite; }
    public void setLifeSprite(int lifeSprite, boolean hit) {
        if (hit) {
            this.lifeSprite -= lifeSprite;
        } else {
            this.lifeSprite +=lifeSprite;
        }
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
}
