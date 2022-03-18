package com.jme.example;

import com.jme.example.loader.Loader;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.HullCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import com.jme3.util.SkyFactory;

public class GameViewer  extends SimpleApplication implements ActionListener{
    private BulletAppState app_state;
    private RigidBodyControl env_control; // environment = env
    private VehicleControl player_control;

    private Spatial env_model;
    private Node player_model;
    private CameraNode camera_node;

    private float steeringValue = 0;
    private float accelerationValue = 0;


    private float critical = 0.0f;
    private float past_yaw_angle = 0.0f;
    private float present_yaw_angle = 0.0f;
    private float past_pitch_angle = 0.0f;
    private float present_pitch_angle = 0.0f;
    private float distance = 5.0f;
    protected Node pointer;

    @Override
    public void simpleInitApp() {
//        ColorRGBA color = new ColorRGBA(0.2f,0,0.5f,1);
        getRootNode().attachChild(SkyFactory.createSky(getAssetManager(), "Textures/sky/BrightSky.dds", SkyFactory.EnvMapType.CubeMap));
        viewPort.setBackgroundColor(ColorRGBA.White);
        app_state = new BulletAppState();
        app_state.setDebugEnabled(true);
        stateManager.attach(app_state);

        setupKeys();
        load_light_source();

        assetManager.registerLocator("town.zip", ZipLocator.class);
        env_model = assetManager.loadModel("main.scene");
        env_model.setLocalScale(2f);

        CollisionShape sceneShape =
                CollisionShapeFactory.createMeshShape(env_model);
        env_control = new RigidBodyControl(sceneShape, 0);
        env_model.addControl(env_control);
        rootNode.attachChild(env_model);
        app_state.getPhysicsSpace().add(env_control);

        float stiffness = 120.0f;//200=f1 car
        float compValue = 0.2f; //(lower than damp!)
        float dampValue = 0.3f;
        final float mass = 400;

        Loader loader = new Loader(assetManager,"Models/cars/car_02/car_03.obj");
        player_model = loader.getNode();

//        flyCam.setEnabled(false);
//        cam.setParallelProjection(false);
        camera_node = new CameraNode("camera_node", cam);
        camera_node.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        pointer =  new Node("pointer");
        pointer.attachChild(camera_node);
        player_model.attachChild(pointer);

       // float cam_node_z = 5*FastMath.cos(FastMath.PI/6);
       // camera_node.setLocalTranslation(new Vector3f(0.0f, -2.0f, -4));

        float camNode_y = distance*FastMath.sin(FastMath.PI/6);
        float camNode_z = distance*FastMath.cos(FastMath.PI/6);

        Vector3f v = player_model.getLocalTranslation();
        pointer.setLocalTranslation(new Vector3f(0.0f, -1.0f, -camNode_z-2));

        //cam.lookAt(player_model.getWorldTranslation(), Vector3f.UNIT_Y);

        player_model.setShadowMode(RenderQueue.ShadowMode.Cast);
        Geometry chasis = findGeom(player_model, "chassis_hi_001");
//        BoundingBox box = (BoundingBox) chasis.getModelBound();
        CapsuleCollisionShape carHull = new CapsuleCollisionShape(0.2f,0.65f,1);
//        BoxCollisionShape carHull = new BoxCollisionShape(box.getExtent(null));
//        CollisionShape carHull = CollisionShapeFactory.createDynamicMeshShape(chasis);
//        HullCollisionShape carHull= new HullCollisionShape(chasis.getMesh());
        player_control = new VehicleControl(carHull, mass);
        player_model.addControl(player_control);

        //Setting default values for wheels
//        player_control.setSuspensionCompression(compValue * 2.0f * FastMath.sqrt(stiffness));
//        player_control.setSuspensionDamping(dampValue * 2.0f * FastMath.sqrt(stiffness));
//        player_control.setSuspensionStiffness(stiffness);
//        player_control.setMaxSuspensionForce(10000);
//
//        Vector3f wheelDirection = new Vector3f(0, -1, 0);
//        Vector3f wheelAxle = new Vector3f(-1, 0, 0);
//
//        Geometry wheel_fr = findGeom(player_model, "wheel_003");
//        wheel_fr.center();
//        box = (BoundingBox) wheel_fr.getModelBound();
//        float wheelRadius = box.getYExtent();
//        float back_wheel_h = (wheelRadius * 1.7f) - 1f;
//        float front_wheel_h = (wheelRadius * 1.9f) - 1f;
//        player_control.addWheel(wheel_fr.getParent(), box.getCenter().add(0, -front_wheel_h, 0),
//                wheelDirection, wheelAxle, 0.2f, wheelRadius, true);
//
//        Geometry wheel_fl = findGeom(player_model, "wheel_001");
//        wheel_fl.center();
//        box = (BoundingBox) wheel_fl.getModelBound();
//        player_control.addWheel(wheel_fl.getParent(), box.getCenter().add(0, -front_wheel_h, 0),
//                wheelDirection, wheelAxle, 0.2f, wheelRadius, true);
//
//        Geometry wheel_br = findGeom(player_model, "wheel_004");
//        wheel_br.center();
//        box = (BoundingBox) wheel_br.getModelBound();
//        player_control.addWheel(wheel_br.getParent(), box.getCenter().add(0, -back_wheel_h, 0),
//                wheelDirection, wheelAxle, 0.2f, wheelRadius, false);
//
//        Geometry wheel_bl = findGeom(player_model, "wheel_002");
//        wheel_bl.center();
//        box = (BoundingBox) wheel_bl.getModelBound();
//        player_control.addWheel(wheel_bl.getParent(), box.getCenter().add(0, -back_wheel_h, 0),
//                wheelDirection, wheelAxle, 0.2f, wheelRadius, false);
//
//        player_control.getWheel(2).setFrictionSlip(4);
//        player_control.getWheel(3).setFrictionSlip(4);

        rootNode.attachChild(player_model);
        app_state.getPhysicsSpace().add(player_model);
        player_control.setGravity(new Vector3f(0,-30f,0));
        player_control.setPhysicsLocation(new Vector3f(0, 10, 0));
    }

