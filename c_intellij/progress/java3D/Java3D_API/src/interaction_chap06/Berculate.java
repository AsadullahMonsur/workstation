package interaction_chap06;

import com.sun.j3d.internal.J3dUtilsI18N;
import com.sun.j3d.utils.behaviors.vp.ViewPlatformAWTBehavior;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Matrix3d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import java.awt.event.MouseEvent;
import java.awt.AWTEvent;

    public class Berculate extends ViewPlatformAWTBehavior {

        private Transform3D longditudeTransform = new Transform3D();
        private Transform3D latitudeTransform = new Transform3D();
        private Transform3D rotateTransform = new Transform3D();

        // needed for integrateTransforms but don't want to new every time
        private Transform3D temp1 = new Transform3D();
        private Transform3D temp2 = new Transform3D();
        private Transform3D translation = new Transform3D();
        private Vector3d transVector = new Vector3d();
        private Vector3d distanceVector = new Vector3d();
        private Vector3d centerVector = new Vector3d();
        private Vector3d invertCenterVector = new Vector3d();

        private double longditude = 0.0;
        private double latitude = 0.0;
        private double startDistanceFromCenter = 20.0;
        private double distanceFromCenter = 20.0;
        private Point3d rotationCenter = new Point3d();
        private Matrix3d rotMatrix = new Matrix3d();
        private Transform3D currentXfm = new Transform3D();

        private int mouseX = 0;
        private int mouseY = 0;

        private double rotXFactor = 1.0;
        private double rotYFactor = 1.0;
        private double transXFactor = 1.0;
        private double transYFactor = 1.0;
        private double zoomFactor = 1.0;

        private double xtrans = 0.0;
        private double ytrans = 0.0;
        private double ztrans = 0.0;

        private boolean zoomEnabled = true;
        private boolean rotateEnabled = true;
        private boolean translateEnabled = true;
        private boolean reverseRotate = false;
        private boolean reverseTrans = false;
        private boolean reverseZoom = false;
        private boolean stopZoom = false;
        private boolean proportionalZoom = false;
        private double minRadius = 0.0;
        private int leftButton = ROTATE;
        private int rightButton = TRANSLATE;
        private int middleButton = ZOOM;

        // the factor to be applied to wheel zooming so that it does not
        // look much different with mouse movement zooming.
        // This is a totally subjective factor.
        private float wheelZoomFactor = 50.0f;

        /**
         * Constructor flag to reverse the rotate behavior
         */
        public static final int REVERSE_ROTATE = 0x010;

        /**
         * Constructor flag to reverse the translate behavior
         */
        public static final int REVERSE_TRANSLATE = 0x020;

        /**
         * Constructor flag to reverse the zoom behavior
         */
        public static final int REVERSE_ZOOM = 0x040;

        /**
         * Constructor flag to reverse all the behaviors
         */
        public static final int REVERSE_ALL = (REVERSE_ROTATE | REVERSE_TRANSLATE |
                REVERSE_ZOOM);

        /**
         * Constructor flag that indicates zoom should stop when it reaches
         * the minimum orbit radius set by setMinRadius().  The minimus
         * radius default is 0.0.
         */
        public static final int STOP_ZOOM = 0x100;

        /**
         * Constructor flag to disable rotate
         */
        public static final int DISABLE_ROTATE = 0x200;

        /**
         * Constructor flag to disable translate
         */
        public static final int DISABLE_TRANSLATE = 0x400;

        /**
         * Constructor flag to disable zoom
         */
        public static final int DISABLE_ZOOM = 0x800;

        /**
         * Constructor flag to use proportional zoom, which determines
         * how much you zoom based on view's distance from the center of
         * rotation.  The percentage of distance that the viewer zooms
         * is determined by the zoom factor.
         */
        public static final int PROPORTIONAL_ZOOM = 0x1000;

        /**
         * Used to set the fuction for a mouse button to Rotate
         */
        private static final int ROTATE = 0;

        /**
         * Used to set the function for a mouse button to Translate
         */
        private static final int TRANSLATE = 1;

        /**
         * Used to set the function for a mouse button to Zoom
         */
        private static final int ZOOM = 2;

        private static final double NOMINAL_ZOOM_FACTOR = .01;
        private static final double NOMINAL_PZOOM_FACTOR = 1.0;
        private static final double NOMINAL_ROT_FACTOR = .01;
        private static final double NOMINAL_TRANS_FACTOR = .01;

        private double rotXMul = NOMINAL_ROT_FACTOR * rotXFactor;
        private double rotYMul = NOMINAL_ROT_FACTOR * rotYFactor;
        private double transXMul = NOMINAL_TRANS_FACTOR * transXFactor;
        private double transYMul = NOMINAL_TRANS_FACTOR * transYFactor;
        private double zoomMul = NOMINAL_ZOOM_FACTOR * zoomFactor;

        /**
         * Parameterless constructor for this behavior.  This is intended for use
         * by ConfiguredUniverse, which requires such a constructor for
         * configurable behaviors.  The Canvas3D used to listen for mouse and
         * mouse motion events is obtained from the superclass
         * setViewingPlatform() method.
         * @since Java 3D 1.3
         */
        public Berculate() {
            super(MOUSE_LISTENER | MOUSE_MOTION_LISTENER | MOUSE_WHEEL_LISTENER);
        }

        /**
         * Creates a new OrbitBehavior
         *
         * @param c The Canvas3D to add the behavior to
         */
        public Berculate(Canvas3D c) {
            this(c, 0 );
        }

        /**
         * Creates a new OrbitBehavior
         *
         * @param c The Canvas3D to add the behavior to
         * @param flags The option flags
         */
        public Berculate(Canvas3D c, int flags) {
            super(c, MOUSE_LISTENER | MOUSE_MOTION_LISTENER | MOUSE_WHEEL_LISTENER | flags );

            if ((flags & DISABLE_ROTATE) != 0) rotateEnabled = false;
            if ((flags & DISABLE_ZOOM) != 0) zoomEnabled = false;
            if ((flags & DISABLE_TRANSLATE) != 0) translateEnabled = false;
            if ((flags & REVERSE_TRANSLATE) != 0) reverseTrans = true;
            if ((flags & REVERSE_ROTATE) != 0)  reverseRotate = true;
            if ((flags & REVERSE_ZOOM) != 0) reverseZoom = true;
            if ((flags & STOP_ZOOM) != 0) stopZoom = true;
            if ((flags & PROPORTIONAL_ZOOM) !=0) {
                proportionalZoom = true;
                zoomMul = NOMINAL_PZOOM_FACTOR * zoomFactor;
            }
        }

        @Override
        protected synchronized void processAWTEvents( final AWTEvent[] events ) {
            motion = false;
            for(int i=0; i<events.length; i++)
                if (events[i] instanceof MouseEvent)
                    processMouseEvent( (MouseEvent)events[i] );
        }

        protected void processMouseEvent( final MouseEvent evt ) {

            if (evt.getID()==MouseEvent.MOUSE_PRESSED) {
                mouseX = evt.getX();
                mouseY = evt.getY();
                motion=true;
            } else if (evt.getID()==MouseEvent.MOUSE_DRAGGED) {
                int xchange = evt.getX() - mouseX;
                int ychange = evt.getY() - mouseY;
                // rotate
                if (rotate(evt)) {
                    if (reverseRotate) {
                        longditude -= xchange * rotXMul;
                        latitude -= ychange * rotYMul;
                    }
                    else {
                        longditude += xchange * rotXMul;
                        latitude += ychange * rotYMul;
                    }
                }
                // translate
                else if (translate(evt)) {
                    if (reverseTrans) {
                        xtrans -= xchange * transXMul;
                        ytrans += ychange * transYMul;
                    }
                    else {
                        xtrans += xchange * transXMul;
                        ytrans -= ychange * transYMul;
                    }
                }
                // zoom
                else if (zoom(evt)) {
                    doZoomOperations( ychange );
                }
                mouseX = evt.getX();
                mouseY = evt.getY();
                motion = true;
            } else if (evt.getID()==MouseEvent.MOUSE_RELEASED ) {
            } else if (evt.getID()==MouseEvent.MOUSE_WHEEL ) {
                if (zoom(evt)) {
                    // if zooming is done through mouse wheel,
                    // the amount of increments the wheel changed,
                    // multiplied with wheelZoomFactor is used,
                    // so that zooming speed looks natural compared to mouse movement zoom.
                    if ( evt instanceof java.awt.event.MouseWheelEvent){
                        // I/O differenciation is made between
                        // java.awt.event.MouseWheelEvent.WHEEL_UNIT_SCROLL or
                        // java.awt.event.MouseWheelEvent.WHEEL_BLOCK_SCROLL so
                        // that behavior remains stable and not dependent on OS settings.
                        // If getWheelRotation() was used for calculating the zoom,
                        // the zooming speed could act differently on different platforms,
                        // if, for example, the user sets his mouse wheel to jump 10 lines
                        // or a block.
                        int zoom =
                                ((int)(((java.awt.event.MouseWheelEvent)evt).getWheelRotation()
                                        * wheelZoomFactor));
                        doZoomOperations( zoom );
                        motion = true;
                    }
                }
            }
        }

        /*extraction of the zoom algorithms so that there is no code duplication or source 'uglyfication'.
         */
        private void doZoomOperations( int ychange ) {
            if (proportionalZoom) {
                if (reverseZoom) {
                    if ((distanceFromCenter -
                            (zoomMul*ychange*distanceFromCenter/100.0)) >
                            minRadius) {
                        distanceFromCenter -= (zoomMul*ychange*
                                distanceFromCenter/100.0);
                    }
                    else {
                        distanceFromCenter = minRadius;
                    }
                }
                else {
                    if ((distanceFromCenter +
                            (zoomMul*ychange*distanceFromCenter/100.0))
                            > minRadius) {
                        distanceFromCenter += (zoomMul*ychange*
                                distanceFromCenter/100.0);
                    }
                    else {
                        distanceFromCenter = minRadius;
                    }
                }
            }
            else {
                if (stopZoom) {
                    if (reverseZoom) {
                        if ((distanceFromCenter - ychange*zoomMul) > minRadius) {
                            distanceFromCenter -= ychange*zoomMul;
                        }
                        else {
                            distanceFromCenter = minRadius;
                        }
                    }
                    else {
                        if ((distanceFromCenter + ychange*zoomMul) > minRadius) {
                            distanceFromCenter += ychange * zoomMul;
                        }
                        else {
                            distanceFromCenter = minRadius;
                        }
                    }
                }
                else {
                    if (reverseZoom) {
                        distanceFromCenter -= ychange*zoomMul;
                    }
                    else {
                        distanceFromCenter += ychange*zoomMul;
                    }
                }
            }
        }



        /**
         * Sets the ViewingPlatform for this behavior.  This method is
         * called by the ViewingPlatform.
         * If a sub-calls overrides this method, it must call
         * super.setViewingPlatform(vp).
         * NOTE: Applications should <i>not</i> call this method.
         */
        @Override
        public void setViewingPlatform(ViewingPlatform vp) {
            super.setViewingPlatform( vp );

            if (vp!=null) {
                resetView();
                integrateTransforms();
            }
        }

        /**
         * Reset the orientation and distance of this behavior to the current
         * values in the ViewPlatform Transform Group
         */
        private void resetView() {
            Vector3d centerToView = new Vector3d();

            targetTG.getTransform( targetTransform );

            targetTransform.get( rotMatrix, transVector );
            centerToView.sub( transVector, rotationCenter );
            distanceFromCenter = centerToView.length();
            startDistanceFromCenter = distanceFromCenter;

            targetTransform.get( rotMatrix );
            rotateTransform.set( rotMatrix );

            // compute the initial x/y/z offset
            temp1.set(centerToView);
            rotateTransform.invert();
            rotateTransform.mul(temp1);
            rotateTransform.get(centerToView);
            xtrans = centerToView.x;
            ytrans = centerToView.y;
            ztrans = centerToView.z;

            // reset rotMatrix
            rotateTransform.set( rotMatrix );
        }

        @Override
        protected synchronized void integrateTransforms() {
            // Check if the transform has been changed by another
            // behavior
            targetTG.getTransform(currentXfm) ;
            if (! targetTransform.equals(currentXfm))
                resetView() ;

            longditudeTransform.rotY( longditude );
            latitudeTransform.rotX( latitude );
            rotateTransform.mul(rotateTransform, latitudeTransform);
            rotateTransform.mul(rotateTransform, longditudeTransform);

            distanceVector.z = distanceFromCenter - startDistanceFromCenter;

            temp1.set(distanceVector);
            temp1.mul(rotateTransform, temp1);

            // want to look at rotationCenter
            transVector.x = rotationCenter.x + xtrans;
            transVector.y = rotationCenter.y + ytrans;
            transVector.z = rotationCenter.z + ztrans;

            translation.set(transVector);
            targetTransform.mul(temp1, translation);

            // handle rotationCenter
            temp1.set(centerVector);
            temp1.mul(targetTransform);

            invertCenterVector.x = -centerVector.x;
            invertCenterVector.y = -centerVector.y;
            invertCenterVector.z = -centerVector.z;

            temp2.set(invertCenterVector);
            targetTransform.mul(temp1, temp2);

            targetTG.setTransform(targetTransform);

            // reset yaw and pitch angles
            longditude = 0.0;
            latitude = 0.0;
        }

        /**
         * Sets the center around which the View rotates.
         * The default is (0,0,0).
         * @param center The Point3d to set the center of rotation to
         */
        public synchronized void setRotationCenter(Point3d center) {
            Point3d centerDelta = new Point3d();
            centerDelta.sub(centerVector, center);
            Transform3D invRot = new Transform3D(rotateTransform);
            invRot.invert();
            invRot.transform(centerDelta);
            xtrans += centerDelta.x;
            ytrans += centerDelta.y;
            ztrans += centerDelta.z;
            rotationCenter.x = center.x;
            rotationCenter.y = center.y;
            rotationCenter.z = center.z;
            centerVector.set(rotationCenter);
        }

        /**
         * Property which sets the center around which the View rotates.
         * Used by ConfiguredUniverse.
         * @param center array of length 1 containing an instance of Point3d
         * @since Java 3D 1.3
         */
        public void RotationCenter(Object[] center) {
            if (! (center.length == 1 && center[0] instanceof Point3d))
                throw new IllegalArgumentException
                        ("RotationCenter must be a single Point3d");

            setRotationCenter((Point3d)center[0]);
        }

        /**
         * Places the value of the center around which the View rotates
         * into the Point3d.
         * @param center The Point3d
         */
        public void getRotationCenter(Point3d center) {
            center.x = rotationCenter.x;
            center.y = rotationCenter.y;
            center.z = rotationCenter.z;
        }

        // TODO
        // Need to add key factors for Rotate, Translate and Zoom
        // Method calls should just update MAX_KEY_ANGLE, KEY_TRANSLATE and
        // KEY_ZOOM
        //
        // Methods also need to correctly set sign of variables depending on
        // the Reverse settings.

        /**
         * Sets the rotation x and y factors.  The factors are used to determine
         * how many radians to rotate the view for each pixel of mouse movement.
         * The view is rotated factor * 0.01 radians for each pixel of mouse
         * movement.  The default factor is 1.0.
         * @param xfactor The x movement multiplier
         * @param yfactor The y movement multiplier
         **/
        public synchronized void setRotFactors(double xfactor, double yfactor) {
            rotXFactor = xfactor;
            rotYFactor = yfactor;
            rotXMul = NOMINAL_ROT_FACTOR * xfactor;
            rotYMul = NOMINAL_ROT_FACTOR * yfactor;
        }

        /**
         * Property which sets the rotation x and y factors.
         * Used by ConfiguredUniverse.
         * @param factors array of length 2 containing instances of Double
         * @since Java 3D 1.3
         */
        public void RotFactors(Object[] factors) {
            if (! (factors.length == 2 &&
                    factors[0] instanceof Double && factors[1] instanceof Double))
                throw new IllegalArgumentException
                        ("RotFactors must be two Doubles");

            setRotFactors(((Double)factors[0]).doubleValue(),
                    ((Double)factors[1]).doubleValue());
        }

        /**
         * Sets the rotation x factor.  The factors are used to determine
         * how many radians to rotate the view for each pixel of mouse movement.
         * The view is rotated factor * 0.01 radians for each pixel of mouse
         * movement.  The default factor is 1.0.
         * @param xfactor The x movement multiplier
         **/
        public synchronized void setRotXFactor(double xfactor) {
            rotXFactor = xfactor;
            rotXMul = NOMINAL_ROT_FACTOR * xfactor;
        }

        /**
         * Property which sets the rotation x factor.
         * Used by ConfiguredUniverse.
         * @param xFactor array of length 1 containing instance of Double
         * @since Java 3D 1.3
         */
        public void RotXFactor(Object[] xFactor) {
            if (! (xFactor.length == 1 && xFactor[0] instanceof Double))
                throw new IllegalArgumentException("RotXFactor must be a Double");

            setRotXFactor(((Double)xFactor[0]).doubleValue());
        }

        /**
         * Sets the rotation y factor.  The factors are used to determine
         * how many radians to rotate the view for each pixel of mouse movement.
         * The view is rotated factor * 0.01 radians for each pixel of mouse
         * movement.  The default factor is 1.0.
         * @param yfactor The y movement multiplier
         **/
        public synchronized void setRotYFactor(double yfactor) {
            rotYFactor = yfactor;
            rotYMul = NOMINAL_ROT_FACTOR * yfactor;
        }

        /**
         * Property which sets the rotation y factor.
         * Used by ConfiguredUniverse.
         * @param yFactor array of length 1 containing instance of Double
         * @since Java 3D 1.3
         */
        public void RotYFactor(Object[] yFactor) {
            if (! (yFactor.length == 1 && yFactor[0] instanceof Double))
                throw new IllegalArgumentException("RotYFactor must be a Double");

            setRotYFactor(((Double)yFactor[0]).doubleValue());
        }

        /**
         * Sets the translation x and y factors.  The factors are used to determine
         * how many units to translate the view for each pixel of mouse movement.
         * The view is translated factor * 0.01 units for each pixel of mouse
         * movement.  The default factor is 1.0.
         * @param xfactor The x movement multiplier
         * @param yfactor The y movement multiplier
         **/
        public synchronized void setTransFactors(double xfactor,
                                                 double yfactor) {
            transXFactor = xfactor;
            transYFactor = yfactor;
            transXMul = NOMINAL_TRANS_FACTOR * xfactor;
            transYMul = NOMINAL_TRANS_FACTOR * yfactor;
        }

        /**
         * Property which sets the translation x and y factors.
         * Used by ConfiguredUniverse.
         * @param factors array of length 2 containing instances of Double
         * @since Java 3D 1.3
         */
        public void TransFactors(Object[] factors) {
            if (! (factors.length == 2 &&
                    factors[0] instanceof Double && factors[1] instanceof Double))
                throw new IllegalArgumentException
                        ("TransFactors must be two Doubles");

            setTransFactors(((Double)factors[0]).doubleValue(),
                    ((Double)factors[1]).doubleValue());
        }

        /**
         * Sets the translation x factor.  The factors are used to determine
         * how many units to translate the view for each pixel of mouse movement.
         * The view is translated factor * 0.01 units for each pixel of mouse
         * movement.  The default factor is 1.0.
         * @param xfactor The x movement multiplier
         **/
        public synchronized void setTransXFactor(double xfactor) {
            transXFactor = xfactor;
            transXMul = NOMINAL_TRANS_FACTOR * xfactor;
        }

        /**
         * Property which sets the translation x factor.
         * Used by ConfiguredUniverse.
         * @param xFactor array of length 1 containing instance of Double
         * @since Java 3D 1.3
         */
        public void TransXFactor(Object[] xFactor) {
            if (! (xFactor.length == 1 && xFactor[0] instanceof Double))
                throw new IllegalArgumentException("TransXFactor must be a Double");

            setTransXFactor(((Double)xFactor[0]).doubleValue());
        }

        /**
         * Sets the translation y factor.  The factors are used to determine
         * how many units to translate the view for each pixel of mouse movement.
         * The view is translated factor * 0.01 units for each pixel of mouse
         * movement.  The default factor is 1.0.
         * @param yfactor The y movement multiplier
         **/
        public synchronized void setTransYFactor(double yfactor) {
            transYFactor = yfactor;
            transYMul = NOMINAL_TRANS_FACTOR * yfactor;
        }

        /**
         * Property which sets the translation y factor.
         * Used by ConfiguredUniverse.
         * @param yFactor array of length 1 containing instance of Double
         * @since Java 3D 1.3
         */
        public void TransYFactor(Object[] yFactor) {
            if (! (yFactor.length == 1 && yFactor[0] instanceof Double))
                throw new IllegalArgumentException("TransYFactor must be a Double");

            setTransYFactor(((Double)yFactor[0]).doubleValue());
        }

        /**
         * Sets the zoom factor.  The factor is used to determine how many
         * units to zoom the view for each pixel of mouse movement.
         * The view is zoomed factor * 0.01 units for each pixel of mouse
         * movement.  For proportional zoom, the view is zoomed factor * 1%
         * of the distance from the center of rotation for each pixel of
         * mouse movement.  The default factor is 1.0.
         * @param zfactor The movement multiplier
         */
        public synchronized void setZoomFactor(double zfactor) {
            zoomFactor = zfactor;
            if (proportionalZoom) {
                zoomMul = NOMINAL_PZOOM_FACTOR * zfactor;
            }
            else {
                zoomMul = NOMINAL_ZOOM_FACTOR * zfactor;
            }
        }

        /**
         * Property which sets the zoom factor.
         * Used by ConfiguredUniverse.
         * @param zFactor array of length 1 containing instance of Double
         * @since Java 3D 1.3
         */
        public void ZoomFactor(Object[] zFactor) {
            if (! (zFactor.length == 1 && zFactor[0] instanceof Double))
                throw new IllegalArgumentException("ZoomFactor must be a Double");

            setZoomFactor(((Double)zFactor[0]).doubleValue());
        }

        /**
         * Returns the x rotation movement multiplier
         * @return The movement multiplier for x rotation
         */
        public double getRotXFactor() {
            return rotXFactor;
        }

        /**
         * Returns the y rotation movement multiplier
         * @return The movement multiplier for y rotation
         */
        public double getRotYFactor() {
            return rotYFactor;
        }

        /**
         * Returns the x translation movement multiplier
         * @return The movement multiplier for x translation
         */
        public double getTransXFactor() {
            return transXFactor;
        }

        /**
         * Returns the y translation movement multiplier
         * @return The movement multiplier for y translation
         */
        public double getTransYFactor() {
            return transYFactor;
        }

        /**
         * Returns the zoom movement multiplier
         * @return The movement multiplier for zoom
         */
        public double getZoomFactor() {
            return zoomFactor;
        }

        /**
         * Enables or disables rotation.  The default is true.
         * @param enabled true or false to enable or disable rotate
         */
        public synchronized void setRotateEnable(boolean enabled) {
            rotateEnabled = enabled;
        }

        /**
         * Property which enables or disables rotation.
         * Used by ConfiguredUniverse.
         * @param enabled array of length 1 containing instance of Boolean
         * @since Java 3D 1.3
         */
        public void RotateEnable(Object[] enabled) {
            if (! (enabled.length == 1 && enabled[0] instanceof Boolean))
                throw new IllegalArgumentException("RotateEnable must be Boolean");

            setRotateEnable(((Boolean)enabled[0]).booleanValue());
        }

        /**
         * Enables or disables zoom. The default is true.
         * @param enabled true or false to enable or disable zoom
         */
        public synchronized void setZoomEnable(boolean enabled) {
            zoomEnabled = enabled;
        }

        /**
         * Property which enables or disables zoom.
         * Used by ConfiguredUniverse.
         * @param enabled array of length 1 containing instance of Boolean
         * @since Java 3D 1.3
         */
        public void ZoomEnable(Object[] enabled) {
            if (! (enabled.length == 1 && enabled[0] instanceof Boolean))
                throw new IllegalArgumentException("ZoomEnable must be Boolean");

            setZoomEnable(((Boolean)enabled[0]).booleanValue());
        }

        /**
         * Enables or disables translate. The default is true.
         * @param enabled true or false to enable or disable translate
         */
        public synchronized void setTranslateEnable(boolean enabled) {
            translateEnabled = enabled;
        }

        /**
         * Property which enables or disables translate.
         * Used by ConfiguredUniverse.
         * @param enabled array of length 1 containing instance of Boolean
         * @since Java 3D 1.3
         */
        public void TranslateEnable(Object[] enabled) {
            if (! (enabled.length == 1 && enabled[0] instanceof Boolean))
                throw new IllegalArgumentException
                        ("TranslateEnable must be Boolean");

            setTranslateEnable(((Boolean)enabled[0]).booleanValue());
        }

        /**
         * Retrieves the state of rotate enabled
         * @return the rotate enable state
         */
        public boolean getRotateEnable() {
            return rotateEnabled;
        }

        /**
         * Retrieves the state of zoom enabled
         * @return the zoom enable state
         */
        public boolean getZoomEnable() {
            return zoomEnabled;
        }

        /**
         * Retrieves the state of translate enabled
         * @return the translate enable state
         */
        public boolean getTranslateEnable() {
            return translateEnabled;
        }

        boolean rotate(MouseEvent evt) {
            if (rotateEnabled) {
                if ((leftButton == ROTATE) &&
                        (!evt.isAltDown() && !evt.isMetaDown())) {
                    return true;
                }
                if ((middleButton == ROTATE) &&
                        (evt.isAltDown() && !evt.isMetaDown())) {
                    return true;
                }
                if ((rightButton == ROTATE) &&
                        (!evt.isAltDown() && evt.isMetaDown())) {
                    return true;
                }
            }
            return false;
        }

        boolean zoom(MouseEvent evt) {
            if (zoomEnabled) {
                if (evt instanceof java.awt.event.MouseWheelEvent) {
                    return true;
                }
                if ((leftButton == ZOOM) &&
                        (!evt.isAltDown() && !evt.isMetaDown())) {
                    return true;
                }
                if ((middleButton == ZOOM) &&
                        (evt.isAltDown() && !evt.isMetaDown())) {
                    return true;
                }
                if ((rightButton == ZOOM) &&
                        (!evt.isAltDown() && evt.isMetaDown())) {
                    return true;
                }
            }
            return false;
        }

        boolean translate(MouseEvent evt) {
            if (translateEnabled) {
                if ((leftButton == TRANSLATE) &&
                        (!evt.isAltDown() && !evt.isMetaDown())) {
                    return true;
                }
                if ((middleButton == TRANSLATE) &&
                        (evt.isAltDown() && !evt.isMetaDown())) {
                    return true;
                }
                if ((rightButton == TRANSLATE) &&
                        (!evt.isAltDown() && evt.isMetaDown())) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Sets the minimum radius for the OrbitBehavior.  The zoom will
         * stop at this distance from the center of rotation.  The default
         * is 0.0.  The minimum will have no affect if the STOP_ZOOM constructor
         * flag is not set.
         * @param r the minimum radius
         * @exception IllegalArgumentException if the radius is less than 0.0
         */
        public synchronized void setMinRadius(double r) {
            if (r < 0.0) {
                throw new IllegalArgumentException(J3dUtilsI18N.getString("OrbitBehavior1"));
            }
            minRadius = r;
        }

        /**
         * Property which sets the minimum radius for the OrbitBehavior.
         * Used by ConfiguredUniverse.
         * @param r array of length 1 containing instance of Double
         * @since Java 3D 1.3
         */
        public void MinRadius(Object[] r) {
            if (! (r.length == 1 && r[0] instanceof Double))
                throw new IllegalArgumentException("MinRadius must be a Double");

            setMinRadius(((Double)r[0]).doubleValue());
        }

        /**
         * Returns the minimum orbit radius.  The zoom will stop at this distance
         * from the center of rotation if the STOP_ZOOM constructor flag is set.
         * @return the minimum radius
         */
        public double getMinRadius() {
            return minRadius;
        }

        /**
         * Set reverse translate behavior.  The default is false.
         * @param state if true, reverse translate behavior
         * @since Java 3D 1.3
         */
        public void setReverseTranslate(boolean state) {
            reverseTrans = state;
        }

        /**
         * Property which sets reverse translate behavior.
         * Used by ConfiguredUniverse.
         * @param state array of length 1 containing instance of Boolean
         * @since Java 3D 1.3
         */
        public void ReverseTranslate(Object[] state) {
            if (! (state.length == 1 && state[0] instanceof Boolean))
                throw new IllegalArgumentException
                        ("ReverseTranslate must be Boolean");

            setReverseTranslate(((Boolean)state[0]).booleanValue());
        }

        /**
         * Set reverse rotate behavior.  The default is false.
         * @param state if true, reverse rotate behavior
         * @since Java 3D 1.3
         */
        public void setReverseRotate(boolean state) {
            reverseRotate = state;
        }

        /**
         * Property which sets reverse rotate behavior.
         * Used by ConfiguredUniverse.
         * @param state array of length 1 containing instance of Boolean
         * @since Java 3D 1.3
         */
        public void ReverseRotate(Object[] state) {
            if (! (state.length == 1 && state[0] instanceof Boolean))
                throw new IllegalArgumentException("ReverseRotate must be Boolean");

            setReverseRotate(((Boolean)state[0]).booleanValue());
        }

        /**
         * Set reverse zoom behavior.  The default is false.
         * @param state if true, reverse zoom behavior
         * @since Java 3D 1.3
         */
        public void setReverseZoom(boolean state) {
            reverseZoom = state;
        }

        /**
         * Property which sets reverse zoom behavior.
         * Used by ConfiguredUniverse.
         * @param state array of length 1 containing instance of Boolean
         * @since Java 3D 1.3
         */
        public void ReverseZoom(Object[] state) {
            if (! (state.length == 1 && state[0] instanceof Boolean))
                throw new IllegalArgumentException("ReverseZoom must be Boolean");

            setReverseZoom(((Boolean)state[0]).booleanValue());
        }

        /**
         * Set proportional zoom behavior.  The default is false.
         * @param state if true, use proportional zoom behavior
         * @since Java 3D 1.3
         */
        public synchronized void setProportionalZoom(boolean state) {
            proportionalZoom = state;

            if (state) {
                zoomMul = NOMINAL_PZOOM_FACTOR * zoomFactor;
            }
            else {
                zoomMul = NOMINAL_ZOOM_FACTOR * zoomFactor;
            }
        }

        /**
         * Property which sets proportional zoom behavior.
         * Used by ConfiguredUniverse.
         * @param state array of length 1 containing instance of Boolean
         * @since Java 3D 1.3
         */
        public void ProportionalZoom(Object[] state) {
            if (! (state.length == 1 && state[0] instanceof Boolean))
                throw new IllegalArgumentException
                        ("ProportionalZoom must be Boolean");

            setProportionalZoom(((Boolean)state[0]).booleanValue());
        }
    }