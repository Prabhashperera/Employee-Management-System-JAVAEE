

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
            alert("Account created successfully! 🎉");
        },
        error: function (err) {
            console.error("Oops! Something went wrong 😢", err);
        }
    });
});

