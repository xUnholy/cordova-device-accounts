/* eslint-env jasmine */
exports.defineAutoTests = function () {
    describe('Device Accounts Information', function () {
        it('should exist', function () {
            expect(window.deviceaccounts).toBeDefined();
        });

    });
};
