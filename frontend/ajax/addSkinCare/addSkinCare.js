$(document).ready(function () {
    // Handle form submission
    $('#addPostForm').submit(function (event) {
        event.preventDefault(); // Prevent the form from submitting normally

        // Get form data
        var blogName = $('#blogName').val();
        var description = $('#description').val();
        var type = $('#type').val();

        // Create the data object
        var data = {
            blogName: blogName,
            description: description,
            type: type
        };

        // Send AJAX request
        $.ajax({
            type: 'POST',
            url: 'https://phpbackend-ec7827cfac3d.herokuapp.com/add_post', // Update the URL based on your server setup
            data: data,
            success: function (response) {
                // Handle success response
                alert(response.message);
                window.location.href = "blogdashboard.html"
            },
            error: function (xhr, status, error) {
                // Handle error response
                console.error(error);
                alert('Failed to add post');
            }
        });
    });
});
