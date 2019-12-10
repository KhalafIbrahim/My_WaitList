package com.example.my_waitlist.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_waitlist.Data.WaitlistContract;
import com.example.my_waitlist.R;


public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.Guestviewholder>
{
    Context context;
    Cursor cursor;

    public GuestAdapter(Context context,Cursor cursor)
    {
        this.context = context;
        this.cursor = cursor;
    }


    @NonNull
    @Override
    public Guestviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item , parent , false);
        return new  Guestviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Guestviewholder holder, int position)
    {
        if (!cursor.moveToPosition(position))
        {
            return;
        }

        String name = cursor.getString(cursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME));
        String size = cursor.getString(cursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE));

        long id = cursor.getLong(cursor.getColumnIndex(WaitlistContract.WaitlistEntry._ID));

        holder.name.setText(name);
        holder.size.setText(size);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount()
    {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (cursor != null)
        {
            cursor.close();
        }

        cursor = newCursor;

        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    public class Guestviewholder extends RecyclerView.ViewHolder
    {
        TextView name,size;

        public Guestviewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name_result);
            size = (TextView) itemView.findViewById(R.id.size_result);
        }
    }
}
