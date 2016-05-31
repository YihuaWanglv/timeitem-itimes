ModuleReport = (function() {
	window.charts = {};
	var api = {
		loadConfig: '/data/user/config',
		category: '/report/category',
		categoryTime:'/report/time/category',
		project: '/report/project',
		projectTime: '/report/time/project',
		location: '/report/location',
		locationTime: '/report/time/location',
		updateChartConfig: '/data/user/config/chart/update'
	}
	function initReports() {
		$.ajax({
			type : "GET",
			url : api.loadConfig,
			data : null,
			success : function(data) {
				console.log(data);
				if (data) {
					for (var i = 0; i < data.reportConfigs.length; i++) {
						var config = data.reportConfigs[i];
						if (config && config.enabled) {
							showReport(config);
						}
					}
					initStatusAndAction();
				}
			}
		});
	}
	function showReport(_config) {
		$('#chart_container').append(getContainer(_config.key, _config));
		loadReport(_config.key, _config.type, _config.title, _config.muti);
		
	}
	function initStatusAndAction() {
		$('.btn-chart-config').on('click', function(){
			var formkey = $(this).attr('data-id');
			if (formkey) {
				$_editField = $('#'+formkey+' .form-edit');
				if ($_editField.hasClass('hidden')) {
					$_editField.removeClass('hidden');
				} else {
					$_editField.addClass('hidden');
				}
			}
		});
		$('.btn-chart-config-save').on('click', function(){
			var formkey = $(this).attr('data-id');
			if (formkey) {
				$form = $('#'+formkey);
				// var data = JSON.stringify($form.serializeArray());
				var data = getFormData($form);
				var enable = $('#'+formkey+' input[name="enable"]').is(':checked')?1:0;
				data.enable = enable;
				console.log(data);
				updateChartConfig(data);
				// $form.submit();
			}
		});
	}
	function getFormData($form){
	    var unindexed_array = $form.serializeArray();
	    var indexed_array = {};
	    $.map(unindexed_array, function(n, i){
	        indexed_array[n['name']] = n['value'];
	    });
	    // $("#ans").is(':checked') ? 1 : 0;
	    return indexed_array;
	}
	function getContainer(_id, _config) {
		return '<div class="jumbotron">'
		    +'		<div class="container">'
		    +'			<div class="row">'+getChartConfigHtml(_config)+'</div>'
		    +'  		<div class="row">'
		    +'    			<div>'
		    +'      			<canvas id="'+_id+'"></canvas>'
		    +'    			</div>'
		    +'  		</div>'
		    +'		</div>'
			+'	</div>';
	}
	function getChartConfigHtml(_config) {
		var checkedStatus = _config.enabled ? 'checked="checked"' : '';
		var key = _config.key;
		var formKey = 'form-'+key;
		var html = '<form class="form-inline" id="'+formKey+'">'+
		  '<input type="hidden" name="key" value="'+key+'">'+
          '<div class="col-lg-12">'+
            '<div class="form-group col-lg-1">'+
              '<button class="btn btn-default btn-chart-config" type="button" title="Config" data-id="'+formKey+'">'+
                '<span class="glyphicon glyphicon-cog"></span>'+
              '</button>'+
            '</div>'+
            '<div class="form-group form-edit col-lg-2 hidden">'+
              '<label>'+
                '<input type="checkbox" name="enable" class="form-inline-checkbox" '+checkedStatus+'>Show This Chart'+
              '</label>'+
            '</div>'+
            '<div class="form-group form-edit col-lg-4 hidden">'+
              '<label for="exampleInputEmail1">Chart Title: </label>'+
              '<input type="text" name="title" class="form-control" placeholder="Chart Title" value="'+_config.title+'">'+
            '</div>'+
            '<div class="form-group form-edit col-lg-1 hidden">'+
              '<button type="button" class="btn btn-default btn-chart-config-save" data-id="'+formKey+'">Save</button>'+
            '</div>'+
          '</div> '+
        '</form>';
        return html;
	}
	function loadReport(_key, _type, _title, _muti) {
		var _url = api[_key];
		$.ajax({
	        type: "GET",
	        url: _url,
	        data: null,
	        success: function(data) {
	        	if (data) {
	        		var configData = initData(_type, data, _muti);
			        var ctx = document.getElementById(_key).getContext("2d");
			        var config = initConfig(_type, configData, null, _title);
		            window.charts[_key] = new Chart(ctx, config);
	        	}
	        }
	    });
	}
	function updateChartConfig(_data) {
		$.ajax({
	        type: "POST",
	        url: api.updateChartConfig,
	        data: _data,
	        success: function(data) {
	        	if (data && data.status) {
	        		alert('saved');
	        		syncChartUpdate(_data);
	        	} else {
	        		alert('save failed!');
	        	}
	        }
	    });
	}
	function syncChartUpdate(_data) {
		
	}
	function initData(_type, _data, _muti) {
		var label = [];
        var datasets = [];
        label = _data.data.labels;
      	var ds = _data.data.dataset;
      	for (var key in ds) {
      		var dataset = {};
      		dataset.label = key;
      		dataset.data = ds[key];
      		if (_type == 'bar') {
      			dataset.backgroundColor = "rgba(220,220,220,0.5)";
      		} else if (_type == 'line') {
      			dataset.fill = false;
	      		dataset.borderColor = Lib_Random.randomColor(0.4);
	      		dataset.backgroundColor = Lib_Random.randomColor(0.5);
	      		dataset.pointBorderColor = Lib_Random.randomColor(0.7);
	      		dataset.pointBackgroundColor = Lib_Random.randomColor(0.5);
	      		dataset.pointBorderWidth = 1;
      		}
      		datasets.push(dataset);
      	}
		var data = {
            labels: label,
            datasets: datasets
        };
      	return data;
	}
	function initConfig(_type, _data, _option, _title) {
		var c = {
			type: _type||'bar',
            data: _data||[],
            options: _option||{
                elements: {
                    rectangle: {
                        borderWidth: 2,
                        borderColor: 'rgb(0, 255, 0)',
                        borderSkipped: 'bottom'
                    }
                },
                responsive: true,
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: _title||'TimeItem时间统计报告'
                }
            }
		};
		return c;
	}
	
	return {
		getContainer:getContainer,
		showReport: showReport,
		initReports: initReports
	}
})();