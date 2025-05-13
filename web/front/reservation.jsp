<jsp:include page="header.jsp" />
<div class="container mt-4 content">
    <h2 class="text-center mb-4">Rechercher une Réservation</h2>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="input-group mb-3">
                <input type="text" id="reservationId" class="form-control" placeholder="Entrez l'ID de la réservation">
                <button class="btn btn-primary" onclick="searchReservation()">Rechercher</button>
            </div>
            <div id="reservationResult" class="mt-3"></div>
        </div>
    </div>
</div>
    
<script type="text/javascript">
    function searchReservation() {
        let reservationId = document.getElementById("reservationId").value;
        let resultDiv = document.getElementById("reservationResult");
        
        if (reservationId.trim() === "") {
            resultDiv.innerHTML = "<p class='text-danger'>Veuillez entrer un ID de réservation valide.</p>";
            return;
        }
        
        
        resultDiv.innerHTML = `<div class='alert alert-success'>Réservation trouvée : <strong>ID ${reservationId}</strong></div>`;
    }
</script>

<jsp:include page="footer.jsp" />