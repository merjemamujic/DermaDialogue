$(document).ready(function () {
    // Function to display posts
    function displayPosts(posts) {
        var container = $('#blogPostsContainer');

        // Loop through each post and create HTML elements
        posts.forEach(function (post) {
            var card = $('<div class="card mb-3">');
            var cardBody = $('<div class="card-body">');
            var cardTitle = $('<h5 class="card-title">').text(post.blog_name);
            var cardText = $('<p class="card-text">').text(post.description);
            var editButton = $('<button class="btn btn-primary">').text('Edit');
            var deleteButton = $('<button class="btn btn-danger">').text('Delete');

            // Add data attribute to store post ID
            editButton.attr('data-post-id', post.id);
            deleteButton.attr('data-post-id', post.id);

            // Append elements to the card body
            cardBody.append(cardTitle, cardText, editButton, deleteButton);
            card.append(cardBody);
            container.append(card);
        });

        // Apply grid layout with four posts per row
        var cards = container.find('.card');
        var rows = Math.ceil(cards.length / 4);
        for (var i = 0; i < rows; i++) {
            var startIndex = i * 4;
            var endIndex = startIndex + 4;
            cards.slice(startIndex, endIndex).wrapAll('<div class="row mb-3"></div>');
        }
    }

    // Function to handle post deletion
    function deletePost(postId) {
        // Display a confirmation dialog
        var confirmation = confirm("Are you sure you want to delete this post?");

        // If the user confirms the deletion
        if (confirmation) {
            $.ajax({
                type: 'DELETE',
                url: 'http://localhost/skincare/backend/delete_post/' + postId,
                success: function (response) {
                    // Reload the posts after successful deletion
                    window.location.reload();
                    fetchPosts();
                },
                error: function (xhr, status, error) {
                    // Handle error response
                    console.error(error);
                    alert('Failed to delete post');
                }
            });
        }
    }

    // Function to handle post editing
    function editPost(postId, blogName, description) {
        // Set the form values
        $('#editPostId').val(postId);
        $('#editTitle').val(blogName);
        $('#editDescription').val(description);

        // Show the modal
        $('#editModal').modal('show');
    }

    // Function to update a post
    function updatePost(postId, blogName, description) {
        var postData = {
            blogName: blogName,
            description: description
        };

        $.ajax({
            type: 'PUT',
            url: 'http://localhost/skincare/backend/edit_post/' + postId,
            data: JSON.stringify(postData),
            success: function () {
                // Hide the modal
                $('#editModal').modal('hide');
                // Refresh the page
                window.location.reload()
            },
            error: function (xhr, status, error) {
                // Handle error response
                console.error(error);
                alert('Failed to update post');
            }
        });
    }

    // Function to fetch all posts
    function fetchPosts() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost/skincare/backend/get_all_posts',
            success: function (response) {
                displayPosts(response);
            },
            error: function (xhr, status, error) {
                // Handle error response
                console.error(error);
                alert('Failed to fetch posts');
            }
        });
    }

    // Event delegation for delete button clicks
    $('#blogPostsContainer').on('click', '.btn-danger', function () {
        var postId = $(this).data('post-id');
        deletePost(postId);
    });

    // Event delegation for edit button clicks
    $('#blogPostsContainer').on('click', '.btn-primary', function () {
        var postId = $(this).data('post-id');
        var blogName = $(this).siblings('.card-title').text();
        var description = $(this).siblings('.card-text').text();
        editPost(postId, blogName, description);
    });

    // Event listener for save changes button in the modal
    $('#saveChanges').click(function () {
        var postId = $('#editPostId').val();
        var blogName = $('#editTitle').val();
        var description = $('#editDescription').val();
        updatePost(postId, blogName, description);
    });

    // Fetch all posts on page load
    fetchPosts();
});
