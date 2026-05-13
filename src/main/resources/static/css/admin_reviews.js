let reviews = [];

function loadAdmin() {
    fetch('/api/reviews')
        .then(r => r.json())
        .then(data => {
            reviews = data;
            renderAdmin(data);
        });
}

function renderAdmin(data) {
    document.getElementById('adminTableBody').innerHTML = data.map(r => `
    <tr>
      <td>${r.reviewId}</td>
      <td>${r.username}</td>
      <td>${r.type}</td>
      <td>${'★'.repeat(r.rating)}</td>
      <td>${r.comment}</td>
      <td>
        <button class="btn-edit" onclick="openEdit('${r.reviewId}', ${r.rating}, '${r.comment.replace(/'/g,"\\'")}')">Edit</button>
        <button class="btn-delete" onclick="deleteReview('${r.reviewId}')">Delete</button>
      </td>
    </tr>
  `).join('');
}

function openEdit(id, rating, comment) {
    document.getElementById('editReviewId').value = id;
    document.getElementById('editRating').value = rating;
    document.getElementById('editComment').value = comment;
    document.getElementById('editModal').style.display = 'flex';
}

function closeModal() {
    document.getElementById('editModal').style.display = 'none';
}

function saveEdit() {
    const id = document.getElementById('editReviewId').value;
    const params = new URLSearchParams();
    params.append('rating',  document.getElementById('editRating').value);
    params.append('comment', document.getElementById('editComment').value);

    fetch('/api/reviews/' + id, { method: 'PUT', body: params })
        .then(() => {
            closeModal();
            loadAdmin();
        });
}

function deleteReview(id) {
    if (!confirm('Delete this review?')) return;
    fetch('/api/reviews/' + id, { method: 'DELETE' })
        .then(() => loadAdmin());
}

loadAdmin();