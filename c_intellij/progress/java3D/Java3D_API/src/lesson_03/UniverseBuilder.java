package lesson_03;

import javax.media.j3d.*;
import javax.vecmath.Vector3f;

public class UniverseBuilder extends Object {
    // User-specified canvas
    Canvas3D canvas;

    // Scene graph elements that the user may want access to
    VirtualUniverse universe;
    Locale locale;
    TransformGroup vpTrans;
    View view;

    public UniverseBuilder(Canvas3D c) {
        this.canvas = c;

        // Establish a virtual universe, with a single hi-res Locale
        universe = new VirtualUniverse();
        locale = new Locale(universe);

        // Create a PhysicalBody and Physical Environment object
        PhysicalBody body = new PhysicalBody();
        PhysicalEnvironment environment =
                new PhysicalEnvironment();

        // Create a View and attach the Canvas3D and the physical
        // body and environment to the view.
        view = new View();
        view.addCanvas3D(c);
        view.setPhysicalBody(body);
        view.setPhysicalEnvironment(environment);

        // Create a branch group node for the view platform
        BranchGroup vpRoot = new BranchGroup();

        // Create a ViewPlatform object, and its associated
        // TransformGroup object, and attach it to the root of the
        // subgraph.  Attach the view to the view platform.
        Transform3D t = new Transform3D();
        t.set(new Vector3f(0.0f, 0.0f, 5.0f));
        ViewPlatform vp = new ViewPlatform();
        TransformGroup vpTrans = new TransformGroup(t);

        vpTrans.addChild(vp);
        vpRoot.addChild(vpTrans);

        view.attachViewPlatform(vp);
        view.setFieldOfView(40.0 * Math.PI / 180.0);
        view.setFrontClipDistance(1.0);
        view.setBackClipDistance(10.0);

        // Attach the branch graph to the universe, via the Locale.
        // The scene graph is now live!
        locale.addBranchGraph(vpRoot);
    }

    public void addBranchGraph(BranchGroup bg) {
        locale.addBranchGraph(bg);
    }
}