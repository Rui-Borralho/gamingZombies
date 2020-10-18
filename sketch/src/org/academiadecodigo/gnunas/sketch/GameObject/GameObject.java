package org.academiadecodigo.gnunas.sketch.GameObject;

import org.academiadecodigo.gnunas.sketch.Position;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class GameObject implements Collidable {

    private Rectangle picture;
    private Position pos;

    public GameObject(Position pos) {

        this.picture = new Rectangle(pos.getX(), pos.getY(), 10, 10);
        this.pos = pos;
        picture.fill();
    }

    public Rectangle getPicture() {
        return picture;
    }

    public Position getPos() {
        return pos;
    }
<<<<<<< HEAD
<<<<<<< HEAD
}

    /*@Override
=======
>>>>>>> master
    public boolean isColliding(GameObject object) {

        return (this.pos.equals(object.pos));

    }
<<<<<<< HEAD
}*/
=======
=======
>>>>>>> master

    public void collided() {

    }

    public void deletePicture() {
        picture.delete();
    }


    @Override
    public void collided(GameObject object) {

    }

    @Override
    public boolean equals(Object object) {

        if (object instanceof GameObject) {

            GameObject object1 = (GameObject) object;

            int rightLimitX = getPos().getX() + picture.getWidth();
            int leftLimitX = getPos().getX();
            int objRightLimitX = object1.getPos().getX();
            int objLeftLimitX = object1.getPos().getX() + object1.picture.getWidth();

            if (rightLimitX >= objLeftLimitX || leftLimitX <= objRightLimitX) {

                if (getPos().getY() <= object1.getPos().getY() + object1.picture.getHeight() || getPos().getY() + picture.getHeight() >= object1.getPos().getY()) {

                    return true;
                }

            }
        }
        return false;
    }
}
<<<<<<< HEAD
>>>>>>> 860c2ea86bf1126d45525bca8d35eb3d39850409
=======
>>>>>>> master
