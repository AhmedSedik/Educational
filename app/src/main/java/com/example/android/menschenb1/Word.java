package com.example.android.menschenb1;

/**
 * Created by zozz on 7/22/2016.
 */
public class Word {

    /** Default word */
    private String mDefaultWord;

    //integer for the image id
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    private int mAudioResourceId;

    public Word(String defaultWord, int imageResourceId, int audioResourceId) {
        mDefaultWord = defaultWord;

        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;


    }
    public int getImageResourceId (){

        return mImageResourceId;
    }
    /**
     * Get the default word.
     */
    public String getDefaultWord() {
        return mDefaultWord;
    }

    public int getAudioResourceId(){
        return mAudioResourceId;
    }

    public boolean hasImage(){

        return mImageResourceId != NO_IMAGE_PROVIDED;

    }
}
