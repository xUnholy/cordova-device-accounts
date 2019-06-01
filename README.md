# Cordova Device Accounts v2 Plugin

[![Downloads](https://img.shields.io/npm/dt/cordova-device-accounts-v2.svg)](https://img.shields.io/npm/dt/cordova-device-accounts-v2.svg)
[![Maintainability](https://api.codeclimate.com/v1/badges/f0701d13b1b6f8e690f0/maintainability)](https://codeclimate.com/github/xUnholy/cordova-device-accounts/maintainability)
[![Known Vulnerabilities](https://snyk.io/test/github/xUnholy/cordova-device-accounts/badge.svg)](https://snyk.io/test/github/xUnholy/cordova-device-accounts)
[![PayPayl donate button](https://img.shields.io/badge/paypal-donate-yellow.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=2PDW7ZH29RSUU&currency_code=AUD&source=url)
[![License](https://img.shields.io/badge/license-GPL_v3.0-blue.svg)](https://github.com/xUnholy/cordova-device-accounts/blob/master/LICENSE.md)

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

* `DeviceAccounts.get(onSuccess, onFail)` : get all accounts registered on device
* `DeviceAccounts.getByType(type, onSuccess, onFail)` : get all accounts registered on device for requested type
* `DeviceAccounts.getEmails(onSuccess, onFail)` : get all emails registered on device (accounts with 'com.google' type)
* `DeviceAccounts.getEmail(onSuccess, onFail)` : get the first email registered on device or undefined
* `DeviceAccounts.getPermissions(onSuccess, onFail)` : get all emails and request permissions from user.

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

A demo Ionic application can be viewed to see a working implementation of this plugin [Here](https://github.com/xUnholy/cordova-device-accounts-demo).

## Compatibility

The following versions are compatible with this plugin:

* Cordova 3.3 +
* Android 4.4 +

## Donations

This is an open-source project and takes the time and effort of several individuals to maintain. If you like this plugin and use it within your applications, a donation would be greatly appreciated.

[![Paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=2PDW7ZH29RSUU&currency_code=AUD&source=url)
