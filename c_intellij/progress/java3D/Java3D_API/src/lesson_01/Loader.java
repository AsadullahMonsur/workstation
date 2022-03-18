package lesson_01;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;

import javax.media.j3d.BranchGroup;

public class Loader {
    private String path ="unknown";
    private double crease_angle = 60.0;
    public Loader(String path) {
        this.path = path;
    }

    public BranchGroup obj_file_to_graph(){
        int position_flag = ObjectFile.RESIZE;
        float resize_helper = (float)(crease_angle*Math.PI)/180;

        ObjectFile objFile = new ObjectFile(position_flag,resize_helper);
        Scene scene = null;
        try {
            scene = objFile.load(path);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("OBJ load Err：" + e.getMessage());
        }
        BranchGroup graph = new BranchGroup();
        graph.addChild(scene.getSceneGroup());
        return graph;
    }
}
