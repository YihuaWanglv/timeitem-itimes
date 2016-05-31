(function(angular) {

  var CategoryFactory = function($resource) {
    return $resource('/manager/category/:categoryId', {
      categoryId: '@categoryId'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  };

  // ItemFactory.$inject = ['$resource'];
  CategoryFactory.$inject = ['$resource'];
  // angular.module("manager.services").factory("Item", ItemFactory);
  angular.module("manager.services").factory("Category", CategoryFactory);
  angular.module('manager.services').factory('Project', function($resource) {
    return $resource('/manager/project/:projectId', {
      projectId: '@projectId'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  });
  angular.module('manager.services').factory('Tag', function($resource) {
    return $resource('/manager/tag/:tagId', {
      tagId: '@tagId'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  });
  angular.module('manager.services').factory('Location', function($resource) {
    return $resource('/manager/location/:locationId', {
      locationId: '@locationId'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  });
}(angular));