package cn.hym.superlib.manager;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.umeng.socialize.bean.SHARE_MEDIA;

import cn.hym.superlib.R;

/**
 * 用来分享的 Dialog ;
 */
public class ShareDialog extends DialogFragment {
    private RecyclerView mShareRv;
    private TextView cancelTv;


    private String[] titles = {"微信朋友圈", "微信", "QQ", "微博"};


    public static ShareDialog newInstance(String webUrl) {

        Bundle args = new Bundle();
        args.putString("webUrl", webUrl);
        ShareDialog fragment = new ShareDialog();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dilaog_share_platform, container, false);
        mShareRv = rootView.findViewById(R.id.share_rv);
        cancelTv = rootView.findViewById(R.id.share_cancel);

        mShareRv.setLayoutManager(new GridLayoutManager(container.getContext(), 4));
        ShareAdapter adapter = new ShareAdapter();
        mShareRv.setAdapter(adapter);


        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

    }


    class ShareAdapter extends RecyclerView.Adapter<ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_share_platform, parent, false);
            ViewHolder holder = new ViewHolder(rootView);

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            for (int i = 0; i < titles.length; i++) {
                String title = titles[position];
                holder.shareTv.setText(title);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SHARE_MEDIA platform = null;

                    switch (position) {
                        case 0:
                            platform = SHARE_MEDIA.WEIXIN_CIRCLE;
                            break;
                        case 1:
                            platform = SHARE_MEDIA.WEIXIN;
                            break;
                        case 2:
                            platform = SHARE_MEDIA.QQ;
                            break;
                        case 3:
                            platform = SHARE_MEDIA.SINA;
                            break;
                    }

//                    OtherPlatformManager.getInstance()
//                            .share(getActivity(),  null,"觅拍",);
                }
            });
        }


        @Override
        public int getItemCount() {
            return titles.length;
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView shareIv;
        TextView shareTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shareIv = itemView.findViewById(R.id.share_iv);
            shareTv = itemView.findViewById(R.id.share_tv);
        }
    }


}
