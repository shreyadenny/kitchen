package com.example.kitchen;

import android.provider.BaseColumns;

public class IngredientContract {

    // To prevent someone from accidentally instantiating the contract class,
        // give it an empty constructor.
        private IngredientContract() {}

        /**
         * Inner class that defines constant values for the pets database table.
         * Each entry in the table represents a single pet.
         */
        public static final class IngredientEntry implements BaseColumns {

            /** Name of database table for ingredients */
            public final static String TABLE_NAME = "ingredients";

            /**
             * Unique ID number for the ingredient (only for use in the database table).
             *
             * Type: INTEGER
             */
            public final static String _ID = BaseColumns._ID;

            public final static String COLUMN_INGREDIENT_NAME ="name";

            public final static String COLUMN_INGREDIENT_MEASUREMENT = "unit";

            public final static String COLUMN_INGREDIENT_QUANTITY = "quantity";

            public static final int MEASUREMENT_KG = 0;
            public static final int MEASUREMENT_GM = 1;
            public static final int MEASUREMENT_L = 2;
            public static final int MEASUREMENT_ML = 3;
            public static final int MEASUREMENT_DOZEN = 4;
            public static final int MEASUREMENT_PACKETS = 5;
        }

    }