    @Override
    public void simpleUpdate(float tpf) {
        //cam.lookAt(player_model.getWorldTranslation(), Vector3f.UNIT_Y);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void load_light_source() {
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);

//        DirectionalLight dl = new DirectionalLight();
//        dl.setColor(ColorRGBA.White);
//        dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
//        rootNode.addLight(dl);
    }

    private Geometry findGeom(Spatial spatial, String name) {
        if (spatial instanceof Node) {
            Node node = (Node) spatial;
            for (int i = 0; i < node.getQuantity(); i++) {
                Spatial child = node.getChild(i);
                Geometry result = findGeom(child, name);
                if (result != null) {
                    return result;
                }
            }
        } else if (spatial instanceof Geometry) {
            if (spatial.getName().startsWith(name)) {
                return (Geometry) spatial;
            }
        }
        return null;
    }

    private void setupKeys() {
        inputManager.addMapping("Lefts", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("Rights", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("Ups", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Downs", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Space", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("Reset", new KeyTrigger(KeyInput.KEY_R));
        inputManager.addMapping("turn_left",  new MouseAxisTrigger(MouseInput.AXIS_X,false));
        inputManager.addMapping("turn_right",  new MouseAxisTrigger(MouseInput.AXIS_X,true));
        inputManager.addMapping("turn_up",  new MouseAxisTrigger(MouseInput.AXIS_Y,false));
        inputManager.addMapping("turn_down",  new MouseAxisTrigger(MouseInput.AXIS_Y,true));
        inputManager.addListener(this, "Lefts");
        inputManager.addListener(this, "Rights");
        inputManager.addListener(this, "Ups");
        inputManager.addListener(this, "Downs");
        inputManager.addListener(this, "Space");
        inputManager.addListener(this, "Reset");
        inputManager.addListener(analogListener,"turn_left","turn_right","turn_up","turn_down");
    }

    @Override
    public void onAction(String binding, boolean value, float tpf) {
        if (binding.equals("Lefts")) {
            if (value) {
                steeringValue += .5f;
            } else {
                steeringValue += -.5f;
            }
            player_control.steer(steeringValue);
        } else if (binding.equals("Rights")) {
            if (value) {
                steeringValue += -.5f;
            } else {
                steeringValue += .5f;
            }
            player_control.steer(steeringValue);
        } //note that our fancy car actually goes backwards..
        else if (binding.equals("Ups")) {
            if (value) {
                accelerationValue -= 800;
            } else {
                accelerationValue += 800;
            }
            player_control.accelerate(accelerationValue);
            player_control.setCollisionShape(CollisionShapeFactory.createDynamicMeshShape(findGeom(player_model, "chassis_hi_001")));
        } else if (binding.equals("Downs")) {
            if (value) {
                player_control.brake(40f);
            } else {
                player_control.brake(0f);
            }
        } else if (binding.equals("Reset")) {
            if (value) {
                System.out.println("Reset");
                player_control.setPhysicsLocation(Vector3f.ZERO);
                player_control.setPhysicsRotation(new Matrix3f());
                player_control.setLinearVelocity(Vector3f.ZERO);
                player_control.setAngularVelocity(Vector3f.ZERO);
                player_control.resetSuspension();
            } else {
            }
        }
    }

    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {

                if (name.equals("turn_right")) {
                    present_yaw_angle = present_yaw_angle + FastMath.PI/180.0f;

                    critical = critical+(present_yaw_angle-past_yaw_angle)*0.9f;
                    player_model.rotate(0.0f,(present_yaw_angle-past_yaw_angle)*0.9f,0.0f);

                    float camNode_y = distance*FastMath.sin(FastMath.PI/6);
                    float camNode_z = distance*FastMath.cos(FastMath.PI/6);

                    pointer.setLocalTranslation(new Vector3f(0.0f, camNode_y, -camNode_z));

                }
                if (name.equals("turn_left")) {
                    present_yaw_angle = present_yaw_angle - FastMath.PI/180.0f;
                    critical = critical+(present_yaw_angle-past_yaw_angle)*0.9f;
                    player_model.rotate(0.0f,(present_yaw_angle-past_yaw_angle)*0.9f,0.0f);

                    float camNode_y = distance*FastMath.sin(FastMath.PI/6);
                    float camNode_z = distance*FastMath.cos(FastMath.PI/6);

                    pointer.setLocalTranslation(new Vector3f(0.0f, camNode_y, -camNode_z));

                }

                if (name.equals("turn_down")) {
                    present_pitch_angle = present_pitch_angle + FastMath.PI/180;

                    if(present_pitch_angle>((FastMath.PI*60.0f/180))){
                        present_pitch_angle = (FastMath.PI*60.0f/180);
                    }
                    camera_node.rotate((present_pitch_angle-past_pitch_angle)*0.9f,0.0f,0.0f);


                }

                if (name.equals("turn_up")) {
                    present_pitch_angle = present_pitch_angle - FastMath.PI/180;
                    if(present_pitch_angle<(-(FastMath.PI/180)*89.0f)){
                        present_pitch_angle = -(FastMath.PI/180)*89.0f;
                    }

                    camera_node.rotate((present_pitch_angle-past_pitch_angle)*0.9f, 0.0f, 0.0f);
                }

                Vector3f v = player_model.getLocalTranslation();
                float x_push = (float)(distance * Math.sin(present_yaw_angle));
                float z_push = (float)(distance * Math.cos(present_yaw_angle));
                float y_push = (float)(distance * Math.tan(present_pitch_angle));

                past_yaw_angle = present_yaw_angle;
                past_pitch_angle = present_pitch_angle;

            }
    };

}
