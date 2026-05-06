<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bike Rental System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<%@ include file="navbar.jsp" %>

<div class="container mt-5">

    <!-- Hero Section -->
    <div class="text-center mb-5">
        <h1 class="display-4 fw-bold">🚲 Bike Rental System</h1>
        <p class="lead text-muted">Rent a bike easily and manage your rentals in one place.</p>
        <a href="/rental/create" class="btn btn-primary btn-lg me-2">Rent a Bike</a>
        <a href="/rental/view" class="btn btn-outline-secondary btn-lg">View Rentals</a>
    </div>

    <!-- Feature Cards -->
    <div class="row g-4">

        <div class="col-md-4">
            <div class="card shadow h-100 text-center p-3">
                <div class="card-body">
                    <h1>➕</h1>
                    <h5 class="card-title fw-bold mt-2">Rent a Bike</h5>
                    <p class="card-text text-muted">Choose hourly or daily rental. Pick your bike and start riding.</p>
                    <a href="/rental/create" class="btn btn-primary">Rent Now</a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow h-100 text-center p-3">
                <div class="card-body">
                    <h1>📋</h1>
                    <h5 class="card-title fw-bold mt-2">View Rentals</h5>
                    <p class="card-text text-muted">See all current and past rentals. Search by User ID.</p>
                    <a href="/rental/view" class="btn btn-success">View History</a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow h-100 text-center p-3">
                <div class="card-body">
                    <h1>🔄</h1>
                    <h5 class="card-title fw-bold mt-2">Return a Bike</h5>
                    <p class="card-text text-muted">Return your bike and update the rental status easily.</p>
                    <a href="/rental/view" class="btn btn-warning">Return Bike</a>
                </div>
            </div>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>