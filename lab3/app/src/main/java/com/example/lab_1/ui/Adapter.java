package com.example.lab_1.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.lab_1.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private String[] localDataSet;

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public ClickListener clickListener = null;
    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        private final TextView textView;
        private ClickListener clickListener;

        public ViewHolder(View view, ClickListener clickL) {
            super(view);
            this.clickListener = clickL;
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textView1);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (itemPosition >= 0 && clickListener != null) {
                clickListener.onItemClick(itemPosition, v);
            }
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public Adapter(String[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_item, viewGroup, false);

        return new ViewHolder(view, clickListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}