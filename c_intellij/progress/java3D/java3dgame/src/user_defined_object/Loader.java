package user_defined_object;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    double anchorX, anchorY, anchorAngle;
    public Loader(String  path, Stage primary_stage){
        try{
            FileReader  fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<Float> vertices = new ArrayList<Float>();
            ArrayList<Float> textures = new ArrayList<Float>();
            ArrayList<Float> normals = new ArrayList<Float>();
            ArrayList<Integer> indices = new ArrayList<Integer>();

            float []array_of_vertices = null;
            float []array_of_textures = null;
            float []array_of_normals = null;
            int []array_of_indices = null;

            String line;

            while(true){
                line = br.readLine();
                String []present_line = line.split(" ");
                if(line.startsWith("v ")){
                    vertices.add(Float.parseFloat(present_line[1]));
                    vertices.add(Float.parseFloat(present_line[2]));
                    vertices.add(Float.parseFloat(present_line[3]));
                }
                else if(line.startsWith("vt ")){
                    textures.add(Float.parseFloat(present_line[1]));
                    textures.add(Float.parseFloat(present_line[2]));
                }
                else if(line.startsWith("vn ")){

                    normals.add(Float.parseFloat(present_line[1]));
                    normals.add(Float.parseFloat(present_line[2]));
                    normals.add(Float.parseFloat(present_line[3]));

                }
                else if(line.startsWith("f ")){
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

                fix_faces(indices,vertex_01);

                fix_faces(indices,vertex_02);

                fix_faces(indices,vertex_03);


//                process_vertex(vertex_01,indices,textures,normals,array_of_textures,array_of_normals);
//                process_vertex(vertex_02,indices,textures,normals,array_of_textures,array_of_normals);
//                process_vertex(vertex_03,indices,textures,normals,array_of_textures,array_of_normals);
//
                line = br.readLine();
            }
            br.close();
//            array_of_vertices = new float[vertices.size()*3];
//            array_of_indices = new int[indices.size()];
//
//            int vertex_pointer = 0;
//            for(Vector3f vertex:vertices){
//                array_of_vertices[vertex_pointer++] =vertex.x;
//                array_of_vertices[vertex_pointer++] =vertex.y;
//                array_of_vertices[vertex_pointer++] =vertex.z;
//            }


            array_of_vertices = new float[vertices.size()];
            int i = 0;
            while (i<array_of_vertices.length){
                System.out.println(i+" fart"+vertices.get(i));
                array_of_vertices[i] = vertices.get(i);
                i++;
            }

            array_of_textures = new float[textures.size()];
            i = 0;
            while (i<array_of_textures.length){
                System.out.println(i+" fart"+textures.get(i));
                array_of_textures[i] = textures.get(i);
                i++;
            }

            array_of_normals = new float[normals.size()];
             i = 0;
            while (i<array_of_normals.length){
                System.out.println(i+" fart"+normals.get(i));
                array_of_normals[i] = normals.get(i);
                i++;
            }

            System.out.println("total size :"+indices.size());
            array_of_indices = new int[indices.size()];
            i = 0;
            while (i<array_of_indices.length){
                System.out.println(i+" fart"+indices.get(i));
                array_of_indices[i] = indices.get(i);
                i++;
            }

            fr.close();
            load_mesh(primary_stage,array_of_vertices,array_of_textures,array_of_normals,array_of_indices);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private static boolean fix_faces(ArrayList<Integer> indices, String []block){
        int i=0;
        while(i<block.length){
            System.out.println(block[i]);
            indices.add(Integer.parseInt(block[i]));
            i++;
        }

        return true;
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

        final TriangleMesh mesh     = new TriangleMesh();
        mesh.getPoints().addAll(array_of_vertices);
        mesh.getTexCoords().addAll(array_of_textures);
        mesh.getFaces().addAll(array_of_indices);
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

        final Group root = new Group(mv);
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
