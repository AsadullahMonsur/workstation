package com.jme.example.accessories;

import com.jme.example.loader.Loader;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class PlayerAppState extends AbstractAppState implements ActionListener {
    private Spatial sceneModel;
    private PlayerData playerData;
    private BulletAppState bulletAppState;
    private InputManager inputManager;
    private Node rootNode;
    private AssetManager assetManager;

    private Spatial player;
    private BetterCharacterControl playerControl;

    private Vector3f walkDirection = new Vector3f(0,0,0);
    private boolean w,a,s,d ;

    private Camera cam;
    private RigidBodyControl landscape;

    public PlayerAppState(BulletAppState bulletAppState, Node rootNode,PlayerData playerData) {
        this.bulletAppState = bulletAppState;
        this.rootNode = rootNode;
        this.playerData = playerData;
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.inputManager = app.getInputManager();
        this.assetManager = app.getAssetManager();
        this.cam = app.getCamera();

        assetManager.registerLocator("town.zip", ZipLocator.class);
        sceneModel = assetManager.loadModel("main.scene");
        sceneModel.setLocalScale(2f);

        // We set up collision detection for the scene by creating a
        // compound collision shape and a static RigidBodyControl with mass zero.
        CollisionShape sceneShape =
                CollisionShapeFactory.createMeshShape(sceneModel);
        landscape = new RigidBodyControl(sceneShape, 0);
        sceneModel.addControl(landscape);
        rootNode.attachChild(sceneModel);
        bulletAppState.getPhysicsSpace().add(landscape);

        initPlayer();
        initInput();
    }


    private void initInput(){
        inputManager.addMapping("Up",new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Down",new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Left",new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("Right",new KeyTrigger(KeyInput.KEY_RIGHT));

        inputManager.addListener(this,
                            "Up","Down","Left","Right");

    }

    @Override
    public void update(float tpf) {
        walkDirection.set(Vector3f.ZERO);
        if(w){
            walkDirection.addLocal(cam.getDirection());
        }
        if(s){
            walkDirection.addLocal(cam.getDirection().negate());
        }
        if(a){
            walkDirection.addLocal(cam.getLeft());
        }
        if(d){
            walkDirection.addLocal(cam.getLeft().negate());
        }

        walkDirection.setY(0);
        playerControl.setWalkDirection(walkDirection.multLocal(20));
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("Up") ) {
            w = isPressed;
        }
        if (name.equals("Down") ) {
            s = isPressed;
        }
        if (name.equals("Left") ) {
            a = isPressed;
        }
        if (name.equals("Right") ) {
            d = isPressed;
        }

    }


    private void initPlayer() {
       Loader loader = new Loader(assetManager,"Models/M-FF_iOS_HERO_Tony_Stark_Iron_Man_Civil_War.obj");
       player = loader.getSpatial();
       player.setLocalTranslation(0,0,0);
       playerControl = new BetterCharacterControl(2,4,80);
       player.addControl(playerControl);
       playerControl.setGravity(new Vector3f(0,30,0));
       playerControl.setJumpForce(new Vector3f(0,20,0));
       playerControl.warp(new Vector3f(0,2,0));
       bulletAppState.getPhysicsSpace().addAll(player);
       rootNode.attachChild(player);

    }
}
