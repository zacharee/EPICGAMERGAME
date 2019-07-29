import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    LinkedList<Attack> attacks = new LinkedList<>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
        for (int i = 0; i < attacks.size(); i++) {
            Attack tempAttack = attacks.get(i);

            tempAttack.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
        for (int i = 0; i < attacks.size(); i++) {
            Attack tempAttack = attacks.get(i);

            tempAttack.render(g);
        }
    }

    public void addAttack(Attack attack) {
        this.attacks.add(attack);
    }

    public void removeAttack(Attack attack) {
        this.attacks.remove(attack);
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}
