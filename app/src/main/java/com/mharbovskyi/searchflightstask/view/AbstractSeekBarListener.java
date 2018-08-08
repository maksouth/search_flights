package com.mharbovskyi.searchflightstask.view;

import android.widget.SeekBar;

public interface AbstractSeekBarListener extends SeekBar.OnSeekBarChangeListener {

    default void onStartTrackingTouch(SeekBar seekBar) {
        //intentionally left empty
    }

    default void onStopTrackingTouch(SeekBar seekBar) {
        //intentionally left empty
    }
}