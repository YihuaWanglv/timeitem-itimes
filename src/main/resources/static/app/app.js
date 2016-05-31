(function(angular) {
  angular.module("myApp.controllers", []);
  angular.module("myApp.services", []);
  angular.module("myApp", ["ngResource", 'ngSanitize', 'ui.select', "myApp.controllers", "myApp.services"]);
}(angular));