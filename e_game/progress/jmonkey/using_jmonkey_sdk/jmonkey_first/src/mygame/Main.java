package mygame;

import character.ConcealedCharacter;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingVolume;
import com.jme3.bullet.BulletAppState;
import static com.jme3.bullet.PhysicsSpace.getPhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.objects.PhysicsCharacter;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.control.CameraControl.ControlDirection;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.util.SkyFactory;
import customs.CubeCharacter;
import environment.Surface;
import loader.Loader;



public class Main extends SimpleApplication implements PhysicsCollisionListener{
    private final String object_path = "Models/car_03.fbx";
    private final String material_path = "Models/car_03.mtl";
    
//    private final String object_path = "Models/M-FF_iOS_HERO_Tony_Stark_Iron_Man_Civil_War.obj";
//    private final String material_path = "Models/M-FF_iOS_HERO_Tony_Stark_Iron_Man_Civil_War.mtl";
//    
    private Geometry mark;

    protected Node pointer;
    protected Node player;
    protected Node player2;

    protected CameraNode camNode;
    private boolean isRunning = true;

    private float fart_angle = 0.0f;
    private float critical = 0.0f;
    private float past_yaw_angle = 0.0f;
    private float present_yaw_angle = 0.0f;
    private float past_pitch_angle = 0.0f;
    private float present_pitch_angle = 0.0f;
    private float distance = 5.0f;
    
    private float past_x = 0.0f;
    private float past_y = 0.0f;
    
    private Quaternion yaw_q;
    private Quaternion pitch_q;
    private final float gravity = 3.0f;
    private final float jump_level = 0.5f;
    private float jump_speed = gravity * 1.20f;

    private boolean jump_status = false;
    private boolean fall_status = false;
    
    private BulletAppState bulletAppState;

    Node obstacles;
    Node floor;
    //CubeCharacter character;
    //ConcealedCharacter character;
    
    GhostControl ghost;

    private boolean mango = true;    
    private RigidBodyControl obstacles_cover;

    private int ufo = 0;
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
      //HelloCollision c = new HelloCollision();
      //c.main(args);
     // HelloPicking c = new HelloPicking();
     // c.main(args);
    }

    @Override
    public void simpleInitApp() {
        initCrossHairs();
        initMark();
        getRootNode().attachChild(SkyFactory.createSky(getAssetManager(), "Textures/BrightSky.dds", SkyFactory.EnvMapType.CubeMap));
        viewPort.setBackgroundColor(ColorRGBA.White);
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
    
//        DirectionalLight sun = new DirectionalLight();
//        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f)
//                               .normalizeLocal());
//        rootNode.addLight(sun);
        
               AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);
        
        Surface surface = new Surface(18,assetManager);
        floor  = surface.form_floor(0);

        obstacles = surface.form_obstacle();
        
        CollisionShape sceneShape =
            CollisionShapeFactory.createMeshShape(obstacles);
       
        obstacles_cover = new RigidBodyControl(sceneShape, 10.4f);
        obstacles_cover.setKinematic(true);
        obstacles.addControl(obstacles_cover);

        Loader loader = new Loader(assetManager,object_path,material_path);
        player = loader.getNode();
        player.scale(0.05f);

//        character = new ConcealedCharacter(0.5f);
//        character.setRay_visibity(1);
//        player.attachChild(character);
        
         CapsuleCollisionShape shape = new CapsuleCollisionShape(0.2f,0.65f,1);
         ghost = new GhostControl(shape); 
         
         player.addControl(ghost);


   
      //  bulletAppState.setDebugEnabled(true);
        bulletAppState.getPhysicsSpace().add(ghost);
        bulletAppState.getPhysicsSpace().addCollisionListener(this);
        bulletAppState.getPhysicsSpace().add(obstacles_cover);

        flyCam.setEnabled(false);
        cam.setParallelProjection(false);
        
        camNode = new CameraNode("Camera_Node", cam);
        camNode.setControlDir(ControlDirection.SpatialToCamera);

        pointer =  new Node("pointer");
        pointer.attachChild(camNode);
        player.attachChild(pointer);
        
        rootNode.attachChild(floor);
        rootNode.attachChild(obstacles);
        rootNode.attachChild(player);
        
