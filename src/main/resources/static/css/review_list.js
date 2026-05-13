let allReviews = [];

function loadReviews() {
    fetch('/api/reviews')
        .then(r => r.json())
        .then(data => {
            allReviews = data;
            renderReviews(data);
        });
}

function filterReviews(type) {
    document.querySelectorAll('.filter-btn').forEach(b => b.classList.remove('active'));
    event.target.classList.add('active');

    const filtered = type === 'ALL' ? allReviews : allReviews.filter(r => r.type === type);
    renderReviews(filtered);
}

function renderReviews(reviews) {
    const stars = n => '★'.repeat(n) + '☆'.repeat(5 - n);

    if (reviews.length === 0) {
        document.getElementById('reviewList').innerHTML = '<p style="color:#aaa">No reviews yet.</p>';
        return;
    }

    document.getElementById('reviewList').innerHTML = reviews.map(r => `
    <div class="review-card">
      <div class="stars">${stars(r.rating)}</div>
      <div>
        <span class="username">${r.username}</span>
        <span class="type-badge badge-${r.type.toLowerCase()}">
          ${r.type === 'BIKE' ? r.bikeModel || 'Bike' : r.routeName || 'Ride'}
        </span>
      </div>
      <div class="comment">${r.comment}</div>
      <div class="meta">${r.date}</div>
    </div>
  `).join('');
}

loadReviews();