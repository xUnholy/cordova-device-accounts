var cordova = require('cordova');

function DeviceAccounts () {}

DeviceAccounts.prototype.get = function (onSuccess, onFail) {
    cordova.exec(onSuccess, onFail, 'DeviceAccounts', 'getDeviceAccounts', []);
};
DeviceAccounts.prototype.getByType = function (type, onSuccess, onFail) {
    cordova.exec(onSuccess, onFail, 'DeviceAccounts', 'getDeviceAccountsByType', [type]);
};
DeviceAccounts.prototype.getPermissions = function (arg1, arg2, arg3) {
    let accountType = 'com.google';
    if (typeof arg1 === 'string') {
        accountType =  arg1;
        return cordova.exec(arg2, arg3, 'DeviceAccounts', 'getPermissions',[accountType]);

    }
    cordova.exec(arg1, arg2, 'DeviceAccounts', 'getPermissions',[accountType]);
};
DeviceAccounts.prototype.getEmails = function (onSuccess, onFail) {
    DeviceAccounts.getByType('com.google', function (accounts) {
        var emails = [];
        for (var i in accounts) {
            emails.push(accounts[i].name);
        }
        onSuccess(emails);
    }, onFail);
};
DeviceAccounts.prototype.getEmail = function (onSuccess, onFail) {
    DeviceAccounts.getEmails(function (emails) {
        if (emails && emails.length > 0) {
            onSuccess(emails[0]);
        } else {
            onSuccess();
        }
    }, onFail);
};

module.exports = new DeviceAccounts();
