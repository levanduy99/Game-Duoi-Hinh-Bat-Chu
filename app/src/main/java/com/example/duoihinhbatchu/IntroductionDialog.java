package com.example.duoihinhbatchu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class IntroductionDialog extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder (getActivity ());
        builder.setTitle ("Introduction")
                .setMessage ("Game Bắt Chữ: Bạn chắc hẳn không xa lạ với game truyền hình" +
                        " \"Đuổi Hình Bắt Chữ\" đâu nhỉ? Thật thú vị khi tự mình khám phá những " +
                        "tầng ý nghĩa đặc sắc, hài hước, vui nhộn, bất ngờ... " +
                        "của các câu chữ ẩn đằng sau những hình ảnh quen thuộc.")
                .setPositiveButton ("OK", new DialogInterface.OnClickListener ( ) {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create ();
    }
}
