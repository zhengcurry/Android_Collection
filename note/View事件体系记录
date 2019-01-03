###### 参考Android艺术开发探索：
1. View的位置参数：top left right bottom





事件分发：Activity中的dispatchTouchEvent()

       /**
        * Called to process touch screen events.  You can override this to
        * intercept all touch screen events before they are dispatched to the
        * window.  Be sure to call this implementation for touch screen events
        * that should be handled normally.
        */
        public boolean dispatchTouchEvent(MotionEvent ev) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                onUserInteraction();
            }
            //返回false，则向下分发
            if (getWindow().superDispatchTouchEvent(ev)) {
                return true;
            }
            return onTouchEvent(ev);
        }


        /**
         * Abstract base class for a top-level window look and behavior policy.  An
         * instance of this class should be used as the top-level view added to the
         * window manager. It provides standard UI policies such as a background, title
         * area, default key processing, etc.
         *
         * <p>The only existing implementation of this abstract class is
         * android.view.PhoneWindow, which you should instantiate when needing a
         * Window.
         */
         public abstract class Window {...}

PhoneWindow：

    private DecorView mDecor;
    @Override
    public boolean superDispatchTouchEvent(MotionEvent event) {
        return mDecor.superDispatchTouchEvent(event);
    }

    /*
     * Constructor for main window of an activity.
     */
    public PhoneWindow(Context context, Window preservedWindow) {
        this(context);
        // Only main activity windows use decor context, all the other windows depend on whatever
        // context that was given to them.
        mUseDecorContext = true;
        if (preservedWindow != null) {
            mDecor = (DecorView) preservedWindow.getDecorView();
            mElevation = preservedWindow.getElevation();
            mLoadElevation = false;
            mForceDecorInstall = true;
            // If we're preserving window, carry over the app token from the preserved
            // window, as we'll be skipping the addView in handleResumeActivity(), and
            // the token will not be updated as for a new window.
            getAttributes().token = preservedWindow.getAttributes().token;
        }
        // Even though the device doesn't support picture-in-picture mode,
        // an user can force using it through developer options.
        boolean forceResizable = Settings.Global.getInt(context.getContentResolver(),
                DEVELOPMENT_FORCE_RESIZABLE_ACTIVITIES, 0) != 0;
        mSupportsPictureInPicture = forceResizable || context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_PICTURE_IN_PICTURE);
    }

    @Override
    public final View getDecorView() {
        if (mDecor == null || mForceDecorInstall) {
            installDecor();
        }
        return mDecor;
    }
