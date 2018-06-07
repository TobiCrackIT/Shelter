package com.example.oluwatobig.shelter.data;

import android.provider.BaseColumns;

import static android.os.FileObserver.CREATE;

/**
 * Created by oluwatobig on 21-May-18.
 */

public final class ShelterContract {

    public static final class ShelterEntry implements BaseColumns{

        public final static String TABLE_NAME="PETS";
        public final static String PET_ID=BaseColumns._ID;
        public final static String PET_NAME="NAME";
        public final static String BREED="BREED";
        public final static String GENDER="GENDER";
        public final static String WEIGHT="WEIGHT";

        public final static int MALE=1;
        public final static int FEMALE=2;
        public final static int UNKNOWN=0;

    }
}
