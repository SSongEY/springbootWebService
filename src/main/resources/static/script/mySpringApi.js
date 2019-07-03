'use strict';

let mySpringApi = (() => {

    const _MAP_PREFIX = '/maps/';
    const _USERS_PREFIX = '/users/';
    const _HISTORY_PREFIX = '/historys';

    function _findAll(data, callback) {
        $.ajax({
            url: _MAP_PREFIX,
            method: 'get',
            data: data,
            contentType: "application/json",
            dataType: 'json'
        }).then((response) => {
            callback(null, response);
        }).catch((response) => {
            let responseJSON = response.responseJSON;
            let message = responseJSON.message;
            callback(message, responseJSON);
        });
    }

    function _myHistory(param, callback) {
        $.ajax({
            url: _USERS_PREFIX+param,
            method: 'get'
        }).then((response) => {
            callback(null, response);
        }).catch((response) => {
            let responseJSON = response.responseJSON;
            let message = responseJSON.message;
            callback(message, responseJSON);
        });
    }

    function _mostKeywords(callback) {
        $.ajax({
            url: _HISTORY_PREFIX,
            method: 'get'
        }).then((response) => {
            callback(null, response);
        }).catch((response) => {
            let responseJSON = response.responseJSON;
            let message = responseJSON.message;
            callback(message, responseJSON);
        });
    }

    return {
        findAll: _findAll,
        myHistory: _myHistory,
        mostKeywords: _mostKeywords
    }
})();

