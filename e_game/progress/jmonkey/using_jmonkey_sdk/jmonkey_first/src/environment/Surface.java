package environment;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

public class Surface {
    private int row;
    private int column;
    private int color = 1; // 1 white, 0 black

    private AssetManager assetManager;
    
        public Surface(int dimension,AssetManager assetManager) {
            this.assetManager = assetManager;
            
            if(dimension<4){
                dimension = 4;
            }
            if(dimension%2!=0){
                dimension = dimension +1;
            }
            this.row = dimension;
            this.column = dimension;
        }

        public Node form_floor(int flag) {

            int r = row;
            int c = column;

            Node floor_parent = new Node("floor_parent");
            floor_parent.setLocalTranslation(new Vector3f(0.0f,
                                                    0.0f,0.0f));
            
            if(flag==1){
                r = 2;  // when this method called from obstacle
                c = 2;
            }

            int width = 0;  // counter
            int z = -5;    // x ,z is co-ordinates

            while(width<r){
                if(color==0){
                    color = 1;
                }
                else{
                    color = 0;
                }

                int x = r/2;
                int length =0;

                while (length<c) {
                    boolean result = false;
                    while (!result){
                        result = floor_installment(x,z,flag,floor_parent);
                    }
                    x = x-1;
                    length++;
                }
                z = z-2;
                width++;
            }
            return floor_parent;
        }

        private boolean floor_installment(int x, int z, int flag, Node floor_parent){
            
          Material material = new Material(assetManager,
                                    "Common/MatDefs/Misc/Unshaded.j3md");
          material.setColor("Color", ColorRGBA.Black);
          
            if(color==1){
                material.setColor("Color", ColorRGBA.White);
                if(flag==1){
                    material.setColor("Color", ColorRGBA.Magenta);
                }
                color = 0;
            }
            else {
                color = 1;
                 material.setColor("Color", ColorRGBA.Black);
                if(flag==1){
                    material.setColor("Color", ColorRGBA.Pink);
                }
            }
       
           
            Box box = new Box(0.5f,0.2f,1.0f);

            if(flag==1) {
                box = new Box(0.5f,1.0f,1.0f);
            }

            Geometry group = new Geometry("Box", box);
            group.setMaterial(material);
            
            group.setLocalTranslation(new Vector3f((float)x,
                                                    -1.5f,(float)z));

            floor_parent.attachChild(group);

            return true;
        }

        public Node form_obstacle(){
            Node obstacle_parent = new Node("floor_parent");
            obstacle_parent.setLocalTranslation(new Vector3f(0.0f,
                                                    0.0f,0.0f));

            int z = -5;  // x,z co-ordinates
            int checker_outter = 1;
            for(int width=0; width<row; width=width+2){
                if(checker_outter%2==0) {
                    int x = (row / 2);
                    if (x % 2 != 0) {
                        x = x - 1;
                    }
                    int checker_inner = 1;
                    for (int length = 0; length < column; length = length + 2) {
                        if (checker_inner % 2 == 0) {
                           boolean result = false;
                           while (!result){
                               result = obstacles_installment(x,z,obstacle_parent);
                           }
                        }
                        checker_inner = checker_inner + 1;
                        x = x - 2;
                    }
                }
                checker_outter = checker_outter+1;
                z = z-4;
            }
            return obstacle_parent;
        }

        private boolean obstacles_installment(int x, int z, 
                                                 Node obstacle_parent) {
            Node group = form_floor(1);
            group.setLocalTranslation(new Vector3f((float)x, 0.5f,
                                                    (float) (z+(1+4)) ));
            obstacle_parent.attachChild(group);
            return true;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

    }