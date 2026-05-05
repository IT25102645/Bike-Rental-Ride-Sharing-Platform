<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rent a Bike</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4" style="max-width: 600px; margin: auto;">
        <h2 class="mb-4 text-center">🚲 Rent a Bike</h2>

        <!-- Show error if bike not available -->
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <form action="/rental/create" method="post">

            <div class="mb-3">
                <label class="form-label">User ID</label>
                <input type="text" name="userId" class="form-control"
                       placeholder="Enter your User ID" required/>
            </div>

            <div class="mb-3">
                <label class="form-label">Bike ID</label>
                <input type="text" name="bikeId" class="form-control"
                       placeholder="Enter Bike ID" required/>
            </div>

            <div class="mb-3">
                <label class="form-label">Rental Type</label>
                <select name="rentalType" class="form-select" required>
                    <option value="">-- Select Type --</option>
                    <option value="hourly">Hourly (LKR 150/hr)</option>
                    <option value="daily">Daily (LKR 1000/day)</option>
                </select>
            </div>

            <div class="mb-3">
                <label class="form-label">End Date</label>
                <input type="date" name="endDate" class="form-control" required/>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">Rent Bike</button>
                <a href="/rental/view" class="btn btn-outline-secondary">View All Rentals</a>
            </div>

        </form>
    </div>
</div>

</body>
</html>