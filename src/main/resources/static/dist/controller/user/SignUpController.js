function addAdminuser() {

    let username = $('#user-name').val();
    let password = $('#user-password').val();
    let cpassword = $('#user-retype-password').val();
    let mobilenumber = $('#mobilenumber').val();

    if (password==cpassword) {
        $.ajax({
            type: "POST",
            url: baseURL + 'user/create',
            processData: false,
            cache: false,
            enctype: 'multipart/form-data',
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                username: username,
                password: password,
                mobilenumber: mobilenumber,
                type: "Admin"
            }),
            error: function (res) {

                showToast(TOAST_TYPE.DANGER, 'Error !', 'Something went wrong !');
            },
            success: function (res) {

                showToast(TOAST_TYPE.SUCCESS, 'Success !', 'User Added Successfully !');
                location.reload();

            }
        }).done(function (data) {
            console.log(data);


        });
    } else {
        showToast(TOAST_TYPE.DANGER, 'Error !', 'Something went wrong !');
    }
}
