package com.dkotenko.pizzasushi.pizzasushi;



import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasketFragment extends Fragment {

    private SQLiteDatabase db;
    private Cursor mCursor;
    private BDHelper sqLiteOpenHelper;

    public BasketFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqLiteOpenHelper = new BDHelper(getActivity());
        db = sqLiteOpenHelper.getWritableDatabase();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        getActivity().setTitle("Корзина");
        TextView sumView = (TextView) view.findViewById(R.id.summa);
        Button orderButton = (Button) view.findViewById(R.id.order_button);

        mCursor = db.query("BASKET", new String[]{"_id", "NAME", "COST"},
                null, null, null, null, null);
        /*CursorAdapter listAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, mCursor,
                new String[]{"NAME"},
                new int[]{android.R.id.text1},
                0);*/
        setSum(mCursor, sumView);
        CursorBasket cursorBasket = new CursorBasket(getActivity(), mCursor, db, sumView);
        ListView listView = (ListView) view.findViewById(R.id.list_order);
        listView.setAdapter(cursorBasket);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCursor.moveToFirst()) {
                    sqLiteOpenHelper.clearBasket();
                    BasketFragment basketFragment = new BasketFragment();

                    FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, basketFragment);
                    ft.addToBackStack(null);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }
            }
        });

        return view;
    }

    private void setSum(Cursor mCurs, TextView sumView) {
        String cost;
        int sum = 0;

        mCurs.moveToFirst();
        while (!mCurs.isAfterLast()) {
            cost = mCurs.getString(mCurs.getColumnIndexOrThrow("COST"));
            sum += Integer.parseInt(cost);
            mCurs.moveToNext();
        }

        sumView.setText("Сумма: " + Integer.toString(sum) + "$");
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Корзина");

        SQLiteOpenHelper sqLiteOpenHelper = new BDHelper(getActivity());
        db = sqLiteOpenHelper.getReadableDatabase();

        mCursor = db.query("PIZZA", new String[]{"_id", "NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "COST", "TO_BASKET"},
                null, null, null, null, null);
        CursorAdapter listAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, mCursor,
                new String[]{"TO_BASKET"},
                new int[]{android.R.id.text1},
                0);
        ListView listView = (ListView) getActivity().findViewById(R.id.listlist);

        listView.setAdapter(listAdapter);
        //setListAdapter(listAdapter);

        //MyCursorAdapter myCursorAdapter = new MyCursorAdapter(getActivity(), mCursor, db);
        //setListAdapter(myCursorAdapter);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
