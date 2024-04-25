function lagring() {

    let feilTeller = 0;

    let innFilmNavnet = $("#select").val();
    if ((innFilmNavnet === "")) {
        feilTeller++;
        alert("Må velges en film");
    }

    let innAntall = $("#antall").val();
    if ((innAntall === 0) || (innAntall === "")) {
        feilTeller++;
        alert("antallbillett må fylles")
    }

    let innFornavn = $("#fornavn").val();
    if (innFornavn === "") {
        feilTeller++;
        alert("Fornavn må fylles")
    }

    let innEtternavn = $("#etternavn").val();
    if (innEtternavn === "") {
        feilTeller++;
        alert(" etternavnet må fylles")
    }

    let innTelefonnr = $("#telefon").val();
    if (innTelefonnr === "") {
        feilTeller++;
        alert("telefon må fylles")
    }

    let innEpost = $("#epost").val();
    if (innEpost === "") {
        feilTeller++;
        alert("e-post må fylles")
    }
    if (feilTeller === 0) {
        const ticket = {
            filmnavn: innFilmNavnet,
            antall: innAntall,
            fornavn: innFornavn,
            etternavn: innEtternavn,
            telefon: innTelefonnr,
            epost: innEpost
        };

        $.post("/save", ticket, function () {
            hentAlle();
        });
        fjerner();
    }
}



function hentAlle() {
    $.get("/vis", function (data) {
        formater(data)
    });
}

function formater(tickets) {

    let ut = "<table class = 'table table-striped mt-4' style='background-color: bisque'>" +
        "<tr>" +
        "<th>Film Navn</th>" +
        "<th>Antall</th>" +
        "<th>Fornavn</th>" +
        "<th>Etternavn</th>" +
        "<th>Telefon Nummer</th>" +
        "<th>E-post</th>" +
        "</tr>";


    for (let t of tickets) {
        ut +=
            "<tr>" +
            "<td>" + t.id + "</td>" +
            "<td>" + t.filmnavn + "</td>" +
            "<td>" + t.antall + "</td>" +
            "<td>" + t.fornavn + "</td>" +
            "<td>" + t.etternavn + "</td>" +
            "<td>" + t.telefon + "</td>" +
            "<td>" + t.epost + "</td>" +
            "</tr>"
    }
    ut += "</table>"
    $("#skriv").html(ut);
}

function slett() {
    $.get("/slett", function () {
        hentAlle();
    });
}

function fjerner() {
    $("#select").val("")
    $("#antall").val("")
    $("#fornavn").val("")
    $("#etternavn").val("")
    $("#telefon").val("")
    $("#epost").val("")
}