package org.academiadecodigo.gnunas.sketch;

import org.academiadecodigo.gnunas.sketch.GameObject.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.List;

public class Game {

    private Field field;
    private CollisionDetector collisiondetector;
    private List<GameObject> gameObjects;
    private List<Zombie> zombieList;
    private Player player;
    private Level[] levels = Level.values();
    private int level = 0;
    private boolean menu;

    public void setMenu(boolean menu) {
        this.menu = menu;
    }


    public void init(){
        field = new Field();
        gameObjects = GameObjectFactory.createObjectLimits();
        zombieList = GameObjectFactory.createZombies(levels[level]);
        gameObjects.addAll(zombieList);
        collisiondetector = new CollisionDetector(gameObjects);
        gameObjects.add(new Key(GameObjectFactory.generatePositionForKeyAndZombies()));
        player = new Player(new Position(40, (field.getHeight()/2)+Field.DEFAULT_PADDING));
    }
    public void start() {

        init();

        while (player.isAlive()) {

            while (!(player.isOpenedDoor()) && player.isAlive()){

                try {

                    Thread.sleep(levels[level].getDelay());
                } catch (Exception ex) {

                    System.out.println(ex);
                }

                collisiondetector.checkCollision(player);

                moveZombies();

            }
            if(!player.isAlive()){
                new Picture(0,0,"floor_bg.png").draw();
                deleteAllGraphics();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Insert something to go back to initial menu
                break;
            }
            //Player abre a porta e da ordem para começar novo nivel
            player.stopPlayer();
            level++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deleteAllGraphics();
            //o init recebe um numero do lvl para criar um determinado lvl, de forma crescente
            if (level < levels.length){
                start();
            }
            break;
        }
        player.stopPlayer();
        new Picture(0,0,"floor_bg.png").draw();
        // depois de parar o jogador , mostra-se no screen Parabens;
    }

    public void moveZombies(){

        for (Zombie zombie : zombieList){
                zombie.move();
                collisiondetector.checkCollision(zombie);
        }
    }
    private void deleteAllGraphics() {
        player.getLanternView().delete();
        player.getPicture().delete();
        field.getMap().delete();
        for(GameObject gameObject: gameObjects){
            gameObject.getPicture().delete();
        }
        for(Zombie zombie: zombieList){
            zombie.getPicture().delete();
        }
    }
}


