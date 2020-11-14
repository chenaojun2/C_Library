package org.chen.cibrary.log;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.chen.cibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于将log显示到界面上
 */
public class ChViewPrinter implements ChLogPrinter {

    private RecyclerView recyclerView;
    private LogAdapter adapter;
    public ChViewPrinterProvider viewProvider;

    public ChViewPrinter(Activity activity) {
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        this.recyclerView = new RecyclerView(activity);
        adapter = new LogAdapter(LayoutInflater.from(recyclerView.getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        viewProvider = new ChViewPrinterProvider(rootView,recyclerView);
    }



    @Override
    public void print(@NonNull ChLogConfig config, int level, String tag, @NonNull String printString) {
        adapter.addItem(new ChLogMo(System.currentTimeMillis(),level,tag,printString));
        recyclerView.smoothScrollToPosition(adapter.getItemCount()-1);
    }

    private static class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {

        private LayoutInflater inflater;
        private List<ChLogMo> logs = new ArrayList<>();

        public LogAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        void addItem(ChLogMo logItem) {
            logs.add(logItem);
            notifyItemInserted(logs.size() - 1);
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.chlog_item, parent, false);
            return new LogViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            ChLogMo logItem = logs.get(position);
            int color = getHighlightColor(logItem.level);
            holder.tagView.setTextColor(color);
            holder.messageView.setTextColor(color);

            holder.tagView.setText(logItem.getFlattened());
            holder.messageView.setText(logItem.log);
        }

        private int getHighlightColor(int logLevel) {
            int highLight;
            switch (logLevel) {
                case ChLogType.V:
                    highLight = 0xffbbbbbb;
                    break;
                case ChLogType.D:
                    highLight = 0xffffffff;
                    break;
                case ChLogType.I:
                    highLight = 0xff6a8759;
                    break;
                case ChLogType.W:
                    highLight = 0xffbbb529;
                    break;
                case ChLogType.E:
                    highLight = 0xffff6b68;
                    break;
                default:
                    highLight = 0xffffff00;
                    break;
            }
            return highLight;
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }
    }


    private static class LogViewHolder extends RecyclerView.ViewHolder {

        TextView tagView;
        TextView messageView;


        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.tag);
            messageView = itemView.findViewById(R.id.message);
        }
    }
}
