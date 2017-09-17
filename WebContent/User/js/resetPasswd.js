
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
                    required: '����������',
                    email: '������һ����Ч������'
                },
                password:
                {
                    required: '����������'
                },
                passwordConfirm:
                {
                    required: '���ٴ���������',
                    equalTo: '�������벻һ��'
                },
                
            },

            // Do not change code below
            errorPlacement: function(error, element)
            {
                error.insertAfter(element.parent());
            }
        });
});
		