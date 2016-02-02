package com.gyw.topbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author gyw
 * @version 1.0
 * @time: 2016-1-29 下午4:05:14
 * @fun: topbar
 */
public class TopBar extends RelativeLayout implements View.OnClickListener {

	// 左边的图片
	@Bind(R.id.iv_top_bar_left)
	ImageView mLeftIv;

	// 标题
	@Bind(R.id.tv_top_bar_title)
	TextView mTitleTv;

	// 最右边的图片
	@Bind(R.id.iv_top_bar_right)
	ImageView mRightIv;

	// 如果有提醒小圆点 , 显示
	@Bind(R.id.iv_top_bar_warn)
	ImageView mWarn;

	private Context mContext;

	private int leftSrc;
	private String centerText;
	private int rightSrc;
	private int warnSrc;
	private boolean isShowWarn;
	private boolean isShowLeft;
	private boolean isShowRight;
	private boolean isBack;

	private float centerTextSize;
	private int centerTextColor;

	private View mView;

	private OnTopBarRightClickListener rightListener;

	private OnTopBarLeftClickListener leftListener;

	public TopBar(Context context) {
		this(context, null);
	}

	public TopBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TopBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		this.mContext = context;

		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

		leftSrc = ta.getResourceId(R.styleable.TopBar_leftSrc, R.mipmap.ic_launcher);
		centerText = ta.getString(R.styleable.TopBar_centerText);
		rightSrc = ta.getResourceId(R.styleable.TopBar_rightSrc, R.mipmap.ic_launcher);
		warnSrc = ta.getResourceId(R.styleable.TopBar_warnSrc, R.mipmap.topbar_warn_dot);
		isShowWarn = ta.getBoolean(R.styleable.TopBar_isShowWarn, false);
		isShowLeft = ta.getBoolean(R.styleable.TopBar_isShowLeft, false);
		isShowRight = ta.getBoolean(R.styleable.TopBar_isShowRight, false);
		isBack = ta.getBoolean(R.styleable.TopBar_isBack, false);

		centerTextSize = ta.getDimension(R.styleable.TopBar_centerTextSize, DisplayUtil.dip2px(mContext, 20));
		centerTextColor = ta.getColor(R.styleable.TopBar_centerTextColor, getResources().getColor(android.R.color.white));

		ta.recycle();

		initContentView();
		initView();
		initListener();
	}

	private void initContentView() {
		mView = View.inflate(mContext, R.layout.view_top_bar, this);
		ButterKnife.bind(this);
	}

	// 初始化页面，根据设置项显示
	private void initView() {

		mTitleTv.setTextSize(centerTextSize);
		mTitleTv.setTextColor(centerTextColor);
		mTitleTv.setText(centerText);

		if (isShowLeft) {
			mLeftIv.setVisibility(View.VISIBLE);
			mLeftIv.setImageResource(leftSrc);
		} else {
			mLeftIv.setVisibility(View.INVISIBLE);
		}

		if (isShowRight) {
			mRightIv.setVisibility(View.VISIBLE);
			mRightIv.setImageResource(rightSrc);
		} else {
			mRightIv.setVisibility(View.INVISIBLE);
		}

		showWarn();
	}

	//是否显示提醒
	private void showWarn() {
		if (isShowWarn) {
			mWarn.setImageResource(warnSrc);
			mWarn.setVisibility(View.VISIBLE);
		} else {
			mWarn.setVisibility(View.INVISIBLE);
		}
	}

	private void initListener() {
		mLeftIv.setOnClickListener(this);
		mRightIv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_top_bar_left:
			if (leftListener != null) {
				leftListener.onTopBarLeftClick();
			}
			break;

		case R.id.iv_top_bar_right:
			if (rightListener != null) {
				rightListener.onTopBarRightClick();
			}
			break;
		}
	}

	public void setOnTopBarRightClickListener(OnTopBarRightClickListener rightListener) {
		this.rightListener = rightListener;
	}

	public void setOnTopBarLeftClickListener(OnTopBarLeftClickListener leftListener) {
		this.leftListener = leftListener;
	}

	interface OnTopBarRightClickListener {
		void onTopBarRightClick();
	}

	interface OnTopBarLeftClickListener {
		void onTopBarLeftClick();
	}

	
	public boolean isShowWarn() {
		return isShowWarn;
	}

	//设置是否显示提醒圆点
	public void setShowWarn(boolean isShowWarn) {
		this.isShowWarn = isShowWarn;
		showWarn();
	}
}
