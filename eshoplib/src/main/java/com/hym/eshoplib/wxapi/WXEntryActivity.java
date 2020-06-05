package com.hym.eshoplib.wxapi;


import com.umeng.weixin.callback.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity {

//	IWXAPI api = WXAPIFactory.createWXAPI(this, "wx9a527a0f8528e1e5", true);
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//		api.registerApp("wx9a527a0f8528e1e5");
//        api.handleIntent(getIntent(), this);
//    }
//
//	@Override
//	protected void onNewIntent(Intent intent) {
//		super.onNewIntent(intent);
//
//		setIntent(intent);
//        api.handleIntent(intent, this);
//	}
//
//	@Override
//	public void onReq(BaseReq req) {
//		Toast.makeText(this, "openid = " + req.openId, Toast.LENGTH_SHORT).show();
//
//		switch (req.getType()) {
//		case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
//			goToGetMsg();
//			break;
//		case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
//			goToShowMsg((ShowMessageFromWX.Req) req);
//			break;
//		case ConstantsAPI.COMMAND_LAUNCH_BY_WX:
//			Toast.makeText(this, "未安装微信", Toast.LENGTH_SHORT).show();
//			break;
//		default:
//			break;
//		}
//	}
//
//	@Override
//	public void onResp(BaseResp resp) {
//		Toast.makeText(this, "openid = " + resp.openId, Toast.LENGTH_SHORT).show();
//
//		if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
//			Toast.makeText(this, "code = " + ((SendAuth.Resp) resp).code, Toast.LENGTH_SHORT).show();
//		}
//
//		int result = 0;
//
//		switch (resp.errCode) {
//		case BaseResp.ErrCode.ERR_OK:
//			result = R.string.errcode_success;
//			break;
//		case BaseResp.ErrCode.ERR_USER_CANCEL:
//			result = R.string.errcode_cancel;
//			break;
//		case BaseResp.ErrCode.ERR_AUTH_DENIED:
//			result = R.string.errcode_deny;
//			break;
//		default:
//			result = R.string.errcode_unknown;
//			break;
//		}
//
//		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
//        finish();
//	}
//
//	private void goToGetMsg() {
////		Intent intent = new Intent(this, GetFromWXActivity.class);
////		intent.putExtras(getIntent());
////		startActivity(intent);
//		finish();
//	}
//
//	private void goToShowMsg(ShowMessageFromWX.Req showReq) {
//		WXMediaMessage wxMsg = showReq.message;
//		WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;
//
//		StringBuffer msg = new StringBuffer();
//		msg.append("description: ");
//		msg.append(wxMsg.description);
//		msg.append("\n");
//		msg.append("extInfo: ");
//		msg.append(obj.extInfo);
//		msg.append("\n");
//		msg.append("filePath: ");
//		msg.append(obj.filePath);
//
////		Intent intent = new Intent(this, ShowFromWXActivity.class);
////		intent.putExtra(Constants.ShowMsgActivity.STitle, wxMsg.title);
////		intent.putExtra(Constants.ShowMsgActivity.SMessage, msg.toString());
////		intent.putExtra(Constants.ShowMsgActivity.BAThumbData, wxMsg.thumbData);
////		startActivity(intent);
//		finish();
//	}
}