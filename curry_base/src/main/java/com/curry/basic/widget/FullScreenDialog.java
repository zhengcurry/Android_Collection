package com.curry.basic.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.curry.basic.utils.SoftKeyboardUtil;
import com.heinqi.curry_base.R;

/**
 * @Desc : 全屏dialog
 * @Author : curry
 * @Date : 2018/9/13
 * <p>
 * TODO：弹出软键盘有显示问题（状态栏、）
 */

@Deprecated
public class FullScreenDialog extends Dialog {
    SearchListener searchListener;

    public FullScreenDialog(@NonNull Context context) {
        super(context, R.style.dialog);
        setOwnerActivity((Activity) context);
//        setContentView(R.layout.dialog_search);

//        init();
    }

    /*public void init() {
        EditText editText = findViewById(R.id.et_search);
        findViewById(R.id.tv_search_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchListener.getText(editText.getText().toString());
                    dismiss();
                    return true;
                }
                return false;
            }
        });
    }*/

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

        getWindow().getDecorView().setPadding(0, 0, 0, 0);

        getWindow().setAttributes(layoutParams);
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);
        // 隐藏软键盘
        SoftKeyboardUtil.hideSoftKeyboard(getOwnerActivity());
    }

    public FullScreenDialog setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
        return this;
    }

    /**
     * @Desc : 将输入的文字回调
     * @Author : curry
     * @Date : 2018/9/13
     */
    public interface SearchListener {
        void getText(String text);
    }
}
