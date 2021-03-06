package com.cqvip.moblib.sychildlibHD;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.cqvip.moblib.sychildlibHD.Book;

/**
 * 
 * @author luojiang
 * 
 */
public class BookAdapter extends BaseAdapter {
	private Context context;
	private List<Book> lists;
//	private List<ImageLoader>illist;
//	private RequestQueue mQueue;
	private ImageLoader mImageLoader;

	public BookAdapter(Context context) {
		this.context = context;
	}

	public BookAdapter(Context context, List<Book> lists) {
		this.context = context;
		this.lists = lists;
	}

	public BookAdapter(Context context, List<Book> lists, ImageLoader imageLoader) {
		this.context = context;
		this.lists = lists;
		this.mImageLoader=imageLoader;
	}

	public List<Book> getLists() {
		return lists;
	}

	
	@Override
	public int getCount() {
		if (lists != null) {
			return lists.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	
	@Override
	public long getItemId(int position) {
		if (this.getCount() > 0 && position < (this.getCount())) {
			return position;
		} else {
			return 0;
		}
	}


	public void addMoreData(List<Book> moreStatus) {
		this.lists.addAll(moreStatus);
		this.notifyDataSetChanged();
	}

	static class ViewHolder {
		TextView title;
		TextView author;
		TextView publisher;
		NetworkImageView img;
		TextView u_abstract;
		// Button btn_comment,btn_item_result_search_share,favorite;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null
				|| convertView.findViewById(R.id.linemore) != null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_book_search, null);
			holder = new ViewHolder();

			holder.title = (TextView) convertView
					.findViewById(R.id.re_name_txt);
			holder.author = (TextView) convertView
					.findViewById(R.id.re_author_txt);
			holder.publisher = (TextView) convertView
					.findViewById(R.id.re_addr_txt);
			holder.img = (NetworkImageView) convertView.findViewById(R.id.re_book_img);
			holder.img.setDefaultImageResId(R.drawable.defaut_book);
			holder.img.setErrorImageResId(R.drawable.defaut_book);
			holder.u_abstract = (TextView) convertView
					.findViewById(R.id.txt_abst);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String author = context.getResources().getString(R.string.author_item);
		String publish = context.getResources()
				.getString(R.string.publish_item);
		String describe = context.getResources().getString(
				R.string.describe_item);
		Book book = lists.get(position);
		holder.title.setText(book.getTitle());
		holder.author.setText(Html.fromHtml(author+book.getAuthor()));
		holder.publisher.setText(Html.fromHtml(publish + book.getPublisher()+","+book.getPublishyear()));
		holder.u_abstract.setText(Html.fromHtml(describe + book.getU_abstract()));

		String url=book.getCover_path();
//        if(!TextUtils.isEmpty(url)){
        	holder.img.setImageUrl(url, mImageLoader);
//        } else {
//            holder.img.setImageResource(R.drawable.defaut_book);
//        }
		return convertView;
	}

}
