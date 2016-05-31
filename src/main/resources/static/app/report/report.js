$(function() {
	
	ModuleReport.initReports();



	serialize = function(obj) {
	  var str = [];
	  for(var p in obj)
	    if (obj.hasOwnProperty(p)) {
	      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	    }
	  return str.join("&");
	}
	var data = {};
	data.__fcno = "fsdfdsfdsfdsfdsfdfr";
	data.__fcts = "1234567890";
	data.id=1;
	data.name='iyihua';
	var md5v = hex_md5(serialize(data));

	console.log(md5v)

	data.__fccy = md5v.toUpperCase();
	$.ajax({
        type: "GET",
        url: "/data/user/test?",
        data: data,
        success: function(data) {
        	console.log(data);
        }
    });

});