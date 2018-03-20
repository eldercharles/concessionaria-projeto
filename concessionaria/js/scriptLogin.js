$("#mensagemError").hide();


function autenticar() {

    /// recuperar os values dos inputs
    let email = $("#email").val();
    let senha = $("#senha").val();

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: 'http://localhost:8081/concessionaria-api/rest/user/autenticar',
        data: JSON.stringify({ email: email, senha: senha }),
        success: function (usuario) {
            if (usuario) {
                localStorage.setItem("usuario",JSON.stringify(usuario));
                window.location = '/concessionaria';
            }
            else {
                $("#mensagemError").show();
                //  alert('usuário ou senha inválida!');
            }
        }
    });
}
function validarUser(e) {

    let nome =  $("#nomeUser").val();
    let email =  $("#emailCadastro").val();
    let senha = $("#password").val();
    let ConfirmSenha = $("#confirm_password").val();

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: 'http://localhost:8081/concessionaria-api/rest/user/',
        data: JSON.stringify({ nome: nome, email: email, senha: senha }),
        success: function (usuario) {
            if (usuario) {
                // alert('Cadastro efetuado com sucesso!')
                window.location = 'http://localhost/concessionaria/login.html';
            }
        }
    });
}

// function validarSenha(){
//     Senha = document.getElementById('password').value;
//     CNovaSenha = document.getElementById('confirm_password').value;
//     if (Senha != CNovaSenha) {
//        alert("SENHAS DIFERENTES!\nFAVOR DIGITAR SENHAS IGUAIS"); 
//     }else{
//        document.FormSenha.submit();
//     }
//  }