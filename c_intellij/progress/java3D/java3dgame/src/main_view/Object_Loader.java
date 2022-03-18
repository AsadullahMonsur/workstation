package main_view;

import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Object_Loader {
    double anchorX, anchorY, anchorAngle;
    public Object_Loader(String  path, Stage primary_stage){
        try{
            FileReader  fr = new FileReader(path);
      //      FileReader  fr = new FileReader(getClass().getResource(path).getPath());
            BufferedReader br = new BufferedReader(fr);
            List<Vector3f> vertices = new ArrayList<Vector3f>();
            List<Vector2f> textures = new ArrayList<Vector2f>();
            List<Vector3f> normals = new ArrayList<Vector3f>();
            List<Integer> indices = new ArrayList<Integer>();

            float []array_of_vertices = null;
            float []array_of_textures = null;
            float []array_of_normals = null;
            int []array_of_indices = null;

            String line;

            while(true){
                line = br.readLine();
                String []present_line = line.split(" ");
                if(line.startsWith("v ")){
                    Vector3f vertex = new Vector3f(
                       Float.parseFloat(present_line[1]),
                       Float.parseFloat(present_line[2]),
                       Float.parseFloat(present_line[3])
                    );
                    vertices.add(vertex);
                }
                else if(line.startsWith("vt ")){
                    Vector2f texture = new Vector2f(
                            Float.parseFloat(present_line[1]),
                            Float.parseFloat(present_line[2])
                    );
                    textures.add(texture);
                }
                else if(line.startsWith("vn ")){
                    Vector3f normal = new Vector3f(
                            Float.parseFloat(present_line[1]),
                            Float.parseFloat(present_line[2]),
                            Float.parseFloat(present_line[3])
                    );
                    normals.add(normal);
                }
                else if(line.startsWith("f ")){
                    array_of_textures = new float[vertices.size()*2];
                    array_of_normals = new float[vertices.size()*3];
                    break;
                }
            }
            while (line!=null){
                if(!line.startsWith("f ")){
                    line = br.readLine();
                    continue;
                }
                String []present_line = line.split( " ");
                String []vertex_01 = present_line[1].split("/");
                String []vertex_02 = present_line[2].split("/");
                String []vertex_03 = present_line[3].split("/");

                 process_vertex(vertex_01,indices,textures,normals,array_of_textures,array_of_normals);
                process_vertex(vertex_02,indices,textures,normals,array_of_textures,array_of_normals);
                process_vertex(vertex_03,indices,textures,normals,array_of_textures,array_of_normals);

                line = br.readLine();
            }
            br.close();
            array_of_vertices = new float[vertices.size()*3];
            array_of_indices = new int[indices.size()];

            int vertex_pointer = 0;
            for(Vector3f vertex:vertices){
                array_of_vertices[vertex_pointer++] =vertex.x;
                array_of_vertices[vertex_pointer++] =vertex.y;
                array_of_vertices[vertex_pointer++] =vertex.z;
            }
            for(int i =0; i<indices.size();i++){
               array_of_indices[i] = indices.get(i);
            }

            fr.close();
            load_mesh(primary_stage,array_of_vertices,array_of_textures,array_of_normals,array_of_indices);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private  static void process_vertex(
            String []vertex_data, List<Integer> indices,
            List<Vector2f> textures,List<Vector3f> normals,
            float []array_of_textures,float []array_of_normals){

        int current_vertex_pointer = Integer.parseInt(vertex_data[0])-1;
        indices.add(current_vertex_pointer);

        Vector2f current_texture = textures.get(Integer.parseInt(vertex_data[1])-1);
        array_of_textures[current_vertex_pointer*2] = current_texture.x;
        array_of_textures[current_vertex_pointer*2+1] = 1 - current_texture.y;

        Vector3f current_normal = normals.get(Integer.parseInt(vertex_data[2])-1);
        array_of_normals[current_vertex_pointer*3] = current_normal.x;
        array_of_normals[current_vertex_pointer*3+1] = current_normal.y;
        array_of_normals[current_vertex_pointer*3+2] = current_normal.z;

    }

    private void load_mesh(Stage primary_stage, float[] array_of_vertices, float[] array_of_textures, float[] array_of_normals, int[] array_of_indices) {
        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.BLUE);
        blueMaterial.setSpecularColor(Color.LIGHTBLUE);

        //Create Material
   //     Material mat = new PhongMaterial();
   //     URL url = getClass().getResource("/resources/Convertible_BaseColor.png");
    //    Image diffuseMap = new Image(url.getPath());

// Set material properties
   //     ((PhongMaterial) mat).setDiffuseMap(diffuseMap);
  //      ((PhongMaterial) mat).setSpecularColor(Color.WHITE);


        final TriangleMesh mesh     = new TriangleMesh();
        mesh.getPoints().addAll(array_of_vertices);
       mesh.getTexCoords().addAll(array_of_textures);
        mesh.getFaces().addAll(array_of_indices);
        mesh.getFaceSmoothingGroups().addAll(new int[array_of_indices.length / mesh.getFaceElementSize()]);
        mesh.getNormals().addAll(array_of_normals);

        final MeshView mv = new MeshView(mesh);
        mv.translateXProperty().setValue(350);
        mv.translateYProperty().setValue(250);
        mv.setScaleX(800);
        mv.setScaleY(800);
        mv.setScaleZ(800);
        mv.setOpacity(1);
        mv.setCullFace(CullFace.NONE);
        mv.setMaterial(blueMaterial);
        mv.setDrawMode(DrawMode.FILL);
  //      mv.setRotationAxis(Rotate.Z_AXIS);
        mv.setRotationAxis(Rotate.Y_AXIS);
 //       mv.setRotationAxis(Rotate.X_AXIS);

        // Use the material for a shape
     //   mv.setMaterial(mat);

        Box rect = new Box(700,200,200);
        rect.setMaterial(blueMaterial);
       // Group root = new Group(rec);
      //  Group root = new Group(mv);


        rect.setMaterial(new PhongMaterial(Color.DARKGREEN));
        rect.setRotationAxis(Rotate.Y_AXIS);
        rect.setTranslateX(400);
        rect.setTranslateY(250);
// try commenting this line out to see what it's effect is . . .
        rect.setCullFace(CullFace.NONE);


        AmbientLight light = new AmbientLight();
        light.setColor(Color.WHITE);
        light.getTransforms().add(new Translate(0,-50,100));

        final Group root = new Group(mv);
        root.getChildren().addAll(light);
//        root.setScaleX(.05);
//        root.setScaleY(.05);
//        root.setScaleZ(.05);
        final Scene scene = new Scene(root, 700, 500, true);

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
                anchorAngle = mv.getRotate();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                mv.setRotate(anchorAngle + anchorX - event.getSceneX());
            }
        });

        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(false);
        perspectiveCamera.translateZProperty().setValue(-10);
        scene.setCamera(perspectiveCamera);
        primary_stage.setTitle("Alhamdulillah");
        // primary_stage.setScene(new Scene(root, 700, 500));
        primary_stage.setScene(scene);
        primary_stage.show();
    }

}
