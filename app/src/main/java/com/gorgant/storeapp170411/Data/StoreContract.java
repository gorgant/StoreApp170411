package com.gorgant.storeapp170411.Data;

/**
 * Created by Ludeyu on 4/11/2017.
 */

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


/**
 * API Contract for the Store app.
 */

public final class StoreContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private StoreContract() {}

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.gorgant.storeapp170411";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.gorgant.storeapp170411/products/ is a valid path for
     * looking at store data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_PRODUCTS = "products";

    /**
     * Inner class that defines constant values for the store database table.
     * Each entry in the table represents a single product.
     */
    public static final class ProductEntry implements BaseColumns {

        /** The content URI to access the product data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of products.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single product.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        /** Name of database table for store */
        public final static String TABLE_NAME = "products";

        /**
         * Unique ID number for the product (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the product.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_NAME ="prodname";

        /**
         * Quantity of the product.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_QUANTITY = "quantity";

        /**
         * Price of the product.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_PRICE = "price";

        /**
         * Indicates if the product is in stock.
         *
         * The only possible values are {@link #INSTOCK_FALSE} or {@link #INSTOCK_TRUE}.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_INSTOCK = "instock";

        /**
         * Possible values for {@link #COLUMN_PRODUCT_INSTOCK}.
         */
        public static final int INSTOCK_FALSE = 0;
        public static final int INSTOCK_TRUE = 1;

        /**
         * Boolean version of whether or not the given product is in stock.
         */
        public static boolean isInStock(int instock) {
            if (instock == INSTOCK_FALSE) {
                return false;
            }
            return true;
        }

        /**
         * The image associated with the product
         *
         * TYPE: BLOB
         */
        public static String COLUMN_PRODUCT_IMAGE = "image";

    }
}
