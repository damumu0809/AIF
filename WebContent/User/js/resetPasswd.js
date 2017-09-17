
$(function()
{
    // Validation
    $("#resetPasswd-form").validate(
        {
            // Rules for form validation
            rules:
            {
               
                email:
                {
                    required: true,
                    email: true
                },
                passwd:
                {
                    required: true,
                    minlength: 8,
                    maxlength: 20
                },
                passwordConfirm:
                {
                    required: true,
                    minlength: 8,
                    maxlength: 20,
                    equalTo: '#password'
                },
                code:
                {
                	required:true
               	}
               
            },

            // Messages for form validation
            messages:
            {
            	
                email:
                {
                    required: '请输入邮箱',
                    email: '请输入一个有效的邮箱'
                },
                password:
                {
                    required: '请输入密码'
                },
                passwordConfirm:
                {
                    required: '请再次输入密码',
                    equalTo: '两次密码不一致'
                },
                
            },

            // Do not change code below
            errorPlacement: function(error, element)
            {
                error.insertAfter(element.parent());
            }
        });
});
		