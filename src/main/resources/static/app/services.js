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
  // angular.module("myApp.services").factory("Item", ItemFactory);
  angular.module("myApp.services").factory("Category", CategoryFactory);
  angular.module('myApp.services').factory('Project', function($resource) {
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
  angular.module('myApp.services').factory('Tag', function($resource) {
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
  angular.module('myApp.services').factory('Item', function($resource) {
    return $resource('/items/:itemId', {
      itemId: '@itemId'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  });
  angular.module('myApp.services').factory('Location', function($resource) {
    return $resource('/manager/location:locationId', {
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