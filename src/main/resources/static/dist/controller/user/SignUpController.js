function addAdminuser() {

    let username = $('#user-name').val();
    let password = $('#user-password').val();
    let cpassword = $('#user-retype-password').val();
<<<<<<< HEAD
<<<<<<< HEAD
    let mobilenumber = $('#mobilenumber').val();
=======
>>>>>>> 4609734 (Initial commit)
=======
    let mobilenumber = $('#mobilenumber').val();
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1

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
<<<<<<< HEAD
<<<<<<< HEAD
                mobilenumber: mobilenumber,
=======
>>>>>>> 4609734 (Initial commit)
=======
                mobilenumber: mobilenumber,
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
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
