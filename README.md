[![Maintainability](https://api.codeclimate.com/v1/badges/f0701d13b1b6f8e690f0/maintainability)](https://codeclimate.com/github/xUnholy/cordova-device-accounts/maintainability)
[![Known Vulnerabilities](https://snyk.io/test/github/xUnholy/cordova-device-accounts/badge.svg)](https://snyk.io/test/github/xUnholy/cordova-device-accounts)
[![License](https://img.shields.io/badge/license-GPL_v3.0-blue.svg)](https://github.com/xUnholy/cordova-device-accounts/blob/master/LICENSE.md)

# Cordova Device Accounts v2 Plugin

Cordova plugin to get the device accounts on Android.

## Install

If you're using Ionic you can use the following commands:

```bash
ionic cordova plugin add cordova-device-accounts-v2 --save
npm install @ionic-native/device-accounts
```

Note: This is the official maintained repository for the "Device Account" plugin, view the Ionic documentation [Here](https://ionicframework.com/docs/native/device-accounts).

Otherwise use the following command:

```bash
cordova plugin add cordova-device-accounts-v2 --save
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
