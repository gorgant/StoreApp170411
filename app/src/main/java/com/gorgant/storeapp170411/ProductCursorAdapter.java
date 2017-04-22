package com.gorgant.storeapp170411;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.gorgant.storeapp170411.Data.StoreContract.ProductEntry;

/**
 * Created by Ludeyu on 4/17/2017.
 */

/**
 * {@link ProductCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of product data as its data source. This adapter knows
 * how to create list items for each row of product data in the {@link Cursor}.
 */

public class ProductCursorAdapter extends CursorAdapter {

    public static final String LOG_TAG = ProductCursorAdapter.class.getSimpleName();

    /**
     * Constructs a new {@link ProductCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public ProductCursorAdapter(Context context, Cursor c) {

        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new (list item) view to hold the data pointed to by cursor.  Not data is set (or
     * bound) to the views yet.
     *
     * @param context Interface to application's global information (app context)
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }


    /**
     * This method binds the product data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current product can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView
     * @param context Interface to application's global information (app context)
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.product_name);
        TextView quantityTextView = (TextView) view.findViewById(R.id.product_quantity);
        TextView priceTextView = (TextView) view.findViewById(R.id.product_price);

        int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);

        String productName = cursor.getString(nameColumnIndex);
        final int productQuantity = cursor.getInt(quantityColumnIndex);
        double productPrice = cursor.getDouble(priceColumnIndex);

        nameTextView.setText(productName);
        quantityTextView.setText(String.valueOf(productQuantity));
        priceTextView.setText(String.valueOf(productPrice));

        Button sellButton = (Button) view.findViewById(R.id.sell_button);

        int idColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
        final int currentProductId = cursor.getInt(idColumnIndex);

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(LOG_TAG,"Sell button click registered! Starting quantity is: " + productQuantity);

                //Decrease Quantity here tapping database?
                if (productQuantity > 0){
                    int newProductQuantity = productQuantity -1;
                    ContentValues values = new ContentValues();
                    values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, newProductQuantity);

                    Log.i(LOG_TAG, "Current content ID is: " + currentProductId);
                    Uri currentProductUri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, currentProductId);
                    Log.i(LOG_TAG, "Current content Uri is: " + currentProductUri);
                    int rowsAffected = context.getContentResolver().update(currentProductUri, values, null, null);
                }
            }
        });


    }
}
