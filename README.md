# cordova-device-accounts

Cordova plugin to get the device accounts on Android

## Install

```bash
ionic cordova plugin add https://github.com/xunholy/cordova-device-accounts.git --save
```

## Required Permissions

Requires the following permissions:

* **android.permission.GET_ACCOUNTS**
* **android.permission.GET_ACCOUNTS_PRIVILEGED**

```typescript
import { AndroidPermissions } from '@ionic-native/android-permissions';

constructor(private androidPermissions: AndroidPermissions) { }

...

return this.androidPermissions.requestPermissions(
      [
        this.androidPermissions.PERMISSION.GET_ACCOUNTS,
        this.androidPermissions.PERMISSION.GET_ACCOUNTS_PRIVILEGED
      ])
      .then(permission => console.log('Permissions Granted: ' + JSON.stringify(permission)))
      .catch(error => console.error(error));
```

## Methods

- `DeviceAccounts.get(onSuccess, onFail)` : get all accounts registered on device
- `DeviceAccounts.getByType(type, onSuccess, onFail)` : get all accounts registered on device for requested type
- `DeviceAccounts.getEmails(onSuccess, onFail)` : get all emails registered on device (accounts with 'com.google' type)
- `DeviceAccounts.getEmail(onSuccess, onFail)` : get the first email registered on device or undefined
- `DeviceAccounts.getPermissions(onSuccess, onFail)` : get all emails and request permissions from user.

Note: since Android 8.0.0+ device accounts are not visible to the application. Users must grant access to email accounts they wish to be visible to the application.

## Example

```typescript
import { DeviceAccounts } from '@ionic-native/device-accounts';

constructor(private deviceAccounts: DeviceAccounts) { }

...

this.deviceAccounts.get()
  .then(accounts => console.log(accounts))
  .catch(error => console.error(error));
```

## Compatibility

The following versions are compatible with this plugin:

* Cordova 3.3 +
* Android 4.4 +