'use strict';

/**
 * @ngdoc overview
 * @name roomAdminApp
 * @description
 * # roomAdminApp
 *
 * Main module of the application.
 */
angular
  .module('roomAdminApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
