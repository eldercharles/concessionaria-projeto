
var user = localStorage.getItem("usuario");
if (user) {
   user = JSON.parse(user);
    $("#usuarioL").html(user.nome);

}else {
    window.location = 'http://localhost/concessionaria/login.html';
    // window.location = '/concessionaria';
}

getFabricantes();

function getFabricantes() {
    $.ajax({                                            // busca o documento
        type: 'GET', // tipo requisicao   
        url: 'http://localhost:8081/concessionaria-api/rest/concessionaria-api/fabricantes', // endereco ws
        // dataType: 'json', // tipo de trafego de dados
        success: function (fabricantes) {
            console.log(fabricantes);
            fabricantes.forEach(fabricante => {
                $('#marca_veiculo').append($('<option>', {
                    value: fabricante,
                    text: fabricante
                }));
            });
        }
    });
}
$('#marca_veiculo').change(function () {
    var fabricante = $('#marca_veiculo').val();
    $('#modelo_veiculo').empty();
    $.ajax({                                            // busca o documento
        type: 'GET', // tipo requisicao   
        url: 'http://localhost:8081/concessionaria-api/rest/concessionaria-api/modelos/' + fabricante, // endereco ws
        // dataType: 'json', // tipo de trafego de dados
        success: function (modelos) {
            console.log(modelos);

            modelos.forEach(modelo => {
                $('#modelo_veiculo').append($('<option>', {
                    value: modelo,
                    text: modelo
                }));
            });
        }
    });
});
$('#btn-pesquisar').click(function (e) {
    e.preventDefault();
    //  var cod_veiculo = $('#cod_veiculo').val();
    var modelo = $('#modelo_veiculo').val();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8081/concessionaria-api/rest/concessionaria-api/modelo/' + modelo,
        dataType: 'json',
        success: function (veiculos) {
            $("#tab_veiculos").empty();
            // $('#loading').hide();
            console.log(veiculos);
            veiculos.map(c =>
                $("#tab_veiculos").append(`
                  <tr id="${c.cod_veiculo}">
                     <td class="td1">${c.cod_veiculo}</td> 
                     <td class="td1">${c.fabricante}</td> 
                     <td class="td1">${c.modelo}</td> 
                     <td class="td1">${c.placa}</td>   
                     <td class="td1">${c.anoFabricacao}</td> 
                     <td class="td1">${c.anoModelo}</td>
                     <td class="td1">${c.tipoCombustivel}</td>
                     <td class="td1">${c.valor}</td>
                     <td class="td1">${c.cor}</td>  
                     <td class="td1">${c.data}</td>         
                     <td> <input type="image" src="Img/delete.png" onclick="excluir(${c.cod_veiculo})"
                     </td>  
                     <td> <input  data-toggle="modal" data-target="#exampleModal" type="image" src="Img/pencil.png" onclick="getAlterar(${c.cod_veiculo})"
                     </td>  
                      </tr>
                      `));
        },
    });
});
function excluir(cod_veiculo) {
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8081/concessionaria-api/rest/concessionaria-api/' + cod_veiculo,
        dataType: 'json',     // Para solicitação entre domínios, isto significa solicitação para diferentes domínios 
        success: function (data) {
            console.log(data);
            var id = $("#" + cod_veiculo).remove();
            alert('Veículo removido com sucesso!');
        }
    });
}
function getAlterar(cod_veiculo) {
    if (cod_veiculo) {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8081/concessionaria-api/rest/concessionaria-api/' + cod_veiculo,
            success: function (veiculo) {
                console.log(veiculo);
                $("#cod_veiculo").val(veiculo.cod_veiculo)
                $("#fabricante").val(veiculo.fabricante)
                $("#modelo").val(veiculo.modelo)
                $("#ano_fabricacao").val(veiculo.anoFabricacao)
                $("#ano_modelo").val(veiculo.anoModelo)
                $("#tipo_combustível").val(veiculo.tipoCombustivel)
                $("#valor").val(veiculo.valor)
                $("#placa").val(veiculo.placa)
                $("#cor").val(veiculo.cor)
                // $("#data").val(veiculo.data)
            }
        });
    }
}
function update() {

    // console.log($("#cod_veiculo").val());
    if ($("#cod_veiculo").val() == '') {
        let carro = {
            fabricante: $("#fabricante").val(),
            modelo: $("#modelo").val(),
            anoFabricacao: $("#ano_fabricacao").val(),
            anoModelo: $("#ano_modelo").val(),
            tipoCombustivel: $("#tipo_combustível").val(),
            valor: $("#valor").val(),
            placa: $("#placa").val(),
            cor: $("#cor").val()
        }

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: 'http://localhost:8081/concessionaria-api/rest/concessionaria-api/',
            data: JSON.stringify(carro),
            success: function (data) {
                window.location.reload(true);
                //  var cod_veiculo = $("#" + cod_veiculo).update();
                console.log(data);
                // function funcAltera();
                alert('Veículo cadastrado com sucesso!');
                $('#exampleModal').modal('hide');
            }
        });
    } else {

        let carro = {
            cod_veiculo: $("#cod_veiculo").val(),
            fabricante: $("#fabricante").val(),
            modelo: $("#modelo").val(),
            anoFabricacao: $("#ano_fabricacao").val(),
            anoModelo: $("#ano_modelo").val(),
            tipoCombustivel: $("#tipo_combustível").val(),
            valor: $("#valor").val(),
            placa: $("#placa").val(),
            cor: $("#cor").val()
        };

        $.ajax({
            type: 'PUT',
            contentType: 'application/json',
            url: 'http://localhost:8081/concessionaria-api/rest/concessionaria-api/',
            data: JSON.stringify(carro),
            success: function (data) {
                // console.log(data);
                window.location.reload(true);
                alert('Veículo atualizado com sucesso!');
                $('#exampleModal').modal('hide');
            }
        });
    }
// }
// var tempo = new Number();
// // Tempo em segundos
// tempo = 900;
// function startCountdown(){
// // Se o tempo não for zerado
// if((tempo - 1) >= 0){
// // Pega a parte inteira dos minutos
// var min = parseInt(tempo/60);
// // horas, pega a parte inteira dos minutos
// var hor = parseInt(min/60);
// //atualiza a variável minutos obtendo o tempo restante dos minutos
// min = min % 60;
// // Calcula os segundos restantes
// var seg = tempo%60;
// // Formata o número menor que dez, ex: 08, 07, ...
// if(min < 10){
// min = "0"+min;
// min = min.substr(0, 2);
// }
// if(seg <=9){
// seg = "0"+seg;
// }
// if(hor <=9){
// hor = "0"+hor;
// }
// // Cria a variável para formatar no estilo hora/cronômetro
// horaImprimivel = hor+':' + min + ':' + seg;
// //JQuery pra setar o valor
// $("#sessao").html(horaImprimivel);
// // Define que a função será executada novamente em 1000ms = 1 segundo
// setTimeout('startCountdown()',1000);
// // diminui o tempo
// tempo--;
// }// Quando o contador chegar a zero faz esta ação
// else {
// window.location = 'http://localhost/concessionaria/login.html';
// }
// }
// // Chama a função ao carregar a tela
// startCountdown();