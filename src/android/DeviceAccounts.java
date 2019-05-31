package com.xunholy.cordova.deviceaccounts;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.accounts.AccountManager;
import android.accounts.Account;
import java.util.List;
import java.util.ArrayList;

/**
 * See :
 * http://developer.android.com/reference/android/accounts/AccountManager.html
 * http://developer.android.com/reference/android/accounts/Account.html
 */
public class DeviceAccounts extends CordovaPlugin {
  public static final int VISIBILITY_VISIBLE = 1;
  public static final int RESULT_OK = -1;
  public static final int RESULT_CANCELED = 0;
  private CallbackContext callbackContext;

    /**
     * Constructor.
     */
    public DeviceAccounts() {
    }

  /**
   * Sets the context of the Command. This can then be used to do things like
   * get file paths associated with the Activity.
   *
   * @param cordova The context of the main Activity.
   * @param webView The CordovaWebView Cordova is running in.
   */
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
  }

  /**
   * Executes the request and returns PluginResult.
   *
   * @param action            The action to execute.
   * @param args              JSONArray of arguments for the plugin.
   * @param callbackContext   The callback id used when calling back into JavaScript.
   * @return                  True if the action was valid, false if not.
   */
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    this.callbackContext = callbackContext;
    if("getDeviceAccounts".equals(action)){
      List<Account> accounts = getAccounts(null);
      JSONArray result = formatResult(accounts);
      this.callbackContext.success(result);
      return true;
    } else if("getDeviceAccountsByType".equals(action)){
      final String type = args.getString(0);
      List<Account> accounts = getAccounts(type);
      JSONArray result = formatResult(accounts);
      this.callbackContext.success(result);
      return true;
    } else if("getPermissions".equals(action)){
        final String accountType = args.getString(0);
      getPermissions(accountType);
      return true;
    } else {
      this.callbackContext.error("DeviceAccounts." + action + " is not a supported function.");
      return false;
    }
  }

  private List<Account> getAccounts(String type) {
    AccountManager manager = AccountManager.get(cordova.getActivity().getApplicationContext());
    Account[] accounts = manager.getAccounts();
    List<Account> Accounts = new ArrayList<Account>();
    for(Account account : accounts){
      if(type == null || account.type.equals(type)){
        Accounts.add(account);
      }
    }
    return Accounts;
  }

  private JSONArray formatResult(List<Account> accounts) throws JSONException {
    JSONArray Accounts = new JSONArray();
    AccountManager manager = AccountManager.get(cordova.getActivity().getApplicationContext());
    for (Account a : accounts) {
      JSONObject Account = new JSONObject();
      Account.put("type", a.type);
      Account.put("name", a.name);
      Accounts.put(Account);
    }
    return Accounts;
  }

  private void getPermissions(String accountType) {
    AccountManager manager = AccountManager.get(cordova.getActivity().getApplicationContext());
    Intent accountIntent = manager.newChooseAccountIntent(null, null, new String[]{accountType}, null, null, null, null);
    cordova.setActivityResultCallback(this);
    cordova.getActivity().startActivityForResult(accountIntent, VISIBILITY_VISIBLE);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    if(requestCode == VISIBILITY_VISIBLE && resultCode == RESULT_OK) {
      this.callbackContext.success("SUCCESS");
    } else {
      this.callbackContext.error("Error Getting Permissions, Code: " + resultCode);
    }
  }

}
