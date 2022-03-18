package loader;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;

import javax.media.j3d.BranchGroup;

public class Loader {
    private String path ="unknown";
    private double crease_angle = 60.0;

    public Loader(String path) {
        this.path = path;
    }

    public BranchGroup object_to_graph(){
        int position_flag = ObjectFile.RESIZE;
        float resize_helper = (float)(crease_angle*Math.PI)/180;

        ObjectFile file = new ObjectFile(position_flag,resize_helper);
        Scene scene = null;
        try {
            scene = file.load(path);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loader Exception: \n" + e.getMessage());
        }

        BranchGroup graph = new BranchGroup();
        graph.addChild(scene.getSceneGroup());
        return graph;
    }
}
