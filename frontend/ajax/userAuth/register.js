$(document).ready(function () {
    // Register button click event handler
    $("#registerButton").click(function () {
        // Get the form values
        var firstName = $("#firstNameInput").val();
        var email = $("#emailInput").val();
        var password = $("#passwordInput").val();
        var lastName = $("#lastNameInput").val();

        // Perform client-side form validation using the ValidationModule
        var validationError = ValidationModule.validateForm(firstName, email, password, lastName);
        if (validationError !== "") {
            alert(validationError);
            return;
        }

        // Create a user object
        var user = {
            firstName: firstName,
            lastName: "",
            email: email,
            password: password
        };

        // Send an AJAX POST request
        $.ajax({
            url: "http://localhost/skincare/backend/register",
            type: "POST",
            data: user,
            success: function (response) {
                alert(response.message);
                window.location.href = "login.html";
            },
            error: function (xhr, status, error) {
                alert("Registration failed. Error: " + error);
            }
        });
    });
});