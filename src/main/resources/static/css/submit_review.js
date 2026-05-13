let selectedRating = 0;

document.querySelectorAll('.star').forEach(star => {
    star.addEventListener('click', function() {
        selectedRating = parseInt(this.getAttribute('data-value'));
        document.getElementById('rating').value = selectedRating;
        document.querySelectorAll('.star').forEach((s, i) => {
            s.classList.toggle('active', i < selectedRating);
        });
    });
});

function toggleFields() {
    const type = document.getElementById('reviewType').value;
    if (type === 'BIKE') {
        document.getElementById('bikeFields').style.display = 'block';
        document.getElementById('rideFields').style.display = 'none';
    } else {
        document.getElementById('bikeFields').style.display = 'none';
        document.getElementById('rideFields').style.display = 'block';
    }
}

function submitReview() {
    const type     = document.getElementById('reviewType').value;
    const username = document.getElementById('username').value.trim();
    const comment  = document.getElementById('comment').value.trim();

    if (!username || !comment || selectedRating === 0) {
        showMessage('Please fill in all fields and select a rating.', 'error');
        return;
    }

    const params = new URLSearchParams();
    params.append('type',     type);
    params.append('username', username);
    params.append('rating',   selectedRating);
    params.append('comment',  comment);
    params.append('userId',   'U' + Date.now());

    if (type === 'BIKE') {
        params.append('bikeModel', document.getElementById('bikeModel').value);
        params.append('bikeId',    document.getElementById('bikeId').value);
    } else {
        params.append('routeName', document.getElementById('routeName').value);
        params.append('rideId',    document.getElementById('rideId').value);
    }

    fetch('/api/reviews', { method: 'POST', body: params })
        .then(r => r.json())
        .then(data => showMessage(data.message, 'success'))
        .catch(() => showMessage('Error submitting review.', 'error'));
}

function showMessage(msg, type) {
    const el = document.getElementById('message');
    el.textContent = msg;
    el.className = 'message ' + type;
}