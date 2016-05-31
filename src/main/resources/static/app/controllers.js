(function(angular) {


  var ItemController = function($scope, $http, $filter, Item, Category, Project, Location, Tag) {
    $scope.page = 1;
    $scope.nomore = 0;
    $scope.newDate = new Date();
    $scope.newItem = initNewItem();
    $scope.showing;
    $scope.showEditLayer = 0;
    $scope.edittingItem;
    $scope.ing = {
      selected: {}
    };
    $http.get('/items/list',{params: {page:$scope.page}})
    .success(function(data, status, headers, config){
      $scope.items = (data&&data.items)?data.items:[];
    })
    .error(function(data, status, headers, config){
      $scope.items = [];
      alert('load failed');
    });
    var listItem = function() {
      $scope.page++;
      $http.get('/items/list',{params: {page:$scope.page}})
      .success(function(data, status, headers, config){
        // $scope.items = (data&&data.items)?data.items:[];
        if (data&&data.items) {
          if (data.items.length > 0) {
            $scope.items = $scope.items.concat(data.items);
          } else {
            $scope.nomore = 1;
          }
        } else {
          $scope.nomore = 1;
        }
      })
      .error(function(data, status, headers, config){
        $scope.items = [];
        alert('load failed');
      });
    }
    function initNewItem() {
      // var _item = {};
      // _item.item = '';
      return {item:''};
    }
    $scope.listNext = function() {
      
      listItem();
    }
    $scope.showItemDate = function(_date) {
      var flag = false;
      if (_date) {
        if (!$scope.showing) {
          $scope.showing = _date;
          flag = true;
        } else {
          if (_date !== $scope.showing) {
            flag = true;
            $scope.showing = _date;
          }
        }
      }
      return flag;
    }
    
    $scope.createItem = function(_item) {
      new Item({
        date: $scope.newDate,
        item: _item.item,
        categoryId: _item.category.categoryId||null,
        projectId: _item.project.projectId||null,
        locationId: _item.location.locationId||null,
        // tags: _item.tag.tagName||'',
        tags: _item.tags||'',
        categoryName: _item.category.categoryName||'',
        projectName: _item.project.projectName||'',
        locationName: _item.location.location||'',
        duration: _item.duration
      }).$save(function(item){
        // $scope.items.push(item);//unshift
        $scope.items.unshift(item);
      });
      $scope.newItem = initNewItem();
    }
    
    $scope.editItem = function(item) {
      item.editting = !item.editting;
      $scope.showEditLayer = 1;
      $scope.edittingItem = item;
      // console.log(item);
      $scope.edittingItem.category = findSelected('category', item.categoryId);
      $scope.edittingItem.project = findSelected('project', item.projectId);
      $scope.edittingItem.location = findSelected('location', item.locationId);
      // $scope.edittingItem.tag = findSelected('tag', item.tagId);
      // $scope.edittingItem.category = item.categoryName;
      //item.$update();
      // $scope.edittingItem.dateTime = new Date(item.date);
      $scope.edittingItem.date = new Date(item.date);
    }
    function findSelected(_type, _id) {
      if (_type && _type == 'category') {
        for (var i = 0; i < $scope.categorys.length; i++) {
          if ($scope.categorys[i].categoryId == _id) return $scope.categorys[i];
        }
      }
      if (_type && _type == 'project') {
        for (var i = 0; i < $scope.projects.length; i++) {
          if ($scope.projects[i].projectId == _id) return $scope.projects[i];
        }
      }
      if (_type && _type == 'location') {
        for (var i = 0; i < $scope.locations.length; i++) {
          if ($scope.locations[i].locationId == _id) return $scope.locations[i];
        }
      }
      if (_type && _type == 'tag') {
        for (var i = 0; i < $scope.tags.length; i++) {
          if ($scope.tags[i].tagId == _id) return $scope.tags[i];
        }
      }
      return null;
    }
    $scope.updateItem = function(item) {
      item.categoryId = item.category.categoryId||null;
      item.projectId = item.project.projectId||null;
      item.locationId = item.location.locationId||null;
      item.categoryName = item.category.categoryName||'';
      item.projectName = item.project.projectName||'';
      item.locationName = item.location.location||'';

      $http.put('/items/' + item.itemId, item)
      .success(function (data, status, headers, config) {
        console.log('ok!');
        item.editting = !item.editting;
        $scope.edittingItem = null;
        $scope.showEditLayer = 0;
      })
      .error(function (data, status, header, config) {
        console.log('not ok!');
      });
    }
    $scope.deleteItem = function(item) {
      $http.delete('/items/' + item.itemId, {}).success(function (data, status) {
        $scope.items.splice($scope.items.indexOf(item),1);
      });
    }
    $scope.changeButtonFlag = function(item) {
      item.editting = !item.editting;
    }
    $scope.closeLayer = function(edit) {
      //item.$update();
      edit.editting = !edit.editting;
      $scope.edittingItem = null;
      $scope.showEditLayer = 0;
    }
    $scope.checkConfig = function() {
      if (!$scope.categorys || $scope.categorys.length==0) return true;
      if (!$scope.projects || $scope.projects.length==0) return true;
      if (!$scope.locations || $scope.locations.length==0) return true;
      return false;
    }

    // resource
    Category.query(function(response) {
      $scope.categorys = response ? response : [];
    });
    Project.query(function(response){
      $scope.projects = response ? response : [];
    });
    Location.query(function(response) {
      $scope.locations = response ? response : [];
    });
    Tag.query(function(response) {
      $scope.tags = response ? response : [];
    });

  }
 
  // angular.module("myApp.controllers").controller("ItemController"
  //   , [
  //     '$scope', '$http', '$filter', 'Item', 'Category', 'Project', 'Location', 'Tag'
  //     , ItemController
  //   ]
  // );
  ItemController.$inject = ['$scope', '$http', '$filter', 'Item', 'Category', 'Project', 'Location', 'Tag'];
  angular.module("myApp.controllers").controller("ItemController", ItemController);
}(angular));