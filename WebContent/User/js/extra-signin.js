
$(function()
{
    // Validation
    $("#signin-form").validate(
        {
            // Rules for form validation
            rules:
            {
                email:
                {
                    required: true
                },
                
                passwd:
                {
                    required: true,
               
                },
               
            },

            // Messages for form validation
            messages:
            {
            	
                email:
                {
                    required: '请输入邮箱',
                },
                passwd:
                {
                    required: '请输入密码'
                }
            },

            // Do not change code below
            errorPlacement: function(error, element)
            {
                error.insertAfter(element.parent());
            }
        });
});
		