$().ready(function() {
	var registForm = $('#regist-form').validate({
        errorClass: "invalid",
        submitHandler: function() {
          // 在这里提交,,,ie7下验证不过时也会执行
          	var params = {};
            params.password = $('#password').val();
            params.name = $('#name').val();
            params.email = $('#email').val();
          	$.ajax({
	            url: '/user',
	            data: params,
	            type:"POST",
	            success: function (data) {
	                if (data.status) {
	                	layer.msg('success!');
                        localStorage.email = params.email;
                        localStorage.username = params.name;
                        localStorage.index = data.data.index;
	                	window.location.href='/view/sign-up-finished.html';	                	
	                } else {
	                	layer.msg('Failed to create your account!' + data.message);
	                }
	            }
	        }).always(function() {

	        });
	        return false;
        },
        //改变错误提示的位置
        errorPlacement: function(error, element) {
            error.insertAfter(element.parent())
        },
        rules: {
            name: {
                minlength: 3,
                // nameStringCheck: true,
                maxlength: 64,  //需求上要求 50
                // checkUnique: true,
                required: true
            },
            email: {
                email: true,
                minlength: 6,
                maxlength: 64,
                // emailStringCheck: true,
                // checkUnique: true,
                required: true
            },
            password: {
                maxlength: 32,
                // passwordStringCheck:true,
                minlength: 6,
                required: true
            }
        }
    });
});