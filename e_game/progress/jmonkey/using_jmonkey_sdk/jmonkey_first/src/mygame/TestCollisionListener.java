/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author Asadullah Monsur
 */
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;

/**
 *
 * @author normenhansen
 */
public class TestCollisionListener extends SimpleApplication implements PhysicsCollisionListener {

    private BulletAppState bulletAppState;
    private Sphere bullet;
    private SphereCollisionShape bulletCollisionShape;

    public static void main(String[] args) {
        TestCollisionListener app = new TestCollisionListener();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        bulletAppState.setDebugEnabled(true);
        bullet = new Sphere(32, 32, 0.4f, true, false);
        bullet.setTextureMode(TextureMode.Projected);
        bulletCollisionShape = new SphereCollisionShape(0.4f);
//
//        PhysicsTestHelper.createPhysicsTestWorld(rootNode, assetManager, bulletAppState.getPhysicsSpace());
//        PhysicsTestHelper.createBallShooter(this, rootNode, bulletAppState.getPhysicsSpace());

        // add ourselves as collision listener
        getPhysicsSpace().addCollisionListener(this);
    }

    private PhysicsSpace getPhysicsSpace(){
        return bulletAppState.getPhysicsSpace();
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    @Override
    public void collision(PhysicsCollisionEvent event) {
        if ("Box".equals(event.getNodeA().getName()) || "Box".equals(event.getNodeB().getName())) {
            if ("bullet".equals(event.getNodeA().getName()) || "bullet".equals(event.getNodeB().getName())) {
                fpsText.setText("You hit the box!");
            }
        }
    }

}
