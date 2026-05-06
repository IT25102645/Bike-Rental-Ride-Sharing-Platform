<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rental History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<%@ include file="../navbar.jsp" %>

<div class="container mt-5">

    <!-- Success Message -->
    <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        ✅ <%= request.getAttribute("success") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
    <% } %>

    <!-- Info Message -->
    <% if (request.getAttribute("info") != null) { %>
    <div class="alert alert-info alert-dismissible fade show" role="alert">
        ℹ️ <%= request.getAttribute("info") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
    <% } %>

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>📋 Rental History</h2>
        <a href="/rental/create" class="btn btn-success">+ Rent a Bike</a>
    </div>

    <!-- Search Bar -->
    <div class="card shadow mb-4 p-3">
        <form action="/rental/search" method="get" class="row g-2 align-items-center">
            <div class="col-auto">
                <label class="col-form-label fw-bold">Search by User ID:</label>
            </div>
            <div class="col-auto">
                <input type="text" name="userId" class="form-control"
                       placeholder="Enter User ID"
                       value="<%= request.getAttribute("searchId") != null ? request.getAttribute("searchId") : "" %>"/>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Search</button>
                <a href="/rental/view" class="btn btn-outline-secondary">Clear</a>
            </div>
        </form>
    </div>

    <!-- Rentals Table -->
    <div class="card shadow">
        <div class="card-body">
            <table class="table table-bordered table-hover text-center">
                <thead class="table-dark">
                <tr>
                    <th>Rental ID</th>
                    <th>User ID</th>
                    <th>Bike ID</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Status</th>
                    <th>Total Fee (LKR)</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<String[]> rentals = (List<String[]>) request.getAttribute("rentals");
                    if (rentals != null && !rentals.isEmpty()) {
                        for (String[] r : rentals) {
                %>
                <tr>
                    <td><%= r[0] %></td>
                    <td><%= r[1] %></td>
                    <td><%= r[2] %></td>
                    <td><%= r[3] %></td>
                    <td><%= r[4] %></td>
                    <td>
                        <% if (r[5].equals("ACTIVE")) { %>
                        <span class="badge bg-success">ACTIVE</span>
                        <% } else if (r[5].equals("RETURNED")) { %>
                        <span class="badge bg-secondary">RETURNED</span>
                        <% } else { %>
                        <span class="badge bg-danger"><%= r[5] %></span>
                        <% } %>
                    </td>
                    <td><%= r[6] %></td>
                    <td>
                        <!-- Update button -->
                        <a href="/rental/update?rentalId=<%= r[0] %>"
                           class="btn btn-warning btn-sm">Return</a>

                        <!-- Delete button -->
                        <form action="/rental/delete" method="post"
                              style="display:inline;"
                              onsubmit="return confirm('Delete this rental?')">
                            <input type="hidden" name="rentalId" value="<%= r[0] %>"/>
                            <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="8" class="text-muted">No rentals found.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>