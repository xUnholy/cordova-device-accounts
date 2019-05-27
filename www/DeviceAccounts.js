var cordova = require('cordova');

function DeviceAccounts() {
}

DeviceAccounts.prototype.get = function (onSuccess, onFail) {
    cordova.exec(onSuccess, onFail, 'DeviceAccounts', 'getDeviceAccounts', []);
};
DeviceAccounts.prototype.getByType = function (type, onSuccess, onFail) {
    cordova.exec(onSuccess, onFail, 'DeviceAccounts', 'getDeviceAccountsByType', [type]);
};
DeviceAccounts.prototype.getPermissions = function (onSuccess, onFail) {
    cordova.exec(onSuccess, onFail, 'DeviceAccounts', 'getPermissions', ['com.google']);
};
DeviceAccounts.prototype.getPermissionsByType = function (type, onSuccess, onFail) {
    return cordova.exec(onSuccess, onFail, 'DeviceAccounts', 'getPermissions', [type]);
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
