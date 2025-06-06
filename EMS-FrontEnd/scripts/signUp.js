

$(".btn-signup").click(function (e) {
    e.preventDefault(); // THIS is the key line!
    alert("Cliekd")

    let email = $(".email").val();
    let name = $(".firstName").val() + " " + $(".lastName").val();
    let password = $(".password").val();
    let phone = $(".phone").val();

    const userData = {
        email: email,
        name: name,
        password: password,
        phone: phone
    };

    $.ajax({
        url: "http://localhost:8080/EMS/signup",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(userData),
        success: function () {
            Swal.fire({
            icon: "success",
            title: "Success",
            text: "Successfully Saved!",
            });

        },
        error: function (err) {
            Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Something went wrong!",
            });

        }
    });
});

// Login

$(".btn-login").click(() => {

    let email = $(".email").val();
    let password = $(".password").val();

    $.ajax({
        url: "http://localhost:8080/EMS/signin",
        method: "GET",
        success: function(resp) {
            resp.map((data) => {
                if(data.email === email && data.password === password) {
                    window.location.href = "index.html";
                }
            })
        }
    })
});