//        pointer.setLocalTranslation(0.0f, 1.0f, -1.0f);
//        player.rotate(0.0f,FastMath.PI/2, 0.0f);
        
        float camNode_y = distance*FastMath.sin(FastMath.PI/6);
        float camNode_z = distance*FastMath.cos(FastMath.PI/6);
        
        Vector3f v = player.getLocalTranslation();
        pointer.setLocalTranslation(new Vector3f(0.0f, -1.0f, -camNode_z-2.0f));
        //cam.lookAt(new Vector3f(new Vector3f(v.x,v.y+1.0f,v.z)), Vector3f.UNIT_Y);


//        ChaseCamera chaseCam = new ChaseCamera(cam, player, inputManager);
//        chaseCam.setSmoothMotion(true);
//        chaseCam.setRotationSensitivity(50f);
//        chaseCam.setDefaultDistance(5);


        initKeys();

    }

    @Override
    public void simpleUpdate(float tpf) {
        jump_manager(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {

    }
    
    
     private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("Pause",  new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("forward",   new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("backward",  new KeyTrigger(KeyInput.KEY_DOWN));

        inputManager.addMapping("Left",   new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("Right",  new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("jump", new KeyTrigger(KeyInput.KEY_SPACE));
        
        inputManager.addMapping("turn_left",  new MouseAxisTrigger(MouseInput.AXIS_X,false));
        inputManager.addMapping("turn_right",  new MouseAxisTrigger(MouseInput.AXIS_X,true));
        inputManager.addMapping("turn_up",  new MouseAxisTrigger(MouseInput.AXIS_Y,false));
        inputManager.addMapping("turn_down",  new MouseAxisTrigger(MouseInput.AXIS_Y,true));
        inputManager.addMapping("zoom_in",  new MouseAxisTrigger(MouseInput.AXIS_WHEEL,true));
        inputManager.addMapping("zoom_out",  new MouseAxisTrigger(MouseInput.AXIS_WHEEL,false));
        inputManager.addMapping("Shoot",new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
              
        // Add the names to the action listener.
        inputManager.addListener(actionListener, "Pause");
        inputManager.addListener(analogListener,"Shoot","forward","backward", "Left", "Right", "jump","turn_left","turn_right","turn_up","turn_down","zoom_in","zoom_out");

    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Pause") && !keyPressed) {
                isRunning = !isRunning;
            }
        }
    };

    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {
           
            if (isRunning) {
                
                if (name.equals("Shoot")) {
                 // farted_face();
                }
                if (name.equals("jump")) {
                    if(jump_status==false){
                      jump_status = true;
                    }
                }
                
                if (name.equals("forward")) { 
                    Vector3f v = player.getLocalTranslation();
                    float radius = 2*value*speed;
                    float x = radius*FastMath.sin(critical);
                    float z = radius*FastMath.cos(critical);

                 // if(!check_obstacles()){
                 // 1 for forward, 2 backward, 3 right, 4 left
                 // 5 up, 6 down
                // if(!character.collision_result(assetManager,rootNode, obstacles, mark,1)){
                 
                if(mango){
                    player.setLocalTranslation((v.x-x), v.y,(v.z-z));
                    
                 }
                
                  //  System.out.println("UFo Client: "+(++ufo));
                }
                if (name.equals("backward")) {
                    Vector3f v = player.getLocalTranslation();
                    float radius = 2*value*speed;
                    float x = radius*FastMath.sin(critical);
                    float z = radius*FastMath.cos(critical);
                
                     // 1 for forward, 2 backward, 3 right, 4 left
                    // 5 up, 6 down
                    // if(!character.collision_result(assetManager,rootNode, obstacles, mark,2)){
                     if(mango){
                    player.setLocalTranslation((v.x+x), v.y,(v.z+z));
                    }
                }
                if (name.equals("Right")) {
                    Vector3f v = player.getLocalTranslation();
                    float radius = 2*value*speed;
                    float x = radius*FastMath.sin(critical+FastMath.PI/2);
                    float z = radius*FastMath.cos(critical+FastMath.PI/2);
                    
                    // 1 for forward, 2 backward, 3 right, 4 left
                    // 5 up, 6 down
                   // if(!character.collision_result(assetManager,rootNode, obstacles, mark,3)){
                    if(mango){
                   player.setLocalTranslation((v.x+x), v.y,(v.z+z));
                    }
                }
                if (name.equals("Left")) {
                    Vector3f v = player.getLocalTranslation();
                    float radius = 2*value*speed;
                    float x = radius*FastMath.sin(critical+FastMath.PI/2);
                    float z = radius*FastMath.cos(critical+FastMath.PI/2);
                    
                     // 1 for forward, 2 backward, 3 right, 4 left
                    // 5 up, 6 down
                    // if(!character.collision_result(assetManager,rootNode, obstacles, mark,4)){
                    if(mango){
                    player.setLocalTranslation((v.x-x), v.y,(v.z-z));
                    }
                }
                if (name.equals("turn_right")) {
                    present_yaw_angle = present_yaw_angle + FastMath.PI/180.0f; 
                    
                    critical = critical+(present_yaw_angle-past_yaw_angle)*0.9f;
                    player.rotate(0.0f,(present_yaw_angle-past_yaw_angle)*0.9f,0.0f);
        
        float camNode_y = distance*FastMath.sin(FastMath.PI/6);
        float camNode_z = distance*FastMath.cos(FastMath.PI/6);

        pointer.setLocalTranslation(new Vector3f(0.0f, camNode_y, -camNode_z));
        
                }
                if (name.equals("turn_left")) {
                    present_yaw_angle = present_yaw_angle - FastMath.PI/180.0f;
                    critical = critical+(present_yaw_angle-past_yaw_angle)*0.9f;
                   player.rotate(0.0f,(present_yaw_angle-past_yaw_angle)*0.9f,0.0f);
                                   
                    float camNode_y = distance*FastMath.sin(FastMath.PI/6);
                    float camNode_z = distance*FastMath.cos(FastMath.PI/6);

                    pointer.setLocalTranslation(new Vector3f(0.0f, camNode_y, -camNode_z));

                }
                
                if (name.equals("turn_down")) {
                    present_pitch_angle = present_pitch_angle + FastMath.PI/180;
                    
                    if(present_pitch_angle>((FastMath.PI*60.0f/180))){
                        present_pitch_angle = (FastMath.PI*60.0f/180);
                    }
                    camNode.rotate((present_pitch_angle-past_pitch_angle)*0.9f,0.0f,0.0f);
                
                
                }
                
                if (name.equals("turn_up")) {
                    present_pitch_angle = present_pitch_angle - FastMath.PI/180;
                    if(present_pitch_angle<(-(FastMath.PI/180)*89.0f)){
                        present_pitch_angle = -(FastMath.PI/180)*89.0f;
                    }
                    
//                    Quaternion q1 = new Quaternion();
//                    q1.fromAngleAxis(present_pitch_angle, new Vector3f(1,0,0));
//                    camNode.setLocalRotation(q1);
                    camNode.rotate((present_pitch_angle-past_pitch_angle)*0.9f, 0.0f, 0.0f);
                }
                
                if (name.equals("zoom_in")) {
                   
                    if(distance>1){
                       distance = distance -1;
                    }
                    
                   float camNode_y = distance*FastMath.sin(FastMath.PI/4);
                   float camNode_z = distance*FastMath.cos(FastMath.PI/4);
                   pointer.setLocalTranslation(new Vector3f(0.0f, camNode_y, -camNode_z));

                }
                
                if (name.equals("zoom_out")) {
                  if(distance<10){
                     distance = distance +1;
                   }
                   float camNode_y = distance*FastMath.sin(FastMath.PI/4);
                   float camNode_z = distance*FastMath.cos(FastMath.PI/4);

                    pointer.setLocalTranslation(new Vector3f(0.0f, camNode_y, -camNode_z));

                }
                
                
//                Vector2f  mc = inputManager.getCursorPosition();
//                float x = mc.getX();
//                float y = mc.getY();
//                                
//                float delx = x-past_x;
//                float dely = y-past_y;
                
                Vector3f v = player.getLocalTranslation();
                float x_push = (float)(distance * Math.sin(present_yaw_angle));
                float z_push = (float)(distance * Math.cos(present_yaw_angle));                  
                float y_push = (float)(distance * Math.tan(present_pitch_angle));

            //    camNode.setLocalTranslation(0.0f,5.0f ,-distance);
            //    camNode.lookAt(new Vector3f(v.x,v.y+1.0f-y_push,v.z), Vector3f.UNIT_Y);

                past_yaw_angle = present_yaw_angle;
                past_pitch_angle = present_pitch_angle;
               
                        
            }
            else {
                System.out.println("Press P to unpause.");

            }
        }
        
    };

    private void jump_manager(float tpf) {
        Vector3f v = player.getLocalTranslation();
        
        if(v.y<-0.75f){
            jump_status = false;
            fall_status = false;
            player.setLocalTranslation(v.x, -0.75f,v.z);
        }
        
        if(jump_status==true){
            float distance = jump_speed*tpf +(1/2)* gravity*tpf*tpf;
           
           if(v.y<jump_level && fall_status==false){
              player.setLocalTranslation(v.x, v.y+distance,v.z);
           }
           else{
               fall_status = true;
           }
           if(fall_status){
               player.setLocalTranslation(v.x, v.y-distance,v.z);
           }
        }
      
    }

    private boolean check_obstacles(){
        CollisionResults results = new CollisionResults();

        Ray ray = new Ray(cam.getLocation(), cam.getDirection());
        obstacles.collideWith(ray, results);

         if (results.size() > 0) {
            CollisionResult closest = results.getClosestCollision();
            System.out.println("Hit : "+closest.getDistance());
            if(closest.getDistance()<2.1f){
              return true;
            }
            
            mark.setLocalTranslation(closest.getContactPoint());
            rootNode.attachChild(mark);

        }
         else {
          rootNode.detachChild(mark);
        }
        
        return false;
    }
    private void farted_face(){
        CollisionResults results = new CollisionResults();

        Ray ray = new Ray(cam.getLocation(), cam.getDirection());
        obstacles.collideWith(ray, results);
        
        
        System.out.println("----- Collisions? " + results.size() + "-----");
        
        for (int i = 0; i < results.size(); i++) {
          float dist = results.getCollision(i).getDistance();
          Vector3f pt = results.getCollision(i).getContactPoint();
          String hit = results.getCollision(i).getGeometry().getName();
          System.out.println("* Collision #" + i);
          System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
          
        }
   
        if (results.size() > 0) {
            CollisionResult closest = results.getClosestCollision();
            mark.setLocalTranslation(closest.getContactPoint());
          rootNode.attachChild(mark);
          rootNode.detachChild(obstacles);
        } else {
          // No hits? Then remove the red mark.
          rootNode.detachChild(mark);
          rootNode.attachChild(obstacles);
        }
      }

  protected void initMark() {
    Sphere sphere = new Sphere(30, 30, 0.2f);
    mark = new Geometry("BOOM!", sphere);
    Material mark_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mark_mat.setColor("Color", ColorRGBA.Red);
    mark.setMaterial(mark_mat);
  }

protected void initCrossHairs() {
    setDisplayStatView(false);
    guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
    BitmapText ch = new BitmapText(guiFont, false);
    ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
    ch.setText("+"); // crosshairs
    ch.setLocalTranslation( // center
      settings.getWidth() / 2 - ch.getLineWidth()/2,
      settings.getHeight() / 2 + ch.getLineHeight()/2, 0);
    guiNode.attachChild(ch);
  }

    @Override
    public void collision(PhysicsCollisionEvent event) {
        
         PhysicsCollisionObject a = event.getObjectA();
        PhysicsCollisionObject b = event.getObjectB();

        if (a == ghost && b == obstacles_cover || a == obstacles_cover && b == ghost) {
            System.out.println((++ufo)+"Player is touching the danger zone!");
        }
    }
}
