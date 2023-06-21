var LoginModule = (function () {
    // Private variables and functions
    var instance;

    function createInstance() {
        // Private variables and functions
        var validateForm = function (email, password) {
            var errorMessage = "";

            if (email === "") {
                errorMessage = "Please enter your email.";
            }

            if (password === "") {
                errorMessage = "Please enter your password.";
            }

            return errorMessage;
        };

        var loginUser = function (user) {
            $.ajax({
                url: "https://phpbackend-ec7827cfac3d.herokuapp.com/login",
                type: "POST",
                data: user,
                success: function (response) {
                    alert(response.message);
                    window.location.href = "blogdashboard.html";
                },
                error: function (xhr, status, error) {
                    alert("Login failed. Error: " + error);
                }
            });
        };

        // Public interface
        var login = function (email, password) {
            // Perform client-side form validation
            var validationError = validateForm(email, password);
            if (validationError !== "") {
                $("#errorMessage").text(validationError);
                return;
            }

            // Create a user object
            var user = {
                email: email,
                password: password
            };

            // Login the user
            loginUser(user);
        };

        // Publicly accessible methods
        return {
            login: login
        };
    }

    return {
        getInstance: function () {
            if (!instance) {
                instance = createInstance();
            }
            return instance;
        }
    };
})();

// Usage:
var loginModule = LoginModule.getInstance();

$(document).ready(function () {
    // Login button click event handler
    $("#loginButton").click(function () {
        // Get the form values
        var email = $("#emailInput").val();
        var password = $("#passwordInput").val();

        // Login the user using the Singleton module
        loginModule.login(email, password);
    });
});
