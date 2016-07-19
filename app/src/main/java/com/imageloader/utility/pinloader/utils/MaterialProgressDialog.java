package com.imageloader.utility.pinloader.utils;

import android.app.Dialog;
import android.content.Context;

import com.imageloader.utility.pinloader.R;


public class MaterialProgressDialog extends Dialog {
    MaterialProgressBar progress1;

    Context mContext;
    MaterialProgressDialog dialog;
	public MaterialProgressDialog(Context context) {
		super(context);
        this.mContext=context;
	}
	public MaterialProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public MaterialProgressDialog show(CharSequence message) {

	    dialog = new MaterialProgressDialog(mContext,R.style.ProgressDialog);
		dialog.setContentView(R.layout.view_material_progress);

        progress1 = (MaterialProgressBar) dialog.findViewById (R.id.progress1);


        progress1.setColorSchemeResources(R.color.red,R.color.green,R.color.blue,R.color.orange);
		dialog.setCancelable(true);
        if(dialog!= null) {
             dialog.show ();
        }
		return dialog;
	}
    public MaterialProgressDialog dismiss(CharSequence message) {
        if(dialog!=null) {
            dialog.dismiss();
        }

            return dialog;

    }


}
