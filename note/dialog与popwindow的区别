# Dialog与PopWindow的区别

> 其中最本质的差别就是：
AlertDialog是非阻塞式对话框：AlertDialog弹出时，后台还可以做事情；
而PopupWindow是阻塞式对话框：PopupWindow弹出时，程序会等待，在PopupWindow退出前，程序一直等待，只有当我们调用了dismiss方法的后，PopupWindow退出，程序才会向下执行。
这两种区别的表现是：AlertDialog弹出时，背景是黑色的，但是当我们点击背景，AlertDialog会消失，证明程序不仅响应AlertDialog的操作，还响应其他操作，其他程序没有被阻塞，这说明了AlertDialog是非阻塞式对话框；PopupWindow弹出时，背景没有什么变化，但是当我们点击背景的时候，程序没有响应，只允许我们操作PopupWindow，其他操作被阻塞。

## 源码分析

### 1. Dialog
从dialog的源码可以看出，一个dialog的创建就是一个window的创建；而Activity也是一个window，所以在onCreate中调用dialog的show方法可以弹出，因为两个window是相互独立的，dialog的弹出不会触发Activity的生命周期；
并且源码中可以看出，dialog默认是点击空白取消的，并位于居中位置。
源码：

    Dialog(@NonNull Context context, @StyleRes int themeResId, boolean createContextThemeWrapper) {
        if (createContextThemeWrapper) {
            if (themeResId == ResourceId.ID_NULL) {
                final TypedValue outValue = new TypedValue();
                context.getTheme().resolveAttribute(R.attr.dialogTheme, outValue, true);
                themeResId = outValue.resourceId;
            }
            mContext = new ContextThemeWrapper(context, themeResId);
        } else {
            mContext = context;
        }

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        final Window w = new PhoneWindow(mContext);
        mWindow = w;
        w.setCallback(this);
        w.setOnWindowDismissedCallback(this);
        w.setOnWindowSwipeDismissedCallback(() -> {
            if (mCancelable) {
                cancel();
            }
        });
        w.setWindowManager(mWindowManager, null, null);
        w.setGravity(Gravity.CENTER);

        mListenersHandler = new ListenersHandler(this);
    }
### 2. PopWindow
从源码来看PopWindow的DecorView是PopupDecorView，PopupDecorView是继承
FrameLayout,所以本质上PopupWindow就是一个View，需要依附一个具体的view，
默认情况下是不能够在onCreate时显示；所以如果想要在界面显示的时候就弹出PopupWindow
需要重写onWindowFocusChanged方法，判断activity完全显示，并且已经拿到焦点，此时
才能进行显示;
否则会报出异常：
`android.view.WindowManager$BadTokenException: Unable to add window -- token null is not valid; is your activity running?`

    private void preparePopup(WindowManager.LayoutParams p) {
        ...
        mDecorView = createDecorView(mBackgroundView);
        mDecorView.setIsRootNamespace(true);
        ...
    }
    /**
     * Wraps a content view in a FrameLayout.
     *
     * @param contentView the content view to wrap
     * @return a FrameLayout that wraps the content view
     */
    private PopupDecorView createDecorView(View contentView) {
        final ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
        final int height;
        if (layoutParams != null && layoutParams.height == WRAP_CONTENT) {
            height = WRAP_CONTENT;
        } else {
            height = MATCH_PARENT;
        }

        final PopupDecorView decorView = new PopupDecorView(mContext);
        decorView.addView(contentView, MATCH_PARENT, height);
        decorView.setClipChildren(false);
        decorView.setClipToPadding(false);

        return decorView;
    }

    private class PopupDecorView extends FrameLayout {
           ...
    }

## 其他
1. PopWindow显示前需要设置宽高；Dialog不需要；
2. PopWindow默认不会响应物理返回键；Dialog会在点击back键时消失；
3. PopWindow不会给界面其他部分添加蒙层；Dialog会；
3. PopWindow没有标题；Dialog默认有标题；


