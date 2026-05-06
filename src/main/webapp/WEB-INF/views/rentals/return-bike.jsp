<%@ <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Return Bike</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<%@ include file="../navbar.jsp" %>

<div class="container mt-5">
    <div class="card shadow p-4" style="max-width: 600px; margin: auto;">
        <h2 class="mb-4 text-center">🔄 Return / Update Rental</h2>

        <form action="/rental/update" method="post">

            <div class="mb-3">
                <label class="form-label">Rental ID</label>
                <input type="text" name="rentalId" class="form-control"
                       value="${rentalId}" readonly/>
            </div>

            <div class="mb-3">
                <label class="form-label">Return Date</label>
                <input type="date" name="endDate" class="form-control" required/>
            </div>

            <div class="mb-3">
                <label class="form-label">Status</label>
                <select name="status" class="form-select" required>
                    <option value="RETURNED">RETURNED</option>
                    <option value="CANCELLED">CANCELLED</option>
                    <option value="ACTIVE">ACTIVE</option>
                </select>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-warning">Update Rental</button>
                <a href="/rental/view" class="btn btn-outline-secondary">Back to List</a>
            </div>

        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>